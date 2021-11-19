package de.fabianrump.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class SuperHeroWithComics(
    @Embedded val superHero: SuperHero,
    @Relation(parentColumn = "id", entityColumn = "superHeroId") val comics: List<SuperHeroComic>
)