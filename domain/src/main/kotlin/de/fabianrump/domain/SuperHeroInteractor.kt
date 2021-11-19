package de.fabianrump.domain

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.SuperHero
import de.fabianrump.database.model.SuperHeroWithComics

interface SuperHeroInteractor {

    suspend fun loadSuperHeroes(): LiveData<List<SuperHero>>

    suspend fun loadSuperHeroById(id: String): LiveData<SuperHeroWithComics>
}