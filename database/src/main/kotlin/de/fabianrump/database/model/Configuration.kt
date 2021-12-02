package de.fabianrump.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Configuration(
    @PrimaryKey val id: Int = 1,
    val systemColor: Int
)