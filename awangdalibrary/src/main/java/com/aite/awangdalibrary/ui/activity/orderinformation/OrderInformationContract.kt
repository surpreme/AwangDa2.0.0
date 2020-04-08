package com.aite.awangdalibrary.ui.activity.orderinformation

import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class OrderInformationContract {
    interface View : BaseView {
        fun getDatasSuccess(mOrderInformationDatasBean:OrderInformationDatasBean)

    }

    interface Presenter : BasePresenter<View> {
        fun getDatas(language_type: String, lang_type: String, order_id: String, key: String)
    }

}