package template.x.view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import template.x.model.GpsPosition
import template_2025.shared.generated.resources.Res
import template_2025.shared.generated.resources.dummy_map
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun LocationVisualizer(
    modifier: Modifier,
    gps: GpsPosition,
    title: String,
    parentScrollEnableState: MutableState<Boolean>
) {
    Image(
        painter = painterResource(Res.drawable.dummy_map),
        contentDescription = "Map",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
