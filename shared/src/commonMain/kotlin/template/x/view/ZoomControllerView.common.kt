package template.x.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import template.x.model.ScalableState

@Composable
expect fun ZoomControllerView(modifier: Modifier, scalableState: ScalableState)
