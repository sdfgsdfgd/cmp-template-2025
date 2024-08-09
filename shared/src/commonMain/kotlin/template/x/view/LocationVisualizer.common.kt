package template.x.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import template.x.model.GpsPosition

@Composable
expect fun LocationVisualizer(
    modifier: Modifier,
    gps: GpsPosition,
    title: String,
    parentScrollEnableState: MutableState<Boolean>
)
