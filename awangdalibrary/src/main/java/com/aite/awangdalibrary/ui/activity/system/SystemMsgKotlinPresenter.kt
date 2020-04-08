package com.aite.awangdalibrary.ui.activity.system

import android.annotation.SuppressLint
import android.util.Log
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.BaseListConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.LogUtils
import io.reactivex.functions.Consumer
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class SystemMsgKotlinPresenter : BasePresenterImpl<SystemMsgKotlinContract.View>(), SystemMsgKotlinContract.Presenter {

    @SuppressLint("CheckResult")
    override fun getCommon(curpage: Int, key: String, lang_type: String) {
        RetrofitBuilder.build().getCommonMsg(curpage, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseListConsumer() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject, isHas: Boolean) {
                        val message_array = datas.optJSONArray("message_array")
                        val list = Gson().fromJson<List<SystemMsgKotlinBean>>(message_array?.toString(), object : TypeToken<List<SystemMsgKotlinBean>>() {}.type)
                        mView?.getSuccess(list, isHas)
                    }

                }, Consumer {
                    LogUtils.e(it)

                })

    }

    @SuppressLint("CheckResult")
    override fun getSystem(curpage: Int, key: String, lang_type: String) {
        RetrofitBuilder.build().getSystemMsg(curpage, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseListConsumer() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject, isHas: Boolean) {
                        val message_array = datas.optJSONArray("message_array")
                        val list = Gson().fromJson<List<SystemMsgKotlinBean>>(message_array?.toString(), object : TypeToken<List<SystemMsgKotlinBean>>() {}.type)
                        mView?.getSuccess(list, isHas)
                    }

                }, Consumer {
                    LogUtils.e(it)

                })

    }

    @SuppressLint("CheckResult")
    override fun getSide(curpage: Int, key: String, lang_type: String) {
        RetrofitBuilder.build().getSideMsg(curpage, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseListConsumer() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject, isHas: Boolean) {
                        val message_array = datas.optJSONArray("message_array")
                        val list = Gson().fromJson<List<SystemMsgKotlinBean>>(message_array?.toString(), object : TypeToken<List<SystemMsgKotlinBean>>() {}.type)
                        mView?.getSuccess(list, isHas)
                    }

                }, Consumer {
                    LogUtils.e(it)

                })
    }

}