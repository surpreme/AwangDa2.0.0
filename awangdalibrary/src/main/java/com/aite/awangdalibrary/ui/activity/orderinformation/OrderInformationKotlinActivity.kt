package com.aite.awangdalibrary.ui.activity.orderinformation

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Rect
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.utils.TextColorUtils
import com.valy.baselibrary.adpter.BaseItemDecoration
import kotlinx.android.synthetic.main.activity_orderinformation_kotlin.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**

 * @Auther: valy

 * @datetime: 2020/3/23

 * @desc:

 */
class OrderInformationKotlinActivity : BaseMVPListActivity<OrderInformationContract.View, OrderInformationPresenter, OrderInformationKotlinBean>(), OrderInformationContract.View {
    var order_id = ""
    var key = ""

    override fun getLayoutId(): Int = R.layout.activity_orderinformation_kotlin

    override fun initViews() {
        super.initViews()
        setStatusBar(0)
        initToolBar(getString(R.string.orderdetails))
    }

    override fun initDatas() {
        if (intent.getStringExtra("order_id") == null || intent.getStringExtra("key") == null) {
            showToast("数据错误")
            onBackPressed()
            return
        } else {
            order_id = intent.getStringExtra("order_id")
            key = intent.getStringExtra("key")
        }
        showLoading()
        mPresenter?.getDatas("zh_cn", "zh_cn", order_id, key)
    }

    override fun setAdapter(): BaseRecyAdapter<OrderInformationKotlinBean>? {
        val mOrderInformationKotlinAdapter = OrderInformationKotlinAdapter(mContext)
        return mOrderInformationKotlinAdapter
    }

    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
//            if (position == 0)
//                rect.top = SystemUtil.dp2px(mContext, 15f)
        }

        override fun doRule(position: Int, rect: Rect) {

        }
    }

    fun stampToDate(timeMillis: Long): String { //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = Date(timeMillis * 1000)
        return simpleDateFormat.format(date)
    }

    override fun getRecyclerViewId(): Int = R.id.recy_viewsl
    @SuppressLint("SetTextI18n")
    override fun getDatasSuccess(mOrderInformationDatasBean: OrderInformationDatasBean) {
        appendDatas(mOrderInformationDatasBean.order_info.extend_order_goods)
        state_tv.text = mOrderInformationDatasBean.order_info.state_desc
        time_tv.text = getString(R.string.ordertime) + stampToDate(mOrderInformationDatasBean.order_info.add_time.toLong())
        no_tv.text = getString(R.string.orderno) + mOrderInformationDatasBean.order_info.order_sn
        recipient_name_tv.text = "${getString(R.string.receiver)}:" + mOrderInformationDatasBean.order_info.extend_order_common.reciver_name
        recipient_phone_tv.text = "${getString(R.string.contact)}:${mOrderInformationDatasBean.order_info.extend_order_common.reciver_info.phone}"
        recipient_address_tv.text = "${getString(R.string.receiveAddress)}:${mOrderInformationDatasBean.order_info.extend_order_common.reciver_info.address}"
        recipient_talk_tv.text = getString(R.string.BuyerMessage) + mOrderInformationDatasBean.order_info.extend_order_common.reciver_info.dlyp
        store_name_tv.text = getString(R.string.storeName) + mOrderInformationDatasBean.order_info.extend_store.store_name
        store_phone_tv.text = getString(R.string.contact) + mOrderInformationDatasBean.order_info.extend_store.store_phone
        store_address_tv.text = getString(R.string.address) + mOrderInformationDatasBean.order_info.extend_store.store_address
        price_tv.text = TextColorUtils.setSpannableStringBuilder("${getString(R.string.itemPrice)}:  $${mOrderInformationDatasBean.order_info.goods_amount}", "${getString(R.string.itemPrice)}:".length, Color.BLACK, Color.RED)
        freight_tv.text = TextColorUtils.setSpannableStringBuilder("${getString(R.string.freight)}:  $${mOrderInformationDatasBean.order_info.delivery_fee}", "${getString(R.string.freight)}:".length, Color.BLACK, Color.RED)
        real_payment_tv.text = TextColorUtils.setSpannableStringBuilder("${getString(R.string.actualPayment)}:  $${mOrderInformationDatasBean.order_info.order_amount}", "${getString(R.string.actualPayment)}:".length, Color.BLACK, Color.RED)
        handling_fee_tv.text = TextColorUtils.setSpannableStringBuilder("${getString(R.string.handlingFee)}:  $0.00", "${getString(R.string.handlingFee)}:".length, Color.BLACK, Color.RED)
    }


}