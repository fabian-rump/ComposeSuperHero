package de.fabianrump.composesuperhero.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.fabianrump.composesuperhero.ui.HomeScreen
import de.fabianrump.composesuperhero.ui.MainViewModel
import de.fabianrump.navigation.Navigator
import org.koin.androidx.compose.viewModel

fun NavGraphBuilder.addMainGraph() {
    navigation(Navigator.NavTarget.Home.label, "start") {
        composable(Navigator.NavTarget.Home.label) {
            val viewModel: MainViewModel by viewModel()
            HomeScreen(viewModel)
        }
    }
}