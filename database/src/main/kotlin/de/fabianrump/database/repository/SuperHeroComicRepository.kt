package de.fabianrump.database.repository

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.SuperHeroComic

interface SuperHeroComicRepository {

    suspend fun loadComicsByCharacterId(id: String)

    suspend fun loadComicById(id: Int): LiveData<SuperHeroComic>
}
