package de.fabianrump.composesuperhero.ui.detail

import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import de.fabianrump.composesuperhero.ui.util.receiveUpdatesOf
import de.fabianrump.database.model.SuperHeroWithComics
import de.fabianrump.domain.SuperHeroInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val superHeroInteractor: SuperHeroInteractor
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
                        viewModelScope.launch { getTopBarColor(context) }
                    }
                }
            }
        }
    }

    private suspend fun getTopBarColor(context: Context) {
        val request = ImageRequest.Builder(context)
            .data(superHero.value?.superHero?.thumbnailLandscape)
            .allowHardware(false)
            .build()

        val drawable = (Coil.imageLoader(context).execute(request) as SuccessResult).drawable
        val palette = Palette.Builder(drawable.toBitmap()).generate()
        withContext(Dispatchers.Main) { thumbnailColor.value = palette.dominantSwatch?.rgb }
    }
}
