package com.aite.awangdalibrary.ui.activity.systeminformation

import com.aite.awangdalibrary.R
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.valy.baselibrary.basekotlin.BaseApp
import kotlinx.android.synthetic.main.activity_system_information.*

/**

 * @Auther: valy

 * @datetime: 2020/4/7

 * @desc:

 */
class SystemInformationKotlinActivity : BaseMVPActivity<SystemInformationKotlinContract.View, SystemInformationKotlinPresenter>(), SystemInformationKotlinContract.View {
    lateinit var time: String
    lateinit var content: String
    var number: String = ""
    override fun getLayoutId(): Int = R.layout.activity_system_information
    override fun initExtra() {
        super.initExtra()
        if (!intent.getStringExtra("time").isNullOrEmpty() && !intent.getStringExtra("content").isNullOrEmpty()) {
            time = intent.getStringExtra("time")
            content = intent.getStringExtra("content")
        } else {
            throw Throwable("getStringExtraNull" + this.localClassName)
        }
        if (!intent.getStringExtra("number").isNullOrEmpty()) {
            number = intent.getStringExtra("number")
        }
    }

    override fun initViews() {
        setStatusBar(0)
        initToolBar("消息详情")
        tv_time.text = time
        tv_information.text = content
    }

    override fun initDatas() {
        if (!number.isNullOrEmpty()) {
            mPresenter?.read(BaseApp.getKey(), number)

        }
    }

    override fun read() {

    }


}