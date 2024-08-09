package template.x

import androidx.compose.runtime.MutableState
import template.x.view.ToastState

class WebPopupNotification(
    private val toastState: MutableState<ToastState>,
    localization: Localization
) : PopupNotification(localization) {
    override fun showPopUpMessage(text: String) {
        toastState.value = ToastState.Shown(text)
    }
}