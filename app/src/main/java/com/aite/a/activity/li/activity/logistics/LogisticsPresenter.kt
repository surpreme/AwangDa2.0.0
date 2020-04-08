package com.aite.a.activity.li.activity.logistics

import android.annotation.SuppressLint
import android.text.TextUtils
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface
import com.aite.a.activity.li.mvp.RetrofitClient
//import com.aite.a.activity.li.mvp.RetrofitClient
import com.aite.a.activity.li.util.LogUtils
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.valy.baselibrary.retrofit.RxScheduler
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class LogisticsPresenter : BasePresenterImpl<LogisticsContract.View>(), LogisticsContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getOrderInformation(key: String, order_id: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .getOrderInformation(key, order_id, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        if (code == "200") {
                            val datasObject = jsonObject.optJSONObject("datas")
                            val mLogisticsNetBean:LogisticsNetBean=Gson().fromJson<LogisticsNetBean>(datasObject.toString(),LogisticsNetBean::class.java)
                            mView?.onOrderInformationSuccess(mLogisticsNetBean)

                        } else {
                            val errorObject = jsonObject.optJSONObject("datas")
                            if (errorObject != null) {
                                val error = errorObject.optString("error")
                                if (error != null && !TextUtils.isEmpty(error)) {
                                    mView?.showError(error)
                                    LogUtils.e(error)

                                }
                            }
                        }
                    } else {
                        val errorObject = jsonObject.optJSONObject("datas")
                        if (errorObject != null) {
                            val error = errorObject.optString("error")
                            if (error != null && !TextUtils.isEmpty(error)) {
                                mView?.showError(error)
                                LogUtils.e(error)

                            }
                        }

                    }
                }) { throwable: Throwable ->
                    LogUtils.e(throwable.message)
                }
    }

}