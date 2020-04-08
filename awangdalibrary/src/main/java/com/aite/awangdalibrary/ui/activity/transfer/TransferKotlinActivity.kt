package com.aite.awangdalibrary.ui.activity.transfer

import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.utils.SystemUtil
import kotlin.math.min

/**

 * @Auther: valy

 * @datetime: 2020/3/13

 * @desc: todo未实现的方法

 */
class TransferKotlinActivity : BaseMVPListActivity<TransferContract.View, TransferPresenter, TransferUIBean>() {
    override fun setAdapter(): BaseRecyAdapter<TransferUIBean>? {
        val mTransferUIAdapter = TransferUIAdapter(mContext)
        return mTransferUIAdapter
    }

    override fun initViews() {
        super.initViews()
        setStatusBar(0)
    }

    override fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)


    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
            if (position == 1) {
                rect.top = SystemUtil.dp2px(mContext, 10f)
            }
            if (position == 2)
                rect.bottom = SystemUtil.dp2px(mContext, 12f)
        }

        override fun doRule(position: Int, rect: Rect) {
            if (position == 1) {
                rect.bottom = rect.top
            } else if (position == 2) {
                rect.bottom = SystemUtil.dp2px(mContext, 24f)
            } else rect.left = 10

        }
    }

    override fun getRecyclerViewId(): Int = R.id.transfer_recycler_view

    override fun onDestroys() {
    }

    override fun getLayoutId(): Int = R.layout.activity_transfer

    override fun initDatas() {
        for (index in 0..10) {
            val f = TransferUIBean()
            when (index) {
                0 -> {
                    f.centerBarTitle = "转让"
                }
                1 -> {
                    f.tips = "我要转让"
                }
                2 -> {
                    val mInformationBean = TransferUIBean.InformationBean()
                    mInformationBean.balance = "5200.00元"
                    mInformationBean.gold = "7.00元"
                    mInformationBean.integral = "7690"
                    f.informationBean = mInformationBean
                }
                3 -> {
                    val mChocieTypeBean = TransferUIBean.ChocieTypeBean()
                    mChocieTypeBean.title = "*转让类型"
                    f.chocieTypeBean = mChocieTypeBean

                }
                4 -> {
                    val mOutBean = TransferUIBean.OutBean()
                    mOutBean.title = "*转让数额"
                    mOutBean.hint = "请输入具体的数字"
                    f.outBean = mOutBean

                }
                5 -> {
                    val mOutBean = TransferUIBean.OutBean()
                    mOutBean.title = "*转让会员"
                    mOutBean.hint = "请输入会员名"
                    f.outBean = mOutBean

                }
                6 -> {
                    val mPasswordBean = TransferUIBean.PasswordBean()
                    mPasswordBean.title = "*支付密码"
                    mPasswordBean.hint = "请输入密码"
                    f.passwordBean = mPasswordBean

                }
                7 -> {
                    val mPhoneUiBean = TransferUIBean.PhoneUiBean()
                    mPhoneUiBean.title = "*手机号"
                    mPhoneUiBean.phone = "12345678888"
                    mPhoneUiBean.sendText = "获取"
                    f.phoneUiBean = mPhoneUiBean

                }
                8 -> {
                    val mOutBean = TransferUIBean.OutBean()
                    mOutBean.title = "*验证码"
                    mOutBean.hint = "请输入您手机上接收的验证码"
                    f.outBean = mOutBean


                }
                9 -> {
                    val mPhoneUiBean = TransferUIBean.PhoneUiBean()
                    mPhoneUiBean.title = "*邮箱"
                    mPhoneUiBean.phone = "2846235731love@gmail.com"
                    mPhoneUiBean.sendText = "获取"
                    f.phoneUiBean = mPhoneUiBean

                }
                10 -> {
                    val mOutBean = TransferUIBean.OutBean()
                    mOutBean.title = "*验证码"
                    mOutBean.hint = "请输入您邮箱上接收的验证码"
                    f.outBean = mOutBean

                }
            }
            mDatasList.add(f)
        }
        appendDatas()
    }

}