package com.aite.a.activity.li.fragment.rechargedetails

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.activity.li.util.DateUtil
import com.jiananshop.a.R
import com.valy.baselibrary.utils.OnClickLstenerInterface

/**

 * @Auther: valy

 * @datetime: 2020/3/17

 * @desc:

 */
class RechargeDetailsRecyAdapter(context: Context?) : BaseRecyAdapter<RechargeDetailsBean>(context) {
    override fun fixData(mMAp: Map<String, String>) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_recharge_details, parent, false))
        }
        return viewHolder!!
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_CONTENT
    }

    var mOnThingClickInterface: OnClickLstenerInterface.OnThingClickInterface? = null
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContentViewHolder).order_number_tv?.text = "订单编号：${mDatas[position].pdr_sn}"
        (holder as ContentViewHolder).price_information_tv?.text = "+ ${mDatas[position].pdr_amount}"
        (holder as ContentViewHolder).order_date_tv?.text = DateUtil.timeStamp2Date(mDatas[position].pdr_add_time, null)
        if (mDatas[position].pdr_payment_state == "0") {
            (holder as ContentViewHolder).pay_order_tv?.text = "未支付"
        } else {
            (holder as ContentViewHolder).pay_order_tv?.text = "已支付"

        }
        if (mDatas[position].pdr_payment_state == "0") {
            if (mOnThingClickInterface != null) {
                holder.pay_order_tv?.setOnClickListener {
                    mOnThingClickInterface?.getThing(mDatas[position].pdr_sn)

                }
            }
        }

    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var order_number_tv: TextView? = null
        var order_date_tv: TextView? = null
        var price_information_tv: TextView? = null
        var pay_order_tv: TextView? = null

        init {
            order_number_tv = itemView.findViewById(R.id.order_number_tv)
            pay_order_tv = itemView.findViewById(R.id.pay_order_tv)
            order_date_tv = itemView.findViewById(R.id.order_date_tv)
            price_information_tv = itemView.findViewById(R.id.price_information_tv)
        }
    }
}