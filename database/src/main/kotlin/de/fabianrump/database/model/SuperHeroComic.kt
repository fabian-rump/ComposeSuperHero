package de.fabianrump.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SuperHeroComic(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val resourceUri: String,
    val superHeroId: String,
    val isbn: String = "",
    val format: String = "",
    val pageCount: String = "",
    val thumbnail: String = ""
)