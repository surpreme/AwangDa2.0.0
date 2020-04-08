package com.aite.a.activity.li.activity.truename

import com.aite.a.activity.li.activity.login.AreaCodeBean
import com.aite.a.bean.PayListBean
import com.aite.mainlibrary.basekotlin.BasePresenter
import com.aite.mainlibrary.basekotlin.BaseView
import okhttp3.MultipartBody

/**

 * @Auther: valy

 * @datetime: 2020/3/6

 * @desc:

 */
open class TrueNameContract {
    interface View : BaseView {
        fun getAreaCodeSuccess(mList: AreaCodeBean)
        fun onCommitSuccess()
        fun onCommitImgSuccess(type: Int, path: String)
        fun onSureTrueNameInformation(state: String)

    }

    interface Presenter : BasePresenter<View> {
        fun getAreaCode(lang_type: String)
        fun commit(parts: List<MultipartBody.Part>)
        fun CommitImg(parts: List<MultipartBody.Part>, type: Int)
        fun onSureTrueNameInformation(key: String)
    }

}