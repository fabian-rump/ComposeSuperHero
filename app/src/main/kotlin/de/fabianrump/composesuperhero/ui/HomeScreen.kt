package de.fabianrump.composesuperhero.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.get

@Composable
fun HomeScreen() {
    val viewModel = get<MainViewModel>()
    val data = viewModel.testData.observeAsState()
    viewModel.fetchDataFromWebService()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = viewModel::onClick) {
            Text(text = data.value ?: "")
        }
    }
}