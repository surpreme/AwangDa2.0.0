package com.aite.awangdalibrary.ui.activity.system

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class SystemMsgKotlinContract {
    interface View : BaseView {
        fun getSuccess(l: List<SystemMsgKotlinBean>,isHas:Boolean)

    }

    interface Presenter : BasePresenter<View> {
        //&curpage=1&key=602ce94be353f432844442a350473014&lang_type=zh_cn&pagesize=15
        fun getCommon(curpage: Int, key: String, lang_type: String)
        fun getSystem(curpage: Int, key: String, lang_type: String)
        fun getSide(curpage: Int, key: String, lang_type: String)
    }

}