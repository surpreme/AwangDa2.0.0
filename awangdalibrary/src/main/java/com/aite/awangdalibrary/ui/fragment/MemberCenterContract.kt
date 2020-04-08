package com.aite.awangdalibrary.ui.fragment

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class MemberCenterContract {
    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {
    }

}