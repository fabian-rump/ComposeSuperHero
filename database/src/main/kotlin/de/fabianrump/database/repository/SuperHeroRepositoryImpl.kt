package de.fabianrump.database.repository

import androidx.lifecycle.LiveData
import de.fabianrump.database.dao.MarvelAttributionDao
import de.fabianrump.database.dao.SuperHeroDao
import de.fabianrump.database.model.MarvelAttribution
import de.fabianrump.database.model.SuperHero
import de.fabianrump.database.model.SuperHeroComic
import de.fabianrump.database.model.SuperHeroWithComics
import de.fabianrump.network.model.CharacterDataWrapper.CharacterDataContainer.Character
import de.fabianrump.network.service.MarvelService
import kotlinx.coroutines.delay
import timber.log.Timber

class SuperHeroRepositoryImpl(
    private val marvelAttributionDao: MarvelAttributionDao,
    private val superHeroDao: SuperHeroDao,
    private val marvelService: MarvelService,
    private val superHeroComicRepository: SuperHeroComicRepository
) : SuperHeroRepository {

    override fun loadMarvelAttributions() =
        marvelAttributionDao.getMarvelAttributions()

    override suspend fun loadSuperHero(id: String): LiveData<SuperHeroWithComics> {
        superHeroComicRepository.loadComicsByCharacterId(id)
        return superHeroDao.getSuperHeroWithComic(id)
    }

    override suspend fun loadSuperHeroes(): LiveData<List<SuperHero>> {
        refreshSuperHeroes()
        return superHeroDao.getAllSuperHeroes()
    }

    private suspend fun refreshSuperHeroes() {
        if (superHeroDao.getSuperHeroesCount() > 0) return

        var offset = 0
        while (true) {
            Timber.d("Downloading superheroes from offset $offset to ${offset + 100}...")
            val (total, count) = downloadAndStoreSuperHeroes(offset)
            offset += count
            delay(100)
            if (offset >= total) break
        }
    }

    private suspend fun downloadAndStoreSuperHeroes(offset: Int): Pair<Int, Int> {
        val response = marvelService.fetchCharacters(offset)

        if (response.isSuccessful)
            response.body()?.let {
                Timber.d("Downloaded ${it.data.count} of ${it.data.total} superheroes.")

                val superHeroesWithComics =
                    it.data.results
                        .filter(::characterWithAvailableThumbnail)
                        .filter(::characterWithDescription)
                        .map(::toSuperHeroWithComics)
                        .toTypedArray()

                Timber.d("Filtered ${it.data.count - superHeroesWithComics.size} superheroes due to no available thumbnail or description.")

                marvelAttributionDao.updateAttribution(MarvelAttribution(it.attributionText))
                superHeroDao.insertSuperHeroesWithComics(*superHeroesWithComics)

                return Pair(it.data.total, it.data.count)
            } ?: Timber.e("Response does not contain any data.")
        else Timber.e(response.errorBody()?.string())

        return Pair(0, 0)
    }

    private fun characterWithAvailableThumbnail(character: Character) =
        !character.thumbnail.path.contains("image_not_available")

    private fun characterWithDescription(character: Character) =
        character.description.isNotEmpty()

    private fun toSuperHeroWithComics(character: Character) =
        SuperHeroWithComics(character.toSuperHero(), character.toSuperHeroComics())

    private fun Character.toSuperHero(): SuperHero =
        SuperHero(
            id,
            name,
            description,
            thumbnail.let { thumbnail -> "${thumbnail.path}/portrait_xlarge.${thumbnail.extension}" },
            thumbnail.let { thumbnail -> "${thumbnail.path}/landscape_incredible.${thumbnail.extension}" }
        )

    private fun Character.toSuperHeroComics(): List<SuperHeroComic> =
        comics.items.map {
            SuperHeroComic(it.name, it.resourceURI, id)
        }
}
