package com.aite.awangdalibrary.utils

import android.content.Context
import com.valy.baselibrary.utils.StatusBarUtils

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
object ScreenSizeUtils {
    /**
     * 系统栏的高度
     */
    fun getStatusBarHeight(mContext: Context): Int = StatusBarUtils.getHeight(mContext)

    /**
     * 屏幕的尺寸
     */
    fun getScreenWidth(mContext: Context): Int = mContext.resources?.displayMetrics?.widthPixels!!

    fun getScreenHeight(mContext: Context): Int = mContext.resources?.displayMetrics?.heightPixels!!

}