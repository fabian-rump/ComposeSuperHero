package de.fabianrump.composesuperhero.ui.di

import de.fabianrump.composesuperhero.ui.detail.DetailViewModel
import de.fabianrump.composesuperhero.ui.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { OverviewViewModel(get(), get()) }
    viewModel { DetailViewModel(get()) }
}