package de.fabianrump.composesuperhero.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fabianrump.composesuperhero.ui.theme.Purple500

@Composable
fun DetailScreen(viewModel: DetailViewModel, popBackStack: () -> Unit) {
    val hero = viewModel.superHero.observeAsState()
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(color = Purple500)
                    .fillMaxWidth()
            ) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            popBackStack()
                        }
                )
                Text("Super Heroes", color = Color.White, fontSize = 18.sp)
            }
        }
    ) {
        Text("Detail from ${hero.value?.superHero?.name}")
    }
}