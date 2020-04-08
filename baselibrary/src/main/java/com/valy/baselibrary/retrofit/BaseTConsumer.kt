package com.valy.baselibrary.retrofit

import com.valy.baselibrary.utils.LogUtils
import io.reactivex.Flowable
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
abstract class BaseTConsumer<T> : Consumer<ResponseBody?> {
    abstract fun error(error: String)
    abstract fun success(datas: T)

    override fun accept(it: ResponseBody?) {
        try {
            val jsonObject = JSONObject(it!!.string())
            val datas = jsonObject.opt("datas") as T
            val datasObject = jsonObject.optJSONObject("datas")
            if (datasObject != null) {
                val error = datasObject.optString("error")
                if (!error.isNullOrEmpty()) {
                    error(error)
                } else {
                    success(datas)

                }
            } else {
                success(datas)
            }
        } catch (e: Exception) {
            LogUtils.e(e)
        }


    }


}