package de.fabianrump.composesuperhero.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.fabianrump.navigation.Navigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(navController: NavHostController, navigator: Navigator) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach { navController.navigate(it.label) }.launchIn(this)
    }

    NavHost(navController = navController, startDestination = "start") {
        addMainGraph { navController.popBackStack() }
    }
}