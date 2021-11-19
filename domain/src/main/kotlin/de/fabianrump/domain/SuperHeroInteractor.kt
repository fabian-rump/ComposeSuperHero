package de.fabianrump.domain

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.SuperHero

interface SuperHeroInteractor {
    suspend fun loadSuperHeroes(): LiveData<List<SuperHero>>
}