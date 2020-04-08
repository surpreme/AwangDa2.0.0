package com.aite.a.activity.li.activity.surePayListKotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
class PayListRecyAdpter : RecyclerView.Adapter<PayListRecyAdpter.ViewHolder> {
    private var listFirst = ArrayList<PayListBean.PaymentBean>()
    private val context: Context?
    private val inflater: LayoutInflater

    constructor(context: Context?, paymentBeans: ArrayList<PayListBean.PaymentBean>) : super() {
        this.listFirst = paymentBeans
        this.context = context
        this.inflater = LayoutInflater.from(context)
    }

//    open fun appendDatas(listFirst: MutableList<PayListBean.PaymentBean>) {
//        this.listFirst.addAll(listFirst)
//        notifyDataSetChanged()
//    }

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
    override fun getItemCount(): Int = listFirst.size

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.recommendBackgroundIv!!.visibility = if (i == 0) View.VISIBLE else View.GONE
        viewHolder.recommendTitleTv!!.visibility = if (i == 0) View.VISIBLE else View.GONE
        viewHolder.selectIv!!.visibility = if (listFirst[i].isSelect) View.VISIBLE else View.GONE
        Glide.with(context!!).load(listFirst[i].payment_image).into(viewHolder.payImageIv!!)
        if (listFirst[i].member_info != null) {
            if (listFirst[i].member_info.available_predeposit != null) {
                viewHolder.payNameTv?.text = String.format("%s%s",
                        listFirst[i].payment_name,
                        listFirst[i].member_info.available_predeposit)
            } else viewHolder.payNameTv?.text = listFirst[i].payment_name
        } else viewHolder.payNameTv?.text = listFirst[i].payment_name
        viewHolder.itemView.setOnClickListener { v: View? ->
            if (onSelectInterface == null) throw Exception("onSelectInterfaceFail")
            if (onSelectInterface != null) {
                for (p in listFirst.indices) {
                    listFirst[p].isSelect = p == i
                }
                notifyDataSetChanged()
                onSelectInterface?.getPosition(i)
            }
        }

    }
}