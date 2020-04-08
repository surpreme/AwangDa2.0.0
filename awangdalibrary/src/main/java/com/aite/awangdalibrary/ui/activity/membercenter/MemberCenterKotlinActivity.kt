package com.aite.awangdalibrary.ui.activity.membercenter

import com.aite.awangdalibrary.R
import com.aite.mainlibrary.basekotlin.BaseActivity
import com.valy.baselibrary.view.GaussianBlur
import kotlinx.android.synthetic.main.item_membercenter_information.*

/**

 * @Auther: valy

 * @datetime: 2020/3/13

 * @desc:

 */
class MemberCenterKotlinActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_membercenter_kotlin

    override fun initViews() {
        setStatusBar(1)
//        GaussianBlur.with(mContext).size(653f).radius(5).put(R.mipmap.membercenter_background, member_back_iv);

    }

    override fun initDatas() {
    }

}