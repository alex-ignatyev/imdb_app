package com.example.imdb_app.ui.movie_info.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import com.example.imdb_app.R
import com.example.imdb_app.domain.model.MovieInfoModel
import com.example.imdb_app.util.base.UIAction
import com.example.imdb_app.util.compose_view.GradientView
import com.example.imdb_app.util.debugPlaceholder

@Preview
@Composable
fun MovieInfoBlock(
    modifier: Modifier = Modifier,
    movie: MovieInfoModel = MovieInfoModel(),
    onActorClick: (String) -> Unit = { },
    actionHandler: (UIAction) -> Unit
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val imageRef = createRef()
        val titleRef = createRef()
        val ratingRef = createRef()
        val yearRef = createRef()
        val countryRef = createRef()
        val descriptionRef = createRef()
        val directorsRef = createRef()
        val deviderRef = createRef()
        val actorsRef = createRef()
        val similarsRef = createRef()
        val imagesRef = createRef()
        val ratingsRef = createRef()

        AsyncImage(
            model = movie.imageUrl,
            contentDescription = "Movie image",
            contentScale = ContentScale.Crop,
            placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
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
            height = 100.dp
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

        Row(
            modifier = Modifier.constrainAs(ratingRef) {
                start.linkTo(titleRef.start)
                end.linkTo(titleRef.end)
                top.linkTo(titleRef.bottom)
            }) {

            if (movie.ratings.imDb.isNotBlank()) {
                Text(
                    text = movie.ratings.imDb,
                    color = if (movie.ratings.imDb.toFloat() >= 7.0) {
                        Color.Green
                    } else if (movie.ratings.imDb.toFloat() < 7.0 && movie.ratings.imDb.toFloat() > 3.0) {
                        Color.Gray
                    } else {
                        Color.Red
                    },
                    fontSize = 12.sp,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = movie.ratings.imDbVotes,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }
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

        Text(
            text = "реж. ${movie.directors}",
            modifier = Modifier.constrainAs(directorsRef) {
                top.linkTo(countryRef.bottom)
                start.linkTo(countryRef.start)
                end.linkTo(countryRef.end)
            },
            color = Color.Gray,
            fontSize = 12.sp,
            maxLines = 1
        )

        Divider(
            modifier = Modifier
                .constrainAs(deviderRef) {
                    top.linkTo(directorsRef.bottom)
                }
                .padding(8.dp),
            color = Color.Gray
        )

        Text(
            text = "\t${movie.description}",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(descriptionRef) {
                    top.linkTo(deviderRef.bottom)
                }
                .padding(start = 8.dp, end = 8.dp),
            fontSize = 14.sp,
        )

        ImagesBlock(
            images = movie.images,
            modifier = Modifier.constrainAs(imagesRef) {
                top.linkTo(descriptionRef.bottom)
            }
        )

        RatingsBlock(
            ratings = movie.ratings,
            modifier = Modifier.constrainAs(ratingsRef) {
                top.linkTo(imagesRef.bottom)
            })

        ActorsBlock(
            actors = movie.actors,
            modifier = Modifier.constrainAs(actorsRef) {
                top.linkTo(ratingsRef.bottom)
            }, actionHandler = actionHandler
        )

        SimilarMoviesBlock(
            similars = movie.similarMovies,
            modifier = Modifier.constrainAs(similarsRef) {
                top.linkTo(actorsRef.bottom)
            }
        )
    }
}