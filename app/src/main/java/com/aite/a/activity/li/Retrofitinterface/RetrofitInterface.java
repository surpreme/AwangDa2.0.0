package com.aite.a.activity.li.Retrofitinterface;

import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.BaseData2;
import com.aite.a.activity.li.activity.BaseData3;
import com.aite.a.activity.li.activity.login.AreaCodeBean;
import com.aite.a.activity.li.fragment.houseFragment.HouseUIBean;
import com.aite.a.activity.li.activity.login.LogInBean;
import com.aite.a.activity.li.fragment.locationFragment.LocationAdvBean;
import com.aite.a.activity.li.fragment.locationFragment.NearByShopListBean;
import com.aite.a.activity.li.fragment.settingFragment.SettingDataBean;
import com.aite.a.activity.li.fragment.shoppingCartFragment.ShoppingCardBean;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Auther: valy
 * @datetime: 2020-01-09
 * @desc:
 * @GET("index.php?act=login&op=loginThreeWay") Flowable<BaseData < LogInBean>> onPostLogInAccount(
 * @Query("username") String username,
 * @Query("password") String password,
 * @Query("loginType") String loginType,
 * @Query("client") String client,
 * @Query("lang_type") String lang_type
 * <p>
 * <p>
 * //添加医生信息
 * @POST("index.php?act=doctor_extend&op=add")
 * @FormUrlEncoded Observable<BaseBean> addDoctorInfo(@Field("key") String key, @Field("work_address") String work_address, @Field("adept") String adept,
 * @Field("introduce") String introduce, @Field("professional_types") int professional_types,
 * @Field("departments") String departments, @Field("position") String position);
 */
public interface RetrofitInterface {
    /**
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * username	post	字符串	必须			用户名/手机号/邮箱
     * password	post	字符串	必须			登录密码
     * client	post	字符串	必须			来源端口 wap ios android
     * device_id	post	字符串	必须			设备id
     * loginType	post	字符串	必须			登陆方式:isMobile、isEmail、isAccount
     * code	post	字符串	可选			手机区号：如00086、00855、00066
     * login_role	post	整型	必须			登录角色 1会员登录 2商户登录 3仓库员登录 4配送员
     * <p>
     * 登录
     *
     * @param username
     * @param password
     * @param loginType
     * @param client
     * @param lang_type
     * @param login_role
     * @return
     */
    @POST("index.php?act=login&op=loginThreeWay")
    @FormUrlEncoded
    Flowable<BaseData<LogInBean>> onPostLogInAccount(
            @Field("username") String username,
            @Field("password") String password,
            @Field("loginType") String loginType,
            @Field("client") String client,
            @Field("lang_type") String lang_type,
            @Field("login_role") String login_role,
            @Field("device_id") String device_id
    );

    /**
     * facebook login
     *
     * @param type
     * @param client
     * @param lang_type
     * @param facebook_id
     * @param device_id
     * @return
     */
    @POST("index.php?act=login&op=app_login")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostFeceBookLogInAccount(
            @Field("type") String type,
            @Field("client") String client,
            @Field("lang_type") String lang_type,
            @Field("facebook_id") String facebook_id,
            @Field("device_id") String device_id
    );

    @POST("index.php?act=member_message&op=showReceivedNewNum")
    @FormUrlEncoded
    Flowable<ResponseBody> onGetMsgNum(
            @Field("key") String key,
            @Field("login_role") String login_role
    );

    @GET("index.php?act=member_pointorder&op=order_info")
    Flowable<ResponseBody> getUseOrderInfo(
            @Query("key") String key,
            @Query("order_id") String order_id,
            @Query("lang_type") String lang_type
    );

    /**
     * wechat
     *
     * @param type
     * @param client
     * @param lang_type
     * @param unionid
     * @param device_id
     * @return
     */
    @POST("index.php?act=login&op=app_login")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostWeChatLogInAccount(
            @Field("type") String type,
            @Field("client") String client,
            @Field("lang_type") String lang_type,
            @Field("unionid") String unionid,
            @Field("device_id") String device_id
    );

    @POST("index.php?act=member_payment&op=getPaymentList_new")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostPayList(
            @Field("key") String key,
            @Field("pay_sn") String pay_sn,
            @Field("lang_type") String lang_type
    );

    @POST("index.php?act=member_payment&op=getPdPaymentList")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostPayList2(
            @Field("key") String key,
            @Field("pay_sn") String pay_sn,
            @Field("lang_type") String lang_type
    );

    @POST("index.php?act=predeposit&op=recharge_add")
    @FormUrlEncoded
    Flowable<ResponseBody> onRechargePayNumber(
            @Field("key") String key,
            @Field("pdr_amount") String pdr_amount,
            @Field("lang_type") String lang_type
    );

    @GET("index.php?act=predeposit&op=index")
    Flowable<ResponseBody> getRechargeDetailsList(
            @Query("key") String key,
            @Query("pagesize") int pagesize,
            @Query("curpage") int curpage,
//            @Query("pdr_sn") String pdr_sn,
            @Query("lang_type") String lang_type
    );

    //修改密码
    @POST("index.php?act=member_index&op=change_pwd")
    @FormUrlEncoded
    Flowable<ResponseBody> onFixPassword(
            @Field("key") String key,
            @Field("new_password") String new_password,
            @Field("old_password") String old_password,
            @Field("lang_type") String lang_type
    );

    //新增地址

    /**
     * member_id	post	整型	必须			会员id
     * true_name	post	字符串	必须			真实姓名
     * country_id	post	整型	必须			国家id
     * province_id	post	整型	必须			省份id
     * city_id	post	整型	必须			城市编号
     * area_id	post	整型	必须			区域编号
     * area_info	post	字符串	必须			省份 城市 区域 中文字符串用空格拼接
     * address	post	字符串	必须			详细地址信息
     * mob_phone	post	字符串	必须			手机号码
     * tel_phone	post	字符串	可选			固话
     * code	post	字符串	必须			手机区号
     * points	post	字符串	必须			经纬度
     * key	post	字符串	必须			会员登陆key
     */
    @POST("index.php?act=member_address&op=address_add")
    @FormUrlEncoded
    Flowable<ResponseBody> onAddAddress(
            @Field("key") String key,
            @Field("member_id") String member_id,
            @Field("true_name") String true_name,
            @Field("country_id") String country_id,
            @Field("province_id") String province_id,
            @Field("city_id") String city_id,
            @Field("area_id") String area_id,
            @Field("area_info") String area_info,
            @Field("address") String address,
            @Field("mob_phone") String mob_phone,
            @Field("code") String code,
            @Field("points") String points,
            @Field("lang_type") String lang_type
    );

    //查看物流key=ba18bcecfd5ab9fb02da420528f9f27d&lang_type=zh_cn&order_id=535
    @GET("index.php?act=member_order&op=search_deliver")
    Flowable<ResponseBody> getOrderInformation(
            @Query("key") String key,
            @Query("order_id") String order_id,
            @Query("lang_type") String lang_type
    );

    //key=63248f64194b9120a078b346ea473ae6&lang_type=zh_cn&password=123456&pay_sn=640636203941471015
    @POST("index.php?act=member_payment&op=pd_pay")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostPayMoneyCard(
            @Field("key") String key,
            @Field("pay_sn") String pay_sn,
            @Field("password") String password,
            @Field("lang_type") String lang_type
    );

    //Pipay支付
//    https://daluxmall.com/mobile/index.php?act=member_payment&op=pay&key=26e3119f79a2840c8b684c176b0f1438&lang_type=zh_cn&pay_sn=130636205613684015&payment_code=pipay
    @GET("index.php?act=member_payment&op=pay")
    Flowable<ResponseBody> onPostPayPipay(
            @Query("key") String key,
            @Query("pay_sn") String pay_sn,
            @Query("payment_code") String payment_code,
            @Query("lang_type") String lang_type
    );

    @GET("index.php?act=member_payment&op=pd_order")
    Flowable<ResponseBody> onPostPayPipay2(
            @Query("key") String key,
            @Query("pay_sn") String pay_sn,
            @Query("payment_code") String payment_code,
            @Query("lang_type") String lang_type
    );

    @POST("index.php?act=login&op=app_login")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostInstagramLogInAccount(
            @Field("type") String type,
            @Field("client") String client,
            @Field("lang_type") String lang_type,
            @Field("instagram_id") String instagram_id,
            @Field("device_id") String device_id
    );

    @POST("index.php?act=member_payment&op=transfer_pay")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostPayUserList(
            @Field("key") String key,
            @Field("lang_type") String lang_type
    );

    /**
     * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * username	post	字符串	必须			微信昵称或QQ昵称
     * avatar_url	post	字符串	必须			微信或QQ头像地址url
     * type	post	整型	必须			1:微信， 2:QQ， 3:Facebook， 4:Instagram
     * unionid	post	字符串	可选			微信关联号
     * qqopenid	post	字符串	可选			QQ关联号
     * facebook_id	post	字符串	可选			Facebook关联id
     * instagram_id	post	字符串	可选			Instagram关联id
     * client	post	字符串	可选			客户端类型 android wap ios
     *
     * @param lang_type
     * @param username
     * @param avatar_url
     * @param type
     * @param facebook_id
     * @param client
     * @return
     */
    //自动注册只有在开启账号注册的时候才能使用
    @POST("index.php?act=login&op=aotoregister")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostFeceBookAutomaticLogInAccount(
            @Field("lang_type") String lang_type,
            @Field("username") String username,
            @Field("avatar_url") String avatar_url,
            @Field("type") String type,
            @Field("facebook_id") String facebook_id,
            @Field("client") String client
    );

    /**
     * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * username	post	字符串	必须			微信昵称或QQ昵称
     * avatar_url	post	字符串	必须			微信或QQ头像地址url
     * type	post	整型	必须			1:微信， 2:QQ， 3:Facebook， 4:Instagram
     * unionid	post	字符串	可选			微信关联号
     * qqopenid	post	字符串	可选			QQ关联号
     * facebook_id	post	字符串	可选			Facebook关联id
     * instagram_id	post	字符串	可选			Instagram关联id
     * client	post	字符串	可选			客户端类型 android wap ios
     *
     * @param lang_type
     * @param username
     * @param avatar_url
     * @param type
     * @param unionid
     * @param client
     * @return
     */
    //自动注册只有在开启账号注册的时候才能使用
    @POST("index.php?act=login&op=aotoregister")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostWeChatAutomaticLogInAccount(
            @Field("lang_type") String lang_type,
            @Field("username") String username,
            @Field("avatar_url") String avatar_url,
            @Field("type") String type,
            @Field("unionid") String unionid,
            @Field("client") String client
    );

    /**
     * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * username	post	字符串	必须			微信昵称或QQ昵称
     * avatar_url	post	字符串	必须			微信或QQ头像地址url
     * type	post	整型	必须			1:微信， 2:QQ， 3:Facebook， 4:Instagram
     * unionid	post	字符串	可选			微信关联号
     * qqopenid	post	字符串	可选			QQ关联号
     * facebook_id	post	字符串	可选			Facebook关联id
     * instagram_id	post	字符串	可选			Instagram关联id
     * client	post	字符串	可选			客户端类型 android wap ios
     *
     * @param lang_type
     * @param username
     * @param avatar_url
     * @param type
     * @param instagram_id
     * @param client
     * @return
     */
    //自动注册只有在开启账号注册的时候才能使用
    @POST("index.php?act=login&op=aotoregister")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostInstagramAutomaticLogInAccount(
            @Field("lang_type") String lang_type,
            @Field("username") String username,
            @Field("avatar_url") String avatar_url,
            @Field("type") String type,
            @Field("instagram_id") String instagram_id,
            @Field("client") String client
    );

    /**
     * 绑定账号 facebook
     *
     * @param lang_type
     * @param username
     * @param password
     * @param type
     * @param facebook_id
     * @param client
     * @return
     */
    @POST("index.php?act=login&op=bind_account")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostFeceBookBindingAccount(
            @Field("lang_type") String lang_type,
            @Field("username") String username,
            @Field("password") String password,
            @Field("type") String type,
            @Field("facebook_id") String facebook_id,
            @Field("client") String client
    );

    @POST("index.php?act=login&op=bind_account")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostWeChatBindingAccount(
            @Field("lang_type") String lang_type,
            @Field("username") String username,
            @Field("password") String password,
            @Field("type") String type,
            @Field("unionid") String unionid,
            @Field("client") String client
    );

    /**
     * 绑定账号
     *
     * @param lang_type
     * @param username
     * @param password
     * @param type
     * @param instagram_id
     * @param client
     * @return
     */
    @POST("index.php?act=login&op=bind_account")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostInstagramBindingAccount(
            @Field("lang_type") String lang_type,
            @Field("username") String username,
            @Field("password") String password,
            @Field("type") String type,
            @Field("instagram_id") String instagram_id,
            @Field("client") String client
    );

    /**
     * 获取个人信息
     *
     * @param lang_type
     * @param key
     * @return
     */
    @POST("index.php?act=member_index&op=getInfo")
    @FormUrlEncoded
    Flowable<ResponseBody> onPeopleInformation(
            @Field("lang_type") String lang_type,
            @Field("key") String key
    );

    @POST("index.php?act=login&op=loginThreeWay")
    @FormUrlEncoded
    Flowable<BaseData<LogInBean>> onPostLogInAccount2(
            @Field("username") String username,
            @Field("password") String password,
            @Field("code") String code,
            @Field("loginType") String loginType,
            @Field("client") String client,
            @Field("lang_type") String lang_type,
            @Field("login_role") String login_role,
            @Field("device_id") String device_id
    );

    @POST("index.php?act=member_address&op=area_list")
    @FormUrlEncoded
    Flowable<ResponseBody> onGetAreaListData(
            @Field("key") String key,
            @Field("area_id") String area_id,
            @Field("lang_type") String lang_type
    );

    @GET("index.php?act=store&op=show")
    Flowable<ResponseBody> getShopInformationData(
            @Query("store_id") String store_id,
            @Query("lang_type") String lang_type
    );

    @GET("index.php?op=getIMUserSig")
    Flowable<ResponseBody> getShopImChatInformationData(
            @Query("key") String key,
            @Query("lang_type") String lang_type,
            @Query("member_id") String member_id
    );

    @GET("index.php?act=store&op=get_store_presales")
    Flowable<ResponseBody> getShopChatData(
            @Query("store_id") String store_id,
            @Query("lang_type") String lang_type
    );


    @GET("index.php?act=store&op=get_store_goods_class")
    Flowable<ResponseBody> getShopInformation2Data(
            @Query("store_id") String store_id,
            @Query("lang_type") String lang_type
    );

    @GET("index.php?act=store&op=get_store_bind_class")
    Flowable<ResponseBody> getShopInformation3Data(
            @Query("store_id") String store_id,
            @Query("lang_type") String lang_type
    );

    @GET("index.php?act=store&op=get_bind_class_goods")
    Flowable<ResponseBody> getShopInformation4Data(
            @Query("store_id") String store_id,
            @Query("gc_id") String gc_id,
            @Query("curpage") int curpage,
            @Query("lang_type") String lang_type
    );

    @GET("index.php?act=goods&op=goods_list")
    Flowable<ResponseBody> getShopInformation5Data(
            @Query("store_id") String store_id,
            @Query("key") String key,
            @Query("page") int page,
            @Query("curpage") int curpage,
            @Query("lang_type") String lang_type
    );

    @GET("index.php?act=goods&op=goods_list")
    Flowable<ResponseBody> getShop2Information5Data(
            @Query("store_id") String store_id,
            @Query("key") String key,
            @Query("page") int page,
            @Query("curpage") int curpage,
            @Query("order") String order,
            @Query("lang_type") String lang_type
    );

    /**
     * 首页
     *
     * @param lang_type 语言类型 语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * @param type      输入数据类型 json html
     * @return
     */
    @GET("index.php?act=index&op=index")
    Flowable<BaseData2<HouseUIBean>> onGetHouseData(
            @Query("lang_type") String lang_type,
            @Query("type") String type
    );

    @GET("index.php?act=index&op=index")
    Flowable<ResponseBody> onGetHouseData2(
            @Query("lang_type") String lang_type,
            @Query("type") String type
    );

    /**
     * 设置页面扫码 弃用
     *
     * @param key
     * @param lang_type
     * @return
     */
    @GET("index.php?act=index&op=index")
    Flowable<ResponseBody> onGetSettingQrData(
            @Query("key") String key,
            @Query("result") String result,
            @Query("lang_type") String lang_type
    );

    /**
     * 获取地区编号
     *
     * @param lang_type
     * @return
     */
    @GET("index.php?act=index&op=getAreaCode")
    Flowable<BaseData<AreaCodeBean>> getAreaCode(
            @Query("lang_type") String lang_type
    );

    /**
     * 猜你喜欢
     *
     * @param key
     * @return
     */
    @POST("index.php?act=member_cart&op=get_guesslike")
    @FormUrlEncoded
    Flowable<ResponseBody> onGetGuessLike(
            @Field("key") String key
    );


    /**
     * ins登录获取个人配置信息 token 和 user_id
     *
     * @param client_id
     * @param client_secret
     * @param grant_type
     * @param redirect_uri
     * @param code
     * @return
     * @POST("curl -X POST \\ https://api.instagram.com/oauth/access_token \\ -F client_id=684477648739411 \\ -F client_secret=eb8c7... \\ -F grant_type=authorization_code \\ -F redirect_uri=https://socialsizzle.herokuapp.com/auth/ \\ -F code=AQDp3TtBQQ...\n")
     */
    @POST("oauth/access_token")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostInstagramConfiguration(
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type,
            @Field("redirect_uri") String redirect_uri,
            @Field("code") String code
    );

    /**
     * 获取用户id name
     *
     * @param userId
     * @param accessToken
     * @return https://graph.instagram.com/
     * https://graph.instagram.com/17841430127968194?fields=id,username&access_token=IGQVJXTVB0NFZA3ckFvd2RiTXVPM1diSjRuMFJYZAVkxMElzUksxblpTR3RidXpKRnlaQXR4c2dyUVpfRDhLS2o3ZAElIclFNbFVpa0VqSTJsV1JJclktalZACQ3k4c01lU1d4Y0tNQW9SbDU5V3hkU3RKcjhPYzZAQYnk5aFo4
     */
    @GET("{user-id}?fields=id,username")
    Flowable<ResponseBody> onGetInstagramConfiguration(
            @Path("user-id") String userId, @Query("access_token") String accessToken);

    @Multipart
    @POST("index.php?act=member_payment&op=transfer")
    Flowable<ResponseBody> onPostOfflinePayInformation(
            @Part List<MultipartBody.Part> content

    );

    @Multipart
    @POST("index.php?act=member_index&op=member_auth")
    Flowable<ResponseBody> onSureTrueName(
            @Part List<MultipartBody.Part> content

    );

    @GET("index.php?act=member_index&op=member_auth")
    Flowable<ResponseBody> onSureTrueNameInformation(
            @Query("key") String key

    );

    @Multipart
    @POST("index.php?act=member_index&op=member_auth_upload")
    Flowable<ResponseBody> onSureTrueNameImg(
            @Part List<MultipartBody.Part> content

    );


    /**
     * 找回密码验证手机验证码
     * <p>
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * regtype	post	字符串	必须	Isaccountreg		注册方式:Isaccountreg Ismobilereg Isemailreg 三选一
     * username	post	字符串	必须			用户名/手机号/邮箱
     * email	post	字符串	可选			邮箱
     * mobile_code	post	字符串	可选			验证码（手机或邮箱校验）
     *
     * @param lang_type
     * @param regtype
     * @param username
     * @param mobile_code
     * @return
     */
    @POST("index.php?act=login&op=find_password")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostPhoneVerificationCode(
            @Field("lang_type") String lang_type,
            @Field("regtype") String regtype,
            @Field("username") String username,
            @Field("mobile_code") String mobile_code

    );

    //fav_id=3&fav_type=store&key=76f919703cb8a7f6937e4893af10baa6&lang_type=zh_cn
    @POST("index.php?act=member_favorites&op=favorites_add")
    @FormUrlEncoded
    Flowable<ResponseBody> onCollectShop(
            @Field("fav_id") String fav_id,
            @Field("fav_type") String fav_type,
            @Field("key") String key,
            @Field("lang_type") String lang_type

    );

    @POST("index.php?act=member_favorites&op=favorites_del")
    @FormUrlEncoded
    Flowable<ResponseBody> onCancelCollectShop(
            @Field("fav_id") String fav_id,
            @Field("fav_type") String fav_type,
            @Field("key") String key,
            @Field("lang_type") String lang_type

    );

    /**
     * 找回密码验证邮箱验证码
     * <p>
     * 参数名字	提交方式	类型	是否必须	默认值	其他	说明	test
     * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * regtype	post	字符串	必须	Isaccountreg		注册方式:Isaccountreg Ismobilereg Isemailreg 三选一
     * username	post	字符串	必须			用户名/手机号/邮箱
     * email	post	字符串	可选			邮箱
     * mobile_code	post	字符串	可选			验证码（手机或邮箱校验）
     *
     * @param lang_type
     * @param regtype
     * @param email
     * @param mobile_code
     * @return
     */
    @POST("index.php?act=login&op=find_password")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostEmailVerificationCode(
            @Field("lang_type") String lang_type,
            @Field("regtype") String regtype,
            @Field("email") String email,
            @Field("mobile_code") String mobile_code

    );

    /**
     * 注册发送手机验证码
     *
     * @param mobile
     * @param lang_type
     * @return
     */
    @POST("index.php?act=login&op=registerSendCode")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostPhoneCode(
            @Field("mobile") String mobile,
            @Field("lang_type") String lang_type
    );

    /**
     * 找回密码发送手机验证码
     *
     * @param mobile
     * @param lang_type
     * @return
     */
    @POST("index.php?act=login&op=retrievePasswordSendCode")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostFindPasswordPhoneCode(
            @Field("mobile") String mobile,
            @Field("lang_type") String lang_type
    );

    /**
     * 注册发送邮箱验证码
     *
     * @param email
     * @param lang_type
     * @return
     */
    @GET("index.php?act=login&op=sendModifyEmail")
    Flowable<ResponseBody> onPostEmailCode(
            @Query("email") String email,
            @Query("lang_type") String lang_type
    );

    /**
     * 找回密码发送邮箱验证码
     *
     * @param email
     * @param lang_type
     * @return
     */
    @GET("index.php?act=login&op=sendModifyEmail")
    Flowable<ResponseBody> onPostFindPasswordEmailCode(
            @Query("email") String email,
            @Query("lang_type") String lang_type
    );

    /**
     * 注册提交 phone
     *
     * @param vercode
     * @param regtype
     * @param username
     * @param password
     * @param client
     * @param account_name
     * @param password_confirm
     * @param lang_type
     * @return 参数名字    提交方式	类型	是否必须	默认值	其他	说明	test
     * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * vercode	post	字符串	可选			验证码（短信或邮箱）
     * regtype	post	字符串	必须	Isaccountreg		注册方式:Isaccountreg Ismobilereg Isemailreg 三选一
     * username	post	字符串	可选			手机号
     * password	post	字符串	必须			密码
     * password_confirm	post	字符串	必须			确认密码
     * email	post	字符串	可选			邮箱
     * client	post	字符串	必须			客户端类型 android wap wechat ios 四选一
     * account_name	post	字符串	必须			用户名
     */
    @POST("index.php?act=login&op=register")
    @FormUrlEncoded
    Flowable<ResponseBody> onRegistrationPhoneSubmission(
            @Field("vercode") String vercode,
            @Field("regtype") String regtype,
            @Field("username") String username,
            @Field("password") String password,
            @Field("client") String client,
            @Field("account_name") String account_name,
            @Field("password_confirm") String password_confirm,
            @Field("lang_type") String lang_type
    );

    /**
     * email
     *
     * @param vercode
     * @param regtype
     * @param email
     * @param password
     * @param client
     * @param account_name
     * @param password_confirm
     * @param lang_type
     * @return
     */
    @POST("index.php?act=login&op=register")
    @FormUrlEncoded
    Flowable<ResponseBody> onRegistrationEmailSubmission(
            @Field("vercode") String vercode,
            @Field("regtype") String regtype,
            @Field("email") String email,
            @Field("password") String password,
            @Field("client") String client,
            @Field("account_name") String account_name,
            @Field("password_confirm") String password_confirm,
            @Field("lang_type") String lang_type
    );

    /**
     * 购物车列表接口
     */
    @POST("index.php?act=member_cart&op=cart_list2")
    @FormUrlEncoded
    Flowable<BaseData<ShoppingCardBean>> onGetShoppingCardList(
            @Field("key") String key
    );

    /**
     * 设置页面
     */
    @POST("index.php?act=member_index")
    @FormUrlEncoded
    Flowable<BaseData<SettingDataBean>> onGetSettingData(
            @Field("key") String key
    );

    /**
     * 附近的商家 广告信息
     */
    @GET("index.php?act=near_store&op=get_near_store_swiper")
    Flowable<BaseData3<LocationAdvBean>> onGetAroundShopData(
            @Query("key") String key
    );

    /**
     * 当前购物车数量
     */
    @POST("index.php?act=member_cart&op=cart_num")
    @FormUrlEncoded
    Flowable<ResponseBody> onPostShopCardNumberData(
            @Field("key") String key
    );

    /**
     * * @param key
     * * @param freight_hash
     * * @param addr_id
     * * @param city_id
     * * @param area_id
     */
    @POST("index.php?act=member_buy&op=change_address")
    @FormUrlEncoded
    Flowable<ResponseBody> onGetGoodsSendPeaceInformationData(
            @Field("key") String key,
            @Field("freight_hash") String freight_hash,
            @Field("addr_id") String addr_id,
            @Field("city_id") String city_id,
            @Field("area_id") String area_id
    );

    @POST("index.php?act=member_buy&op=buy_step1")
    @FormUrlEncoded
    Flowable<ResponseBody> onGetGoodsInformationData(
            @Field("key") String key,
            @Field("cart_id") String cart_id);

    /**
     * 附近的商家 列表index.php?act=index&op=nearby_store
     * params.addQueryStringParameter("key", Mark.State.UserKey);
     * params.addQueryStringParameter("points", points);
     * params.addQueryStringParameter("city_id", city_id);
     * params.addQueryStringParameter("area_id", area_id);
     * params.addQueryStringParameter("class_id", class_id);
     * params.addQueryStringParameter("sort_type", sort_type);
     * params.addQueryStringParameter("curpage", String.valueOf(pages));
     * params.addQueryStringParameter("sort_dis_type", sort_dis_type);
     */
    @GET("index.php?act=index&op=nearby_store")
    Flowable<BaseData<NearByShopListBean>> onGetAroundShopListShopData(
            @Query("key") String key,
            @Query("curpage") int curpage,
            @Query("points") String points,
            @Query("city_id") String city_id,
            @Query("area_id") String area_id,
            @Query("class_id") String class_id,
            @Query("sort_type") String sort_type,
            @Query("sort_dis_type") String sort_dis_type

    );
}
