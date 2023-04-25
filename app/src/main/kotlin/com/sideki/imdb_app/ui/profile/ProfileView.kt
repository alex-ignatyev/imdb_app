package com.sideki.imdb_app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import coil.compose.AsyncImage
import com.sideki.imdb_app.R.drawable
import com.sideki.imdb_app.db.entity.SelectedMoviesEntity
import com.sideki.imdb_app.ui.profile.ProfileAction.OnChangePasswordTextClicked
import com.sideki.imdb_app.ui.profile.ProfileAction.OnLogOutTextClicked
import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.debugPlaceholder

@Composable
fun ProfileView(movies: List<SelectedMoviesEntity>,actionHandler: (UIAction) -> Unit) {

    ConstraintLayout(constraintSet = ConstraintsOfScreen(), modifier = Modifier.fillMaxSize()) {
        Background()
        Title(
            text = "Profile", modifier = Modifier
                .padding(16.dp)
                .layoutId("title")
        )
        DividerLine(
            modifier = Modifier
                .layoutId("dividerTop")
                .padding(10.dp)
                .height(1.dp)
        )
        ChangePassword(
            text = "Change password", modifier = Modifier
                .padding(10.dp)
                .layoutId("changePassword"), actionHandler
        )
        DividerLine(
            modifier = Modifier
                .layoutId("dividerBottom")
                .padding(4.dp)
                .height(1.dp)
        )
        SelectedMovies(movies = movies)
        LogOut(
            text = "Log Out", modifier = Modifier
                .layoutId("logOut")
                .padding(16.dp), userLoggedOut = actionHandler
        )
    }
}

@Composable
fun ConstraintsOfScreen(): ConstraintSet {
    val constraints = ConstraintSet {
        val title = createRefFor("title")
        val dividerTop = createRefFor("dividerTop")
        val changePassword = createRefFor("changePassword")
        val dividerBottom = createRefFor("dividerBottom")
        val logOut = createRefFor("logOut")
        val selectedMovies = createRefFor("selectedMovies")

        constrain(title) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(dividerTop) {
            top.linkTo(title.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(changePassword) {
            top.linkTo(dividerTop.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(dividerBottom) {
            top.linkTo(changePassword.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(selectedMovies){
            top.linkTo(dividerBottom.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

        constrain(logOut) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
    return constraints
}

@Composable
fun Background() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.DarkGray,
                        Color.Black
                    ), endY = 1100f
                )
            )
    )
}

@Composable
fun Title(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun DividerLine(modifier: Modifier = Modifier) {
    Divider(modifier = modifier.padding(horizontal = 20.dp), color = Color.Gray)
}

@Composable
fun ChangePassword(text: String, modifier: Modifier = Modifier, actionHandler: (UIAction) -> Unit) {
    Box(modifier = modifier) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.clickable {
                actionHandler.invoke(OnChangePasswordTextClicked())
            })
    }
}

@Composable
fun LogOut(text: String, modifier: Modifier = Modifier, userLoggedOut: (UIAction) -> Unit) {
    Box(modifier = modifier) {
        Text(
            text = text,
            color = Color.Red,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.clickable {
                userLoggedOut.invoke(OnLogOutTextClicked())
            })
    }
}

@Composable
fun SelectedMovies(modifier: Modifier = Modifier, movies: List<SelectedMoviesEntity>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
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
