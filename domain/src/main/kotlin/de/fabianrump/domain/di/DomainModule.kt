package de.fabianrump.domain.di

import de.fabianrump.domain.ColorCalculator
import de.fabianrump.domain.ColorCalculatorImpl
import de.fabianrump.domain.SuperHeroComicInteractor
import de.fabianrump.domain.SuperHeroComicInteractorImpl
import de.fabianrump.domain.SuperHeroInteractor
import de.fabianrump.domain.SuperHeroInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    factory<SuperHeroInteractor> { SuperHeroInteractorImpl(get()) }
    factory<SuperHeroComicInteractor> { SuperHeroComicInteractorImpl(get()) }
    factory<ColorCalculator> { ColorCalculatorImpl() }
}