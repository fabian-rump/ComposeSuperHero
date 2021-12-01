package de.fabianrump.composesuperhero.ui.comic_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.fabianrump.composesuperhero.R
import de.fabianrump.composesuperhero.ui.main.MainViewModel
import de.fabianrump.database.model.SuperHeroComic

@Composable
fun ComicDetailScreen(sharedViewModel: MainViewModel, viewModel: ComicDetailViewModel, popBackStack: () -> Unit) {
    val comic = viewModel.comic.observeAsState()
    val color = sharedViewModel.color.observeAsState()

    rememberSystemUiController().setSystemBarsColor(Color(color.value ?: 0xFFFFFF))
    Scaffold(
        topBar = { ComicDetailTopBar(name = viewModel.comic.value?.name ?: "", color.value ?: 0xFFFFFF, popBackStack) }
    ) {
        val lazyListState = rememberLazyListState()

        LazyColumn(Modifier.fillMaxSize(), lazyListState) {
            item { ComicDetailThumbnailHeader(comic, lazyListState) }
            item { ComicDetailCharactersHeader(color.value ?: 0xFFFFFF) }
            item { FirstCharacterItem(comic.value?.characters?.firstOrNull() ?: "", color.value ?: 0xFFFFFF) }
            items(comic.value?.characters?.takeLast(comic.value?.characters?.size?.minus(1) ?: 0) ?: listOf()) {
                CharacterItem(it, color.value ?: 0xFFFFFF)
            }
        }
    }
}

@Composable
private fun FirstCharacterItem(characterName: String, color: Int) {
    Card(
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: placeholder
                contentDescription = "Character Image",
                colorFilter = ColorFilter.tint(Color.Black, blendMode = BlendMode.SrcAtop)
            )
            Text(characterName, color = Color(color), modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun CharacterItem(characterName: String, color: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: placeholder
                contentDescription = "Character Image",
                colorFilter = ColorFilter.tint(Color.Black, blendMode = BlendMode.SrcAtop)
            )
            Text(characterName, color = Color(color), modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ComicDetailCharactersHeader(color: Int) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Characters", color = Color(color), fontWeight = FontWeight.Bold)
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
