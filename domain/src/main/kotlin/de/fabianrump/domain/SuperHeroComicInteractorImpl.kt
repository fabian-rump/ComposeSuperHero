package de.fabianrump.domain

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.SuperHeroComic
import de.fabianrump.database.repository.SuperHeroComicRepository

class SuperHeroComicInteractorImpl(
    private val superHeroComicRepository: SuperHeroComicRepository
) : SuperHeroComicInteractor {

    override suspend fun loadComicById(id: Int): LiveData<SuperHeroComic> = superHeroComicRepository.loadComicById(id)
}