package de.fabianrump.domain

import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult

class ColorCalculatorImpl : ColorCalculator {

    override suspend fun calculateDominantColor(context: Context, url: String): Int {

        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false)
            .build()

        val drawable = (Coil.imageLoader(context).execute(request) as SuccessResult).drawable
        val palette = Palette.Builder(drawable.toBitmap()).generate()
        return palette.dominantSwatch?.rgb ?: 0xFFFFFF
    }
}