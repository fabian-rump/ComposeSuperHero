package de.fabianrump.composesuperhero.ui.hero_detail

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.composesuperhero.ui.navigation.navigateToComicDetails
import de.fabianrump.composesuperhero.ui.util.receiveUpdatesOf
import de.fabianrump.database.model.SuperHeroWithComics
import de.fabianrump.domain.ColorCalculator
import de.fabianrump.domain.SuperHeroInteractor
import de.fabianrump.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroDetailViewModel(
    private val navigator: Navigator,
    private val superHeroInteractor: SuperHeroInteractor,
    private val colorCalculator: ColorCalculator
) : ViewModel() {

    val superHero = MediatorLiveData<SuperHeroWithComics>()
    val thumbnailColor = MediatorLiveData<Int>()

    fun initialize(id: String, context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedSuperHero = superHeroInteractor.loadSuperHeroById(id)
                withContext(Dispatchers.Main) {
                    superHero.receiveUpdatesOf(fetchedSuperHero)

                    thumbnailColor.addSource(fetchedSuperHero) {
                        viewModelScope.launch {
                            val dominantColor =
                                colorCalculator.calculateDominantColor(context, fetchedSuperHero.value?.superHero?.thumbnailLandscape ?: "")
                            thumbnailColor.value = dominantColor
                        }
                    }
                }
            }
        }
    }

    fun navigateToComic(id: Int) = navigateToComicDetails(navigator, id)
}
