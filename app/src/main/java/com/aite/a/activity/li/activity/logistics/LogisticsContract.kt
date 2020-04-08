package com.aite.a.activity.li.activity.logistics

import com.aite.a.bean.PayListBean
import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class LogisticsContract {
    interface View : BaseView {
        fun onOrderInformationSuccess(mLogisticsNetBean: LogisticsNetBean)

    }

    interface Presenter : BasePresenter<View> {
        fun getOrderInformation(key: String, order_id: String, lang_type: String)
    }

}