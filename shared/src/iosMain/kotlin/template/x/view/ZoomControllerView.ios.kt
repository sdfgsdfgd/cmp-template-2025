package template.x.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import template.x.model.ScalableState

@Composable
actual fun ZoomControllerView(modifier: Modifier, scalableState: ScalableState) {
    // No need for additional ZoomControllerView for iOS
}
