package com.aite.awangdalibrary.ui.event

/**

 * @Auther: valy

 * @datetime: 2020/3/28

 * @desc:

 */
open class RefreshOrderBus {
    fun builder(index: Int): RefreshOrderBus {
        val mCancelOrderBus = RefreshOrderBus()
        mCancelOrderBus.index = index
        return mCancelOrderBus
    }

    var index: Int = 0
    override fun toString(): String {
        return "RefreshOrderBus(index='$index')"
    }

}