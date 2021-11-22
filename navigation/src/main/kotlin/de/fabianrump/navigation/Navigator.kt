package de.fabianrump.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _sharedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: NavTarget, vararg argument: Any) {
        _sharedFlow.tryEmit(navTarget.label.plus(argument.toNavArguments()))
    }

    private fun <T> Array<T>.toNavArguments(): String = joinToString {
        "/$it"
    }

    sealed class NavTarget(val label: String) {
        object Home : NavTarget("home")
        object HeroDetail : NavTarget("hero_detail")
        object ComicDetail : NavTarget("comic_detail")
    }
}
