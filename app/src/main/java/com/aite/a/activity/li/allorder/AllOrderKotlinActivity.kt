package com.aite.a.activity.li.allorder

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.aite.a.fargment.PickupCodeDialogFragment
import com.aite.a.fargment.orderitemkotlin.OrderItemKotlinFragment
import com.aite.awangdalibrary.ui.activity.coupon.AllOrderKotlinContract
import com.aite.awangdalibrary.ui.activity.coupon.AllOrderKotlinPresenter
import com.aite.awangdalibrary.ui.event.RefreshOrderBus
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.google.android.material.tabs.TabLayout
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.FragmentViewPagerAdapter
import com.valy.baselibrary.basekotlin.BaseApp
import com.valy.baselibrary.bean.BaseConstant
import com.valy.baselibrary.dialogfragment.TalkDialogFragment
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.view.TabBuilder
import com.valy.baselibrary.xrbus.RxBus
import kotlinx.android.synthetic.main.activity_allorder_kotlin.*


/**

 * @Auther: valy

 * @datetime: 2020/3/26

 * @desc:

 */
class AllOrderKotlinActivity : BaseMVPActivity<AllOrderKotlinContract.View, AllOrderKotlinPresenter>(), AllOrderKotlinContract.View {
    override fun getLayoutId(): Int = R.layout.activity_allorder_kotlin
    val fragments = ArrayList<Fragment>()

    override fun initViews() {
        initToolBar("全部订单")
        state_tab.addTab(state_tab.newTab().setText(getString(R.string.all_orders)))
        state_tab.addTab(state_tab.newTab().setText("待支付"))
        state_tab.addTab(state_tab.newTab().setText("待发货"))
        state_tab.addTab(state_tab.newTab().setText("待收货"))
        state_tab.addTab(state_tab.newTab().setText("已完成"))
        state_tab.addTab(state_tab.newTab().setText("已到达"))

        for (index in 0..5) {
//            var mOrderFargment: OrderItemKotlinFragment? = null
//
//            mOrderFargment =
            fragments.add(OrderItemKotlinFragment().bulider(index))
        }
        val mFragmentViewPagerAdapter = FragmentViewPagerAdapter(supportFragmentManager, fragments)
        fragment_viewpager.adapter = mFragmentViewPagerAdapter
        fragment_viewpager.offscreenPageLimit = state_tab.tabCount
        //滑动绑定
        fragment_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(state_tab))
        state_tab.addOnTabSelectedListener(object : TabBuilder() {
            override fun onSelected(tab: TabLayout.Tab) {
                fragment_viewpager.currentItem = tab.position
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun initDatas() {
        if (intent.getIntExtra("viewPager", 0) != 0) {
            state_tab.selectTab(state_tab.getTabAt(intent.getIntExtra("viewPager", 0)))

        }
        RxBus.build().toObservable(this@AllOrderKotlinActivity, String::class.java).subscribe { msg ->
            //处理消息
            PickupCodeDialogFragment.newInstance(msg)?.show(
                    supportFragmentManager, "PickupCode")

        }
        RxBus.build().toObservable(this@AllOrderKotlinActivity, CancelOrderBus::class.java).subscribe { msg ->
            val mTalkDialogFragment = TalkDialogFragment().newInstance("您确认要取消此订单吗", "确定")
            mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                mPresenter?.cancelOrder(msg.index, msg.order_id, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)
            }
            mTalkDialogFragment?.show(supportFragmentManager, "cancel")

        }
        RxBus.build().toObservable(this@AllOrderKotlinActivity, SureGotGoodsOrderBus::class.java).subscribe { msg ->
            val mTalkDialogFragment = TalkDialogFragment().newInstance("您确认要收货吗", "确定")
            mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                mPresenter?.sureGotGoods(msg.index, msg.order_id, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)
            }
            mTalkDialogFragment?.show(supportFragmentManager, "got")

        }
        RxBus.build().toObservable(this@AllOrderKotlinActivity, DeleteOrderBus::class.java).subscribe { msg ->
            val mTalkDialogFragment = TalkDialogFragment().newInstance("您确认要删除吗", "确定")
            mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                mPresenter?.deleteGoods(msg.index, msg.order_id, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)
            }
            mTalkDialogFragment?.show(supportFragmentManager, "delete")

        }
    }

    override fun cancelOrderSuccess(index: Int) {
        RxBus.build().post(RefreshOrderBus().builder(index))

    }

    override fun sureGotGoodsSuccess(index: Int) {
        RxBus.build().post(RefreshOrderBus().builder(index))

    }

    override fun deleteSuccess(index: Int) {
        RxBus.build().post(RefreshOrderBus().builder(index))
    }




}