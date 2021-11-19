package de.fabianrump.composesuperhero.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.fabianrump.database.model.SuperHeroWithComics

@Composable
fun DetailScreen(viewModel: DetailViewModel, popBackStack: () -> Unit) {
    val hero = viewModel.superHero.observeAsState()
    Scaffold(
        topBar = { DetailTopBar(viewModel, popBackStack) }
    ) {
        val lazyListState = rememberLazyListState()
        LazyColumn(Modifier.fillMaxSize(), lazyListState) {
            item { DetailThumbnailHeader(hero, lazyListState) }
            item { Description(hero) }
            items(hero.value?.comics ?: listOf()) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        ComicItem(it.name, isHeader = true)
                        ComicItem(it.format)
                        ComicItem(it.isbn)
                        LastComicItem(it.pageCount)
                    }
                }
            }
        }
    }
}

@Composable
private fun LastComicItem(content: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            // TODO
        }) {
        Text(
            text = content.checkUnknown(),
            fontSize = 12.sp,
            fontStyle = if (content.isEmpty()) FontStyle.Italic else null
        )
        Icon(
            tint = Color.Black,
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "Open Comic",
        )
    }
}

@Composable
private fun ComicItem(content: String, isHeader: Boolean = false) {
    Text(
        text = content.checkUnknown(),
        Modifier.padding(4.dp),
        fontSize = 12.sp,
        fontWeight = if (isHeader) FontWeight.Bold else null,
        fontStyle = if (content.isEmpty()) FontStyle.Italic else null
    )
}

@Composable
private fun Description(hero: State<SuperHeroWithComics?>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = hero.value?.superHero?.description ?: "",
            Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun DetailThumbnailHeader(
    hero: State<SuperHeroWithComics?>,
    lazyListState: LazyListState
) {
    var scrolledY = 0f
    var previousOffset = 0
    Image(
        painter = rememberImagePainter(
            data = hero.value?.superHero?.thumbnailLandscape,
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .graphicsLayer {
                scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                translationY = scrolledY * 0.5f
                previousOffset = lazyListState.firstVisibleItemScrollOffset
            }
            .height(224.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun DetailTopBar(viewModel: DetailViewModel, popBackStack: () -> Unit) {
    val hero = viewModel.superHero.observeAsState()
    val color = viewModel.thumbnailColor.observeAsState()

    rememberSystemUiController().setSystemBarsColor(Color(color.value ?: 0x0000FF))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Color(color.value ?: 0x0000FF))
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
        Text(hero.value?.superHero?.name ?: "", color = Color.White, fontSize = 18.sp)
    }
}

private fun String.checkUnknown() = if (isEmpty()) "unbekannt" else this
