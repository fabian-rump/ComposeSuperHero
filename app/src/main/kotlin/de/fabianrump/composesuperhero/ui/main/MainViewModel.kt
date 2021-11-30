package de.fabianrump.composesuperhero.ui.main

import androidx.lifecycle.ViewModel
import de.fabianrump.composesuperhero.ui.navigation.navigateToEvents
import de.fabianrump.composesuperhero.ui.navigation.navigateToHeroes
import de.fabianrump.composesuperhero.ui.navigation.navigateToSeries
import de.fabianrump.navigation.Navigator

class MainViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun navigateToHeroes() = navigateToHeroes(navigator)

    fun navigateToSeries() = navigateToSeries(navigator)

    fun navigateToEvents() = navigateToEvents(navigator)
}
