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
abstract class BaseStringConsumer
//    : Consumer<ResponseBody?> {
//    abstract fun error(error: String)
//    abstract fun success(datas: String)
//
//    override fun accept(it: ResponseBody?) {
//        val jsonObject = JSONObject(it!!.string())
//        val datas = jsonObject.optJSONObject("datas")
//        val datasString = jsonObject.optString("datas")
//        if (datas != null) {
//            val error = datas.optString("error")
//            if (!error.isNullOrEmpty()) {
//                error(error)
//            }
//        } else {
//            if (!datasString.isNullOrEmpty())
//                success(datasString)
//
//        }
//
//    }


//}