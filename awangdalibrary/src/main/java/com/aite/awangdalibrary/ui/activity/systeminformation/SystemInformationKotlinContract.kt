package com.aite.awangdalibrary.ui.activity.systeminformation

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class SystemInformationKotlinContract {
    interface View : BaseView {
      /*  fun getSuccess(history: MutableList<String>)*/
        fun read()

    }

    interface Presenter : BasePresenter<View> {
      /*  fun getHistory(key: String, lang_type: String)*/
        fun read(key: String, message_ids: String)
    }

}