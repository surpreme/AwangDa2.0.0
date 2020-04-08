package com.aite.awangdalibrary.ui.activity.coupon

import com.aite.awangdalibrary.ui.fragment.collectgoods.CollectGoodsBean
import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class CollectGoodsContract {
    interface View : BaseView {
        fun getDatasSuccess(list: List<CollectGoodsBean>, ishaseMore: Boolean)
        fun cancelCollectSuccess()

    }

    interface Presenter : BasePresenter<View> {
        fun getDatas(curpage: Int, fav_type: String, key: String, lang_type: String)
        fun cancelCollect(key: String, fav_id: String)

    }

}