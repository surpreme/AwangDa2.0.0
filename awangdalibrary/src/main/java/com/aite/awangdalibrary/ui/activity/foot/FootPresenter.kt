package com.aite.awangdalibrary.ui.activity.foot

import android.annotation.SuppressLint
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.BaseListConsumer
import com.valy.baselibrary.retrofit.BaseTConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
class FootPresenter : BasePresenterImpl<FootContract.View>(), FootContract.Presenter {
    @SuppressLint("CheckResult")
    override fun getList(curpage: Int, key: String, lang_type: String) {
        RetrofitBuilder.build().getFootList(curpage, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseListConsumer(false) {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: JSONObject, isHas: Boolean) {
                        val list = datas.optJSONArray("list")
                        if (list != null) {
                            val list = Gson().fromJson<List<FootListBean>>(list.toString(), object : TypeToken<List<FootListBean>>() {}.type)
                            mView?.getListSuccess(list, isHas)
                            mView?.closeLoading()
                        }
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun deleteFoot(postion: Int, goods_id: String, key: String, lang_type: String) {
        RetrofitBuilder.build().deleteFoot(goods_id, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseTConsumer<String>() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: String) {
                        mView?.deleteSuccess(postion)
                        mView?.closeLoading()
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun deleteMoreFoot(goods_ids: String, key: String, lang_type: String) {
        RetrofitBuilder.build().deleteMoreFoot(goods_ids, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(@SuppressLint("CheckResult")
                object : BaseTConsumer<String>() {
                    override fun error(error: String) {
                        mView?.closeLoading()
                        mView?.showError(error)
                    }

                    override fun success(datas: String) {
                        mView?.deleteMoreSuccess()
                        mView?.closeLoading()
                    }
                })
    }

}