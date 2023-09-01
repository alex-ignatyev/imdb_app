package com.example.imdb_app.ui.change_password

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.imdb_app.R
import com.example.imdb_app.ui.change_password.ChangePasswordAction.CurrentPasswordChanged
import com.example.imdb_app.ui.change_password.ChangePasswordAction.NewPasswordChanged
import com.example.imdb_app.ui.change_password.ChangePasswordAction.OnBackButtonClicked
import com.example.imdb_app.ui.change_password.ChangePasswordAction.OnChangePasswordButtonClicked
import com.example.imdb_app.ui.change_password.ChangePasswordAction.RepeatNewPasswordChanged
import com.example.imdb_app.util.base.UIAction

@Composable
fun ChangePasswordView(state: ChangePasswordState, actionHandler: (UIAction) -> Unit) {

    Background()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutLineTextField(
            text = "Current password",
            input = state.currentPassword,
            error = state.currentPasswordError,
            onValueChange = { actionHandler.invoke(CurrentPasswordChanged(it)) }
        )
        OutLineTextField(
            text = "New password",
            input = state.newPassword,
            error = state.newPasswordError,
            onValueChange = { actionHandler.invoke(NewPasswordChanged(it)) }
        )
        OutLineTextField(
            text = "Repeat new password",
            input = state.repeatNewPassword,
            error = state.repeatNewPasswordError,
            onValueChange = { actionHandler.invoke(RepeatNewPasswordChanged(it)) }
        )
        OutLineButton(text = "Change password", actionHandler = actionHandler)
        OutLineButton(text = "Back", actionHandler = actionHandler)
    }
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
                    ),
                    endY = 1100f
                )
            )
    )
}

@Composable
fun OutLineTextField(
    text: String,
    input: String,
    error: String,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val maxCharacter = 20
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            label = { Text(text, style = TextStyle(color = if (error.isNotEmpty()) Color.Red else Color.White)) },
            singleLine = true,
            placeholder = { Text(text, style = TextStyle(color = Color.White)) },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "vectorLock",
                    tint = Color.White
                )
            },
            trailingIcon = {
                val image = if (isPasswordVisible)
                    painterResource(id = R.drawable.ic_visibility_off)
                else painterResource(id = R.drawable.ic_visibility)
                val description = if (isPasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(painter = image, description, tint = Color.White)
                }
            },
            modifier = Modifier
                .padding(start = 36.dp, end = 36.dp, top = 20.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (error.isNotEmpty()) Color.Red else Color.White,
                unfocusedBorderColor = if (error.isNotEmpty()) Color.Red else Color.White,
                textColor = if (error.isNotEmpty()) Color.Red else Color.White,
                cursorColor = if (error.isNotEmpty()) Color.Red else Color.White
            )
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = error,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 36.dp),
                color = Color.Red
            )
            Text(
                text = "${input.length} / $maxCharacter",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 36.dp),
                color = if (error.isNotEmpty()) Color.Red else Color.White
            )
        }
    }
}

@Composable
fun OutLineButton(text: String, modifier: Modifier = Modifier, actionHandler: (UIAction) -> Unit) {
    OutlinedButton(
        onClick = {
            if (text == "Change password") actionHandler.invoke(OnChangePasswordButtonClicked())
            else actionHandler.invoke(OnBackButtonClicked())
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color.Transparent
        ),
        border = BorderStroke(color = Color.White, width = 1.dp),
        modifier = modifier
            .padding(start = 36.dp, end = 36.dp, top = 20.dp)
            .fillMaxWidth()
    ) {
        Text(text, color = Color.White)
    }
}
