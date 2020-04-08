package com.aite.awangdalibrary.ui.activity.systeminformation

import android.annotation.SuppressLint
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.BaseTConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.LogUtils
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class SystemInformationKotlinPresenter : BasePresenterImpl<SystemInformationKotlinContract.View>(), SystemInformationKotlinContract.Presenter {
    @SuppressLint("CheckResult")
    override fun read(key: String, message_ids: String) {
        RetrofitBuilder.build().onReadMsg(key, message_ids)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseTConsumer<String>() {
                    override fun error(error: String) {
                        LogUtils.e(error)

                    }

                    override fun success(datas: String) {
                        if (datas == "1") {
                            mView?.read()
                            LogUtils.d("read$message_ids")
                        }
                    }

                })
    }
    /* @SuppressLint("CheckResult")
     override fun getHistory(key: String, lang_type: String) {
         RetrofitBuilder.build().getSearchHistory(key, lang_type)
                 .compose(RxScheduler.Flo_io_main())
                 .subscribe(object : BaseConsumer() {
                     override fun error(error: String) {
                         mView?.closeLoading()
                         LogUtils.e(error)

                     }

                     override fun success(datas: JSONObject) {
                         val history = datas.optJSONArray("history")
                         if (history != null) {
                             val historyList = Gson().fromJson<List<String>>(history.toString(), object : TypeToken<List<String>>() {}.type)
                             if (historyList != null)
                                 mView?.getSuccess((historyList as MutableList<String>))
                         }


                     }

                 })
     }

     @SuppressLint("CheckResult")
     override fun saveHistory(key: String, history: String, client: String, lang_type: String) {
         RetrofitBuilder.build().saveHistory(key, history, client, lang_type)
                 .compose(RxScheduler.Flo_io_main())
                 .subscribe({
                     val jsonObject = JSONObject(it!!.string())
                     val error_code = jsonObject.optInt("error_code")
                     if (error_code == 0) {
                         mView?.onSaveSuccess()
                     }
                 }, {
                     LogUtils.e(it)
                 })

     }*/

}