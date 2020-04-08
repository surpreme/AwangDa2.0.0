package com.aite.awangdalibrary.ui.activity.coupon

import android.annotation.SuppressLint
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.awangdalibrary.base.RetrofitInterfaces
import com.aite.awangdalibrary.ui.fragment.collectgoods.CollectGoodsBean
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valy.baselibrary.retrofit.BaseListConsumer
import com.valy.baselibrary.retrofit.BaseTConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.LogUtils
import io.reactivex.functions.Consumer
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class CollectGoodsPresenter : BasePresenterImpl<CollectGoodsContract.View>(), CollectGoodsContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getDatas(curpage: Int, fav_type: String, key: String, lang_type: String) {
        mView?.showLoading()
        RetrofitBuilder.build()
                .getCollectDatas(curpage, fav_type, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseListConsumer() {
                    override fun error(error: String) {
                        mView?.showError(error)
                        mView?.closeLoading()

                    }

                    override fun success(datas: JSONObject, isHas: Boolean) {
                        val mList = datas.optJSONArray("favorites_list")
                        if (mList != null) {
                            val mCollectGoodsBean =
                                    Gson().fromJson<List<CollectGoodsBean>>(mList.toString(),
                                            object : TypeToken<List<CollectGoodsBean>>() {}.type)
                            if (mCollectGoodsBean != null) {
                                mView?.getDatasSuccess(mCollectGoodsBean, isHas)
                            }
                        }
                        mView?.closeLoading()
                    }
                }, Consumer {
                    LogUtils.e(it)
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

}