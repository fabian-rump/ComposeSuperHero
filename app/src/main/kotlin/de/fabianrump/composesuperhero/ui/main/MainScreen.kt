package de.fabianrump.composesuperhero.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.fabianrump.composesuperhero.R
import de.fabianrump.composesuperhero.ui.navigation.addMainGraph

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val color = viewModel.color.observeAsState().value

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = color?.let { Color(it) } ?: Color(MaterialTheme.colors.primary.toArgb())
        ) {
            BottomNavigationItem(selected = true, onClick = {
                viewModel.navigateToHeroes(navController)
            }, icon = { Icon(painterResource(id = R.drawable.hero), "") })

            BottomNavigationItem(selected = true, onClick = {
                viewModel.navigateToSeries(navController)
            }, icon = { Icon(painterResource(id = R.drawable.series), "") })

            BottomNavigationItem(selected = true, onClick = {
                viewModel.navigateToEvents(navController)
            }, icon = { Icon(painterResource(id = R.drawable.events), "") })
        }
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = "mainRoute") {
                addMainGraph(viewModel) { navController.popBackStack() }
            }
        }
    }
}