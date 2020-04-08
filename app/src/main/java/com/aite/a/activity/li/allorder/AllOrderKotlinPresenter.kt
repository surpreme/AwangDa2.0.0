package com.aite.awangdalibrary.ui.activity.coupon

import android.annotation.SuppressLint
import com.aite.a.activity.li.util.LogUtils
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.BaseListConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class AllOrderKotlinPresenter : BasePresenterImpl<AllOrderKotlinContract.View>(), AllOrderKotlinContract.Presenter {
    @SuppressLint("CheckResult")
    override fun cancelOrder(index: Int, order_id: String, key: String, lang_type: String) {
        RetrofitBuilder.build().cancelOrder(order_id, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject) {
                        mView?.cancelOrderSuccess(index)
                        mView?.closeLoading()
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun sureGotGoods(index: Int, order_id: String, key: String, lang_type: String) {
        RetrofitBuilder.build().sureGotGoods(order_id, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject) {
                        mView?.sureGotGoodsSuccess(index)
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun deleteGoods(index: Int, order_id: String, key: String, lang_type: String) {
        RetrofitBuilder.build().deleteOrderGoods(order_id, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject) {
                        mView?.sureGotGoodsSuccess(index)
                    }
                })
    }
}