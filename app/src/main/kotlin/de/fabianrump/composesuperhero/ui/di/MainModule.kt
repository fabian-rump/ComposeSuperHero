package de.fabianrump.composesuperhero.ui.di

import de.fabianrump.composesuperhero.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel() }
}