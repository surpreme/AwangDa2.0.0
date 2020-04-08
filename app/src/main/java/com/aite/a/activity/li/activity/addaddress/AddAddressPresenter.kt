package com.aite.a.activity.li.activity.addaddress

import android.annotation.SuppressLint
import android.app.Activity
import android.text.TextUtils
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface
import com.aite.a.activity.li.activity.BaseData
import com.aite.a.activity.li.activity.login.AreaCodeBean
import com.aite.a.activity.li.mvp.RetrofitClient
import com.aite.a.activity.li.util.LogUtils
import com.aite.mainlibrary.basekotlin.BasePresenterImpl
import com.valy.baselibrary.retrofit.RxScheduler
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class AddAddressPresenter : BasePresenterImpl<AddAddressContract.View>(), AddAddressContract.Presenter {
    @SuppressLint("CheckResult")
    override fun onGetAreaCode(lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .getAreaCode(lang_type)
                .compose(RxScheduler.Flo_io_main())
                .filter { areaCodeBeanBaseData: BaseData<AreaCodeBean> ->
                    if (!areaCodeBeanBaseData.isSuccessed) {
                        mView?.showError(areaCodeBeanBaseData.datas.error)
                        LogUtils.e(areaCodeBeanBaseData.datas.error)
                        return@filter false
                    } else return@filter true
                }
                .map { obj: BaseData<AreaCodeBean> -> obj.datas }
                .filter { areaCodeBeanBaseData: AreaCodeBean ->
                    if (areaCodeBeanBaseData.error != null) {
                        mView?.showError(areaCodeBeanBaseData.error)
                        LogUtils.e(areaCodeBeanBaseData.error)
                        return@filter false
                    } else {
                        return@filter true
                    }
                }.subscribe({ areaCodeBean: AreaCodeBean ->
                    mView?.onGetAreaCodeSuccess(areaCodeBean)
                }) { throwable: Throwable ->
                    LogUtils.e(throwable.message)
                }
    }

    /**
     * @Field("key") String key,
     * @Field("member_id") String member_id,
     * @Field("true_name") String true_name,
     * @Field("country_id") String country_id,
     * @Field("province_id") String province_id,
     * @Field("city_id") String city_id,
     * @Field("area_id") String area_id,
     * @Field("area_info") String area_info,
     * @Field("address") String address,
     * @Field("mob_phone") String mob_phone,
     * @Field("code") String code,
     * @Field("points") String points,
     * @Field("lang_type") String lang_type
     *         fun addAddress(key: String?, member_id: String?, true_name: String?, country_id: String?, province_id: String?, city_id: String?, area_id: String?, area_info: String?, address: String?, mob_phone: String?, code: String?, points: String?, lang_type: String?) {
     */
    @SuppressLint("CheckResult")
    override fun onCommitData(key: String?, member_id: String?, true_name: String?, country_id: String?, province_id: String?, city_id: String?, area_id: String?, area_info: String?, address: String?, mob_phone: String?, code: String?, points: String?, lang_type: String?) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onAddAddress(key, member_id, true_name, country_id, province_id, city_id, area_id, area_info, address, mob_phone, code, points, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe({ responseBody ->
                    val jsonObject = JSONObject(responseBody.string())
                    val code = jsonObject.optString("code")
                    val error_code = jsonObject.optString("error_code")
                    if (code != null) {
                        if (code == "200") {
                            val successObject = jsonObject.optJSONObject("datas")
                            val address_id = successObject.optString("address_id")
                            if (address_id != null)
                                (mView?.getContext() as Activity).runOnUiThread {
                                    mView?.onCommitDataSuccess(address_id)

                                }

                        } else {
                            val errorObject = jsonObject.optJSONObject("datas")
                            if (errorObject != null) {
                                val error = errorObject.optString("error")
                                if (error != null && !TextUtils.isEmpty(error)) {
                                    mView?.showError(error)
                                    LogUtils.e(error)
                                }
                            }
                        }
                    } else {
                        val errorObject = jsonObject.optJSONObject("datas")
                        if (errorObject != null) {
                            val error = errorObject.optString("error")
                            if (error != null && !TextUtils.isEmpty(error)) {
                                LogUtils.e(error)
                                mView?.showError(error)
                            }
                        }

                    }
                }) { throwable: Throwable ->
                    LogUtils.e(throwable.message)
                }

    }

}