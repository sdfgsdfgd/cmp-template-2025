import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import template.x.*
import template.x.style.ImageViewerTheme
import template.x.ioDispatcher
import template.x.view.Toast
import template.x.view.ToastState
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun ImageViewerWeb() {
    val toastState = remember { mutableStateOf<ToastState>(ToastState.Hidden) }
    val ioScope: CoroutineScope = rememberCoroutineScope { ioDispatcher }
    val dependencies = remember(ioScope) { getDependencies(toastState) }

    ImageViewerTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ImageViewerCommon(
                dependencies = dependencies
            )
            Toast(toastState)
        }
    }
}

fun getDependencies(toastState: MutableState<ToastState>) = object : Dependencies() {
    override val imageStorage: ImageStorage = WebImageStorage()
    override val sharePicture = WebSharePicture()
    override val notification = WebPopupNotification(toastState, localization)
}
