package com.aite.awangdalibrary.ui.activity.coupon

import com.aite.a.fargment.orderitemkotlin.OrderItemBean
import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class AllOrderKotlinContract {
    interface View : BaseView {
        fun cancelOrderSuccess(index: Int)
        fun sureGotGoodsSuccess(index: Int)
        fun deleteSuccess(index: Int)

    }

    interface Presenter : BasePresenter<View> {
        fun cancelOrder(index: Int, order_id: String, key: String, lang_type: String)
        fun sureGotGoods(index: Int, order_id: String, key: String, lang_type: String)
        fun deleteGoods(index: Int, order_id: String, key: String, lang_type: String)

    }

}