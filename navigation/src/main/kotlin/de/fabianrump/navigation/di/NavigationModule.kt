package de.fabianrump.navigation.di

import de.fabianrump.navigation.Navigator
import org.koin.dsl.module

val navigationModule = module {
    single { Navigator() }
}