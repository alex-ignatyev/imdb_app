package com.sideki.imdb_app.ui.selected_movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sideki.imdb_app.R.drawable
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.debugPlaceholder

@Composable
fun SelectedMoviesView(
    movies: List<SelectedMoviesEntity>,
    actionHandler: (UIAction) -> Unit
) {
    SelectedMovies(movies = movies)
}

@Composable
fun SelectedMovies(modifier: Modifier = Modifier, movies: List<SelectedMoviesEntity>) {
    LazyVerticalGrid(columns = Fixed(2), content = {
        items(movies) { item ->
            Column {
                AsyncImage(
                    model = item.image, contentDescription = "Selected movies image",
                    placeholder = debugPlaceholder(debugPreview = drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(210.dp)
                        .height(405.dp)
                        .padding(4.dp)
                )
                Text(
                    text = item.title,
                    color = Color.White
                )
            }
        }
    })
}
