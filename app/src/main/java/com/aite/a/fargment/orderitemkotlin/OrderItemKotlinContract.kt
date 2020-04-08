package com.aite.awangdalibrary.ui.activity.coupon

import com.aite.a.fargment.orderitemkotlin.OrderItemBean
import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class OrderItemKotlinContract {
    interface View : BaseView {
        fun getOrderListSuccess(x: List<OrderItemBean>, a: Boolean)
        fun sureGotGoodsSuccess()
    }

    interface Presenter : BasePresenter<View> {
        fun getOrderList(type: Int, curpage: Int, key: String, lang_type: String)
        fun sureGotGoods(order_id: String, key: String, lang_type: String)


    }

}