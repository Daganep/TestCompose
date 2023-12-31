package com.example.testcompose.app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.testcompose.app.presentation.MainViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailsScreen(viewModel: MainViewModel, itemId: String) {

    val currentMovie = viewModel.moviesList
        .observeAsState(listOf()).value
        .firstOrNull { it.id == itemId.toInt()}

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        LazyColumn {
            item {
                Column(horizontalAlignment =  Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberImagePainter(currentMovie?.image?.medium),
                        contentDescription = null,
                        modifier = Modifier.size(512.dp)
                    )
                    Text(
                        text = currentMovie?.name ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Text(
                            text = "Rating: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = currentMovie?.rating?.average.toString(),
                            fontSize = 18.sp
                        )
                    }
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Text(
                            text = "Genre: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        currentMovie?.genres?.take(2)?.forEach {
                            Text(
                                text = " [$it] ",
                                fontSize = 18.sp
                            )
                        }
                    }
                    Text(
                        text = HtmlCompat.fromHtml(currentMovie?.summary ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
                        modifier = Modifier.padding(top = 10.dp),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}