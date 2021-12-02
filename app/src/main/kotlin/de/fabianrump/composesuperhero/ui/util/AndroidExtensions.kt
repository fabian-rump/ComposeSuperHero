package de.fabianrump.composesuperhero.ui.util

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> MediatorLiveData<T>.receiveUpdatesOf(liveData: LiveData<T>, customBlock: () -> Unit = {}) {
    addSource(liveData) { newValue -> value = newValue }
    customBlock()
}

@Composable
fun getDefaultSystemColor() = MaterialTheme.colors.primary.toArgb()
