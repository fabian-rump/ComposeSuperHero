package de.fabianrump.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarvelAttribution(
    @PrimaryKey val attribution: String
)