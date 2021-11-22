package de.fabianrump.composesuperhero.ui.comic_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.fabianrump.database.model.SuperHeroComic

@Composable
fun ComicDetailScreen(viewModel: ComicDetailViewModel, popBackStack: () -> Unit) {
    val comic = viewModel.comic.observeAsState()
    val color = viewModel.color.observeAsState()

    rememberSystemUiController().setSystemBarsColor(Color(color.value ?: 0xFFFFFF))
    Scaffold(
        topBar = { ComicDetailTopBar(name = viewModel.comic.value?.name ?: "", color.value ?: 0xFFFFFF, popBackStack) }
    ) {
        val lazyListState = rememberLazyListState()


        LazyColumn(Modifier.fillMaxSize(), lazyListState) {
            item { ComicDetailThumbnailHeader(comic, lazyListState) }
        }
    }
}

@Composable
private fun ComicDetailThumbnailHeader(
    hero: State<SuperHeroComic?>,
    lazyListState: LazyListState
) {
    var scrolledY = 0f
    var previousOffset = 0
    Image(
        painter = rememberImagePainter(
            data = hero.value?.thumbnail,
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
fun ComicDetailTopBar(name: String, color: Int, popBackStack: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Color(color))
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
        Text(name, color = Color.White, fontSize = 18.sp)
    }
}
