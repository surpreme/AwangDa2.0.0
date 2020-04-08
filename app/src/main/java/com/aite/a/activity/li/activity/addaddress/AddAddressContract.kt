package com.aite.a.activity.li.activity.addaddress

import com.aite.a.activity.li.activity.login.AreaCodeBean
import com.aite.a.bean.PayListBean
import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class AddAddressContract {
    interface View : BaseView {
        fun onGetAreaCodeSuccess(mAreaCodeBean: AreaCodeBean)
        fun onCommitDataSuccess(i: String)

    }

    interface Presenter : BasePresenter<View> {
        fun onGetAreaCode(lang_type: String)
        fun onCommitData(key: String?, member_id: String?, true_name: String?, country_id: String?, province_id: String?, city_id: String?, area_id: String?, area_info: String?, address: String?, mob_phone: String?, code: String?, points: String?, lang_type: String?)
    }

}