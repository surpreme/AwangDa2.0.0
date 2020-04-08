package com.aite.a.activity.li.activity.surePayListKotlin

import com.aite.a.bean.PayListBean
import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class SurePayListContract {
    interface View : BaseView {
        fun onPayListTableSuccess(payListBean: PayListBean)
        fun onPayMoneyCardSuccess(s: String)
        fun onPayMoneyCardFail(s: String)
        fun onPayPipay(s: String)

    }

    interface Presenter : BasePresenter<View> {
        fun getPayListTable(key: String, pay_sn: String, lang_type: String)
        fun getPayListTable2(key: String, pay_sn: String, lang_type: String)
        fun getPayMoneyCard(key: String, pay_sn: String, password: String, lang_type: String)
        fun getPayPipay(key: String, pay_sn: String, payment_code: String, lang_type: String)
        fun getPayPipay2(key: String, pay_sn: String, payment_code: String, lang_type: String)
    }

}