package de.fabianrump.composesuperhero.ui.detail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun initialize(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedSuperHero = superHeroInteractor.loadSuperHeroById(id)
                withContext(Dispatchers.Main) {
                    superHero.receiveUpdatesOf(fetchedSuperHero)
                }
            }
        }
    }
}
