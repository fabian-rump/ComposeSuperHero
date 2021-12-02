package de.fabianrump.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.fabianrump.database.dao.ConfigurationDao
import de.fabianrump.database.dao.MarvelAttributionDao
import de.fabianrump.database.dao.SuperHeroComicDao
import de.fabianrump.database.dao.SuperHeroDao
import de.fabianrump.database.model.Configuration
import de.fabianrump.database.model.Converters
import de.fabianrump.database.model.MarvelAttribution
import de.fabianrump.database.model.SuperHero
import de.fabianrump.database.model.SuperHeroComic

@Database(entities = [SuperHero::class, SuperHeroComic::class, MarvelAttribution::class, Configuration::class], version = 2)
@TypeConverters(Converters::class)
abstract class SuperHeroesDatabase : RoomDatabase() {
    abstract fun superHeroDao(): SuperHeroDao
    abstract fun superHeroComicDao(): SuperHeroComicDao
    abstract fun marvelAttributionDao(): MarvelAttributionDao
    abstract fun configurationDao(): ConfigurationDao
}