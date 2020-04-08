package com.aite.awangdalibrary.ui.activity.coupon

import android.annotation.SuppressLint
import com.aite.a.activity.li.util.LogUtils
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.a.fargment.orderitemkotlin.OrderItemBean
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valy.baselibrary.retrofit.BaseListConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class OrderItemKotlinPresenter : BasePresenterImpl<OrderItemKotlinContract.View>(), OrderItemKotlinContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getOrderList(type: Int, curpage: Int, key: String, lang_type: String) {
        RetrofitBuilder.build().getOrderItem(type, curpage, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseListConsumer() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject, isHas: Boolean) {
                        val order_group_list = datas.optJSONArray("order_group_list")
                        if (order_group_list != null) {
                            val morder_group_list = Gson().fromJson<List<OrderItemBean>>(order_group_list.toString(), object : TypeToken<List<OrderItemBean>>() {}.type)
                            mView?.getOrderListSuccess(morder_group_list, isHas)
                            mView?.closeLoading()
                        }
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun sureGotGoods(order_id: String, key: String, lang_type: String) {
        RetrofitBuilder.build().sureGotGoods(order_id, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseListConsumer() {
                    override fun error(error: String) {
                        LogUtils.e(error)
                    }

                    override fun success(datas: JSONObject, isHas: Boolean) {
                        mView?.sureGotGoodsSuccess()
                    }
                })
    }

}