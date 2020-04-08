package com.aite.a.activity.li.fragment.rechargedetails

import android.annotation.SuppressLint
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface
import com.aite.a.activity.li.mvp.RetrofitClient
import com.aite.a.activity.li.util.LogUtils
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valy.baselibrary.retrofit.RxScheduler
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class RechargeDetailsPresenter : BasePresenterImpl<RechargeDetailsContract.View>(), RechargeDetailsContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getList(key: String, pagesize: Int, curpage: Int, pdr_sn: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .getRechargeDetailsList(key, pagesize, curpage, lang_type)//, pdr_sn
                .compose(RxScheduler.Flo_io_main())
                .subscribe {
                    LogUtils.d("ssdsdsdsds")
                    val jsonObject = JSONObject(it.string())
                    val code: String = jsonObject.optString("code")
                    LogUtils.d(code)
                    val datas: JSONObject = jsonObject.optJSONObject("datas")
                    if (datas != null) {
                        val error = datas.optString("error")
                        if (!error.isNullOrEmpty()) {
                            mView?.showError(error)
                        } else {
                            val jsonArry = datas.optJSONArray("list")
                            val hasmore = jsonObject.optBoolean("hasmore")
                            val member_info = datas.optJSONObject("member_info")
                            val x = member_info.optString("available_predeposit")
                            val y = member_info.optString("freeze_predeposit")
                            if (x != null && y != null)
                                mView?.getInformation(x, y)
                            val rechargeDetailsBean: MutableList<RechargeDetailsBean> =
                                    Gson().fromJson<MutableList<RechargeDetailsBean>>(
                                            jsonArry.toString(),
                                            object : TypeToken<MutableList<RechargeDetailsBean?>?>() {}.type)
                            mView?.getListSuccess(rechargeDetailsBean, hasmore)
                        }

                    }

                }

    }


}