package com.aite.awangdalibrary.ui.activity.coupon

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.fragment.collectstore.CollectStoreBean
import com.bumptech.glide.Glide

/**

 * @Auther: valy

 * @datetime: 2020/3/12

 * @desc:

 */
class CollectStoreAdapter(context: Context?) : BaseRecyAdapter<CollectStoreBean>(context) {
    override fun fixData(mMAp: Map<String, String>) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_collect_store_kotlin, parent, false))

        }
        return viewHolder!!
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_CONTENT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_TYPE_CONTENT -> {
                Glide.with(context!!).load(mDatas[position].store_avatar).into((holder as ContentViewHolder).good_iv!!)
                holder.store_name_tv?.text = mDatas[position].store_name
                holder.area_tv?.text = mDatas[position].area_info
                holder.information_tv?.text = mDatas[position].store_zy
                holder.itemView.setOnClickListener {
                    if (tClickInterface != null) {
                        tClickInterface?.getT(it, mDatas[position].store_id)
                    }
                }
                holder.delete_iv?.setOnClickListener {
                    if (mOnDeleteClickListener != null) {
                        mOnDeleteClickListener?.onClick(it, mDatas[position].store_id)
                    }
                }
            }
        }
    }

    var mOnDeleteClickListener: OnDeleteClickListener? = null

    interface OnDeleteClickListener {
        fun onClick(v: View, s: String)

    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var good_iv: ImageView? = null
        var information_tv: TextView? = null
        var store_name_tv: TextView? = null
        var area_tv: TextView? = null
        var delete_iv: ImageView? = null

        init {
            good_iv = itemView.findViewById(R.id.good_iv)
            information_tv = itemView.findViewById(R.id.information_tv)
            store_name_tv = itemView.findViewById(R.id.store_name_tv)
            area_tv = itemView.findViewById(R.id.area_tv)
            delete_iv = itemView.findViewById(R.id.delete_iv)
        }
    }
}