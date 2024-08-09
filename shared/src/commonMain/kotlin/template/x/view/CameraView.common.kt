package template.x.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import template.x.PlatformStorableImage
import template.x.model.PictureData

@Composable
expect fun CameraView(
    modifier: Modifier,
    onCapture: (picture: PictureData.Camera, image: PlatformStorableImage) -> Unit
)
