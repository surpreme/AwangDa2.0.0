package com.aite.a.activity.li.basekotlin

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.aite.mainlibrary.basekotlin.BaseView
import com.valy.baselibrary.utils.OnClickLstenerInterface
import java.util.*

/**

 * @Auther: liziyang

 * @datetime: 2020-01-19

 * @desc:

 */
abstract class BaseMVPListActivity<V : BaseView, T : BasePresenterImpl<V>, N> : BaseMVPActivity<V, T>(), BaseView {
    protected var recycler_view: RecyclerView? = null
    abstract fun setAdapter(): BaseRecyAdapter<N>?
    open fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    abstract fun addItemDecoration(): ItemDecoration
    abstract fun getRecyclerViewId(): Int
    open fun rightClick() {}
    var mAdapter: BaseRecyAdapter<N>? = null
    protected var mDatasList = ArrayList<N>()


    override fun getContext(): Context {
        return this
    }

    override fun initViews() {
        recycler_view = findViewById(getRecyclerViewId())
        recycler_view?.layoutManager = setLayoutManager()
        recycler_view?.adapter = setAdapter().also { mAdapter = it }
        if (recycler_view?.itemDecorationCount == 0) {
            recycler_view?.addItemDecoration(addItemDecoration())
        }
        mAdapter?.toolBarClickInterface = object : OnClickLstenerInterface.OnToolBarClickInterface {
            override fun rightClick(v: View?) {
                //TODO
                rightClick()
            }

            override fun back() {
                onBackPressed()
            }
        }
    }

    /**
     * 为追加数据而设计
     * 同时可以使用baseRecyAdpater fixdatas刷新数据
     */
    open fun appendDatas(list: List<N>) {
        mDatasList.addAll(list)
        mAdapter?.appendDatas(list)
    }

    /**
     * 为多种类item设计的静态页面数据
     */
    fun appendDatas() {
        mAdapter?.appendDatas(mDatasList)
        mAdapter?.notifyDataSetChanged()
    }

    /**
     * 重置数据
     */
    fun refreshDatas() {
        mAdapter?.refreshDatas(mDatasList)
    }

    fun clearData() {
        mDatasList.clear()
        mAdapter?.clearDatas()
    }


}