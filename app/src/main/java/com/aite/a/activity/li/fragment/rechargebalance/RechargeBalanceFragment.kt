package com.aite.a.activity.li.fragment.rechargebalance

import android.annotation.SuppressLint
import android.content.Intent
import android.text.InputFilter
import android.view.View
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface
import com.aite.a.activity.li.activity.surePayListKotlin.SurePayListKotlinActivity
import com.aite.a.activity.li.mvp.RetrofitClient
import com.aite.a.base.Mark
import com.aite.mainlibrary.basekotlin.BaseFragment
import com.jiananshop.a.R
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.view.CashierInputFilter
import kotlinx.android.synthetic.main.fragment_recharge_balance.*
import org.json.JSONObject


/**

 * @Auther: valy

 * @datetime: 2020/3/17

 * @desc:

 */
class RechargeBalanceFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_recharge_balance

    override fun initViews(view: View) {
        val filters = arrayOf<InputFilter>(CashierInputFilter())
        ed_je.filters = filters //设置金额输入的过滤器，保证只能输入金额类型
        tv_zxsubmit.setOnClickListener {
            getSn(Mark.State.UserKey, ed_je.text.toString(), "zh_cn")
        }

    }

    override fun initDatas() {
    }

    @SuppressLint("CheckResult")
    fun getSn(key: String, pdr_amount: String, lang_type: String) {
        RetrofitClient.getInstance().retrofit.create(RetrofitInterface::class.java)
                .onRechargePayNumber(key, pdr_amount, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe {
                    val jsonObject = JSONObject(it.string())
                    val code: String = jsonObject.optString("code")
                    val datas: JSONObject = jsonObject.optJSONObject("datas")
                    if (datas != null) {
                        val error = datas.optString("error")
                        if (!error.isNullOrEmpty()) {
                            showToast(error)
                        } else {
                            val pay_sn = datas.optString("pay_sn")
                            if (!pay_sn.isNullOrEmpty()) {
                                val intent = Intent(mContext, SurePayListKotlinActivity::class.java)
                                intent.putExtra("PAY_SN", pay_sn)
                                intent.putExtra("type", it.toString())
                                startActivity(intent)
                            }
                        }

                    }

                }

    }
}