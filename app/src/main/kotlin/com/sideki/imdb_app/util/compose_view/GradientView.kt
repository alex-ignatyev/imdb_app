package com.sideki.imdb_app.util.compose_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientView(
    modifier: Modifier = Modifier,
    startColor: Color = Color.Black,
    endColor: Color = Color.Transparent,
    height: Int = 80
) {
    Box(
        modifier = modifier
            .height(height.dp)
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