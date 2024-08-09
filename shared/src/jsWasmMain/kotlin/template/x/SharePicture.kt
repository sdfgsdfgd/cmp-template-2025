package template.x

import template.x.filter.PlatformContext
import template.x.model.PictureData

class WebSharePicture : SharePicture {
    override fun share(context: PlatformContext, picture: PictureData) {
        error("Should not be called")
    }
}
