package de.fabianrump.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SuperHero(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val thumbnailPortrait: String,
    val thumbnailLandscape: String
)