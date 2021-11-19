package de.fabianrump.composesuperhero.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import de.fabianrump.database.model.SuperHero

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val heroes = viewModel.superHeroes.observeAsState()

    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        items(items = heroes.value ?: listOf(), itemContent = {
            Box(contentAlignment = Alignment.BottomCenter) {
                SuperHeroThumbnail(it)
                OverlayGradient()
                SuperHeroName(it)
            }
        })
    }
}

@Composable
private fun SuperHeroName(it: SuperHero) {
    Text(it.name, color = Color.White)
}

@Composable
private fun SuperHeroThumbnail(it: SuperHero) {
    Image(
        painter = rememberImagePainter(
            data = it.thumbnailLandscape,
        ),
        contentScale = ContentScale.Crop,
        contentDescription = "Super Hero Thumbnail",
        modifier = Modifier
            .fillMaxWidth()
            .height(224.dp)
    )
}

@Composable
private fun OverlayGradient() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(224.dp)
            .background(
                Brush.verticalGradient(
                    listOf(Color.Transparent, Color.Black),
                    0f,
                    750f,
                )
            )
    )
}
