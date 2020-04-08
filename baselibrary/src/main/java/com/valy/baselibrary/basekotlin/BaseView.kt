package com.aite.mainlibrary.basekotlin

import android.content.Context

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

interface BaseView {
    fun getContext(): Context
    fun showError(msg: String)
    fun Untoken()
    fun closeLoading()
    fun showLoading()

}
