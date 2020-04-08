package com.aite.awangdalibrary.ui.activity.coupon

import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.utils.SystemUtil

/**

 * @Auther: valy

 * @datetime: 2020/3/12

 * @desc:

 */
class CouponKotlinActivity : BaseMVPListActivity<CouponContract.View, CouponPresenter, CouponUIBean>() {
    override fun setAdapter(): BaseRecyAdapter<CouponUIBean>? {
//        val mCouponUIAdapter = CouponUIAdapter(mContext)
//        return mCouponUIAdapter
        TODO()
    }

    override fun initViews() {
        super.initViews()
        setStatusBar(0)
        initToolBar("失效卷")

    }

    override fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)


    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
            if (position != mAdapter.itemCount)
                rect.bottom = SystemUtil.dp2px(mContext, 20f)
        }

        override fun doRule(position: Int, rect: Rect) {
            rect.left = rect.right
        }
    }

    override fun getRecyclerViewId(): Int = R.id.recycler_view

    override fun onDestroys() {
    }

    override fun getLayoutId(): Int = R.layout.activity_coupon

    override fun initDatas() {
        for (index in 0..10) {
            val f = CouponUIBean()
            if (index % 2 != 0)
                f.couponTitle = "苏宁易购"
            else
                f.couponTitle = "天猫超市"
            mDatasList.add(f)
        }
        appendDatas()
    }

}