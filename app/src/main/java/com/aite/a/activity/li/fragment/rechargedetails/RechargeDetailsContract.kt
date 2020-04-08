package com.aite.a.activity.li.fragment.rechargedetails

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class RechargeDetailsContract {
    interface View : BaseView {
        fun getListSuccess(mlist: MutableList<RechargeDetailsBean>, isHaveMore: Boolean)
        fun getInformation(x: String, y: String)

    }

    interface Presenter : BasePresenter<View> {
        fun getList(key: String, pagesize: Int, curpage: Int, pdr_sn: String, lang_type: String)

    }

}