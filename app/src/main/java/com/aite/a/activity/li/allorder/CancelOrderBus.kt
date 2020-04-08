package com.aite.a.activity.li.allorder

/**

 * @Auther: valy

 * @datetime: 2020/3/28

 * @desc:

 */
class CancelOrderBus {
    fun builder(order_id: String,index: Int): CancelOrderBus {
        val mCancelOrderBus = CancelOrderBus()
        mCancelOrderBus.order_id = order_id
        mCancelOrderBus.index = index
        return mCancelOrderBus
    }


    var order_id: String = ""
    var index: Int = 0

    override fun toString(): String {
        return "CancelOrderBus(order_id='$order_id', index=$index)"
    }

}