package com.aite.a.activity

import android.annotation.SuppressLint
import android.graphics.Rect
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface
import com.aite.a.activity.li.activity.login.LogInActivity
//import com.valy.baselibrary.retrofit.RetrofitClient

import com.aite.a.activity.li.util.LogUtils
import com.aite.a.activity.li.util.PopupWindowUtil
import com.aite.a.adapter.FixPasswordUIRecyAdapter
import com.aite.a.base.Mark
import com.aite.a.bean.FixPasswordUIBean
import com.aite.a.utils.SystemUtil
import com.aite.a.utils.ToastUtils
import com.aite.mainlibrary.basekotlin.BaseActivity
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.retrofit.RetrofitClients
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.OnClickLstenerInterface
import kotlinx.android.synthetic.main.recycler_layout.*
import org.json.JSONObject

/**
 * @Auther: valy
 * @datetime: 2020/3/5
 * @desc:
 */
class FixPasswordActivity : BaseActivity() {
    private var fixPasswordUIRecyAdapter: FixPasswordUIRecyAdapter? = null
    private var datasBean = ArrayList<FixPasswordUIBean>()

    override fun getLayoutId(): Int {
        return R.layout.recycler_layout
    }

    override fun initViews() {
        setStatusBar(0)
        recycler_view.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler_view.setBackgroundColor(resources.getColor(R.color.gray2))
        fixPasswordUIRecyAdapter = FixPasswordUIRecyAdapter(mContext, datasBean)
        fixPasswordUIRecyAdapter?.mOnCommitInterface = OnClickLstenerInterface.OnCommitInterface { mDatasBean ->
            if (mDatasBean[2].outStr != mDatasBean[3].outStr) {
//                ToastUtils.showToast(mContext, "请检查输入的密码")
                PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                        this,
                        null,
                        getString(R.string.Pleasecheckthepasswordentered),
                        null
                ) { v: View? ->
                    PopupWindowUtil.getmInstance().dismissPopWindow()
                    onBackPressed()
                }
            } else {
                runOnUiThread(Runnable {
                    fixPassword(Mark.State.UserKey, mDatasBean[2].outStr, mDatasBean[1].outStr, "zh_cn")
                })
            }

        }
        fixPasswordUIRecyAdapter?.toolBarClickInterface = object : OnClickLstenerInterface.OnToolBarClickInterface {
            override fun rightClick(v: View?) {
                //TODO
            }

            override fun back() {
                onBackPressed()
            }
        }
        recycler_view.adapter = fixPasswordUIRecyAdapter
        //设置分割线 默认1dp
        if (recycler_view.itemDecorationCount == 0) {
            recycler_view.addItemDecoration(object : BaseItemDecoration(mContext) {
                override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
                    //设置间距 从0开始 从上方开始算
                    if (position == count - 1) {
                        rect.top = SystemUtil.dp2px(mContext, 60f)
                        rect.left = SystemUtil.dp2px(mContext, 10f)
                        rect.right = SystemUtil.dp2px(mContext, 10f)
                    }
                    if (position == 0) {
                        rect.bottom = SystemUtil.dp2px(mContext, 5f)

                    }
                }

                override fun doRule(position: Int, rect: Rect) {
                    //|| position == mAdapter.itemCount - 2
                    //画线 从下方开始算 加上索引从0开始 所以+2 mAdapter可以获取当前recy的适配器     rect.left = rect.right 就不会绘制当前线
                    if (position == 0) {
                        rect.bottom += SystemUtil.dp2px(mContext, 10f)
                    }
                }
            })
        }
    }

    ///https://daluxmall.com/mobile/index.php?act=member_index&op=change_pwd
    override fun initDatas() {
        for (index in 0..4) {
            val f = FixPasswordUIBean()
            when (index) {
                0 -> {
                    f.centerBarTitle = getString(R.string.Changepassword)
                }
                1 -> {
                    f.hintTitle = getString(R.string.OldPWD)
                    f.hintContent = getString(R.string.Pleaseinputoldpwd)
                }
                2 -> {
                    f.hintTitle = getString(R.string.NewPWD)
                    f.hintContent = getString(R.string.Pleaseinputnewpwd)
                }
                3 -> {
                    f.hintTitle = getString(R.string.PasswordAgain)
                    f.hintContent = getString(R.string.Pleaseenternewpasswordagain)
                }
                4 -> {
                    f.bottomStr = getString(R.string.Commitmodify)
                }
            }
            datasBean.add(f)

        }
        fixPasswordUIRecyAdapter?.notifyDataSetChanged()
    }

    @SuppressLint("CheckResult")
    fun fixPassword(key: String?, new_password: String?, old_password: String?, lang_type: String?) {
        RetrofitClients.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onFixPassword(key, new_password, old_password, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        if (code == "200") {
                            val successObject: String = jsonObject.optString("datas")
                            if (successObject == "1")
                                PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                                        this,
                                        null,
                                        getString(R.string.Modifysuccessed),
                                        null
                                ) { v: View? ->
                                    PopupWindowUtil.getmInstance().dismissPopWindow()
                                    startActivity(LogInActivity::class.java)
                                }
                        } else {
                            val errorObject = jsonObject.optJSONObject("datas")
                            if (errorObject != null) {
                                val error = errorObject.optString("error")
                                if (error != null && !TextUtils.isEmpty(error)) {
                                    ToastUtils.showToast(mContext, error)
                                    LogUtils.e(error)
                                    PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                                            this,
                                            null,
                                            error,
                                            null
                                    ) { v: View? ->
                                        PopupWindowUtil.getmInstance().dismissPopWindow()
                                        onBackPressed()
                                    }
                                }
                            }
                        }
                    } else {
                        val errorObject = jsonObject.optJSONObject("datas")
                        if (errorObject != null) {
                            val error = errorObject.optString("error")
                            if (error != null && !TextUtils.isEmpty(error)) {
                                LogUtils.e(error)
                                ToastUtils.showToast(mContext, error)
                                PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                                        this,
                                        null,
                                        error,
                                        null
                                ) { v: View? -> PopupWindowUtil.getmInstance().dismissPopWindow() }
                            }
                        }

                    }
                }) { throwable: Throwable ->
                    LogUtils.e(throwable.message)
                }
    }
}