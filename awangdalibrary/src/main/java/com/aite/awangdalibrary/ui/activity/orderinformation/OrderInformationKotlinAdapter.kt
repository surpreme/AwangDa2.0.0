package com.aite.awangdalibrary.ui.activity.orderinformation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.Image
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.bumptech.glide.Glide
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.utils.StatusBarUtils
import com.valy.baselibrary.utils.SystemUtil
import kotlinx.android.synthetic.main.activity_login_kotlin.*

/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class OrderInformationKotlinAdapter(context: Context?) : BaseRecyAdapter<OrderInformationKotlinBean>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_order_item, parent, false))
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
                (holder as ContentViewHolder).itemView.setBackgroundColor(Color.WHITE)
                Glide.with(context!!).load(mDatas[position].goods_image).into(holder.goods_iv!!)
                holder.goods_name_tv?.text = mDatas[position].goods_name
                holder.goods_number_tv?.text = "x${mDatas[position].goods_num}"
                holder.goods_price_tv?.text = "$${mDatas[position].goods_price}"
            }
        }
    }


    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var goods_iv: ImageView? = null
        var goods_name_tv: TextView? = null
        var goods_number_tv: TextView? = null
        var goods_state_tv: TextView? = null
        var goods_price_tv: TextView? = null

        init {
            goods_iv = itemView.findViewById(R.id.goods_iv)
            goods_name_tv = itemView.findViewById(R.id.goods_name_tv)
            goods_number_tv = itemView.findViewById(R.id.goods_number_tv)
            goods_state_tv = itemView.findViewById(R.id.goods_state_tv)
            goods_price_tv = itemView.findViewById(R.id.goods_price_tv)
        }
    }


}