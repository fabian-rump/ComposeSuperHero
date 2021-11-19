package de.fabianrump.database.repository

import android.util.Log
import de.fabianrump.database.dao.MarvelAttributionDao
import de.fabianrump.database.dao.SuperHeroComicDao
import de.fabianrump.database.model.MarvelAttribution
import de.fabianrump.database.model.SuperHeroComic
import de.fabianrump.network.service.MarvelService

class SuperHeroComicRepositoryImpl(
    private val marvelAttributionDao: MarvelAttributionDao,
    private val superHeroComicDao: SuperHeroComicDao,
    private val marvelService: MarvelService
) : SuperHeroComicRepository {

    override suspend fun loadComicsByCharacterId(id: String) {
        downloadAndStoreSuperHeroComics(id)
    }

    private suspend fun downloadAndStoreSuperHeroComics(
        characterId: String
    ): Pair<Int, Int> {
        val response = marvelService.fetchComics(characterId = characterId)

        if (response.isSuccessful)
            response.body()?.let {
                val superHeroesWithComics =
                    it.data.results.map { comic ->
                        SuperHeroComic(
                            comic.title,
                            comic.resourceURI,
                            characterId,
                            comic.isbn,
                            comic.format,
                            comic.pageCount.toString()
                        )
                    }.toTypedArray()

                marvelAttributionDao.updateAttribution(MarvelAttribution(it.attributionText))
                superHeroComicDao.insertComics(*superHeroesWithComics)

                return Pair(it.data.total, it.data.count)
            }
        else Log.e("FAIL", response.errorBody()?.string() ?: "nothing")

        return Pair(0, 0)
    }
}