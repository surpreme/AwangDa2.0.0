package com.valy.baselibrary.retrofit

import android.annotation.SuppressLint
import com.valy.baselibrary.utils.LogUtils
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.operators.maybe.MaybeDoAfterSuccess
import okhttp3.ResponseBody
import org.json.JSONObject
import org.reactivestreams.Subscriber
import retrofit2.Retrofit

/**

 * @Auther: valy

 * @datetime: 2020/3/18

 * @desc:


 */
object FlowableListBuilder {
    //todo
    @SuppressLint("CheckResult")
    fun subscribes(t: Flowable<ResponseBody>, mResult: Result): Flowable<ResponseBody> {
        t.compose(RxScheduler.Flo_io_main()).subscribe({ responseBody ->
            val jsonObject = JSONObject(responseBody!!.string())
            val datas = jsonObject.optJSONObject("datas")
            if (datas != null) {
                val error = datas.optString("error")
                if (!error.isNullOrEmpty()) {
                    mResult.error(error)
                } else {
                    val hasmore: Boolean? = jsonObject.optBoolean("hasmore")
                    if (hasmore != null) {
                        mResult.success(datas, hasmore)
                    } else
                        mResult.success(datas, false)
                }
            }
        }, { throwable: Throwable ->
            LogUtils.e(throwable)
        })
        return t

    }

    interface Result {
        fun error(error: String)
        fun success(datas: JSONObject, isHas: Boolean)
    }


}