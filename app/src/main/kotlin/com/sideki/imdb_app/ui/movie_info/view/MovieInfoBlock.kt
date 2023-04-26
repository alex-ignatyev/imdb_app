package com.sideki.imdb_app.ui.movie_info.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnAddMovieClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnDeleteMovieClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoState
import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.compose_view.GradientView
import com.sideki.imdb_app.util.debugPlaceholder

@Composable
fun MovieInfoBlock(
    modifier: Modifier = Modifier,
    movie: MovieInfoModel = MovieInfoModel(),
    handleAction: (UIAction) -> Unit,
    state: MovieInfoState
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

        FloatingActionButton(
            modifier = Modifier
                .constrainAs(createRef()) {
                    bottom.linkTo(imageRef.bottom)
                    end.linkTo(parent.end)
                }
                .padding(10.dp),
            onClick = {
                if (!state.isMovieAdded) {
                    handleAction.invoke(OnAddMovieClicked(movie))
                } else handleAction.invoke(OnDeleteMovieClicked(movie))
            },
            backgroundColor = if (!state.isMovieAdded) Color.Green else Color.Red,
            contentColor = Color.White
        ) {
            if (!state.isMovieAdded) Icon(Filled.Add, "Add movie to selected") else Icon(
                Filled.Delete,
                "Remove movie from selected"
            )
        }

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

        if (movie.images.isNotEmpty()) {
            ImagesBlock(
                images = movie.images,
                modifier = Modifier.constrainAs(imagesRef) {
                    top.linkTo(descriptionRef.bottom)
                }
            )
        }

        RatingsBlock(
            ratings = movie.ratings,
            modifier = Modifier.constrainAs(ratingsRef) {
                top.linkTo(imagesRef.bottom)
            })

        if (movie.actors.isNotEmpty()) {
            ActorsBlock(
                actors = movie.actors,
                modifier = Modifier.constrainAs(actorsRef) {
                    top.linkTo(ratingsRef.bottom)
                })
        }

        if (movie.similarMovies.isNotEmpty()) {
            SimilarMoviesBlock(
                similars = movie.similarMovies,
                modifier = Modifier.constrainAs(similarsRef) {
                    top.linkTo(actorsRef.bottom)
                }
            )
        }
    }
}