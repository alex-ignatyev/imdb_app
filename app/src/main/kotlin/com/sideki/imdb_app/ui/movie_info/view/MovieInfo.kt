package com.sideki.imdb_app.ui.movie_info.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.sideki.imdb_app.R.drawable
import com.sideki.imdb_app.domain.model.MovieInfoModel
import com.sideki.imdb_app.util.compose_view.GradientView
import com.sideki.imdb_app.util.debugPlaceholder

@Preview
@Composable
fun MovieInfo(
    movie: MovieInfoModel = MovieInfoModel(),
    onBackClick: () -> Unit = { }
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val imageRef = createRef()
        val titleRef = createRef()
        val ratingRef = createRef()
        val yearRef = createRef()
        val countryRef = createRef()
        val deviderRef = createRef()
        val descriptionRef = createRef()

        AsyncImage(model = movie.imageUrl,
            contentDescription = "Movie image",
            contentScale = ContentScale.Crop,
            placeholder = debugPlaceholder(drawable.ic_launcher_background),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                })

        GradientView(
            Modifier.constrainAs(createRef()) {
                bottom.linkTo(imageRef.bottom)
            },
            Color.Transparent,
            Color.Black,
            height = 100
        )

        Text(
            text = movie.title,
            color = Color.White,
            modifier = Modifier
                .constrainAs(titleRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(imageRef.bottom)
                }
                .padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            maxLines = 1
        )

        Row(modifier = Modifier.constrainAs(ratingRef) {
            start.linkTo(titleRef.start)
            end.linkTo(titleRef.end)
            top.linkTo(titleRef.bottom)
        }) {
            Text(
                text = movie.rating,
                color = if (movie.rating.toFloat() >= 7.0) {
                    Color.Green
                } else if (movie.rating.toFloat() < 7.0 && movie.rating.toFloat() > 3.0) {
                    Color.Gray
                } else {
                    Color.Red
                },
                fontSize = 12.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = movie.ratingVotes,
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1
            )
        }

        Text(
            text = "${movie.year}, ${movie.genres.lowercase()}",
            modifier = Modifier.constrainAs(yearRef) {
                top.linkTo(ratingRef.bottom)
                start.linkTo(ratingRef.start)
                end.linkTo(ratingRef.end)
            },
            color = Color.Gray,
            fontSize = 12.sp,
            maxLines = 1
        )

        Text(
            text = "${movie.countries}, ${movie.runtimeMins} мин",
            modifier = Modifier.constrainAs(countryRef) {
                top.linkTo(yearRef.bottom)
                start.linkTo(yearRef.start)
                end.linkTo(yearRef.end)
            },
            color = Color.Gray,
            fontSize = 12.sp,
            maxLines = 1
        )

        Divider(
            modifier = Modifier
                .constrainAs(deviderRef) {
                    top.linkTo(countryRef.bottom)
                }
                .padding(8.dp),
            color = Color.Gray
        )

        Text(
            text = movie.description,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(descriptionRef) {
                    top.linkTo(deviderRef.bottom)
                }
                .padding(start = 16.dp, end = 16.dp),
            fontSize = 14.sp,
        )

        GradientView(height = 80)

        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Toolbar"
                )
            }
        }
    }
}
