package de.fabianrump.database.repository

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.MarvelAttribution
import de.fabianrump.database.model.SuperHero
import de.fabianrump.database.model.SuperHeroWithComics

interface SuperHeroRepository {
    fun loadMarvelAttributions(): LiveData<List<MarvelAttribution>>
    suspend fun loadSuperHero(id: String): LiveData<SuperHeroWithComics>
    suspend fun loadSuperHeroes(): LiveData<List<SuperHero>>
}