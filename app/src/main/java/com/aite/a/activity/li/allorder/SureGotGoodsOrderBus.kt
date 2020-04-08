package com.aite.a.activity.li.allorder

/**

 * @Auther: valy

 * @datetime: 2020/3/28

 * @desc:

 */
class SureGotGoodsOrderBus {
    fun builder(order_id: String,index: Int): SureGotGoodsOrderBus {
        val mSureGotGoodsOrderBus = SureGotGoodsOrderBus()
        mSureGotGoodsOrderBus.order_id = order_id
        mSureGotGoodsOrderBus.index = index
        return mSureGotGoodsOrderBus
    }

    var order_id: String = ""
    var index: Int = 0
    override fun toString(): String {
        return "SureGotGoodsOrderBus(order_id='$order_id')"
    }

}