package de.fabianrump.composesuperhero.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import de.fabianrump.composesuperhero.ui.comic_detail.ComicDetailScreen
import de.fabianrump.composesuperhero.ui.comic_detail.ComicDetailViewModel
import de.fabianrump.composesuperhero.ui.events.EventsScreen
import de.fabianrump.composesuperhero.ui.hero_detail.HeroDetailScreen
import de.fabianrump.composesuperhero.ui.hero_detail.HeroDetailViewModel
import de.fabianrump.composesuperhero.ui.overview.OverviewScreen
import de.fabianrump.composesuperhero.ui.overview.OverviewViewModel
import de.fabianrump.composesuperhero.ui.series.SeriesScreen
import de.fabianrump.navigation.Navigator
import org.koin.androidx.compose.viewModel
import timber.log.Timber

fun NavGraphBuilder.addHeroesGraph(popBackStack: () -> Unit) {
    navigation(Navigator.NavTarget.Heroes.label, "heroesRoute") {
        composable(Navigator.NavTarget.Heroes.label) {
            val viewModel: OverviewViewModel by viewModel()
            OverviewScreen(viewModel)
        }

        composable("${Navigator.NavTarget.HeroDetail.label}/{heroId}", arguments = listOf(navArgument("heroId") { type = StringType })) {
            val heroId = it.arguments?.getString("heroId") ?: "-1"
            Timber.d("Hero ID: $heroId")
            val viewModel: HeroDetailViewModel by viewModel()
            viewModel.initialize(heroId, LocalContext.current)
            HeroDetailScreen(viewModel, popBackStack)
        }

        composable("${Navigator.NavTarget.ComicDetail.label}/{comicId}", arguments = listOf(navArgument("comicId") { type = IntType })) {
            val comicId = it.arguments?.getInt("comicId") ?: -1
            Timber.d("Comic ID: $comicId")
            val viewModel: ComicDetailViewModel by viewModel()
            viewModel.initialize(comicId, LocalContext.current)
            ComicDetailScreen(viewModel, popBackStack)
        }
    }
}

fun NavGraphBuilder.addSeriesGraph() {
    navigation(Navigator.NavTarget.Series.label, "seriesRoute") {
        composable(Navigator.NavTarget.Series.label) {
            SeriesScreen()
        }
    }
}

fun NavGraphBuilder.addEventsGraph() {
    navigation(Navigator.NavTarget.Events.label, "eventsRoute") {
        composable(Navigator.NavTarget.Events.label) {
            EventsScreen()
        }
    }
}

fun NavGraphBuilder.addMainGraph(popBackStack: () -> Unit) {
    navigation("heroesRoute", "mainRoute") {
        addHeroesGraph(popBackStack)
        addSeriesGraph()
        addEventsGraph()
    }
}
