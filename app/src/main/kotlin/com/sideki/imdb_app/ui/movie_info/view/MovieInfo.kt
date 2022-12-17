package com.sideki.imdb_app.ui.movie_info.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.sideki.imdb_app.util.compose_view.GradientView
import com.sideki.imdb_app.util.debugPlaceholder

@Preview
@Composable
fun MovieInfo(
    title: String = "Movie title",
    rating: String = "7.2", //TODO Добавить цвет в зависимости от рейта
    description: String = "Movie description",
    imageUrl: String = "",
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
        val descriptionRef = createRef()

        AsyncImage(model = imageUrl,
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
            text = title,
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

        Text(
            text = rating,
            color = Color.White,
            modifier = Modifier
                .constrainAs(ratingRef) {
                    start.linkTo(titleRef.start)
                    end.linkTo(titleRef.end)
                    top.linkTo(titleRef.bottom)
                }
                .padding(top = 4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            maxLines = 1
        )

        Text(text = description,
            color = Color.White,
            modifier = Modifier
                .constrainAs(descriptionRef) {
                    top.linkTo(imageRef.bottom)
                }
                .padding(start = 8.dp)
        )

        GradientView(height = 80)

        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Filled.ArrowBack, tint = Color.White, contentDescription = "")
            }
        }
    }
}
