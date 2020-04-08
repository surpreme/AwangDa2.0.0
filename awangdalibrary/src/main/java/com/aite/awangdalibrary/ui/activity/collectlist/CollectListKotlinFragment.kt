package com.aite.awangdalibrary.ui.activity.collectlist

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.fragment.collectgoods.CollectGoodsKotlinFragment
import com.aite.awangdalibrary.ui.fragment.collectstore.CollectStoreKotlinFragment
import com.aite.mainlibrary.basekotlin.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.valy.baselibrary.adpter.FragmentViewPagerAdapter
import com.valy.baselibrary.view.TabBuilder
import kotlinx.android.synthetic.main.fragment_collect_list_kotlin.*

/**

 * @Auther: valy

 * @datetime: 2020/3/19

 * @desc:

 */
class CollectListKotlinFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_collect_list_kotlin

    override fun initViews(view: View) {
        view.findViewById<TabLayout>(R.id.tab_ll).addTab(view.findViewById<TabLayout>(R.id.tab_ll).newTab().setText(getString(R.string.items)))
        view.findViewById<TabLayout>(R.id.tab_ll).addTab(view.findViewById<TabLayout>(R.id.tab_ll).newTab().setText(getString(R.string.store)))
        val fragments = ArrayList<Fragment>()
        fragments.add(CollectGoodsKotlinFragment())
        fragments.add(CollectStoreKotlinFragment())
        val mFragmentViewPagerAdapter = FragmentViewPagerAdapter(childFragmentManager, fragments)
        view.findViewById<ViewPager>(R.id.fragment_viewpager).adapter = mFragmentViewPagerAdapter
        view.findViewById<ViewPager>(R.id.fragment_viewpager).offscreenPageLimit = tab_ll.tabCount
        //滑动绑定
        view.findViewById<ViewPager>(R.id.fragment_viewpager).addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.findViewById<TabLayout>(R.id.tab_ll)))
        view.findViewById<TabLayout>(R.id.tab_ll).addOnTabSelectedListener(object : TabBuilder() {
            override fun onSelected(tab: TabLayout.Tab) {
                view.findViewById<ViewPager>(R.id.fragment_viewpager).currentItem = tab.position
            }
        })
    }

    override fun initDatas() {
    }

}