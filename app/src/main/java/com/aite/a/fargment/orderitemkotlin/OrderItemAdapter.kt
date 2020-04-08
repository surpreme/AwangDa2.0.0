package com.aite.awangdalibrary.ui.activity.coupon

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.ComplaintsApplyForActivity
import com.aite.a.activity.GoodsSevaluationActivity
import com.aite.a.activity.OrderRefundActivity
import com.aite.a.activity.li.activity.ShopHomeActivity
import com.aite.a.activity.li.activity.logistics.LogisticsKotlinActivity
import com.aite.a.activity.li.activity.surePayListKotlin.SurePayListKotlinActivity
import com.aite.a.activity.li.allorder.CancelOrderBus
import com.aite.a.activity.li.allorder.DeleteOrderBus
import com.aite.a.activity.li.allorder.SureGotGoodsOrderBus
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.base.Mark
import com.aite.a.fargment.orderitemkotlin.OrderItemBean
import com.aite.awangdalibrary.ui.activity.orderinformation.OrderInformationKotlinActivity
import com.aite.awangdalibrary.ui.activity.productdetails.ProductDetailsKotlinActivity
import com.bumptech.glide.Glide
import com.jiananshop.a.R
import com.valy.baselibrary.xrbus.RxBus

/**

 * @Auther: valy

 * @datetime: 2020/3/12

 * @desc:class OrderItemAdapter(context: Context?) : BaseRecyAdapter<OrderItemBean>(context) {


 */
class OrderItemAdapter(context: Context?, type: Int) : BaseRecyAdapter<OrderItemBean>(context) {
    private var mType: Int = 1

    init {
        mType = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            VIEW_TYPE_CONTENT -> viewHolder = ContentViewHolder(inflater.inflate(R.layout.order_list_group_item_kotlin, parent, false))
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
                holder.add_time?.text = context?.getString(R.string.order_reminder2).toString() + mDatas[position].order_sn
                val mItemShopAdapter = ItemShopAdapter(context, mDatas[position].order_list, mType)
                holder.order_list_group?.adapter = mItemShopAdapter
                holder.order_list_group?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            }
        }
    }


    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var add_time: TextView? = null
        var tv_deleteorder: TextView? = null
        var bt_pay: Button? = null
        var order_list_group: RecyclerView? = null


        init {
            add_time = itemView.findViewById(R.id.add_time)
            tv_deleteorder = itemView.findViewById(R.id.tv_deleteorder)
            bt_pay = itemView.findViewById(R.id.bt_pay)
            order_list_group = itemView.findViewById(R.id.order_list_group)
        }
    }

    private class ItemShopAdapter : RecyclerView.Adapter<ItemShopAdapter.ItemViewHolder> {
        private var context: Context?
        private val inflater: LayoutInflater
        private var mOrderBean = ArrayList<OrderItemBean.OrderListBean>()
        private var mItemType = 1

        constructor(context: Context?, mOrderBean: List<OrderItemBean.OrderListBean>, type: Int) {
            this.context = context
            this.inflater = LayoutInflater.from(context)
            this.mOrderBean = mOrderBean as ArrayList<OrderItemBean.OrderListBean>
            this.mItemType = type
        }

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            /**
             *
            order_cancel 取消订单
            order_refund_cancel 退款取消
            order_complain 投诉
            order_receive 收货
            order_logistics 查看物流
            order_evaluation 评价
            order_datails 订单详情
            detele_bt 删除订单
            order_pay 确认支付
            qr_iv 取件码
            order_refund_tv 订单退款
             */
            var order_cancel: TextView? = null
            var order_refund_tv: TextView? = null
            var order_refund_cancel: TextView? = null
            var order_complain: TextView? = null
            var order_receive: TextView? = null
            var order_logistics: TextView? = null
            var order_evaluation: TextView? = null
            var order_datails: TextView? = null
            var detele_bt: Button? = null
            var order_pay: Button? = null
            var qr_iv: Button? = null

            var affirm_tx: Button? = null
            var state_desc: TextView? = null
            var amount: TextView? = null
            var shippingFeeTv: TextView? = null
            var writeOffCode_tv: TextView? = null
            var goods_price: TextView? = null
            var order_store: TextView? = null
            var tv_refund: TextView? = null
            var listView: RecyclerView? = null

            init {
                order_refund_tv = itemView.findViewById(R.id.order_refund_tv)
                order_evaluation = itemView.findViewById(R.id.order_evaluation)
                order_receive = itemView.findViewById(R.id.order_receive)
                order_complain = itemView.findViewById(R.id.order_complain)
                order_refund_cancel = itemView.findViewById(R.id.order_refund_cancel)
                order_cancel = itemView.findViewById(R.id.order_cancel)
                qr_iv = itemView.findViewById(R.id.qr_iv)
                order_store = itemView.findViewById(R.id.order_store)
                writeOffCode_tv = itemView.findViewById(R.id.writeOffCode_tv)
                goods_price = itemView.findViewById(R.id.goods_price)
                tv_refund = itemView.findViewById(R.id.tv_refund)
                order_logistics = itemView.findViewById(R.id.order_logistics)
                order_datails = itemView.findViewById(R.id.order_datails)
                amount = itemView.findViewById(R.id.amount)
                detele_bt = itemView.findViewById(R.id.detele_bt)
                affirm_tx = itemView.findViewById(R.id.affirm_tx)
                order_pay = itemView.findViewById(R.id.order_pay)
                listView = itemView.findViewById(R.id.order_item_lv)
                state_desc = itemView.findViewById(R.id.state_desc)
                shippingFeeTv = itemView.findViewById(R.id.goods_shipping_fee)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(inflater.inflate(R.layout.order_item_kotlin, parent, false));
        }

        override fun getItemCount(): Int = mOrderBean.size
        //订单状态：0(已取消)10:未付款（待付款）;20:已付款（代发货）;30:待收货;40:已收货（已完成）;35：已到达 orderstate
        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val mShopAdapterSon = ItemShopAdapterSon(context, mOrderBean[position].extend_order_goods, mOrderBean[position].isIf_complain)
            holder.listView?.adapter = mShopAdapterSon
            holder.listView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            holder.goods_price!!.text = context!!.getString(R.string.zhifufangshi).toString() + mOrderBean[position].payment_name
            holder.amount?.text = mOrderBean[position].order_amount
            holder.shippingFeeTv?.text = mOrderBean[position].shipping_fee
            holder.itemView.setBackgroundColor(Color.WHITE)


            holder.order_cancel?.visibility = if (mOrderBean[position].isIf_cancel) View.VISIBLE else View.GONE
            holder.order_refund_cancel?.visibility = View.GONE
//            holder.order_refund_cancel?.visibility = if (mOrderBean[position].isIf_refund_cancel) View.VISIBLE else View.GONE
            holder.order_complain?.visibility = View.GONE
//            holder.order_complain?.visibility = if (mOrderBean[position].isIf_complain) View.VISIBLE else View.GONE
            holder.order_receive?.visibility = if (mOrderBean[position].order_state == "35") View.VISIBLE else View.GONE
            holder.order_logistics?.visibility = if (mOrderBean[position].isIf_deliver) View.VISIBLE else View.GONE
            holder.order_evaluation?.visibility = if (mOrderBean[position].isIf_evaluation) View.VISIBLE else View.GONE
            holder.order_datails?.visibility = View.VISIBLE
            holder.detele_bt?.visibility = if (mOrderBean[position].isIf_delete) View.VISIBLE else View.GONE
            holder.order_refund_tv?.visibility = if (mOrderBean[position].refund == "1") View.VISIBLE else View.GONE
            holder.order_pay?.visibility = if (mOrderBean[position].order_state == "10") View.VISIBLE else View.GONE
            holder.qr_iv?.visibility = if (TextUtils.isEmpty(mOrderBean[position].write_off_code)) View.GONE else View.VISIBLE

            //验证码
            if (!TextUtils.isEmpty(mOrderBean[position].write_off_code)) {
                holder.writeOffCode_tv?.text = String.format(context?.getString(R.string.receivecode)!!, mOrderBean[position].write_off_code)
                holder.qr_iv?.setOnClickListener {
                    RxBus.build().post(mOrderBean[position].order_id)
                }
            } else {
                holder.writeOffCode_tv!!.text = ""
            }

            if (mOrderBean[position].isIf_lock) {
                holder.state_desc?.text = context?.getString(R.string.refundstate)
            } else {
                holder.state_desc?.text = mOrderBean[position].state_desc
            }

            holder.order_cancel?.setOnClickListener {
                //取消
                RxBus.build().post(CancelOrderBus().builder(mOrderBean[position].order_id, mItemType))
            }
            holder.order_receive?.setOnClickListener {
                //确认收货
                RxBus.build().post(SureGotGoodsOrderBus().builder(mOrderBean[position].order_id, mItemType))
            }
            holder.detele_bt?.setOnClickListener {
                //删除
                RxBus.build().post(DeleteOrderBus().builder(mOrderBean[position].order_id, mItemType))
            }
            holder.order_datails?.setOnClickListener {
                //订单详情
                val intent = Intent(context, OrderInformationKotlinActivity::class.java)
                intent.putExtra("order_id", mOrderBean[position].order_id)
                intent.putExtra("key", Mark.State.UserKey)
                context?.startActivity(intent)
            }
            holder.order_evaluation?.setOnClickListener {
                //评价
                val intent = Intent()
                intent.setClass(context, GoodsSevaluationActivity::class.java)
                intent.putExtra("order_sn", mOrderBean[position].order_sn)
                intent.putExtra("mIndex", mItemType)
                context?.startActivity(intent)
            }
            holder.order_refund_tv?.setOnClickListener {
                //订单退款
                //交易完成 -> 订单退款
                val intenttk2 = Intent(context, OrderRefundActivity::class.java)
                intenttk2.putExtra("order_id", mOrderBean[position].order_id)
                context?.startActivity(intenttk2)
            }
            holder.order_logistics?.setOnClickListener {
                //物流
                context?.startActivity(Intent(context, LogisticsKotlinActivity::class.java).putExtra("order_id", mOrderBean[position].order_id))
            }

            holder.order_refund_cancel?.setOnClickListener {
                //退款取消 暂时不需要
//                RxBus.build().post(CancelOrderBus().builder(mOrderBean[position].order_id))
            }
            holder.order_pay?.setOnClickListener {
                val intent = Intent(context, SurePayListKotlinActivity::class.java)
                intent.putExtra("PAY_SN", mOrderBean[position].pay_sn)
                context?.startActivity(intent)
            }
            holder.order_datails?.setOnClickListener {
                //订单详情
                val intent = Intent(context, OrderInformationKotlinActivity::class.java)
                intent.putExtra("order_id", mOrderBean[position].order_id)
                intent.putExtra("key", Mark.State.UserKey)
                context!!.startActivity(intent
                )


            }
            holder.qr_iv?.setOnClickListener {
                RxBus.build().post(mOrderBean[position].write_off_code)
            }
            holder.order_store?.setOnClickListener {
                val intent = Intent(context, ShopHomeActivity::class.java)
                intent.putExtra("store_id", mOrderBean[position].store_id)
                context?.startActivity(intent)
            }
        }

    }

    private class ItemShopAdapterSon : RecyclerView.Adapter<ItemShopAdapterSon.ItemViewHolder> {
        private var context: Context?
        private val inflater: LayoutInflater
        private var mSonBean = ArrayList<OrderItemBean.OrderListBean.ExtendOrderGoodsBean>()
        private var if_complain = false

        constructor(context: Context?, mSonBean: List<OrderItemBean.OrderListBean.ExtendOrderGoodsBean>, if_complain: kotlin.Boolean) {
            this.context = context
            this.inflater = LayoutInflater.from(context)
            this.mSonBean = mSonBean as ArrayList<OrderItemBean.OrderListBean.ExtendOrderGoodsBean>
            this.if_complain = if_complain
        }

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var imageView: ImageView? = null
            var details: TextView? = null
            var num: TextView? = null
            var factPay: TextView? = null
            var tv_refundreturn: TextView? = null
            var tv_complaint: TextView? = null
            var rl_intent: RelativeLayout? = null

            init {
                imageView = itemView.findViewById(R.id.order_item_iv_image)
                details = itemView.findViewById(R.id.order_item_tv_details)
                num = itemView.findViewById(R.id.order_item_tv_num)
                factPay = itemView.findViewById(R.id.order_item_tv_price)
                tv_refundreturn = itemView.findViewById(R.id.tv_refundreturn)
                tv_complaint = itemView.findViewById(R.id.tv_complaint)
                rl_intent = itemView.findViewById(R.id.order_list_rl_)
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(inflater.inflate(R.layout.order_goods_list_item, parent, false))
        }

        override fun getItemCount(): Int = mSonBean.size
        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

            holder.details?.setText(mSonBean[position].goods_name)
            Glide.with(context!!).load(mSonBean[position].goods_image_url).into(holder.imageView!!)
            holder.num?.text = "x" + mSonBean[position].goods_num
            holder.factPay?.text = setSpannableStringBuilder("$" + mSonBean[position].goods_price, "$".length, 0)
            if (mSonBean[position].refund == 1) {
                holder.tv_refundreturn!!.visibility = View.VISIBLE
            } else {
                holder.tv_refundreturn!!.visibility = View.GONE
            }
            if (if_complain) {
                holder.tv_complaint!!.visibility = View.VISIBLE
            } else {
                holder.tv_complaint!!.visibility = View.GONE
            }
            holder.rl_intent!!.setOnClickListener {
                val intent = Intent(context, ProductDetailsKotlinActivity::class.java)
                intent.putExtra("goods_id", mSonBean[position].goods_id)
                intent.putExtra("key", Mark.State.UserKey)
                context?.startActivity(intent)
            }
            holder.tv_refundreturn!!.setOnClickListener {
                val intenttk2 = Intent(context, OrderRefundActivity::class.java)
                intenttk2.putExtra("order_id", mSonBean[position].order_id)
                intenttk2.putExtra("goods_id", mSonBean[position].rec_id)
                context?.startActivity(intenttk2)
            }
            holder.tv_complaint!!.setOnClickListener {
                // 投诉
                val intent2 = Intent(context, ComplaintsApplyForActivity::class.java)
                intent2.putExtra("goods_id", mSonBean[position].rec_id)
                intent2.putExtra("order_id", mSonBean[position].order_id)
                context?.startActivity(intent2)
            }
        }

        private fun setSpannableStringBuilder(text: String, start: Int, end: Int): SpannableStringBuilder? {
            val builder = SpannableStringBuilder(text)
            //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
            val redSpan = ForegroundColorSpan(Color.parseColor("#060606")) //黑
            val blackSpan = ForegroundColorSpan(Color.parseColor("#DA2230")) //红
            builder.setSpan(redSpan, 0, start, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            builder.setSpan(blackSpan, start, if (end == 0) text.length else end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            return builder
        }
    }
}