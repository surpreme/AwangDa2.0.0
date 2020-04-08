package com.valy.baselibrary.retrofit

import com.google.gson.GsonBuilder
import com.valy.baselibrary.gson.NullStringEmptyTypeAdapterFactory


/**

 * @Auther: valy

 * @datetime: 2020/3/19

 * @desc:

 */
object GsonBuilders {
    fun <T> fromJson(json: String, classOfT: Class<T>): T {
        return GsonBuilder().registerTypeAdapterFactory(NullStringEmptyTypeAdapterFactory()).create().fromJson<T>(json, classOfT)
    }

}