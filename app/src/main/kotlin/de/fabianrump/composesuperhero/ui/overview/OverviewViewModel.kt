package de.fabianrump.composesuperhero.ui.overview

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fabianrump.composesuperhero.ui.util.receiveUpdatesOf
import de.fabianrump.database.model.SuperHero
import de.fabianrump.domain.SuperHeroInteractor
import de.fabianrump.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OverviewViewModel(
    private val navigator: Navigator,
    private val superHeroInteractor: SuperHeroInteractor
) : ViewModel() {

    val superHeroes = MediatorLiveData<List<SuperHero>>()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedSuperHeroes = superHeroInteractor.loadSuperHeroes()
                withContext(Dispatchers.Main) {
                    superHeroes.receiveUpdatesOf(fetchedSuperHeroes)
                }
            }
        }
    }

    fun navigateToDetail(id: String) {
        navigator.navigateTo(Navigator.NavTarget.Detail(id))
    }
}
