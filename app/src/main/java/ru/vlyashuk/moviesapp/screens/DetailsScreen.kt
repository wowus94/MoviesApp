package ru.vlyashuk.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ru.vlyashuk.moviesapp.MainViewModel
import ru.vlyashuk.moviesapp.R
import ru.vlyashuk.moviesapp.utils.Constants.HtmlText

@Composable
fun DetailsScreen(viewModel: MainViewModel, itemId: String) {

    val currentItem = viewModel.allMovies
        .observeAsState(listOf()).value
        .firstOrNull() {
            it.id == itemId.toInt()
        }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        LazyColumn {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(currentItem?.image?.medium),
                        contentDescription = stringResource(R.string.image_movies_content_description),
                        modifier = Modifier.size(256.dp)
                    )
                    Text(
                        text = currentItem?.name ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "Rating: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = currentItem?.rating?.average.toString(),
                            fontSize = 18.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "Genre: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        currentItem?.genres?.take(2)?.forEach {
                            Text(
                                text = " $it",
                                fontSize = 18.sp
                            )
                        }
                    }

                    HtmlText(
                        html = currentItem?.summary ?: "",
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }

}