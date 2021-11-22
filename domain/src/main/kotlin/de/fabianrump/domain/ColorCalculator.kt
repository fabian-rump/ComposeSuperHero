package de.fabianrump.domain

import android.content.Context

interface ColorCalculator {

    suspend fun calculateDominantColor(context: Context, url: String): Int
}