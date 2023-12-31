package com.example.testcompose.app.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.testcompose.app.presentation.MainViewModel
import com.example.testcompose.data.models.Movies

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    val moviesList = viewModel.moviesList.observeAsState(listOf()).value
    Log.d("MyFilter", "moviesList: $moviesList")

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(moviesList.take(10)) {item ->
                MovieItem(item)
            }
        }
    }
}

@Composable
fun MovieItem(item: Movies) {
    Row(modifier = Modifier.fillMaxSize()) {
        Text(text = item.id.toString())
        Text(text = item.name)
    }
}