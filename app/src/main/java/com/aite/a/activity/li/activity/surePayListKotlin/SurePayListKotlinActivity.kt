package com.aite.a.activity.li.activity.surePayListKotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.OfflinePayActivity
import com.aite.a.activity.PaySuccessActivity
import com.aite.a.activity.PhoneCertificationActivity
import com.valy.baselibrary.bean.BaseConstant
import com.aite.a.activity.li.activity.BaseWebViewActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.activity.li.util.LogUtils
import com.aite.a.activity.li.util.TextColorUtils
import com.aite.a.base.Mark
import com.aite.a.bean.PayListBean
import com.aite.a.fargment.CardPayDialogFragment
import com.aite.a.utils.SystemUtil
import com.aite.a.utils.ToastUtils
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.dialogfragment.TalkDialogFragment
import com.valy.baselibrary.utils.OnClickLstenerInterface
import kotlinx.android.synthetic.main.activity_pay_order_three.*

/**
 * @Auther: valy
 * @datetime: 2020-02-27
 * @desc:
 */
class SurePayListKotlinActivity : BaseMVPListActivity<SurePayListContract.View, SurePayListPresenter, PayListBean.PaymentBean>(), SurePayListContract.View {
    private var payListRecyAdpter: PayListRecyAdpter2? = null
    private var payCode = ""
    private var payTag = 0
    private var mOrderd_amount: Double = 0.0
    private var pay_sn = ""
    override fun getLayoutId(): Int = R.layout.activity_pay_order_three

    override fun initViews() {
        super.initViews()
        setStatusBar("#FF5000")
        initToolBar(getString(R.string.ConfirmPayment))
        bank_price_tv.text = TextColorUtils.setSpannableStringBuilder(
                getString(R.string.threePay) + "0%   $" + "0.00",
                getString(R.string.threePay).length,
                "#060606",
                "#FF5000")

    }


    override fun initDatas() {
        if (intent.getStringExtra("PAY_SN") == null) {
            onBackPressed()
            return
        }
        pay_sn = intent.getStringExtra("PAY_SN")
        if (!intent.getStringExtra("type").isNullOrEmpty()) {
            mPresenter?.getPayListTable2(
                    Mark.State.UserKey,
                    intent.getStringExtra("PAY_SN"),
                    BaseConstant.CURRENTLANGUAGE)
        } else
            mPresenter?.getPayListTable(
                    Mark.State.UserKey,
                    intent.getStringExtra("PAY_SN"),
                    BaseConstant.CURRENTLANGUAGE)


    }

    override fun onClick() {
        sure_pay_btn.setOnClickListener {
            when (payTag) {
                1 -> {
                    showPasswordDailogFragment()
                }
                2 -> {
                    val intent = Intent(mContext, OfflinePayActivity::class.java)
                    intent.putExtra("pay_sn", pay_sn)
                    intent.putExtra("type", "1")
                    startActivity(intent)
                }
                3 -> {
                    if (!intent.getStringExtra("type").isNullOrEmpty())
                        mPresenter?.getPayPipay2(Mark.State.UserKey, pay_sn, payCode, "zh_cn")
                    else
                        mPresenter?.getPayPipay(Mark.State.UserKey, pay_sn, payCode, "zh_cn")
                }
            }
        }

    }

    private fun showPasswordDailogFragment() {
        val mDialogFragment = CardPayDialogFragment()
        mDialogFragment.setOnDialogListener { person ->
            mPresenter?.getPayMoneyCard(Mark.State.UserKey, pay_sn, person, "zh_cn")

        }
        mDialogFragment.show(supportFragmentManager, "CardPayDialogFragment")

    }

    @SuppressLint("SetTextI18n")
    override fun onPayListTableSuccess(payListBean: PayListBean) {
        payListBean.payment[0].isSelect = true
        appendDatas(payListBean.payment)
        mOrderd_amount = payListBean.info.order_amount.toDouble()
        when (mDatasList[0].payment_code) {
            "predeposit" -> {
                payTag = 1
            }
            "transfer" -> {
                payTag = 2
            }
            "pipay" -> {
                payTag = 3
            }
        }
        order_number_tv.text = "${getString(R.string.orderNumber)}${payListBean.info.pay_sn}"
        if (null != payListBean.info.order_amount) {
            all_price_tv.text =
                    TextColorUtils.setSpannableStringBuilder(
                            "${getString(R.string.TotalPrice)} $" + payListBean.info.order_amount,
                            getString(R.string.TotalPrice).length,
                            "#060606",
                            "#FF5000")
            all_price_over_tv.text =
                    TextColorUtils.setSpannableStringBuilder(
                            "${getString(R.string.Total)} $" + payListBean.info.order_amount,
                            getString(R.string.Total).length,
                            "#060606",
                            "#FF5000")
        }
    }

    override fun onPayMoneyCardSuccess(s: String) {
        ToastUtils.showToast(mContext, s)
        startActivity(PaySuccessActivity::class.java)
        finish()
    }

    override fun onPayMoneyCardFail(s: String) {
        runOnUiThread {
            val mTalkDialogFragment = TalkDialogFragment().newInstance(s, "忘记密码")
            mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                startActivity(PhoneCertificationActivity::class.java)
            }
            mTalkDialogFragment?.show(supportFragmentManager, "TalkDialog")
        }

    }

    override fun onPayPipay(s: String) {
        val intent = Intent(mContext, BaseWebViewActivity::class.java)
        intent.putExtra("isHideToolBar", "false")
        intent.putExtra("title", "")
        intent.putExtra("loadDataWithBaseURL", s)
        startActivity(intent)
        finish()
    }

    override fun onDestroys() {
    }

    override fun setLayoutManager(): LinearLayoutManager = GridLayoutManager(mContext, 2) as LinearLayoutManager

    override fun addItemDecoration(): BaseItemDecoration = BaseItemDecoration(
            SystemUtil.dip2px(mContext, 1f),
            SystemUtil.dip2px(mContext, 1f),
            SystemUtil.dip2px(mContext, 1f),
            SystemUtil.dip2px(mContext, 1f),
            SystemUtil.dip2px(mContext, 1f),
            SystemUtil.dip2px(mContext, 1f),
            Color.parseColor("#EDEBEF"),
            mContext,
            2f,
            "4:1")


    override fun getRecyclerViewId(): Int = R.id.pay_list_recy


    override fun setAdapter(): BaseRecyAdapter<PayListBean.PaymentBean>? {
        payListRecyAdpter = PayListRecyAdpter2(mContext)
        payListRecyAdpter?.onSelectInterface = OnClickLstenerInterface.OnRecyClickInterface { position ->
            run {
                LogUtils.d("***************************$position*************************************")
                if (mDatasList[position].payment_code != null) {
                    payCode = mDatasList[position].payment_code
                    when (mDatasList[position].payment_code) {
                        "predeposit" -> {
                            payTag = 1
                        }
                        "transfer" -> {
                            payTag = 2
                        }
                        "pipay" -> {
                            payTag = 3
                        }
                    }
                    if (!TextUtils.isEmpty(mDatasList[position].handling_fee) && mDatasList[position].handling_fee != "0") {
                        try {
                            val uu: Double = mOrderd_amount * mDatasList[position].handling_fee.toDouble() / 100
                            bank_price_tv.text = TextColorUtils.setSpannableStringBuilder(
                                    "${getString(R.string.threePay)}：" + mDatasList[position].handling_fee + "%   $" + haveTwoDouble(uu),
                                    getString(R.string.threePay).length,
                                    "#060606",
                                    "#FF5000")
                            all_price_over_tv.text =
                                    TextColorUtils.setSpannableStringBuilder(
                                            "${getString(R.string.Total)} $" + haveTwoDouble(uu + mOrderd_amount),
                                            getString(R.string.Total).length,
                                            "#060606",
                                            "#FF5000")
                        } catch (e: Exception) {
                            LogUtils.e(e)
                        }
                    } else {
                        bank_price_tv.text = TextColorUtils.setSpannableStringBuilder(
                                "${getString(R.string.threePay)}： 0%   $0.00",
                                getString(R.string.threePay).length,
                                "#060606",
                                "#FF5000")
                        all_price_over_tv.text =
                                TextColorUtils.setSpannableStringBuilder(
                                        "${getString(R.string.Total)} $" + haveTwoDouble(mOrderd_amount),
                                        getString(R.string.Total).length,
                                        "#060606",
                                        "#FF5000")
                    }
                }
            }
        }
        return payListRecyAdpter
    }
}