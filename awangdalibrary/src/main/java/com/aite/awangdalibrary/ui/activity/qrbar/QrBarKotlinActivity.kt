package com.aite.awangdalibrary.ui.activity.qrbar

import com.aite.awangdalibrary.R
import com.aite.mainlibrary.basekotlin.BaseActivity

/**

 * @Auther: valy

 * @datetime: 2020/3/12

 * @desc:

 */
class QrBarKotlinActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_qrbar

    override fun initViews() {
        setStatusBar(0)
        initToolBar("二维码")
    }

    override fun initDatas() {
    }

}