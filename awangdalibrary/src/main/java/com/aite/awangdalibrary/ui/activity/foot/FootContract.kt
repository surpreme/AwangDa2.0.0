package com.aite.awangdalibrary.ui.activity.foot

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class FootContract {
    interface View : BaseView {
        fun getListSuccess(list: List<FootListBean>, isHas: Boolean)
        fun deleteSuccess(postion: Int)
        fun deleteMoreSuccess()

    }

    interface Presenter : BasePresenter<View> {
        fun getList(curpage: Int, key: String, lang_type: String)
        fun deleteFoot(postion: Int, goods_id: String, key: String, lang_type: String)
        fun deleteMoreFoot(goods_ids: String, key: String, lang_type: String)

    }

}