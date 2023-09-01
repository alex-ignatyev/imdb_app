package com.example.imdb_app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.imdb_app.ui.profile.ProfileAction.OnChangePasswordTextClicked
import com.example.imdb_app.ui.profile.ProfileAction.OnLogOutTextClicked
import com.example.imdb_app.util.base.UIAction

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
