import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import template.x.Dependencies
import template.x.DesktopImageStorage
import template.x.ImageViewerCommon
import template.x.Notification
import template.x.PopupNotification
import template.x.SharePicture
import template.x.filter.PlatformContext
import template.x.model.PictureData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test

class ImageViewerTest {
    @get:Rule
    val rule = createComposeRule()

    private val dependencies = object : Dependencies() {
        override val notification: Notification = object : PopupNotification(localization) {
            override fun showPopUpMessage(text: String) {
            }
        }
        override val imageStorage: DesktopImageStorage =
            DesktopImageStorage(CoroutineScope(Dispatchers.Main))
        override val sharePicture: SharePicture = object : SharePicture {
            override fun share(context: PlatformContext, picture: PictureData) {}
        }
    }

    @Test
    fun testToggleGalleryStyleButton() {
        rule.setContent {
            ImageViewerCommon(dependencies)
        }

        rule.onNodeWithTag("squaresGalleryView").assertExists()
        rule.onNodeWithTag("listGalleryView").assertDoesNotExist()
        rule.onNodeWithTag("toggleGalleryStyleButton").performClick()
        rule.onNodeWithTag("squaresGalleryView").assertDoesNotExist()
        rule.onNodeWithTag("listGalleryView").assertExists()
    }
}