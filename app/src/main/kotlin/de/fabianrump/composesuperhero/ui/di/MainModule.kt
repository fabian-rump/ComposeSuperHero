package de.fabianrump.composesuperhero.ui.di

import de.fabianrump.composesuperhero.ui.comic_detail.ComicDetailViewModel
import de.fabianrump.composesuperhero.ui.hero_detail.HeroDetailViewModel
import de.fabianrump.composesuperhero.ui.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { OverviewViewModel(get(), get()) }
    viewModel { HeroDetailViewModel(get(), get(), get()) }
    viewModel { ComicDetailViewModel(get(), get()) }
}