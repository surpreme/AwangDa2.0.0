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

 * @desc: 再次

 */
abstract class BaseConsumer : Consumer<ResponseBody?> {
    abstract fun error(error: String)
    abstract fun success(datas: JSONObject)
    open fun againLogIn() {}

    override fun accept(it: ResponseBody?) {
        try {
            val jsonObject = JSONObject(it!!.string())
            val datas = jsonObject.optJSONObject("datas")
            val token_expired = jsonObject.optJSONObject("token_expired")
            val login = jsonObject.optJSONObject("token_expired")
            if (datas != null) {
                val error = datas.optString("error")
                if (!error.isNullOrEmpty()) {
                    if (login != null || token_expired != null) {
                        againLogIn()
                        return
                    }
                    error(error)
                } else {
                    success(datas)

                }
            }
        } catch (e: Exception) {
            LogUtils.e(e)
        }


    }


}