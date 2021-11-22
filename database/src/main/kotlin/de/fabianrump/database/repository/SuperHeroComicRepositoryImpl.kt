package de.fabianrump.database.repository

import de.fabianrump.database.dao.MarvelAttributionDao
import de.fabianrump.database.dao.SuperHeroComicDao
import de.fabianrump.database.model.MarvelAttribution
import de.fabianrump.database.model.SuperHeroComic
import de.fabianrump.network.service.MarvelService
import timber.log.Timber

class SuperHeroComicRepositoryImpl(
    private val marvelAttributionDao: MarvelAttributionDao,
    private val superHeroComicDao: SuperHeroComicDao,
    private val marvelService: MarvelService
) : SuperHeroComicRepository {

    override suspend fun loadComicsByCharacterId(id: String) {
        downloadAndStoreSuperHeroComics(id)
    }

    override suspend fun loadComicById(id: Int) = superHeroComicDao.getSuperHeroComicById(id)

    private suspend fun downloadAndStoreSuperHeroComics(
        characterId: String
    ): Pair<Int, Int> {
        val response = marvelService.fetchComics(characterId = characterId)

        if (response.isSuccessful)
            response.body()?.let {
                val superHeroesWithComics =
                    it.data.results.map { comic ->
                        SuperHeroComic(
                            comic.id,
                            comic.title,
                            comic.resourceURI,
                            characterId,
                            comic.isbn,
                            comic.format,
                            comic.pageCount.toString(),
                            comic.thumbnail.let { thumbnail -> "${thumbnail.path}/landscape_incredible.${thumbnail.extension}" },
                        )
                    }.toTypedArray()

                marvelAttributionDao.updateAttribution(MarvelAttribution(it.attributionText))
                superHeroComicDao.insertComics(*superHeroesWithComics)

                return Pair(it.data.total, it.data.count)
            }
        else Timber.d(response.errorBody()?.string())

        return Pair(0, 0)
    }
}