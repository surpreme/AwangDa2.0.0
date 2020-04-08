package com.aite.mainlibrary.basekotlin

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valy.baselibrary.dialogfragment.LoadingDialogFragment
import com.valy.baselibrary.utils.LogUtils
import java.lang.reflect.ParameterizedType

/**

 * @Auther: liziyang

 * @datetime: 2020-01-19

 * @desc:

 */
abstract class BaseMVPActivity<V : BaseView, T : BasePresenterImpl<V>> : BaseActivity(), BaseView {
    open fun onDestroys() {}
    var mPresenter: T? = null

    override fun getContext(): Context {
        return this
    }

    override fun Untoken() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initExtra() {
        super.initExtra()
        mPresenter = getInstance<T>(this, 1)
        mPresenter!!.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null)
            mPresenter!!.detachView()
        onDestroys()
    }

    fun <T> getInstance(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                    .newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

        return null
    }

    var mLoadingDialogFragment: LoadingDialogFragment? = null
    override fun showLoading() {
        mLoadingDialogFragment = LoadingDialogFragment()
        mLoadingDialogFragment?.show(supportFragmentManager, "LoadingDialogFragment")
    }

    override fun onStop() {
        super.onStop()
        closeLoading()

    }

    override fun closeLoading() {
        if (mLoadingDialogFragment != null) {
            mLoadingDialogFragment?.dismissAllowingStateLoss()
        }
    }

    override fun showError(msg: String) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show()
        LogUtils.e(msg + this.localClassName)
    }


}