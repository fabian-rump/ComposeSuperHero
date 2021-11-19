package de.fabianrump.database.repository

interface SuperHeroComicRepository {
    suspend fun loadComicsByCharacterId(id: String)
}