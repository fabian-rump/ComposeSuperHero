package de.fabianrump.composesuperhero.ui.main

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.fabianrump.composesuperhero.ui.navigation.navigateToEvents
import de.fabianrump.composesuperhero.ui.navigation.navigateToHeroes
import de.fabianrump.composesuperhero.ui.navigation.navigateToSeries
import de.fabianrump.composesuperhero.ui.theme.Purple200
import de.fabianrump.composesuperhero.ui.theme.Purple500
import de.fabianrump.composesuperhero.ui.theme.Purple700
import de.fabianrump.database.model.Configuration
import de.fabianrump.database.repository.ConfigurationRepository
import de.fabianrump.navigation.Navigator
import de.fabianrump.navigation.Navigator.NavTarget
import de.fabianrump.navigation.Navigator.NavTarget.Events
import de.fabianrump.navigation.Navigator.NavTarget.Heroes
import de.fabianrump.navigation.Navigator.NavTarget.Series
import kotlinx.coroutines.launch

class MainViewModel(
    private val navigator: Navigator,
    private val configurationRepository: ConfigurationRepository
) : ViewModel() {

    private var currentVisibleScreen: NavTarget = Heroes
    val color = MediatorLiveData<Int>()

    init {
        viewModelScope.launch { configurationRepository.insertConfiguration(Configuration(systemColor = Purple700.toArgb())) }
        viewModelScope.launch { color.addSource(configurationRepository.getLiveConfiguration()) { color.value = it?.systemColor } }
    }

    fun navigateToHeroes(navController: NavController) = navigateToHeroes(navigator).also { Heroes.handleBackStack(navController) }

    fun navigateToSeries(navController: NavController) = navigateToSeries(navigator).also { Series.handleBackStack(navController) }

    fun navigateToEvents(navController: NavController) = navigateToEvents(navigator).also { Events.handleBackStack(navController) }

    private fun NavTarget.handleBackStack(navController: NavController) {
        if (currentVisibleScreen == this) navController.popBackStack(determineRoute(), false)
        currentVisibleScreen = this
    }

    private fun NavTarget.determineRoute() = when (this) {
        Events -> "eventsRoute"
        Heroes -> "heroesRoute"
        Series -> "seriesRoute"
        else -> "" // else is not needed
    }
}
