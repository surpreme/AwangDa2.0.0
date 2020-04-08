package com.aite.a.activity.li.activity.logistics

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.activity.li.util.DateUtil
import com.aite.a.activity.li.util.LogUtils
import com.jiananshop.a.R


/**

 * @Auther: valy

 * @datetime: 2020/3/10

 * @desc:

 */
class LogisticsRecyAdapter(context: Context?) : BaseRecyAdapter<LogisticsKotlinUIBean>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.linearlayout_layout, parent, false))
            VIEW_TYPE_CONTENT_TWO -> viewHolder = ContentViewHolderTWO(inflater.inflate(R.layout.text_layout, parent, false))
            VIEW_TYPE_TOOLBAR -> viewHolder = ToolBarViewHolder(inflater.inflate(R.layout.toolbar_layout, parent, false))
        }
        return viewHolder!!
    }

    @SuppressLint("RtlHardcoded", "InflateParams")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_TYPE_TOOLBAR -> {
                (holder as ToolBarViewHolder).tv_title?.text = mDatas[position].centerBarTitle
                (holder as ToolBarViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.gray2)!!)

                (holder as ToolBarViewHolder).iv_back?.setOnClickListener {
                    if (toolBarClickInterface != null) toolBarClickInterface!!.back()
                }

            }
            VIEW_TYPE_CONTENT -> {
                if (!mDatas[position].deliver_info.isNullOrEmpty()) {
                    for (opk in 0 until mDatas[position].deliver_info.size) {
                        try {
                            val inflater = LayoutInflater
                                    .from(context)
                            val view = inflater.inflate(
                                    R.layout.item_logitics, null)
                            val point: ImageView = view.findViewById(R.id.point)
                            val line1: View = view.findViewById(R.id.line1)
                            val line2: View = view.findViewById(R.id.line2)
                            val tv_date: TextView = view.findViewById(R.id.tv_date)
                            val tv_time: TextView = view.findViewById(R.id.tv_time)
                            val tv_express_state: TextView = view.findViewById(R.id.tv_express_state)
                            val tv_express_detail: TextView = view.findViewById(R.id.tv_express_detail)
//                            val params = line1.layoutParams
//                            val params2 = line2.layoutParams
//                            params.height=40//设置当前控件布局的高度
//                            params2.height=40//设置当前控件布局的高度
//                            line1.layoutParams = params;//将设置好的布局参数应用到控件中
//                            line2.layoutParams = params;//将设置好的布局参数应用到控件中
                            val dateHourString: String = DateUtil.timeStamp2Date(mDatas[position].deliver_info[opk].time, "MM.dd")
                            val dateMinuteString: String = DateUtil.timeStamp2Date(mDatas[position].deliver_info[opk].time, "HH:mm")
                            tv_express_state.text = mDatas[position].deliver_info[opk].context
                            tv_date.text = dateHourString
                            tv_time.text = dateMinuteString
                            if (opk == 0) {
                                point.setColorFilter(Color.parseColor("#FF9846"))
                                tv_date.setTextColor(Color.BLACK)
                                tv_time.setTextColor(Color.BLACK)
                                tv_express_state.setTextColor(Color.BLACK)
                                line1.visibility = View.GONE
                                line2.visibility = View.VISIBLE
                            } else if (opk == mDatas[position].deliver_info.size - 1) {
                                point.setColorFilter(Color.GRAY)
                                line1.visibility = View.VISIBLE
                                line2.visibility = View.GONE
                            } else {
                                point.setColorFilter(Color.GRAY)
                                line1.visibility = View.VISIBLE
                                line2.visibility = View.VISIBLE
                            }
                            (holder as ContentViewHolder).linearLayout_view?.addView(view)
                        } catch (e: Exception) {
                            LogUtils.e(e)
                        }
                    }


                }

            }
            VIEW_TYPE_CONTENT_TWO -> {
                (holder as ContentViewHolderTWO).textView?.text = mDatas[position].userInformation
                (holder as ContentViewHolderTWO).textView?.textSize = 13f
                (holder as ContentViewHolderTWO).textView?.gravity = Gravity.CENTER_VERTICAL
                (holder as ContentViewHolderTWO).textView?.setPadding(50, 0, 0, 0)
                (holder as ContentViewHolderTWO).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position].userInformation != null && mDatas[position].userInformation != "")
            VIEW_TYPE_CONTENT_TWO
        else if (mDatas[position].deliver_info != null && !mDatas[position].deliver_info.isNullOrEmpty())
            VIEW_TYPE_CONTENT
        else if (mDatas[position].centerBarTitle != null && mDatas[position].centerBarTitle != "")
            VIEW_TYPE_TOOLBAR
        else 0
    }

    /**
     * 思路是动态添加布局进去
     */
    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearLayout_view: LinearLayout? = null


        init {
            linearLayout_view = itemView.findViewById(R.id.linearLayout_view)
        }
    }

    class ContentViewHolderTWO(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null


        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    override fun fixData(mMAp: Map<String, String>) {


    }
}