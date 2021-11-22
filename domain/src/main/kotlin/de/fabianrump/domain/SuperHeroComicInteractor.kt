package de.fabianrump.domain

import androidx.lifecycle.LiveData
import de.fabianrump.database.model.SuperHeroComic

interface SuperHeroComicInteractor {

    suspend fun loadComicById(id: Int): LiveData<SuperHeroComic>
}