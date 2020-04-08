package com.aite.a.activity.li.allorder

/**

 * @Auther: valy

 * @datetime: 2020/3/28

 * @desc:

 */
class DeleteOrderBus {
    fun builder(order_id: String, index: Int): DeleteOrderBus {
        val mDeleteOrderBus = DeleteOrderBus()
        mDeleteOrderBus.order_id = order_id
        mDeleteOrderBus.index = index
        return mDeleteOrderBus
    }

    var index = 0
    var order_id: String = ""
    override fun toString(): String {
        return "DeleteOrderBus(order_id='$order_id')"
    }

}