package com.aite.a.activity.li.activity.surePayListKotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.text.TextUtils
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface
import com.aite.a.activity.li.mvp.RetrofitClient
import com.aite.a.activity.li.util.LogUtils
import com.aite.a.bean.PayListBean
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.valy.baselibrary.retrofit.RxScheduler
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class SurePayListPresenter : BasePresenterImpl<SurePayListContract.View>(), SurePayListContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getPayListTable(key: String, pay_sn: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onPostPayList(key, pay_sn, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        if (code == "200") {
                            if (datas != null) {
                                val payListBean = Gson().fromJson(datas.toString(), PayListBean::class.java)
                                (mView?.getContext() as Activity).runOnUiThread {
                                    mView?.onPayListTableSuccess(payListBean)
                                }
                            }
                        } else {
                            if (datas != null) {
                                val error = datas.optString("error")
                                if (error != null && !TextUtils.isEmpty(error)) {
                                    mView?.showError(error)
                                }
                            }
                        }
                    }
                    if (error_code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        if (datas != null) {
                            val error = datas.optString("error")
                            if (error != null && !TextUtils.isEmpty(error)) {
                                mView?.showError(error)
                            }
                        }
                    }
                }) { throwable: Throwable ->
                    LogUtils.e(throwable.message)
                }

    }

    @SuppressLint("CheckResult")
    override fun getPayListTable2(key: String, pay_sn: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onPostPayList2(key, pay_sn, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        if (code == "200") {
                            if (datas != null) {
                                val payListBean = Gson().fromJson(datas.toString(), PayListBean::class.java)
                                (mView?.getContext() as Activity).runOnUiThread {
                                    mView?.onPayListTableSuccess(payListBean)
                                }
                            }
                        } else {
                            if (datas != null) {
                                val error = datas.optString("error")
                                if (error != null && !TextUtils.isEmpty(error)) {
                                    mView?.showError(error)
                                }
                            }
                        }
                    }
                    if (error_code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        if (datas != null) {
                            val error = datas.optString("error")
                            if (error != null && !TextUtils.isEmpty(error)) {
                                mView?.showError(error)
                            }
                        }
                    }
                }) { throwable: Throwable ->
                    LogUtils.e(throwable.message)
                }
    }

    //钱包支付
    @SuppressLint("CheckResult")
    override fun getPayMoneyCard(key: String, pay_sn: String, password: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onPostPayMoneyCard(key, pay_sn, password, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        val datasString = jsonObject.optString("datas")
                        if (code == "200") {
                            if (datasString != null) {
                                (mView?.getContext() as Activity).runOnUiThread {
                                    mView?.onPayMoneyCardSuccess(datasString)
                                }

                            }
                        } else {
                            if (datas != null) {
                                val error = datas.optString("error")
                                if (null != error && !TextUtils.isEmpty(error)) mView?.onPayMoneyCardFail(error)
                            }
                        }
                    }
                    if (error_code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        if (datas != null) {
                            val error = datas.optString("error")
                            if (null != error && !TextUtils.isEmpty(error)) mView?.showError(error)
                        }
                    }
                }) { throwable: Throwable -> LogUtils.e(throwable.message) }
    }

    //pipay支付
    @SuppressLint("CheckResult")
    override fun getPayPipay(key: String, pay_sn: String, payment_code: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onPostPayPipay(key, pay_sn, payment_code, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        val datasString = jsonObject.optString("datas")
                        if (code == "200") {
                            if (datasString != null) {
                                LogUtils.d(datasString)
                                mView?.onPayPipay(datasString)

                            }
                        } else {
                            if (datas != null) {
                                val error = datas.optString("error")
                                if (null != error && !TextUtils.isEmpty(error)) mView?.showError(error)
                            }
                        }
                    }
                    if (error_code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        if (datas != null) {
                            val error = datas.optString("error")
                            if (null != error && !TextUtils.isEmpty(error)) mView?.showError(error)
                        }
                    }
                }) { throwable: Throwable -> LogUtils.e(throwable.message) }
    }

    @SuppressLint("CheckResult")
    override fun getPayPipay2(key: String, pay_sn: String, payment_code: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onPostPayPipay2(key, pay_sn, payment_code, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        val datasString = jsonObject.optString("datas")
                        if (code == "200") {
                            if (datasString != null) {
                                LogUtils.d(datasString)
                                mView?.onPayPipay(datasString)

                            }
                        } else {
                            if (datas != null) {
                                val error = datas.optString("error")
                                if (null != error && !TextUtils.isEmpty(error)) mView?.showError(error)
                            }
                        }
                    }
                    if (error_code != null) {
                        val datas = jsonObject.optJSONObject("datas")
                        if (datas != null) {
                            val error = datas.optString("error")
                            if (null != error && !TextUtils.isEmpty(error)) mView?.showError(error)
                        }
                    }
                }) { throwable: Throwable -> LogUtils.e(throwable.message) }
    }

}