package de.fabianrump.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _sharedFlow = MutableSharedFlow<NavTarget>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: NavTarget) {
        _sharedFlow.tryEmit(navTarget)
    }

    sealed class NavTarget(val label: String) {
        object Home : NavTarget("home")
        data class HeroDetail(val id: String) : NavTarget("hero_detail/$id") {
            companion object {
                const val label = "hero_detail"
            }
        }

        data class ComicDetail(val id: Int) : NavTarget("comic_detail/$id") {
            companion object {
                const val label = "comic_detail"
            }
        }
    }
}