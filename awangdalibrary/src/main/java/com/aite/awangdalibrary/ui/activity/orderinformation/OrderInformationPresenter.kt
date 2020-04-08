package com.aite.awangdalibrary.ui.activity.orderinformation

import android.annotation.SuppressLint
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.GsonBuilder
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.GsonBuilders
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.LogUtils
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class OrderInformationPresenter : BasePresenterImpl<OrderInformationContract.View>(), OrderInformationContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getDatas(language_type: String, lang_type: String, order_id: String, key: String) {
        RetrofitBuilder.build().onOrderInformation(language_type, lang_type, order_id, key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {

                    }

                    override fun success(datas: JSONObject) {
                        val mOrderInformationDatasBean = GsonBuilders.fromJson(datas.toString(), OrderInformationDatasBean::class.java)
                        mView?.getDatasSuccess(mOrderInformationDatasBean)
                        mView?.closeLoading()
                    }

                })

    }

}