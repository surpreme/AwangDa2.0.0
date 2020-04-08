package com.aite.awangdalibrary.ui.activity.registered

import android.content.Intent
import android.graphics.Color
import android.view.ViewGroup
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.membercenter.MemberCenterKotlinActivity
import com.aite.awangdalibrary.ui.activity.transfer.TransferKotlinActivity
import com.aite.mainlibrary.basekotlin.BaseActivity
import com.valy.baselibrary.utils.LogUtils
import kotlinx.android.synthetic.main.activity_login_kotlin.*
import kotlinx.android.synthetic.main.activity_login_kotlin.background_iv
import kotlinx.android.synthetic.main.activity_login_kotlin.toolbar_lls
import kotlinx.android.synthetic.main.activity_regisered_kotlin.*

/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class RegisteredKotlinActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_regisered_kotlin

    override fun initViews() {
        //toolbar
        initToolBar("注册")
        setStatusBar(1)
        back?.setColorFilter(Color.WHITE)
        toolbar_title?.setTextColor(Color.WHITE)
        toolbar_lls.setPadding(0, getStatusBarHeight(), 0, 0)
        //背景图
        val params: ViewGroup.LayoutParams = background_iv.layoutParams as ViewGroup.LayoutParams
        params.height = (getScreenWidth() * 0.65).toInt()
        background_iv.layoutParams = params
    }

    override fun initDatas() {
        phone_cardView.setOnClickListener {
            LogUtils.d(it.left)
            val intent = Intent()
            intent.action = "com.aite.registered.RegisteredActivity"
            startActivity(intent)
//            startActivity(TransferKotlinActivity::class.java)
        }
        username_cardView.setOnClickListener {
            startActivity(MemberCenterKotlinActivity::class.java)
        }

    }

}