package com.aite.a.activity.li.fragment.rechargedetails

import android.content.Intent
import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.activity.surePayListKotlin.SurePayListKotlinActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.base.Mark
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.basekotlin.BaseMVPSmartListFragment
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.utils.SystemUtil
import kotlinx.android.synthetic.main.fragment_recharge_detail.*

/**

 * @Auther: valy

 * @datetime: 2020/3/17

 * @desc: 余额

 */
class RechargeDetailsFragment : BaseMVPSmartListFragment<RechargeDetailsContract.View, RechargeDetailsPresenter, RechargeDetailsBean>(), RechargeDetailsContract.View {
    override fun setAdapter(): BaseRecyAdapter<RechargeDetailsBean>? {
        val mRechargeDetailsRecyAdapter = RechargeDetailsRecyAdapter(mContext)
        mRechargeDetailsRecyAdapter.mOnThingClickInterface = OnClickLstenerInterface.OnThingClickInterface {
            val intent = Intent(mContext, SurePayListKotlinActivity::class.java)
            intent.putExtra("PAY_SN", it.toString())
            intent.putExtra("type", "1")
            startActivity(intent)
        }
        return mRechargeDetailsRecyAdapter
    }

    override fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)


    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
            if (position == 0)
                rect.top = SystemUtil.dp2px(mContext, 15f)
        }

        override fun doRule(position: Int, rect: Rect) {

        }
    }

    override fun getRecyclerViewId(): Int = R.id.recycler_smart_view
    override fun getSmartLayoutId(): Int = R.id.smartlayout
    override fun getLayoutResId(): Int = R.layout.fragment_recharge_detail

    override fun getPageDatas(postion: Int) {
        mPresenter?.getList(Mark.State.UserKey, 15, postion, "", "zh_cn")

    }


    override fun getListSuccess(mlist: MutableList<RechargeDetailsBean>, boolean: Boolean) {
        isMore = boolean
        appendDatas(mlist)
    }

    override fun getInformation(x: String, y: String) {
        order_info_tv.text = "可用金额 ：$${x}       冻结金额 ：$${y}"

    }

    override fun onDestroys() {
    }
}