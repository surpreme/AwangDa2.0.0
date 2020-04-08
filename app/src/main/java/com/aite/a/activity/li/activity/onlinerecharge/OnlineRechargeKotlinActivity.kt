package com.aite.a.activity.li.activity.onlinerecharge

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.aite.a.activity.li.fragment.rechargebalance.RechargeBalanceFragment
import com.aite.a.activity.li.fragment.rechargedetails.RechargeDetailsFragment
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.google.android.material.tabs.TabLayout
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.FragmentViewPagerAdapter
import kotlinx.android.synthetic.main.activity_recharge_online.*
import java.util.*

/**

 * @Auther: valy

 * @datetime: 2020/3/17

 * @desc: 在线充值

 */
class OnlineRechargeKotlinActivity : BaseMVPActivity<OnlineRechargeContract.View, OnlineRechargePresenter>(), OnlineRechargeContract.View {
    override fun onDestroys() {

    }

    override fun getLayoutId(): Int = R.layout.activity_recharge_online

    override fun initViews() {
        initToolBar("在线充值")
    }

    @SuppressLint("InflateParams")
    override fun initDatas() {
        val tab1 = tab_layout.newTab()
        tab1.text = "在线充值"
        val tab2 = tab_layout.newTab()
        tab2.text = "充值明细"
        tab_layout.addTab(tab1)
        tab_layout.addTab(tab2)
        val fragments = ArrayList<Fragment>()
        fragments.add(RechargeBalanceFragment())
        fragments.add(RechargeDetailsFragment())
        val mFragmentViewPagerAdapter = FragmentViewPagerAdapter(supportFragmentManager, fragments)
        viewpager.adapter = mFragmentViewPagerAdapter
        viewpager.offscreenPageLimit = tab_layout.tabCount
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpager.currentItem = tab!!.position
            }

        })

    }

}