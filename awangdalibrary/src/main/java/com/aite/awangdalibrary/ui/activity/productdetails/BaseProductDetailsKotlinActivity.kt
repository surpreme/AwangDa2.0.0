package com.aite.awangdalibrary.ui.activity.productdetails

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.aite.mainlibrary.basekotlin.BaseView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.google.android.material.tabs.TabLayout
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.file.GlideBuilder
import com.valy.baselibrary.utils.GlideImageLoader
import com.valy.baselibrary.view.TabBuilder
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView
import java.util.regex.Pattern

/**

 * @Auther: valy

 * @datetime: 2020/3/23

 * @desc:

 */
abstract class BaseProductDetailsKotlinActivity<V : BaseView, T : BasePresenterImpl<V>, N> : BaseMVPListActivity<V, T, N>(), BaseView {
    protected var curY = 0
    //消息红点标记
    protected var badge: Badge? = null

    //初始化banner
    protected fun initBanner(banner: Banner) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        banner.setImageLoader(GlideImageLoader())
        banner.setBannerAnimation(Transformer.Default)
        banner.setDelayTime(3000)
        banner.isAutoPlay(true)
        banner.setIndicatorGravity(BannerConfig.CENTER)
    }

    protected fun startShowBanner(banner: Banner, mList: List<String>) {
        banner.setImages(mList)
        banner.startAutoPlay()
        banner.start()
    }

    protected fun initTabLayout(tabView: TabLayout, mList: List<String>) {
        for (element in mList) {
            tabView.addTab(tabView.newTab().setText(element))
        }


    }
    override fun setLayoutManager(): LinearLayoutManager = GridLayoutManager(mContext, 2)
    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {

        }

        override fun doRule(position: Int, rect: Rect) {

        }
    }
    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    fun isInteger(str: String?): Boolean {
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }

    protected fun initBadge(v: View) {
        badge = QBadgeView(this).bindTarget(v) //view
                .setBadgeBackgroundColor(Color.RED)
        badge?.setBadgeTextSize(30f, false)
        //        badge.setGravityOffset((enter_iv.getX() / 4) , enter_iv.getY() / 4, false);
        badge?.isShowShadow = true
    }

    //由于ins分享只能分享本地图片 需要保存到本地
    fun saveUrlToFlie(photourl: String) {
        Thread(Runnable {
            try {
                if (!TextUtils.isEmpty(photourl)) {
                    val bitmap = Glide.with(this)
                            .asBitmap()
                            .load(photourl)
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get()
                    GlideBuilder.saveBitmap(bitmap)

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }

    //分享至ins feed
    fun createInstagramIntent(type: String, uri: Uri) { // Create the new Intent using the 'Send' action.
        val share = Intent(Intent.ACTION_SEND)
        share.type = type
        share.putExtra(Intent.EXTRA_STREAM, uri)
        val intent = Intent.createChooser(share, "Share to")
        if (packageManager.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        } else {
            showToast("您没有此app可分享")
        }
        // Broadcast the Intent.
    }

    protected fun initNestedScroll(nestView: NestedScrollView, tabView: TabLayout, view1: View, view2: View) {
        nestView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            curY = scrollY
            Log.e("TAG", scrollY.toString() + "  " + oldScrollY + "  " + scrollX + "   " + view2.top)
            //informationLl.getBottom() <= scrollY &&
            if (scrollY < view1.top && tabView.selectedTabPosition != 0) {
                tabView.selectTab(tabView.getTabAt(0))
                //allInformationLl.getBottom() <= scrollY &&
            } else if (scrollY >= view1.top && scrollY < view2.top && tabView.selectedTabPosition != 1) {
                tabView.selectTab(tabView.getTabAt(1))
                //talkLl.getBottom() <= scrollY&&
            } else if (scrollY >= view2.top && tabView.selectedTabPosition != 2) {
                tabView.selectTab(tabView.getTabAt(2))
            } else {
            }
        }
        tabView.addOnTabSelectedListener(object : TabBuilder() {
            override fun onSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) { //顶部
                    if (curY >= view1.top) {
                        nestView.fullScroll(NestedScrollView.FOCUS_UP)
                    }
                } else if (tab.position == 1) {
                    if (curY < view1.top || curY >= view2.top) {
                        nestView.scrollTo(0, view1.top)
                    }
                } else if (tab.position == 2) {
                    if (curY <= view2.top) nestView.scrollTo(0, view2.top)
                    //                    //底部
//                    nestedScrollview.fullScroll(NestedScrollView.FOCUS_DOWN);
                }
            }
        })
    }

}