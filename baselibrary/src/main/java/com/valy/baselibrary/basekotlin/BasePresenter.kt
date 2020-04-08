package com.aite.mainlibrary.basekotlin

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

interface BasePresenter<V : BaseView> {
    fun attachView(view: V)

    fun detachView()
}
