package com.aite.awangdalibrary.ui.activity.foot

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.valy.baselibrary.adpter.sticky.PowerGroupListener
import com.valy.baselibrary.adpter.sticky.PowerfulStickyDecoration
import com.valy.baselibrary.basekotlin.BaseApp
import com.valy.baselibrary.basekotlin.BaseMVPSmartListActivity
import com.valy.baselibrary.bean.BaseConstant
import com.valy.baselibrary.dialogfragment.TalkDialogFragment
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.utils.SystemUtil
import com.valy.baselibrary.xrbus.RxBus
import kotlinx.android.synthetic.main.activity_foot_list_kotlin.*


/**

 * @Auther: valy

 * @datetime: 2020/3/30

 * @desc:

 */
class FootKotlinActivity : BaseMVPSmartListActivity<FootContract.View, FootPresenter, FootListBean>(), FootContract.View {
    override fun getSmartLayoutId(): Int = R.id.foot_smartlayout
    override fun getRecyclerViewId(): Int = R.id.foot_recycler_smart_view
    override fun getLayoutId(): Int = R.layout.activity_foot_list_kotlin
    override fun getSmartEmptyId(): Int = R.id.foot_smart_frame

    override fun setAdapter(): BaseRecyAdapter<FootListBean>? {
        val mFootKotlinAdapter = FootKotlinAdapter(mContext)
        return mFootKotlinAdapter
    }

    override fun initViews() {
        super.initViews()
        initToolBar("我的足迹", "编辑")
        tv_right_title?.setTextColor(Color.BLACK)
        seleted_all_rb.visibility = View.GONE
        delete_tv.visibility = View.GONE
        seleted_all_rb.setOnCheckedChangeListener { buttonView, isChecked ->
            mAdapter?.fixData(mapOf("isAllSeleted" to isChecked.toString()))
        }
    }

    override fun onClick() {
        super.onClick()
        tv_right_title?.setOnClickListener {
            tv_right_title?.text = if (tv_right_title?.text.toString() == "完成") "编辑" else "完成"
            if (tv_right_title?.text.toString() == "完成") {
                mAdapter?.fixData(mapOf("isSeleted" to "true"))
                seleted_all_rb.visibility = View.VISIBLE
                delete_tv.visibility = View.VISIBLE
            } else {
                mAdapter?.fixData(mapOf("isSeleted" to "false"))
                seleted_all_rb.visibility = View.GONE
                delete_tv.visibility = View.GONE
            }
        }
        delete_tv.setOnClickListener {
            val mTalkDialogFragment = TalkDialogFragment().newInstance("您确认要清除这些足迹吗？", "确定")
            mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                val a = mutableListOf<String>()
                if (FootSelectListBean.mSelectList.isNotEmpty()) {
                    for (index in FootSelectListBean.mSelectList) {
                        a.add(index.value)
                    }
                    mPresenter?.deleteMoreFoot(bulidList(a, ",").toString(), BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)
                }
            }
            mTalkDialogFragment?.show(supportFragmentManager, "clearMore")

        }
    }

    private fun bulidList(fixList: List<String>, t: String): StringBuilder {
        val warehouse_order_id = StringBuilder()
        for (i in fixList.indices) {
            val a = fixList[i]
            if (i != fixList.size - 1)
                warehouse_order_id.append(a).append(t)
            else
                warehouse_order_id.append(a)
        }
        return warehouse_order_id
    }

    @SuppressLint("CheckResult")
    override fun initDatas() {
        super.initDatas()
        RxBus.build().toObservable(this@FootKotlinActivity, FootDeleteBus::class.java, Lifecycle.Event.ON_STOP).subscribe { msg ->
            //处理消息
            val mTalkDialogFragment = TalkDialogFragment().newInstance("您确认要删除此足迹吗", "确定")
            mTalkDialogFragment?.show(supportFragmentManager, "deleteFootOne")
            mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                mPresenter?.deleteFoot(msg.postion, msg.goods_id, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)

            }

        }
    }

    override fun getPageDatas(mCurrentPage: Int) {
        super.getPageDatas(mCurrentPage)
        showLoading()
        mPresenter?.getList(mCurrentPage, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)
    }

    override fun addItemDecoration(): RecyclerView.ItemDecoration {
        val mGroupListener = object : PowerGroupListener {
            override fun getGroupName(position: Int): String = mDatasList[position].date.date_txt

            @SuppressLint("InflateParams")
            override fun getGroupView(position: Int): View {
                val view: View = layoutInflater.inflate(R.layout.item_text, null, false)
                val textView: TextView = view.findViewById<TextView>(R.id.textView)
                textView.text = mDatasList[position].date.date_txt
                textView.gravity = Gravity.CENTER_VERTICAL
                textView.textSize = 19f
                textView.setPadding(SystemUtil.dip2px(mContext, 15f), 0, 0, 0)
                return view
            }
        }
        return PowerfulStickyDecoration.Builder
                .init(mGroupListener)
                .build()
    }

    override fun onStop() {
        super.onStop()
        if (FootSelectListBean.mSelectList.isNotEmpty()) {
            FootSelectListBean.mSelectList.clear()
        }
    }

    override fun getListSuccess(list: List<FootListBean>, isHas: Boolean) {
        isMore = isHas
        appendDatas(list)
    }

    override fun deleteSuccess(postion: Int) {
        refresh()
    }

    override fun deleteMoreSuccess() {
        refresh()
        mAdapter?.fixData(mapOf("isSeleted" to "false"))
        seleted_all_rb.visibility = View.GONE
        delete_tv.visibility = View.GONE
        tv_right_title?.text= "完成"
    }


}