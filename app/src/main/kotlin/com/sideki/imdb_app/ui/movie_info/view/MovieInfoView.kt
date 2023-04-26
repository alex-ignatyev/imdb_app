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
import com.sideki.imdb_app.domain.model.MovieInfoModel
import com.sideki.imdb_app.ui.movie_info.MovieInfoAction.OnBackButtonClicked
import com.sideki.imdb_app.ui.movie_info.MovieInfoState
import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.compose_view.GradientView

@Composable
fun MovieInfoView(
    movie: MovieInfoModel = MovieInfoModel(),
    state: MovieInfoState,
    actionHandler: (UIAction) -> Unit
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
            handleAction = actionHandler,
            state = state
        )

        GradientView(
            height = 80,
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
            IconButton(onClick = {actionHandler.invoke(OnBackButtonClicked())}) {
                Icon(
                    Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Toolbar"
                )
            }
        }
    }
}
