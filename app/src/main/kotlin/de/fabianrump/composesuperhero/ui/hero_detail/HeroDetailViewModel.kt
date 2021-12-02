package de.fabianrump.composesuperhero.ui.hero_detail

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.composesuperhero.ui.navigation.navigateToComicDetails
import de.fabianrump.database.model.SuperHeroWithComics
import de.fabianrump.database.repository.ConfigurationRepository
import de.fabianrump.domain.ColorCalculator
import de.fabianrump.domain.SuperHeroInteractor
import de.fabianrump.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroDetailViewModel(
    private val navigator: Navigator,
    private val superHeroInteractor: SuperHeroInteractor,
    private val colorCalculator: ColorCalculator,
    private val configurationRepository: ConfigurationRepository
) : ViewModel() {

    val superHero = MediatorLiveData<SuperHeroWithComics>()

    fun initialize(id: String, context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedSuperHero = superHeroInteractor.loadSuperHeroById(id)
                withContext(Dispatchers.Main) {
                    superHero.addSource(fetchedSuperHero) {
                        superHero.value = it
                        viewModelScope.launch {
                            val dominantColor =
                                colorCalculator.calculateDominantColor(context, fetchedSuperHero.value?.superHero?.thumbnailLandscape ?: "")
                            configurationRepository.updateSystemColor(dominantColor)
                        }
                    }
                }
            }
        }
    }

    fun navigateToComic(id: Int) = navigateToComicDetails(navigator, id)
}
