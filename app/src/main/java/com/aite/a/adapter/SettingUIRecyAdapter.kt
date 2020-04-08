package com.aite.a.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.bean.SettingFirstBean
import com.bumptech.glide.Glide
import com.jiananshop.a.R
import com.valy.baselibrary.utils.OnClickLstenerInterface

/**

 * @Auther: valy

 * @datetime: 2020/3/4

 * @desc:

 */

open class SettingUIRecyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private val mContext: Context
    private val listFirst: List<SettingFirstBean>?
    private val inflater: LayoutInflater
    private val VIEW_TYPE_ONE: Int = 1
    private val VIEW_TYPE_TWO: Int = 2
    private val VIEW_TYPE_THREE: Int = 3

    constructor(mContext: Context?, listFirst: List<SettingFirstBean>?) : super() {
        this.mContext = mContext!!
        this.listFirst = listFirst
        this.inflater = LayoutInflater.from(mContext)
    }

    var clickInterface: OnClickLstenerInterface.OnRecyClickInterface? = null
    var toolBarClickInterface: OnClickLstenerInterface.OnToolBarClickInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_ONE -> viewHolder = ItemViewHolder(inflater.inflate(R.layout.item_setting_recy, parent, false))
            VIEW_TYPE_TWO -> viewHolder = BottomViewHolder(inflater.inflate(R.layout.text_layout, parent, false))
            VIEW_TYPE_THREE -> viewHolder = ToolBarViewHolder(inflater.inflate(R.layout.toolbar_layout, parent, false))
        }
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return listFirst?.size ?: 0
    }


    override fun getItemViewType(position: Int): Int {
        if (listFirst?.get(position)?.title != null && !listFirst.get(position).title.equals(""))
            return VIEW_TYPE_ONE
        else if (listFirst?.get(position)?.bottomStr != null && !listFirst.get(position).bottomStr.equals(""))
            return VIEW_TYPE_TWO
        else if (listFirst?.get(position)?.centerBarTitle != null && !listFirst.get(position).centerBarTitle.equals(""))
            return VIEW_TYPE_THREE
        else return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_TYPE_ONE -> {
                Glide.with(mContext).load(listFirst?.get(position)?.imgId).into((holder as ItemViewHolder).ivImg!!)
                (holder as ItemViewHolder).tvTitle?.text = listFirst?.get(position)?.title
                (holder as ItemViewHolder).itemView.setOnClickListener {
                    if (clickInterface != null) clickInterface!!.getPosition(position)
                }

            }
            VIEW_TYPE_TWO -> {
                (holder as BottomViewHolder).tvBottom?.text = listFirst?.get(position)?.bottomStr
                (holder as BottomViewHolder).tvBottom?.setBackgroundColor(mContext.resources.getColor(R.color.orangeff6c00))
                (holder as BottomViewHolder).tvBottom?.setTextColor(mContext.resources.getColor(R.color.white))
                (holder as BottomViewHolder).itemView.setOnClickListener {
                    if (clickInterface != null) clickInterface!!.getPosition(position)
                }
//                val layoutParams: LinearLayout.LayoutParams = (holder as BottomViewHolder).tvBottom?.layoutParams as LinearLayout.LayoutParams
//                layoutParams.topMargin = 50
//                (holder as BottomViewHolder).tvBottom?.layoutParams = layoutParams;
            }
            VIEW_TYPE_THREE -> {
                (holder as ToolBarViewHolder).tv_title?.text = listFirst?.get(position)?.centerBarTitle
                (holder as ToolBarViewHolder).itemView.setBackgroundColor(mContext.resources.getColor(R.color.white))
                (holder as ToolBarViewHolder).tv_right_title?.text = listFirst?.get(position)?.rightBarTitle
                if (listFirst?.get(position)?.rightBarTitleColor != null && !listFirst.get(position).rightBarTitleColor.equals(""))
                    (holder as ToolBarViewHolder).tv_right_title?.setTextColor(Color.parseColor(listFirst.get(position).rightBarTitleColor))
                (holder as ToolBarViewHolder).iv_back?.setOnClickListener {
                    if (toolBarClickInterface != null) toolBarClickInterface!!.back()
                }
                (holder as ToolBarViewHolder).tv_right_title?.setOnClickListener { v ->
                    if (toolBarClickInterface != null) toolBarClickInterface!!.rightClick(v)
                }
            }
        }

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var ivImg: ImageView? = null

        init {
            tvTitle = itemView.findViewById(R.id.setting_tv)
            ivImg = itemView.findViewById(R.id.setting_icon)
        }
    }

    class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvBottom: TextView? = null

        init {
            tvBottom = itemView.findViewById(R.id.textView)
        }
    }

    class ToolBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_back: ImageView? = null
        var tv_title: TextView? = null
        var tv_right_title: TextView? = null

        init {
            iv_back = itemView.findViewById(R.id.iv_back)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_right_title = itemView.findViewById(R.id.tv_right_title)
        }
    }


}