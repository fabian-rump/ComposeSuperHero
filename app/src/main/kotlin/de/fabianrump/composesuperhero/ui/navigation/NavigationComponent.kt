package de.fabianrump.composesuperhero.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import de.fabianrump.composesuperhero.ui.main.MainScreen
import de.fabianrump.composesuperhero.ui.main.MainViewModel
import de.fabianrump.navigation.Navigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.viewModel

@Composable
fun NavigationComponent(navController: NavHostController, navigator: Navigator) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach { navController.navigate(it) }.launchIn(this)
    }

    val viewModel: MainViewModel by viewModel()
    MainScreen(navController, viewModel)
}