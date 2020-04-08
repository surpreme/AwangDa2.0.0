package com.aite.a.activity.li.activity.surePayListKotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.bean.PayListBean
import com.bumptech.glide.Glide
import com.jiananshop.a.R
import com.valy.baselibrary.utils.OnClickLstenerInterface
import java.util.ArrayList

/**
 * @Auther: valy
 * @datetime: 2020/3/6
 * @desc:
 */
class PayListRecyAdpter2(context: Context?) : BaseRecyAdapter<PayListBean.PaymentBean>(context) {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recommendBackgroundIv: ImageView? = null
        var recommendTitleTv: TextView? = null
        var payImageIv: ImageView? = null
        var payNameTv: TextView? = null
        var selectIv: ImageView? = null

        init {
            recommendBackgroundIv = itemView.findViewById(R.id.recommend_background_iv)
            recommendTitleTv = itemView.findViewById(R.id.recommend_title_tv)
            payImageIv = itemView.findViewById(R.id.pay_image_iv)
            payNameTv = itemView.findViewById(R.id.pay_name_tv)
            selectIv = itemView.findViewById(R.id.select_iv)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_recy_pay_order_three, parent, false)
        return ViewHolder(view)
    }

    var onSelectInterface: OnClickLstenerInterface.OnRecyClickInterface? = null


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, i: Int) {
        (holder as ViewHolder).recommendBackgroundIv!!.visibility = if (i == 0) View.VISIBLE else View.GONE
        (holder as ViewHolder).recommendTitleTv!!.visibility = if (i == 0) View.VISIBLE else View.GONE
        (holder as ViewHolder).selectIv!!.visibility = if (mDatas[i].isSelect) View.VISIBLE else View.GONE
        Glide.with(context!!).load(mDatas[i].payment_image).into((holder as ViewHolder).payImageIv!!)
        if (mDatas[i].member_info != null) {
            if (mDatas[i].member_info.available_predeposit != null) {
                (holder as ViewHolder).payNameTv?.text = String.format("%s%s",
                        mDatas[i].payment_name,
                        mDatas[i].member_info.available_predeposit)
            } else (holder as ViewHolder).payNameTv?.text = mDatas[i].payment_name
        } else (holder as ViewHolder).payNameTv?.text = mDatas[i].payment_name
        (holder as ViewHolder).itemView.setOnClickListener { v: View? ->
            if (onSelectInterface == null) throw Exception("onSelectInterfaceFail")
            if (onSelectInterface != null) {
                for (p in mDatas.indices) {
                    mDatas[p].isSelect = p == i
                }
                notifyDataSetChanged()
                onSelectInterface?.getPosition(i)
            }
        }
    }

    override fun fixData(mMAp: Map<String, String>) {

    }


}