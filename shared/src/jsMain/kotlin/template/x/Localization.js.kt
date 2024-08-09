package template.x

import kotlinx.browser.window

actual fun getCurrentLanguage(): AvailableLanguages =
    when (window.navigator.languages.firstOrNull() ?: window.navigator.language) {
        "de" -> AvailableLanguages.DE
        else -> AvailableLanguages.EN
    }

actual fun getCurrentPlatform(): String = "Web JS"
