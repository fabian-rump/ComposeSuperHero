package de.fabianrump.composesuperhero.ui.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.fabianrump.composesuperhero.R
import de.fabianrump.composesuperhero.ui.navigation.addMainGraph

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(bottomBar = {
        BottomNavigation {
            BottomNavigationItem(selected = true, onClick = {
                viewModel.navigateToHeroes()
            }, icon = { Icon(painterResource(id = R.drawable.hero), "") })

            BottomNavigationItem(selected = true, onClick = {
                viewModel.navigateToSeries()
            }, icon = { Icon(painterResource(id = R.drawable.series), "") })

            BottomNavigationItem(selected = true, onClick = {
                viewModel.navigateToEvents()
            }, icon = { Icon(painterResource(id = R.drawable.events), "") })
        }
    }) {
        NavHost(navController = navController, startDestination = "mainRoute") {
            addMainGraph { navController.popBackStack() }
        }
    }
}