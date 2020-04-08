package com.valy.baselibrary.basekotlin

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.aite.mainlibrary.basekotlin.BaseView
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.valy.baselibrary.R

/**

 * @Auther: valy

 * @datetime: 2020/3/13

 * @desc:

 */
abstract class BaseMVPSmartListActivity<V : BaseView, T : BasePresenterImpl<V>, N> : BaseMVPListActivity<V, T, N>(), BaseView {
    abstract fun getSmartLayoutId(): Int
    open fun getSmartEmptyId(): Int=R.id.smart_frame
    open fun getPageDatas(mCurrentPage: Int) {}
    private var isHaveMore: Boolean = true
    var isMore: Boolean = true
    private var mCurrentPage: Int = 1
    private var mSmartRefreshLayout: SmartRefreshLayout? = null
    private var mSmartFrameLayout: ViewGroup? = null

    override fun initViews() {
        super.initViews()
        mSmartRefreshLayout = this.findViewById(getSmartLayoutId())
        mSmartRefreshLayout?.setRefreshHeader(WaterDropHeader(mContext))
        mSmartFrameLayout = this.findViewById(getSmartEmptyId())
        showNoData()
        mSmartRefreshLayout?.setOnRefreshListener {
            refresh()
            it.finishRefresh()
        }
        mSmartRefreshLayout?.setOnLoadMoreListener {
            if (isHaveMore) {
                mCurrentPage++
                getPageDatas(mCurrentPage)
                it.finishLoadMore()
            } else {
                it.finishLoadMoreWithNoMoreData()
            }

        }


    }

    open fun refresh() {
        clearData()
        mCurrentPage = 1
        isHaveMore = true
        isMore = true
        getPageDatas(mCurrentPage)
    }

    var mNoDataIv: ImageView? = null
    fun showNoData() {
        mNoDataIv = ImageView(mContext)
        mNoDataIv?.scaleType = ImageView.ScaleType.CENTER_INSIDE
        mNoDataIv?.setImageDrawable(resources.getDrawable(R.drawable.smart_nodata_icon))
        val linearParams = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT)
        mSmartFrameLayout?.addView(mNoDataIv, linearParams)
    }

    fun stopNoData() {
        if (mNoDataIv != null)
            mSmartFrameLayout?.removeView(mNoDataIv)
    }

    override fun initDatas() {
        getPageDatas(mCurrentPage)

    }

    override fun appendDatas(list: List<N>) {
        if (list.isNotEmpty() && mCurrentPage == 1)
            stopNoData()
        if (isHaveMore)
            super.appendDatas(list)
        isHaveMore = list.isNotEmpty() && isMore

    }
}