package de.fabianrump.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["name", "resourceUri"])
data class SuperHeroComic(
    val name: String,
    val resourceUri: String,
    val superHeroId: String,
    val isbn: String = "",
    val format: String = "",
    val pageCount: String = ""
)