package com.aite.awangdalibrary.base

import com.valy.baselibrary.retrofit.RetrofitClients


/**

 * @Auther: valy

 * @datetime: 2020/3/20

 * @desc:

 */
 object RetrofitBuilder {

    fun build(): RetrofitInterfaces {
        return RetrofitClients.getInstance().retrofit.create(RetrofitInterfaces::class.java)
    }
}