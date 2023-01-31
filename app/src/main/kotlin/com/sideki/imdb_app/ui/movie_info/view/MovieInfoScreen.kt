package com.sideki.imdb_app.ui.movie_info.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sideki.imdb_app.model.model.MovieInfoModel
import com.sideki.imdb_app.util.compose_view.GradientView

@Preview
@Composable
fun MovieInfoScreen(
    movie: MovieInfoModel = MovieInfoModel(),
    onActorClick: (String) -> Unit = { },
    onBackClick: () -> Unit = { }
) {
    ConstraintLayout() {
        val toolbarRef = createRef()
        val gradientRef = createRef()
        val contentRef = createRef()

        MovieInfoBlock(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
                .constrainAs(contentRef) {
                    top.linkTo(parent.top)
                },
            movie = movie,
            onActorClick = onActorClick
        )

        GradientView(
            modifier = Modifier.constrainAs(gradientRef) {
                top.linkTo(parent.top)
            })

        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.constrainAs(toolbarRef) {
                top.linkTo(parent.top)
            }
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

