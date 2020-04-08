package com.aite.a.activity.li.activity.logistics

import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.base.Mark
import com.aite.a.utils.SystemUtil
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.BaseItemDecoration

/**

 * @Auther: valy

 * @datetime: 2020/3/10

 * @desc: 这里分为三层 toolbar 两个textview 和一个空布局
 * 空布局动态添加数据addView

 */
class LogisticsKotlinActivity : BaseMVPListActivity<LogisticsContract.View, LogisticsPresenter, LogisticsKotlinUIBean>(), LogisticsContract.View {
    private var order_id: String = ""


    override fun getLayoutId(): Int = R.layout.recycler_layout

    override fun initViews() {
        super.initViews()
        if (intent.getStringExtra("order_id") == null) {
            showToast("订单号错误")
            onBackPressed()
            return
        } else {
            order_id = intent.getStringExtra("order_id")
        }
        setStatusBar("#EEEEEE")
        recycler_view?.setBackgroundColor(resources.getColor(R.color.white))
    }

    override fun initDatas() {
        for (index in 0..2) {
            val f = LogisticsKotlinUIBean()
            when (index) {
                0 -> {
                    f.centerBarTitle = getString(R.string.Logistics)
                }
                1 -> {
                    f.userInformation = getString(R.string.Deliveryman)
                }
                2 -> {
                    f.userInformation = getString(R.string.PhoneNo)
                }
            }
            mDatasList.add(f)
        }
        appendDatas()
        mPresenter?.getOrderInformation(Mark.State.UserKey, order_id, "zh_cn")

    }

    override fun setAdapter(): BaseRecyAdapter<LogisticsKotlinUIBean>? {
        val logisticsRecyAdapter = LogisticsRecyAdapter(mContext)
        return logisticsRecyAdapter
    }

    override fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)


    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
            if (position == 0) {
                rect.bottom = SystemUtil.dp2px(mContext, 15f)
            } else if (position == 2) {
                rect.bottom = SystemUtil.dp2px(mContext, 50f)
            }
        }

        override fun doRule(position: Int, rect: Rect) {
            rect.bottom = rect.top
        }
    }

    override fun getRecyclerViewId(): Int = R.id.recycler_view


    override fun onDestroys() {
    }


    override fun onOrderInformationSuccess(mLogisticsNetBean: LogisticsNetBean) {
        if (mLogisticsNetBean.delivery_staff_info != null && mLogisticsNetBean.delivery_staff_info.toString() != "null") {
            if (mLogisticsNetBean.delivery_staff_info.staff_name != null && mLogisticsNetBean.delivery_staff_info.staff_name.toString() != "null") {
                mDatasList[1].userInformation = "${getString(R.string.Deliveryman)}${mLogisticsNetBean.delivery_staff_info.staff_name}"

            }
            if (mLogisticsNetBean.delivery_staff_info.staff_phone != null && mLogisticsNetBean.delivery_staff_info.staff_phone.toString() != "null") {
                mDatasList[2].userInformation = "${getString(R.string.PhoneNo)}${mLogisticsNetBean.delivery_staff_info.staff_phone}"
            }
        }
        if (mLogisticsNetBean.deliver_info != null && mLogisticsNetBean.deliver_info.toString() != "null") {
            val f = LogisticsKotlinUIBean()
            val deliver_info: MutableList<LogisticsKotlinUIBean.DeliverInfoBean>? = ArrayList()
            for (oo in 0 until mLogisticsNetBean.deliver_info.size) {
                val w = LogisticsKotlinUIBean.DeliverInfoBean()
                w.context = mLogisticsNetBean.deliver_info[oo].context
                w.time = mLogisticsNetBean.deliver_info[oo].time
                w.status = mLogisticsNetBean.deliver_info[oo].status
                deliver_info?.add(w)
            }
            f.deliver_info = deliver_info
            mDatasList.add(f)

        }
        refreshDatas()
    }
}