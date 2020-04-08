package com.aite.a.fargment.orderitemkotlin

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.aite.awangdalibrary.ui.event.RefreshOrderBus
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.coupon.OrderItemAdapter
import com.aite.awangdalibrary.ui.activity.coupon.OrderItemKotlinContract
import com.aite.awangdalibrary.ui.activity.coupon.OrderItemKotlinPresenter
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.basekotlin.BaseApp
import com.valy.baselibrary.basekotlin.BaseMVPSmartListFragment
import com.valy.baselibrary.bean.BaseConstant
import com.valy.baselibrary.xrbus.RxBus

/**

 * @Auther: valy

 * @datetime: 2020/3/28

 * @desc:

 */
class OrderItemKotlinFragment : BaseMVPSmartListFragment<OrderItemKotlinContract.View, OrderItemKotlinPresenter, OrderItemBean>(), OrderItemKotlinContract.View {
    var mIndex = 1
    override fun setAdapter(): BaseRecyAdapter<OrderItemBean> {
        val mOrderItemAdapter = OrderItemAdapter(mContext, mIndex)
        return mOrderItemAdapter
    }

    @SuppressLint("CheckResult")
    override fun initExtra() {
        super.initExtra()
        if (arguments?.getInt("index") != null) {
            mIndex = arguments?.getInt("index")!!
        } else {
            mIndex = 1
        }

    }

    @SuppressLint("CheckResult")
    override fun initDatas() {
        super.initDatas()
        RxBus.build().toObservable(this@OrderItemKotlinFragment, RefreshOrderBus::class.java, Lifecycle.Event.ON_STOP).subscribe { msg ->
            //处理消息
            if (mIndex == msg.index)
                refresh()

        }
    }

    override fun getPageDatas(mCurrentPage: Int) {
        super.getPageDatas(mCurrentPage)
        mPresenter?.getOrderList(mIndex, mCurrentPage, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)
    }

    fun bulider(index: Int): OrderItemKotlinFragment {
        val fragment = OrderItemKotlinFragment()
        val bundle = Bundle()
        bundle.putInt("index", index)
        fragment.arguments = bundle
        return fragment
    }

    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
        }

        override fun doRule(position: Int, rect: Rect) {
        }
    }

    override fun getSmartLayoutId(): Int = R.id.smartlayout

    override fun getRecyclerViewId(): Int = R.id.recycler_smart_view

    override fun getLayoutResId(): Int = R.layout.recycler_smart_layout


    override fun getOrderListSuccess(x: List<OrderItemBean>, a: Boolean) {
        isMore = a
        appendDatas(x)

    }

    override fun sureGotGoodsSuccess() {
        refresh()
    }

}