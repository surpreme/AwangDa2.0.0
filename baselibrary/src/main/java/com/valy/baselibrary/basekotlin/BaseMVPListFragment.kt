package com.valy.baselibrary.basekotlin

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.mainlibrary.basekotlin.BaseFragment
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.aite.mainlibrary.basekotlin.BaseView
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.dialogfragment.LoadingDialogFragment
import com.valy.baselibrary.utils.LogUtils
import com.valy.baselibrary.utils.OnClickLstenerInterface
import java.lang.reflect.ParameterizedType
import java.util.ArrayList

/**

 * @Auther: valy

 * @datetime: 2020/3/13

 * @desc:

 */
abstract class BaseMVPListFragment<V : BaseView, T : BasePresenterImpl<V>, N> : BaseFragment(), BaseView {
    protected var recycler_view: RecyclerView? = null
    abstract fun setAdapter(): BaseRecyAdapter<N>?
    open fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    abstract fun addItemDecoration(): BaseItemDecoration
    abstract fun getRecyclerViewId(): Int
    open fun onDestroys(){}
    var mPresenter: T? = null
    var mAdapter: BaseRecyAdapter<N>? = null
    protected var mDatasList = ArrayList<N>()
    override fun Untoken() {

    }

    override fun initViews(view: View) {
        recycler_view = view.findViewById(getRecyclerViewId())
        recycler_view?.layoutManager = setLayoutManager()
        recycler_view?.adapter = setAdapter().also { mAdapter = it }
        if (recycler_view?.itemDecorationCount == 0) {
            recycler_view?.addItemDecoration(addItemDecoration())
        }

    }

    open fun appendDatas(list: List<N>) {
        mDatasList.addAll(list)
        mAdapter?.appendDatas(list)
    }

    open fun appendDatas() {
        mAdapter?.appendDatas(mDatasList)
        mAdapter?.notifyDataSetChanged()
    }

    fun refreshDatas() {
        mAdapter?.refreshDatas(mDatasList)
    }

    fun clearData() {
        mDatasList.clear()
        mAdapter?.clearDatas()
    }

    override fun initExtra() {
        super.initExtra()
        mPresenter = getInstance<T>(this, 1)
        mPresenter!!.attachView(this as V)

    }


    override fun getContext(): Context = activity!!

    override fun showError(msg: String) {
        showToast(msg)
        LogUtils.e(msg)
    }

    var mLoadingDialogFragment: LoadingDialogFragment? = null
    override fun showLoading() {
        mLoadingDialogFragment = LoadingDialogFragment()
        mLoadingDialogFragment?.show(childFragmentManager, "LoadingDialogFragment")
    }

    override fun onStop() {
        super.onStop()
        closeLoading()
    }

    override fun closeLoading() {
        if (mLoadingDialogFragment != null) {
            if (mLoadingDialogFragment!!.isResumed)
                mLoadingDialogFragment?.dismiss()
        }


    }

    fun <T> getInstance(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                    .newInstance()
        } catch (e: java.lang.InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }
}