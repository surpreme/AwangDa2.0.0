package com.aite.a.activity.li.activity.truename

import android.annotation.SuppressLint
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface
import com.aite.a.activity.li.activity.BaseData
import com.aite.a.activity.li.activity.login.AreaCodeBean
import com.aite.a.activity.li.mvp.RetrofitClient
import com.aite.a.activity.li.util.LogUtils
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.valy.baselibrary.retrofit.RxScheduler
import okhttp3.MultipartBody
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class TrueNamePresenter : BasePresenterImpl<TrueNameContract.View>(), TrueNameContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getAreaCode(lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .getAreaCode(lang_type)
                .compose(RxScheduler.Flo_io_main())
                .filter { mAreaCodeBean: BaseData<AreaCodeBean> ->
                    if (!mAreaCodeBean.isSuccessed) {
                        mView?.showError(mAreaCodeBean.datas.error)
                        return@filter false
                    } else return@filter true
                }
                .map { mList: BaseData<AreaCodeBean> -> mList.datas }
                .filter { mErrorBean: AreaCodeBean ->
                    if (mErrorBean.error != null) {
                        mView?.showError(mErrorBean.error)
                        return@filter false
                    } else return@filter true
                }
                .subscribe({ mList: AreaCodeBean ->
                    mView?.getAreaCodeSuccess(mList)
                }) { throwable: Throwable ->
                    mView?.showError(throwable.message.toString())
                }
    }

    @SuppressLint("CheckResult")
    override fun commit(parts: List<MultipartBody.Part>) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onSureTrueName(parts)
                .compose(RxScheduler.Flo_io_main())
                .subscribe {
                    val jsonObject = JSONObject(it.string())
                    val code: String = jsonObject.optString("code")
                    if (!code.isNullOrEmpty() && code == "200") {
                        mView?.onCommitSuccess()
                    }
                    LogUtils.d(code)
                    val datas: JSONObject = jsonObject.optJSONObject("datas")
                    if (datas != null) {
                        val error = datas.optString("error")
                        if (error != null) {
                            mView?.showError(error)
                        }

                    }

                }

    }

    @SuppressLint("CheckResult")
    override fun CommitImg(parts: List<MultipartBody.Part>, type: Int) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onSureTrueNameImg(parts)
                .compose(RxScheduler.Flo_io_main())
                .subscribe {
                    val jsonObject = JSONObject(it.string())
                    val code: String = jsonObject.optString("code")
                    LogUtils.d(code)
                    val datas: JSONObject = jsonObject.optJSONObject("datas")
                    if (datas != null) {
                        val error = datas.optString("error")
                        if (!error.isNullOrEmpty()) {
                            mView?.showError(error)
                        } else {
                            val mPath = datas.optString("file_path")
                            mView?.onCommitImgSuccess(type, mPath)
                        }

                    }

                }
    }

    @SuppressLint("CheckResult")
    override fun onSureTrueNameInformation(key: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onSureTrueNameInformation(key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe {
                    val jsonObject = JSONObject(it.string())
                    val code: String = jsonObject.optString("code")
                    LogUtils.d(code)
                    val datas: JSONObject = jsonObject.optJSONObject("datas")
                    if (datas != null) {
                        val error = datas.optString("error")
                        if (!error.isNullOrEmpty()) {
                            mView?.showError(error)
                        } else {
                            val state = datas.optString("state")
                            mView?.onSureTrueNameInformation(state)
                            //状态 -1：未提交过认证信息 0：已提交认证申请，但未审核 1：已审核通过 2：审核失败
                        }

                    }

                }
    }

}