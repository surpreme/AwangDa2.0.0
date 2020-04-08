package com.aite.awangdalibrary.ui.activity.foot

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.productdetails.ProductDetailsKotlinActivity
import com.bumptech.glide.Glide
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.basekotlin.BaseApp
import com.valy.baselibrary.xrbus.RxBus

/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class FootKotlinAdapter(context: Context?) : BaseRecyAdapter<FootListBean>(context) {
    private var isShow: Boolean = false
    private var isAllSeleted: Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.item_foot_list, parent, false))
        }
        return viewHolder!!
    }

    override fun fixData(mMAp: Map<String, String>) {
        if (mMAp.containsKey("isSeleted"))
            isShow = mMAp["isSeleted"].equals("true")
        if (mMAp.containsKey("isAllSeleted"))
            isAllSeleted = mMAp["isAllSeleted"].equals("true")
        notifyDataSetChanged()
        /*  for (bean in mDatas) {
              for (s in bean.goods_list) {
                  s.isSeleted =
              }

          }*/

    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position].date != null) {
            VIEW_TYPE_CONTENT
        } else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            //时间戳
            VIEW_TYPE_CONTENT -> {
                (holder as ContentViewHolder).itemView.setBackgroundColor(context?.resources?.getColor(R.color.white)!!)
                val mI = ItemFootAdapterSon(context, mDatas[position].goods_list, this.isShow, this.isAllSeleted)
                holder.recycler_view?.adapter = mI
                holder.recycler_view?.addItemDecoration(BaseItemDecoration(context))
                holder.recycler_view?.layoutManager = LinearLayoutManager(context)

            }

        }
    }
}


class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recycler_view: RecyclerView? = null

    init {
        recycler_view = itemView.findViewById(R.id.foot_recy)
    }
}

private class ItemFootAdapterSon : RecyclerView.Adapter<ItemFootAdapterSon.ItemViewHolder> {
    private var context: Context?
    private val inflater: LayoutInflater
    private var isSelected = false
    private var isAllSelected = false
    private var mSonBean = ArrayList<FootListBean.GoodsListBean>()

    constructor(context: Context?, mSonBean: List<FootListBean.GoodsListBean>, isSelected: Boolean, isAllSelected: Boolean) {
        this.isSelected = isSelected
        this.isAllSelected = isAllSelected
        this.context = context
        this.inflater = LayoutInflater.from(context)
        this.mSonBean = mSonBean as ArrayList<FootListBean.GoodsListBean>
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_goodsnamemi: TextView? = null
        var tv_goodsprice: TextView? = null
        var iv_goodstu: ImageView? = null
        var iv_close: ImageView? = null
        var selected_rb: CheckBox? = null
        var rl_zhuitem: RelativeLayout? = null

        init {
            selected_rb = itemView.findViewById(R.id.selected_rb)
            tv_goodsnamemi = itemView.findViewById(R.id.tv_goodsnamemi)
            tv_goodsprice = itemView.findViewById(R.id.tv_goodsprice)
            iv_goodstu = itemView.findViewById(R.id.iv_goodstu)
            iv_close = itemView.findViewById(R.id.iv_close)
            rl_zhuitem = itemView.findViewById(R.id.rl_zhuitem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(inflater.inflate(R.layout.myfootprint_item, parent, false))
    }

    override fun getItemCount(): Int = mSonBean.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.setBackgroundColor(Color.WHITE)
        Glide.with(context!!).load(mSonBean[position].goods_image).into(holder.iv_goodstu!!)
        holder.tv_goodsnamemi?.text = mSonBean[position].goods_name
        holder.tv_goodsprice?.text = mSonBean[position].goods_promotion_price
        holder.selected_rb?.visibility = if (isSelected) View.VISIBLE else View.GONE
        holder.selected_rb?.isChecked = isAllSelected
        holder.iv_close?.visibility = if (isSelected) View.GONE else View.VISIBLE
        holder.selected_rb?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                FootSelectListBean.mSelectList[mSonBean[position].goods_id] = mSonBean[position].goods_id
            } else {
                if (FootSelectListBean.mSelectList.containsKey(mSonBean[position].goods_id)) {
                    FootSelectListBean.mSelectList.remove(mSonBean[position].goods_id)

                }
            }
        }
        holder.iv_close?.setOnClickListener {
            RxBus.build().post(FootDeleteBus().builder(position, mSonBean[position].goods_id))
        }
        holder.itemView.setOnClickListener {

            val intent = Intent(context, ProductDetailsKotlinActivity::class.java)
            intent.putExtra("goods_id", mSonBean[position].goods_id)
            intent.putExtra("key", BaseApp.getKey())
            context?.startActivity(intent)

        }

    }

}