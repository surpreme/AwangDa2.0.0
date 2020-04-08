package com.aite.awangdalibrary.ui.activity.collectlist

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.fragment.collectgoods.CollectGoodsKotlinFragment
import com.aite.awangdalibrary.ui.fragment.collectstore.CollectStoreKotlinFragment
import com.aite.mainlibrary.basekotlin.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.valy.baselibrary.adpter.FragmentViewPagerAdapter
import com.valy.baselibrary.view.TabBuilder
import kotlinx.android.synthetic.main.fragment_collect_list_kotlin.*

/**

 * @Auther: valy

 * @datetime: 2020/3/19

 * @desc:

 */
class CollectListKotlinActivity : BaseActivity() {


    override fun getLayoutId(): Int = R.layout.activity_collect_list_kotlin

    override fun initViews() {
        initToolBar("收藏列表")
        findViewById<TabLayout>(R.id.tab_ll).addTab(findViewById<TabLayout>(R.id.tab_ll).newTab().setText(getString(R.string.items)))
        findViewById<TabLayout>(R.id.tab_ll).addTab(findViewById<TabLayout>(R.id.tab_ll).newTab().setText(getString(R.string.store)))
        val fragments = ArrayList<Fragment>()
        fragments.add(CollectGoodsKotlinFragment())
        fragments.add(CollectStoreKotlinFragment())
        val mFragmentViewPagerAdapter = FragmentViewPagerAdapter(supportFragmentManager, fragments)
        findViewById<ViewPager>(R.id.fragment_viewpager).adapter = mFragmentViewPagerAdapter
        findViewById<ViewPager>(R.id.fragment_viewpager).offscreenPageLimit = tab_ll.tabCount
        //滑动绑定
        findViewById<ViewPager>(R.id.fragment_viewpager).addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(findViewById<TabLayout>(R.id.tab_ll)))
        findViewById<TabLayout>(R.id.tab_ll).addOnTabSelectedListener(object : TabBuilder() {
            override fun onSelected(tab: TabLayout.Tab) {
                findViewById<ViewPager>(R.id.fragment_viewpager).currentItem = tab.position
            }
        })
    }

    override fun initDatas() {
        tab_ll.selectTab(tab_ll.getTabAt(intent.getIntExtra("viewPager", 0) - 1))

    }

}