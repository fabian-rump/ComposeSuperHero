package de.fabianrump.composesuperhero.ui.comic_detail

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.composesuperhero.ui.util.receiveUpdatesOf
import de.fabianrump.database.model.SuperHeroComic
import de.fabianrump.database.repository.ConfigurationRepository
import de.fabianrump.domain.ColorCalculator
import de.fabianrump.domain.SuperHeroComicInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicDetailViewModel(
    private val superHeroComicInteractor: SuperHeroComicInteractor,
    private val colorCalculator: ColorCalculator,
    private val configurationRepository: ConfigurationRepository
) : ViewModel() {

    val comic = MediatorLiveData<SuperHeroComic>()

    fun initialize(comicId: Int, context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedComic = superHeroComicInteractor.loadComicById(comicId)
                withContext(Dispatchers.Main) {
                    comic.addSource(fetchedComic) {
                        comic.value = it
                        viewModelScope.launch {
                            withContext(Dispatchers.IO) {
                                val dominantColor = colorCalculator.calculateDominantColor(context, fetchedComic.value?.thumbnail ?: "")
                                configurationRepository.updateSystemColor(dominantColor)
                            }
                        }
                    }
                }
            }
        }
    }
}