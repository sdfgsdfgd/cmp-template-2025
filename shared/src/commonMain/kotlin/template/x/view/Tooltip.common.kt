package template.x.view

import androidx.compose.runtime.Composable

@Composable
expect fun Tooltip(
    text: String,
    content: @Composable () -> Unit
)
