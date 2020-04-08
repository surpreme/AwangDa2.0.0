package com.aite.awangdalibrary.ui.fragment

import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.transfer.TransferUIAdapter
import com.bumptech.glide.Glide
import com.valy.baselibrary.utils.SystemUtil
import kotlinx.android.synthetic.main.activity_login_kotlin.*

/**

 * @Auther: valy

 * @datetime: 2020/3/13

 * @desc:

 */
class MemberCenterAdapter(context: Context?) : BaseRecyAdapter<MemberCenterUIBean>(context) {
    val VIEW_TYPE_CONTENT_FOUR: Int = 6//tips
    val VIEW_TYPE_CONTENT_THREE: Int = 5//tips


    override fun fixData(mMAp: Map<String, String>) {
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position].informationBean != null)
            VIEW_TYPE_CONTENT
        else if (mDatas[position].tips != null && mDatas[position].tips != "") {
            VIEW_TYPE_CONTENT_FOUR
        } else if (mDatas[position].orderBean != null && mDatas[position].orderBean.isNotEmpty() && mDatas[position].type == 1)
            VIEW_TYPE_CONTENT_TWO
        else if (mDatas[position].orderBean != null && mDatas[position].orderBean.isNotEmpty() && mDatas[position].type == 2)
            VIEW_TYPE_CONTENT_THREE
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT ->
                viewHolder = InformationViewHolder(inflater.inflate(R.layout.item_membercenter_information, parent, false))
            VIEW_TYPE_CONTENT_TWO ->
                viewHolder = OrderViewHolder(inflater.inflate(R.layout.item_member_recycler_layout, parent, false))
            VIEW_TYPE_CONTENT_THREE ->
                viewHolder = OrderViewHolder(inflater.inflate(R.layout.item_member_recycler_layout, parent, false))
            VIEW_TYPE_CONTENT_FOUR ->
                viewHolder = TextViewHolder(inflater.inflate(R.layout.item_text, parent, false))

        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            VIEW_TYPE_CONTENT -> {
                (holder as InformationViewHolder).user_name_tv?.text = mDatas[position].informationBean.name
                (holder as InformationViewHolder).integral_content_tv?.text = mDatas[position].informationBean.integral
                (holder as InformationViewHolder).gold_content_tv?.text = mDatas[position].informationBean.gold
                (holder as InformationViewHolder).balance_content_tv?.text = mDatas[position].informationBean.balance
                context?.let { Glide.with(it).load(mDatas[position].informationBean.iconUrl).into((holder as InformationViewHolder).uselogo_iv!!) }
                holder.setting_iv?.setOnClickListener {
                }

            }
            VIEW_TYPE_CONTENT_TWO -> {
                if (mDatas[position].type == 1)
                    (holder as OrderViewHolder).item_member_recycler_view?.layoutManager = GridLayoutManager(context, 5)
                if (mDatas[position].type == 2)
                    (holder as OrderViewHolder).item_member_recycler_view?.layoutManager = GridLayoutManager(context, 4)
                (holder as OrderViewHolder).item_member_recycler_view?.adapter = ItemMemberCenterAdapter(context, mDatas[position].orderBean)

            }
            VIEW_TYPE_CONTENT_THREE -> {
                if (mDatas[position].type == 1)
                    (holder as OrderViewHolder).item_member_recycler_view?.layoutManager = GridLayoutManager(context, 5)
                if (mDatas[position].type == 2)
                    (holder as OrderViewHolder).item_member_recycler_view?.layoutManager = GridLayoutManager(context, 4
                    )
                (holder as OrderViewHolder).item_member_recycler_view?.adapter = ItemMemberCenterAdapter(context, mDatas[position].orderBean)

            }
            VIEW_TYPE_CONTENT_FOUR -> {
                (holder as TextViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                holder.itemView.setPadding(SystemUtil.dip2px(context, 10f), 0, 0, 0)
                holder.textView?.text = mDatas[position].tips
                holder.textView?.gravity = Gravity.CENTER_VERTICAL
                holder.textView?.setTextColor(context.resources?.getColor(R.color.black)!!)
            }
        }
    }

    class InformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uselogo_iv: ImageView? = null
        var setting_iv: ImageView? = null
        var user_name_tv: TextView? = null
        var gold_content_tv: TextView? = null
        var balance_content_tv: TextView? = null
        var integral_content_tv: TextView? = null

        init {
            setting_iv = itemView.findViewById(R.id.setting_iv)
            uselogo_iv = itemView.findViewById(R.id.uselogo_iv)
            user_name_tv = itemView.findViewById(R.id.user_name_tv)
            gold_content_tv = itemView.findViewById(R.id.gold_content_tv)
            balance_content_tv = itemView.findViewById(R.id.balance_content_tv)
            integral_content_tv = itemView.findViewById(R.id.integral_content_tv)
        }
    }

    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView? = null
        var title: TextView? = null

        init {
            icon = itemView.findViewById(R.id.icon)
            title = itemView.findViewById(R.id.title)
        }
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_member_recycler_view: RecyclerView? = null

        init {
            item_member_recycler_view = itemView.findViewById(R.id.item_member_recycler_view)

        }
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null

        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    private class ItemMemberCenterAdapter : RecyclerView.Adapter<ItemMemberCenterAdapter.ItemViewHolder> {
        private var context: Context?
        private val inflater: LayoutInflater
        private var mOrderBean = ArrayList<MemberCenterUIBean.OrderBean>()

        constructor(context: Context?, mOrderBean: List<MemberCenterUIBean.OrderBean>) {
            this.context = context
            this.inflater = LayoutInflater.from(context)
            this.mOrderBean = mOrderBean as ArrayList<MemberCenterUIBean.OrderBean>
        }

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var icon: ImageView? = null
            var title: TextView? = null

            init {
                icon = itemView.findViewById(R.id.icon)
                title = itemView.findViewById(R.id.title)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(inflater.inflate(R.layout.item_membercenter_icon_title, parent, false));
        }

        override fun getItemCount(): Int = mOrderBean.size
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.title?.text = mOrderBean[position].name
            holder.icon?.setImageDrawable(context?.resources?.getDrawable(mOrderBean[position].iconId))
            holder.itemView.setBackgroundColor(Color.WHITE)
            holder.itemView.setPadding(5, SystemUtil.dip2px(context, 9f), 5, SystemUtil.dip2px(context, 9f))
            if (mOrderBean.size == 7 || mOrderBean.size == 8) {
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(SystemUtil.dip2px(context, 18f), SystemUtil.dip2px(context, 18f))
                holder.icon?.layoutParams = params
            } else {
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(SystemUtil.dip2px(context, 31f), SystemUtil.dip2px(context, 31f))
                holder.icon?.layoutParams = params
            }
//            holder.icon?.scaleType = ImageView.ScaleType.MATRIX
//                holder.icon?.setPadding(10, 10, 10, 10)

            holder.itemView.setOnClickListener {
            }
        }
    }


}