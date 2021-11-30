package de.fabianrump.composesuperhero.ui.navigation

import de.fabianrump.navigation.Navigator

fun navigateToHeroDetails(navigator: Navigator, id: String) = navigator.navigateTo(Navigator.NavTarget.HeroDetail, id)
fun navigateToComicDetails(navigator: Navigator, id: Int) = navigator.navigateTo(Navigator.NavTarget.ComicDetail, id)
fun navigateToHeroes(navigator: Navigator) = navigator.navigateTo(Navigator.NavTarget.Heroes)
fun navigateToSeries(navigator: Navigator) = navigator.navigateTo(Navigator.NavTarget.Series)
fun navigateToEvents(navigator: Navigator) = navigator.navigateTo(Navigator.NavTarget.Events)
