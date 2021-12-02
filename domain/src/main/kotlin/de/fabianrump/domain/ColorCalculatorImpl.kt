package de.fabianrump.domain

import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import timber.log.Timber

class ColorCalculatorImpl : ColorCalculator {

    override suspend fun calculateDominantColor(context: Context, url: String): Int {
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false)
            .build()

        val coil = (Coil.imageLoader(context).execute(request))
        return if (coil is SuccessResult) {
            val drawable = (Coil.imageLoader(context).execute(request) as SuccessResult).drawable
            val palette = Palette.Builder(drawable.toBitmap()).generate()
            palette.dominantSwatch?.rgb ?: 0xFFFFFF
        } else {
            Timber.d("Coil Request was unsuccessful: $coil")
            0xFFFFFF
        }
    }
}