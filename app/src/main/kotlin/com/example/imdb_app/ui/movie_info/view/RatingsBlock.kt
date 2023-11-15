package com.example.imdb_app.ui.movie_info.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdb_app.domain.model.MovieInfoModel.RatingsModel


@Preview
@Composable
fun RatingsBlock(
    modifier: Modifier = Modifier,
    ratings: RatingsModel = RatingsModel()
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        item {
            if (ratings.metacritic.isNotBlank()) {
                RatingView(title = "Metacritic", rating = ratings.metacritic)
            }
            if (ratings.metacritic.isNotBlank()) {
                RatingView(title = "RottenTomatoes", rating = ratings.rottenTomatoes)
            }
            if (ratings.metacritic.isNotBlank()) {
                RatingView(title = "TheMovieDB", rating = ratings.theMovieDb)
            }
            if (ratings.metacritic.isNotBlank()) {
                RatingView(title = "FilmAffinity", rating = ratings.filmAffinity)
            }
        }
    }
}

@Preview
@Composable
fun RatingView(
    title: String = "Рейтинг",
    rating: String = "0.0"
) {
    Column(
        modifier = Modifier
            .height(60.dp)
            .width(100.dp)
            .padding(8.dp)
            .background(getRatingColor(rating), shape = RoundedCornerShape(4.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = rating,
            color = Color.White,
            fontSize = 16.sp
        )

        Text(
            text = title,
            color = Color.White,
            fontSize = 8.sp
        )
    }
}

private fun getRatingColor(rating: String): Color {
    return if (rating.contains(".")) {
        val ratingFloat = rating.toFloat()
        if (ratingFloat <= 4.0f) {
            Color.Red
        } else if (ratingFloat in 4.1f..6.9f) {
            Color.DarkGray
        } else {
            Color.Green
        }
    } else {
        Color.Green
        val ratingInt = rating.toInt()
        if (ratingInt <= 40) {
            Color.Red
        } else if (ratingInt in 41..69) {
            Color.DarkGray
        } else {
            Color.Green
        }
    }
}
