package de.fabianrump.database.di

import android.content.Context
import androidx.room.Room
import de.fabianrump.database.SuperHeroesDatabase
import de.fabianrump.database.repository.ConfigurationRepository
import de.fabianrump.database.repository.ConfigurationRepositoryImpl
import de.fabianrump.database.repository.SuperHeroComicRepository
import de.fabianrump.database.repository.SuperHeroComicRepositoryImpl
import de.fabianrump.database.repository.SuperHeroRepository
import de.fabianrump.database.repository.SuperHeroRepositoryImpl
import org.koin.dsl.module

val databaseModule = module {
    single { provideSuperHeroesDatabase(get()) }
    single { provideSuperHeroDao(get()) }
    single { provideMarvelAttributionDao(get()) }
    single { provideSuperHeroComicDao(get()) }
    single { provideConfigurationDao(get()) }
    factory<SuperHeroRepository> { SuperHeroRepositoryImpl(get(), get(), get(), get()) }
    factory<SuperHeroComicRepository> { SuperHeroComicRepositoryImpl(get(), get(), get()) }
    factory<ConfigurationRepository> { ConfigurationRepositoryImpl(get()) }
}

private fun provideSuperHeroesDatabase(applicationContext: Context) =
    Room.databaseBuilder(
        applicationContext,
        SuperHeroesDatabase::class.java, "super_heroes_db"
    ).fallbackToDestructiveMigration().build()

private fun provideSuperHeroDao(superHeroesDatabase: SuperHeroesDatabase) =
    superHeroesDatabase.superHeroDao()

private fun provideMarvelAttributionDao(superHeroesDatabase: SuperHeroesDatabase) =
    superHeroesDatabase.marvelAttributionDao()

private fun provideSuperHeroComicDao(superHeroesDatabase: SuperHeroesDatabase) =
    superHeroesDatabase.superHeroComicDao()

private fun provideConfigurationDao(superHeroesDatabase: SuperHeroesDatabase) =
    superHeroesDatabase.configurationDao()