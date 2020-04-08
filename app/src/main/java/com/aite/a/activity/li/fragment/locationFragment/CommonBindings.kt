package com.aite.a.activity.li.fragment.locationFragment

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import com.aite.a.view.PullToRefreshLayout
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import me.goldze.mvvmhabit.base.BaseModel

/**

 * @Auther: liziyang

 * @datetime: 2020-01-16

 * @desc: 弃用

 */
class CommonBindings : BaseModel() {
    /**
     * 状态绑定，控制停止刷新
     */
    @BindingAdapter(value = ["refreshing", "moreLoading", "hasMore"], requireAll = false)
    fun bindSmartRefreshLayout(
            smartLayout: SmartRefreshLayout,
            refreshing: ObservableBoolean,
            moreLoading: ObservableBoolean,
            hasMore: ObservableBoolean) {
        if (refreshing.get()) smartLayout.finishRefresh()
        if (moreLoading.get()) smartLayout.finishLoadMore()
        smartLayout.setNoMoreData(hasMore.get())

    }

    /**
     * 控制自动刷新
     */
    @BindingAdapter(value = ["autoRefresh"])
    fun bindSmartRefreshLayout(smartLayout: SmartRefreshLayout, autoRefresh: ObservableBoolean) {
        if (autoRefresh.get()) smartLayout.autoRefresh()
    }

    @BindingAdapter(value = ["onRefreshListener", "onLoadMoreListener"], requireAll = false)
    fun bindListener(smartLayout: SmartRefreshLayout,
                     onRefreshListener: OnRefreshListener?,
                     loadMoreListener: OnLoadMoreListener?) {
        smartLayout.setOnRefreshListener(onRefreshListener)
        smartLayout.setOnLoadMoreListener(loadMoreListener)
    }

}