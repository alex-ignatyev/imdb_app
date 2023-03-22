package com.sideki.imdb_app.ui.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
import androidx.compose.runtime.collectAsState
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sideki.imdb_app.R
import com.sideki.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {

    private val vm by viewModels<ChangePasswordVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ) = setContent {
        val state = vm.state.collectAsState()
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            OutLineTextField(
                modifier = Modifier
                    .padding(start = 36.dp, end = 36.dp, top = 20.dp)
                    .fillMaxWidth(),
                "Current password",
                input = state.value.currentPassword,
                error = state.value.currentPasswordError,
                onValueChange = { vm.obtainCurrentPasswordChanges(it) }
            )
            OutLineTextField(
                modifier = Modifier
                    .padding(start = 36.dp, end = 36.dp, top = 20.dp)
                    .fillMaxWidth(),
                "New password",
                input = state.value.newPassword,
                error = state.value.newPasswordError,
                onValueChange = { vm.obtainNewPasswordChanges(it) }
            )
            OutLineTextField(
                modifier = Modifier
                    .padding(start = 36.dp, end = 36.dp, top = 20.dp)
                    .fillMaxWidth(),
                "Repeat new password",
                input = state.value.repeatNewPassword,
                error = state.value.repeatNewPasswordError,
                onValueChange = { vm.obtainRepeatNewPasswordChanges(it) }
            )
            OutLineButton(text = "Back", action = { findNavController().popBackStack() })
        }
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
                    ), endY = 1100f
                )
            )
    )
}

@Composable
fun OutLineTextField(
    modifier: Modifier = Modifier,
    text: String,
    input: String,
    error: Boolean,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val maxCharacter = 20
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            label = { Text(text, style = TextStyle(color = if (error) Color.Red else Color.White)) },
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
            modifier = modifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (error) Color.Red else Color.White,
                unfocusedBorderColor = if (error) Color.Red else Color.White,
                textColor = if (error) Color.Red else Color.White,
                cursorColor = if (error) Color.Red else Color.White
            )
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (error) {
                    if (input.length < 8) "Minimum 8 character"
                    else ""
                } else "",
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
                color = if (error) Color.Red else Color.White
            )
        }
    }
}

@Composable
fun OutLineButton(text: String, modifier: Modifier = Modifier, action: () -> Unit) {
    OutlinedButton(
        onClick = action,
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
