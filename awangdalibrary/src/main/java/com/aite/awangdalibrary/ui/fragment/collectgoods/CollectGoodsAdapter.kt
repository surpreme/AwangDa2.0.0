package com.aite.awangdalibrary.ui.activity.coupon

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.fragment.collectgoods.CollectGoodsBean
import com.bumptech.glide.Glide

/**

 * @Auther: valy

 * @datetime: 2020/3/12

 * @desc:

 */
class CollectGoodsAdapter(context: Context?) : BaseRecyAdapter<CollectGoodsBean>(context) {
    override fun fixData(mMAp: Map<String, String>) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_collect_kotlin, parent, false))

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
                Glide.with(context!!).load(mDatas[position].goods_image_url).into((holder as ContentViewHolder).good_iv!!)
                (holder as ContentViewHolder).goods_name_tv?.text = mDatas[position].goods_name
                holder.price_tv?.text = "￥${mDatas[position].goods_price}"
                if (mOnClickCollectInterface != null) {
                    holder.itemView.setOnClickListener {
                        mOnClickCollectInterface?.onGoods(it, mDatas[position].goods_id)
                    }
                    holder.store_iv?.setOnClickListener {
                        mOnClickCollectInterface?.onShop(it, mDatas[position].store_id)
                    }
                    holder.delete_iv?.setOnClickListener {
                        mOnClickCollectInterface?.onDelete(it, mDatas[position].goods_id)
                    }
                }
            }
        }
    }

    var mOnClickCollectInterface: OnClickCollectInterface? = null

    interface OnClickCollectInterface {
        fun onShop(v: View, s: String)
        fun onGoods(v: View, s: String)
        fun onDelete(v: View, s: String)
    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var good_iv: ImageView? = null
        var goods_name_tv: TextView? = null
        var price_tv: TextView? = null
        var store_iv: ImageView? = null
        var delete_iv: ImageView? = null

        init {
            good_iv = itemView.findViewById(R.id.good_iv)
            price_tv = itemView.findViewById(R.id.price_tv)
            goods_name_tv = itemView.findViewById(R.id.goods_name_tv)
            store_iv = itemView.findViewById(R.id.store_iv)
            delete_iv = itemView.findViewById(R.id.delete_iv)
        }
    }
}