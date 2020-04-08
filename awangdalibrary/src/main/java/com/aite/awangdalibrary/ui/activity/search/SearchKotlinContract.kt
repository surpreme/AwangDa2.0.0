package com.aite.awangdalibrary.ui.activity.search

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class SearchKotlinContract {
    interface View : BaseView {
        fun getSuccess(history: MutableList<String>)
        fun onSaveSuccess()

    }

    interface Presenter : BasePresenter<View> {
        fun getHistory(key: String, lang_type: String)
        fun saveHistory(key: String, history: String, client: String, lang_type: String)
    }

}