package de.fabianrump.domain.di

import de.fabianrump.domain.SuperHeroInteractor
import de.fabianrump.domain.SuperHeroInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    factory<SuperHeroInteractor> { SuperHeroInteractorImpl(get()) }
}