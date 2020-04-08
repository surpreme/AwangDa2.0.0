package com.aite.awangdalibrary.ui.activity.coupon

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.findpassword.FindPassWordUIAdapter
import com.aite.awangdalibrary.ui.activity.productdetails.GoodsInformationBean
import com.valy.baselibrary.utils.LogUtils

/**

 * @Auther: valy

 * @datetime: 2020/3/12

 * @desc:

 */
class CouponUIAdapter(context: Context?) : BaseRecyAdapter<GoodsInformationBean.VouchersBean>(context) {

    override fun fixData(mMAp: Map<String, String>) {
        super.fixData(mMAp)
        mDatas[mMAp["x"]?.toInt()!!].isCan_exchange = true
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_coupon_unused, parent, false))
        }
        return viewHolder!!
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_CONTENT
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_TYPE_CONTENT -> {
                (holder as ContentViewHolder).itemView.setPadding(20, 0, 20, 0)
                holder.price_tv?.text = "$${mDatas[position].voucher_t_price}"
                holder.date_tv?.text = "有效期至 :${mDatas[position].voucher_t_end_date_format}"
                holder.name_tv?.text = "满${mDatas[position].voucher_t_limit}元可用"
                if (!mDatas[position].isCan_exchange) {
                    holder.received_iv?.visibility = View.VISIBLE
                    holder.receive_tv?.visibility = View.GONE
                    holder.itemView.alpha = 0.6f
                } else {
                    holder.received_iv?.visibility = View.GONE
                    holder.receive_tv?.visibility = View.VISIBLE
                    holder.itemView.alpha = 1.0f
                }
                holder.receive_tv?.setOnClickListener {
                    if (clickLstenerInterface != null) {
                        clickLstenerInterface?.getPosition(it, position)
                    }
                }
            }

        }
    }


    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var price_tv: TextView? = null
        var information_tv: TextView? = null
        var name_tv: TextView? = null
        var date_tv: TextView? = null
        var receive_tv: TextView? = null
        var received_iv: TextView? = null

        init {
            price_tv = itemView.findViewById(R.id.price_tv)
            information_tv = itemView.findViewById(R.id.information_tv)
            name_tv = itemView.findViewById(R.id.name_tv)
            date_tv = itemView.findViewById(R.id.date_tv)
            receive_tv = itemView.findViewById(R.id.receive_tv)
            received_iv = itemView.findViewById(R.id.received_iv)
        }
    }
}