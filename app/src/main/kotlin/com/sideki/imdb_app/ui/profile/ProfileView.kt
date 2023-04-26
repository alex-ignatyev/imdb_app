package com.sideki.imdb_app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.sideki.imdb_app.R.drawable
import com.sideki.imdb_app.ui.profile.ProfileAction.OnChangePasswordTextClicked
import com.sideki.imdb_app.ui.profile.ProfileAction.OnLogOutTextClicked
import com.sideki.imdb_app.ui.profile.ProfileAction.OnSelectedMoviesTextClicked
import com.sideki.imdb_app.util.base.UIAction

@Composable
fun ProfileView(actionHandler: (UIAction) -> Unit) {

    ConstraintLayout(constraintSet = ConstraintsOfScreen(), modifier = Modifier.fillMaxSize()) {
        Background()
        Title(
            text = "Profile", modifier = Modifier
                .padding(16.dp)
                .layoutId("title")
        )
        DividerLine(
            modifier = Modifier
                .layoutId("firstDivider")
                .padding(10.dp)
                .height(2.dp)
        )
        TextWithIcon(
            text = "Change password", modifier = Modifier
                .padding(14.dp)
                .layoutId("changePassword"), actionHandler,
            id = drawable.ic_edit
        )
        DividerLine(
            modifier = Modifier
                .layoutId("secondDivider")
                .padding(4.dp)
                .height(2.dp)
        )
        TextWithIcon(
            text = "Selected movies",
            modifier = Modifier
                .padding(14.dp)
                .layoutId("selectedMovies"),
            actionHandler = actionHandler,
            id = drawable.ic_favorite
        )
        DividerLine(
            modifier = Modifier
                .layoutId("thirdDivider")
                .padding(4.dp)
                .height(2.dp)
        )
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
        val firstDivider = createRefFor("firstDivider")
        val changePassword = createRefFor("changePassword")
        val secondDivider = createRefFor("secondDivider")
        val selectedMovies = createRefFor("selectedMovies")
        val thirdDivider = createRefFor("thirdDivider")
        val logOut = createRefFor("logOut")

        constrain(title) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(firstDivider) {
            top.linkTo(title.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(changePassword) {
            top.linkTo(firstDivider.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(secondDivider) {
            top.linkTo(changePassword.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(selectedMovies) {
            top.linkTo(secondDivider.bottom)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(thirdDivider) {
            top.linkTo(selectedMovies.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
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
fun TextWithIcon(text: String, modifier: Modifier = Modifier, actionHandler: (UIAction) -> Unit, id: Int) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.padding(start = 20.dp, end = 8.dp)) {
            Icon(painter = painterResource(id = id), contentDescription = "", tint = Color.White)
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .clickable {
                        if (text == "Change password")
                            actionHandler.invoke(OnChangePasswordTextClicked())
                        else actionHandler.invoke(OnSelectedMoviesTextClicked())
                    }
                    .padding(start = 8.dp)
            )
        }
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
