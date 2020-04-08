package com.aite.awangdalibrary.ui.activity.productdetails

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.bumptech.glide.Glide

/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class GuessLikeKotlinAdapter(context: Context?) : BaseRecyAdapter<GuessLikeBean>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_guesslike_kotlin, parent, false))
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
                Glide.with(context!!).load(mDatas[position].goods_image_url).into(holder.good_list_iv!!)
                holder.goods_name_tv?.text = mDatas[position].goods_name
                holder.goods_price_tv?.text = "x${mDatas[position].goods_price}"
                holder.goods_elder_price_tv?.text = "$${mDatas[position].goods_marketprice}"
                holder.itemView.setOnClickListener {
                    if (clickLstenerInterface != null) {
                        clickLstenerInterface?.getPosition(it, position)
                    }

                }
            }
        }
    }


    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var good_list_iv: ImageView? = null
        var goods_name_tv: TextView? = null
        var goods_price_tv: TextView? = null
        var goods_elder_price_tv: TextView? = null

        init {
            good_list_iv = itemView.findViewById(R.id.good_list_iv)
            goods_name_tv = itemView.findViewById(R.id.goods_name_tv)
            goods_elder_price_tv = itemView.findViewById(R.id.goods_elder_price_tv)
            goods_price_tv = itemView.findViewById(R.id.goods_price_tv)
        }
    }


}