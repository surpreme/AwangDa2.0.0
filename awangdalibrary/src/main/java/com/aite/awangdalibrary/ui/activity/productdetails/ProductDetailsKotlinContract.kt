package com.aite.awangdalibrary.ui.activity.productdetails

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class ProductDetailsKotlinContract {
    interface View : BaseView {
        fun getDatasSuccess(mGoodsInformationBean: GoodsInformationBean)
        fun getShopCardNumSuccess(s: String)
        fun addGoodsToCardSuccess()
        fun collectSuccess()
        fun cancelCollectSuccess()
    }

    interface Presenter : BasePresenter<View> {
        fun getDatas(goods_id: String, lang_type: String, key: String)
        fun getShopCardNum(key: String)
        fun addCollect(key: String, fav_id: String, fav_type: String)
        fun cancelCollect(key: String, fav_id: String)
        fun addGoodsToCard(lang_type: String,
                           goods_id: String,
                           quantity: String,
                           key: String)
    }

}