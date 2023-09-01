package com.example.imdb_app.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow
import androidx.fragment.app.Fragment

fun Fragment.setContent(
    compositionStrategy: ViewCompositionStrategy = DisposeOnDetachedFromWindow,
    context: Context? = getContext(),
    content: @Composable () -> Unit
): ComposeView? {
    context ?: return null
    val view = ComposeView(context)
    view.setViewCompositionStrategy(compositionStrategy)
    view.setContent(content)
    return view
}
