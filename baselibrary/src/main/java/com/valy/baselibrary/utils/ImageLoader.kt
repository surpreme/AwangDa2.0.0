package com.valy.baselibrary.utils

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoaderInterface

/**

 * @Auther: valy

 * @datetime: 2020/3/23

 * @desc:

 */
abstract class ImageLoader : ImageLoaderInterface<ImageView> {
    override fun createImageView(context: Context?): ImageView {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return ImageView(context)
    }

}