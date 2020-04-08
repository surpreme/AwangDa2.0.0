package com.aite.awangdalibrary.ui.activity.productdetails

import android.annotation.SuppressLint
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.BaseTConsumer
import com.valy.baselibrary.retrofit.GsonBuilders
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.LogUtils
import org.json.JSONObject
import java.util.*


/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class ProductDetailsKotlinPresenter : BasePresenterImpl<ProductDetailsKotlinContract.View>(), ProductDetailsKotlinContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getDatas(goods_id: String, lang_type: String, key: String) {
        RetrofitBuilder.build()
                .getGoodsInformation(goods_id, lang_type, key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)

                    }

                    override fun againLogIn() {
                        mView?.closeLoading()


                    }


                    override fun success(datas: JSONObject) {
                        val mGoodsInformationBean = GsonBuilders.fromJson(datas.toString(), GoodsInformationBean::class.java)
                        val specLists: MutableList<GoodsInformationBean.GoodsInfoBean.SpectListBean> = ArrayList<GoodsInformationBean.GoodsInfoBean.SpectListBean>()
                        try {
                            val spec_list = datas.getJSONObject("spec_list")
                            val keys: Iterator<*> = spec_list.keys()
                            while (keys.hasNext()) {
                                val key = keys.next() as String
                                val string = spec_list.getString(key)
                                specLists.add(GoodsInformationBean.GoodsInfoBean.SpectListBean(key, string))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        mGoodsInformationBean.goods_info.spec_list = specLists
                        mView?.getDatasSuccess(mGoodsInformationBean)
                        mView?.closeLoading()
                    }

                })
    }

    @SuppressLint("CheckResult")
    override fun getShopCardNum(key: String) {
        RetrofitBuilder.build()
                .onPostShopCardNumberData(key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {
                        mView?.showError(error)

                    }

                    override fun success(datas: JSONObject) {
                        val cart_goods_num: Int? = datas.optInt("cart_goods_num")
                        if (cart_goods_num != null) {
                            mView?.getShopCardNumSuccess(cart_goods_num.toString())
                        }
                    }

                })
    }

    @SuppressLint("CheckResult")
    override fun addCollect(key: String, fav_id: String, fav_type: String) {
        RetrofitBuilder.build()
                .onCollectGoods(key, fav_id, fav_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseTConsumer<String>() {
                    override fun error(error: String) {
                        mView?.showError(error)

                    }

                    override fun success(datas: String) {
                        LogUtils.d(datas)
                        mView?.collectSuccess()

                    }

                })
    }

    @SuppressLint("CheckResult")
    override fun cancelCollect(key: String, fav_id: String) {
        RetrofitBuilder.build()
                .onCancelCollectGoods(key, fav_id)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseTConsumer<String>() {
                    override fun error(error: String) {
                        mView?.showError(error)

                    }

                    override fun success(datas: String) {
                        LogUtils.d(datas)
                        mView?.cancelCollectSuccess()

                    }

                })
    }

    @SuppressLint("CheckResult")
    override fun addGoodsToCard(lang_type: String, goods_id: String, quantity: String, key: String) {
        RetrofitBuilder.build()
                .addGoodsToCard(lang_type, goods_id, quantity, key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseTConsumer<String>() {
                    override fun error(error: String) {
                        mView?.showError(error)

                    }

                    override fun success(datas: String) {
                        mView?.addGoodsToCardSuccess()

                    }

                })
    }


}