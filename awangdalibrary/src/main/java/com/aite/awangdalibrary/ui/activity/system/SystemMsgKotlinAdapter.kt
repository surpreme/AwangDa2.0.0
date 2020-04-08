package com.aite.awangdalibrary.ui.activity.system

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
import com.valy.baselibrary.utils.ClutterUtils

/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class SystemMsgKotlinAdapter : BaseRecyAdapter<SystemMsgKotlinBean> {
    private var mType = 1

    constructor(context: Context?, s: Int) : super(context) {
        mType = s
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_system_msg_kotlin, parent, false))
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
                when (mType) {
                    1 -> {
                        Glide.with(context!!).load(R.drawable.msg_order_kotlin).into(holder.iv_photo!!)
                        holder.tv_msg_name?.text = mDatas[position].from_member_name

                    }
                    2 -> {
                        Glide.with(context!!).load(R.drawable.msg_system_kotlin).into(holder.iv_photo!!)
                        holder.tv_msg_name?.text = "系统消息"
                    }
                    3 -> {
                        Glide.with(context!!).load(R.drawable.msg_order_kotlin).into(holder.iv_photo!!)
                        holder.tv_msg_name?.text = mDatas[position].from_member_name
                    }
                }
                holder.tv_msg_time?.text = ClutterUtils.TimeStamp2Date(mDatas[position].message_update_time, "yy-MM-dd HH:mm")
                holder.tv_msg_count?.visibility = if (mDatas[position].message_open == "0") View.VISIBLE else View.GONE
                holder.itemView.setOnClickListener {
                    mDatas[position].message_open = "1"
                    notifyItemChanged(position)
                    clickLstenerInterface?.getPosition(it, position)
                }
            }
        }
    }


    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_photo: ImageView? = null
        var tv_msg_name: TextView? = null
        var tv_msg_content: TextView? = null
        var tv_msg_time: TextView? = null
        var tv_msg_count: TextView? = null

        init {
            iv_photo = itemView.findViewById(R.id.iv_photo)
            tv_msg_name = itemView.findViewById(R.id.tv_msg_name)
            tv_msg_content = itemView.findViewById(R.id.tv_msg_content)
            tv_msg_time = itemView.findViewById(R.id.tv_msg_time)
            tv_msg_count = itemView.findViewById(R.id.tv_msg_count)
        }
    }


}