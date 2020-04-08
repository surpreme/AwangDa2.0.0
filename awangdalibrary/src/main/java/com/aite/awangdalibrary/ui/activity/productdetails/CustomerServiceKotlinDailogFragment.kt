package com.aite.awangdalibrary.ui.activity.productdetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valy.baselibrary.basekotlin.BaseDialogFragment
import com.valy.baselibrary.dialogfragment.SureTalkDialogFragment
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.LogUtils
import com.valy.baselibrary.utils.OnClickLstenerInterface
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.dailog_frament_customer_service.*
import org.json.JSONObject
import java.util.*

/**
 * @Auther: valy
 * @datetime: 2020/2/29
 * @desc:
 */
class CustomerServiceKotlinDailogFragment : BaseDialogFragment() {
    var mStore_id: String? = ""
    var customerServiceRecyAdapter: CustomerServiceKotlinRecyAdapter? = null
    val shopChatBeansList = ArrayList<StoreCallcenterDialogBean>()
    fun newInstance(store_id: String?): CustomerServiceKotlinDailogFragment {
        val fragment = CustomerServiceKotlinDailogFragment()
        val bundle = Bundle()
        bundle.putString("store_id", store_id)
        fragment.arguments = bundle
        return fragment
    }


    override fun setContentView(): Int = R.layout.dailog_frament_customer_service

    override fun initViews(view: View) {
        if (arguments != null) {
            val bundle = arguments
            if (bundle!!["store_id"] != null) {
                mStore_id = bundle.getString("store_id")
            } else {
                dismiss()
                onDestroy()
            }
        } else {
            dismiss()
            onDestroy()
        }
        customerServiceRecyAdapter = CustomerServiceKotlinRecyAdapter(context, shopChatBeansList)
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        customerServiceRecyAdapter?.setClickInterface {
            if (shopChatBeansList[it].type != null && shopChatBeansList[it].type == "im") {
                ARouter.getInstance().build("/jn/DialogueActivity").withString("member_id", shopChatBeansList[it].num).navigation()
                dismiss()
            }
        }
        recycler_view.adapter = customerServiceRecyAdapter
    }

    override fun initDatas() {
        getShopChat(mStore_id, "zh_cn")
    }

    @SuppressLint("CheckResult")
    private fun getShopChat(store_id: String?, lang_type: String) {
        RetrofitBuilder
                .build()
                .getShopChatData(store_id, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {
                        Toast.makeText(mContext, error, Toast.LENGTH_LONG).show()
                        LogUtils.e(error)
                    }

                    override fun success(datas: JSONObject) {
                        val chatArray = datas.optJSONArray("store_callcenter")
                        val mCallcenterListBean = Gson().fromJson<List<GoodsInformationBean.StoreCallcenterBean>>(chatArray.toString(),
                                object : TypeToken<List<GoodsInformationBean.StoreCallcenterBean>>() {}.type)
                        var isHaveChat = false
                        if (mCallcenterListBean != null) {
                            for (i in mCallcenterListBean.indices) {
                                val xxxx = StoreCallcenterDialogBean()
                                xxxx.title = mCallcenterListBean[i].type_name
                                shopChatBeansList.add(xxxx)
                                if (mCallcenterListBean[i].callcenter_list != null && !mCallcenterListBean.get(i).callcenter_list.isEmpty() && mCallcenterListBean.get(i).callcenter_list.size > 0) {
                                    isHaveChat = true
                                    for (p in mCallcenterListBean[i].callcenter_list.indices) {
                                        val uuuuuuu = StoreCallcenterDialogBean()
                                        uuuuuuu.img = mCallcenterListBean[i].callcenter_list[p].img
                                        uuuuuuu.name = mCallcenterListBean[i].callcenter_list[p].name
                                        uuuuuuu.num = mCallcenterListBean[i].callcenter_list[p].num
                                        uuuuuuu.type = mCallcenterListBean[i].callcenter_list[p].type
                                        shopChatBeansList.add(uuuuuuu)
                                    }
                                }
                            }
                            shopChatBeansList.toString()
                            customerServiceRecyAdapter!!.notifyDataSetChanged()
                        }
                        if (!isHaveChat) {
//                            val mSureTalkDialogFragment = SureTalkDialogFragment().newInstance("本店暂无客服", "确定")
//                            mSureTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
//                                mSureTalkDialogFragment?.dismiss()
//                            }
//                            mSureTalkDialogFragment?.show(childFragmentManager, "mSureTalkDialogFragment")
                            Toast.makeText(mContext, "本店暂无客服", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }

                    }
                }, Consumer {
                    LogUtils.e(it)
                })
    }


    /**
     * 设置位置在底部 而且设置背景为透明
     */
    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
        params?.gravity = Gravity.CENTER
        //        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params?.width = 800
        params?.height = 550
        window?.attributes = params
        //设置背景透明
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}