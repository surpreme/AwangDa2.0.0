package com.aite.awangdalibrary.base

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.*

/**

 * @Auther: valy

 * @datetime: 2020/3/18

 * @desc:

 */
interface RetrofitInterfaces {
    @GET("index.php?act=member_order&op=show_order")
    fun onOrderInformation(
            @Query("language_type") language_type: String,
            @Query("lang_type") lang_type: String,
            @Query("order_id") order_id: String,
            @Query("key") key: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=goods&op=goods_detail")
    fun getGoodsInformation(
            @Query("goods_id") goods_id: String,
            @Query("lang_type") lang_type: String,
            @Query("key") key: String?
    ): Flowable<ResponseBody>

    @POST("index.php?act=member_cart&op=cart_add")
    @FormUrlEncoded
    fun addGoodsToCard(
            @Field("lang_type") lang_type: String,
            @Field("goods_id") goods_id: String,
            @Field("quantity") quantity: String,
            @Field("key") key: String
    ): Flowable<ResponseBody>

    /**
     *    params.addBodyParameter("lang_type", BaseConstant.CURRENTLANGUAGE);
    params.addBodyParameter("key", State.UserKey);
    params.addBodyParameter("order_id", order_id);
     */
    @POST("index.php?act=member_order&op=order_cancel")
    @FormUrlEncoded
    fun cancelOrder(
            @Field("order_id") order_id: String,
            @Field("key") key: String,
            @Field("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=member_order&op=order_list")
    fun getOrderItem(
            @Query("type") type: Int,
            @Query("curpage") curpage: Int,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    // index.php?act=member_goodsbrowse&op=list_new
    @GET("index.php?act=member_goodsbrowse&op=list_new")
    fun getFootList(
            @Query("curpage") curpage: Int,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>


    @GET("index.php?act=member_order&op=order_receive")
    fun sureGotGoods(
            @Query("order_id") order_id: String,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=member_order&op=order_del")
    fun deleteOrderGoods(
            @Query("order_id") order_id: String,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>


    @POST("index.php?act=pointvoucher&op=voucherexchange_save")
    @FormUrlEncoded
    fun receiveCoupon(
            @Field("key") key: String,
            @Field("vid") vid: String

    ): Flowable<ResponseBody>

    @POST("index.php?act=member_goodsbrowse&op=del_one")
    @FormUrlEncoded
    fun deleteFoot(
            @Field("goods_id") goods_id: String,
            @Field("key") key: String,
            @Field("lang_type") lang_type: String

    ): Flowable<ResponseBody>

    @POST("index.php?act=member_goodsbrowse&op=del_bat")
    @FormUrlEncoded
    fun deleteMoreFoot(
            @Field("goods_ids") goods_id: String,//商品拼接字符串,逗号隔开
            @Field("key") key: String,
            @Field("lang_type") lang_type: String

    ): Flowable<ResponseBody>

    @POST("index.php?act=member_index&op=member_history_save")
    @FormUrlEncoded
    fun saveHistory(
            @Field("key") key: String,
            @Field("history") history: String,
            @Field("client") client: String,
            @Field("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=member_message&op=message")
    fun getCommonMsg(
            @Query("curpage") curpage: Int,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=member_message&op=systemmsg")
    fun getSystemMsg(
            @Query("curpage") curpage: Int,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=member_message&op=personalmsg")
    fun getSideMsg(
            @Query("curpage") curpage: Int,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=member_index&op=member_history")
    fun getSearchHistory(
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>

    //index.php?act=member_favorites&op=favorites_list
    @GET("index.php?act=member_favorites&op=favorites_list")
    fun getCollectDatas(
            @Query("curpage") curpage: Int,
            @Query("fav_type") fav_type: String,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>


    /**
     * 当前购物车数量
     */
    @POST("index.php?act=member_cart&op=cart_num")
    @FormUrlEncoded
    fun onPostShopCardNumberData(
            @Field("key") key: String
    ): Flowable<ResponseBody>

    //收藏
    @POST("index.php?act=member_favorites&op=favorites_add")
    @FormUrlEncoded
    fun onCollectGoods(
            @Field("key") key: String,
            @Field("fav_id") fav_id: String,
            @Field("fav_type") fav_type: String
    ): Flowable<ResponseBody>

    @POST("index.php?act=member_message&op=message_open")
    @FormUrlEncoded
    fun onReadMsg(
            @Field("key") key: String,
            @Field("message_ids") message_ids: String
    ): Flowable<ResponseBody>

    @POST("index.php?act=member_favorites&op=favorites_del")
    @FormUrlEncoded
    fun onCancelCollectGoods(
            @Field("key") key: String,
            @Field("fav_id") fav_id: String
    ): Flowable<ResponseBody>

    @GET("index.php?act=store&op=get_store_presales")
    fun getShopChatData(
            @Query("store_id") store_id: String?,
            @Query("lang_type") lang_type: String?
    ): Flowable<ResponseBody>
}