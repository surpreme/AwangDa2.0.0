package com.aite.awangdalibrary.ui.activity.foot

/**

 * @Auther: valy

 * @datetime: 2020/3/30

 * @desc:

 */
class FootDeleteBus {
    fun builder(postion: Int, goods_id: String): FootDeleteBus {
        val mFootDeleteBus = FootDeleteBus()
        mFootDeleteBus.goods_id = goods_id
        mFootDeleteBus.postion = postion
        return mFootDeleteBus
    }

    override fun toString(): String {
        return "FootDeleteBus(order_id='$goods_id')"
    }


    var goods_id: String = ""
    var postion: Int = 0

}