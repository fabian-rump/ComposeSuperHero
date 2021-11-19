package de.fabianrump.composesuperhero.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.database.model.SuperHero
import de.fabianrump.domain.SuperHeroInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val superHeroInteractor: SuperHeroInteractor
) : ViewModel() {

    val superHeroes = MediatorLiveData<List<SuperHero>>()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                superHeroes.receiveUpdatesOf(superHeroInteractor.loadSuperHeroes())
            }
        }
    }

    private fun <T> MediatorLiveData<T>.receiveUpdatesOf(liveData: LiveData<T>) {
        addSource(liveData) { newValue -> value = newValue }
    }
}
