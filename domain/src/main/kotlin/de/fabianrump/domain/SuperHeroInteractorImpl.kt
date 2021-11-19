package de.fabianrump.domain

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.SuperHero
import de.fabianrump.database.model.SuperHeroWithComics
import de.fabianrump.database.repository.SuperHeroRepository

class SuperHeroInteractorImpl(
    private val superHeroRepository: SuperHeroRepository
) : SuperHeroInteractor {

    override suspend fun loadSuperHeroes(): LiveData<List<SuperHero>> = superHeroRepository.loadSuperHeroes()

    override suspend fun loadSuperHeroById(id: String): LiveData<SuperHeroWithComics> = superHeroRepository.loadSuperHero(id)
}