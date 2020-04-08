package com.aite.awangdalibrary.ui.activity

import android.graphics.Bitmap
import android.graphics.Color
import android.view.ViewGroup
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.coupon.CouponKotlinActivity
import com.aite.awangdalibrary.ui.activity.findpassword.FindPassWordKotlinActivity
import com.aite.awangdalibrary.ui.activity.registered.RegisteredKotlinActivity
import com.aite.mainlibrary.basekotlin.BaseActivity
import com.valy.baselibrary.utils.CodeUtils
import kotlinx.android.synthetic.main.activity_login_kotlin.*
import kotlin.math.roundToInt


/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class LogInKotlinActivity : BaseActivity() {
    var mCodeUtils: CodeUtils? = null

    override fun getLayoutId(): Int = R.layout.activity_login_kotlin

    override fun initViews() {
        //toolbar
        initToolBar("登录")
        setStatusBar(1)
        back?.setColorFilter(Color.WHITE)
        toolbar_title?.setTextColor(Color.WHITE)
        toolbar_lls.setPadding(0, getStatusBarHeight(), 0, 0)
        //cardView
        login_cardView.setPadding(0, (getScreenHeight() * 0.3).roundToInt() + getStatusBarHeight(), 0, 0)
        //验证码
        mCodeUtils = CodeUtils.getInstance()
        val bitmap: Bitmap = mCodeUtils!!.createBitmap()
        verification_iv.setImageBitmap(bitmap)
        //背景图
        val params: ViewGroup.LayoutParams = background_iv.layoutParams as ViewGroup.LayoutParams
        params.height = (getScreenWidth() * 0.65).toInt()
        background_iv.layoutParams = params
    }

    override fun onClick() {
        super.onClick()
        //重置验证码
        verification_iv.setOnClickListener {
            val createBitmap: Bitmap = mCodeUtils!!.createBitmap()
            verification_iv.setImageBitmap(createBitmap)
        }
        verification_refresh_tv.setOnClickListener {
            val createBitmap: Bitmap = mCodeUtils!!.createBitmap()
            verification_iv.setImageBitmap(createBitmap)
        }
        find_key_tv.setOnClickListener {
            startActivity(FindPassWordKotlinActivity::class.java)
        }
        registered_tv.setOnClickListener{
            startActivity(RegisteredKotlinActivity::class.java)

        }
        login_btn.setOnClickListener{
            startActivity(CouponKotlinActivity::class.java)

        }
    }

    override fun initDatas() {
    }


}