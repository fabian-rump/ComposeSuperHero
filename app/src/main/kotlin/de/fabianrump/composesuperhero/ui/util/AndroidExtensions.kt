package de.fabianrump.composesuperhero.ui.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> MediatorLiveData<T>.receiveUpdatesOf(liveData: LiveData<T>) {
    addSource(liveData) { newValue -> value = newValue }
}