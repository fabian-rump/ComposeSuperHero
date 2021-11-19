package de.fabianrump.composesuperhero.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import de.fabianrump.composesuperhero.ui.detail.DetailScreen
import de.fabianrump.composesuperhero.ui.detail.DetailViewModel
import de.fabianrump.composesuperhero.ui.overview.OverviewScreen
import de.fabianrump.composesuperhero.ui.overview.OverviewViewModel
import de.fabianrump.navigation.Navigator
import org.koin.androidx.compose.viewModel

fun NavGraphBuilder.addMainGraph(popBackStack: () -> Unit) {
    navigation(Navigator.NavTarget.Home.label, "start") {
        composable(Navigator.NavTarget.Home.label) {
            val viewModel: OverviewViewModel by viewModel()
            OverviewScreen(viewModel)
        }

        composable("${Navigator.NavTarget.Detail.label}/{heroId}", arguments = listOf(navArgument("heroId") { type = NavType.StringType })) {
            val heroId = it.arguments?.getString("heroId") ?: "-1"
            val viewModel: DetailViewModel by viewModel()
            viewModel.initialize(heroId)
            DetailScreen(viewModel, popBackStack)
        }
    }
}