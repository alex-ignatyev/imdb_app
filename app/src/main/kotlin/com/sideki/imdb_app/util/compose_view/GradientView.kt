package com.sideki.imdb_app.util.compose_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientView(
    modifier: Modifier = Modifier,
    startColor: Color = Color.Black,
    endColor: Color = Color.Transparent,
    height: Dp = 80.dp
) {
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        startColor,
                        endColor
                    )
                )
            )
    )
}
