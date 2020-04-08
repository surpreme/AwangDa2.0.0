package com.aite.awangdalibrary.ui.activity.system

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.activity.systeminformation.SystemInformationKotlinActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.basekotlin.BaseApp
import com.valy.baselibrary.basekotlin.BaseMVPSmartListActivity
import com.valy.baselibrary.bean.BaseConstant
import com.valy.baselibrary.utils.OnClickLstenerInterface

/**

 * @Auther: valy

 * @datetime: 2020/4/7

 * @desc:

 */
class SystemKotlinMsgActivity : BaseMVPSmartListActivity<SystemMsgKotlinContract.View, SystemMsgKotlinPresenter, SystemMsgKotlinBean>(), SystemMsgKotlinContract.View {
    override fun getSmartLayoutId(): Int = R.id.smartlayout
    var mType: Int = 1
    override fun setAdapter(): BaseRecyAdapter<SystemMsgKotlinBean>? {
        val mSystemMsgKotlinAdapter = SystemMsgKotlinAdapter(mContext, mType)
        mSystemMsgKotlinAdapter.clickLstenerInterface = object : OnClickLstenerInterface.OnRecyClicksInterface {
            override fun getPosition(v: View?, position: Int) {
                val intent = Intent(mContext, SystemInformationKotlinActivity::class.java)
                intent.putExtra("time", mDatasList[position].message_update_time)
                intent.putExtra("content", mDatasList[position].message_body)
                if (mDatasList[position].message_open == "0") {
                    intent.putExtra("number", mDatasList[position].message_id)
                }
                startActivity(intent)
            }

        }
        return mSystemMsgKotlinAdapter
    }

    override fun initExtra() {
        super.initExtra()
        if (intent.getStringExtra("msgType") != null) {
            when (intent.getStringExtra("msgType")) {
                "Common" -> {
                    mType = 1
                    initToolBar("普通消息")
                }
                "System" -> {
                    mType = 2
                    initToolBar("系统消息")
                }
                "OutSide" -> {
                    mType = 3
                    initToolBar("站内消息", "发布")
                    tv_right_title.setTextColor(Color.BLACK)
                    tv_right_title.setOnClickListener {
                        ARouter.getInstance().build("/jn/SendLetterActivity").navigation()
                    }
                }
                else -> {
                    throw Throwable("getStringExtraNull" + this.localClassName)

                }
            }
        } else {
            throw Throwable("getStringExtraNull" + this.localClassName)
        }
    }

    override fun getPageDatas(mCurrentPage: Int) {
        super.getPageDatas(mCurrentPage)
        showLoading()
        when (mType) {
            1 -> {
                mPresenter?.getCommon(mCurrentPage, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)

            }
            2 -> {
                mPresenter?.getSystem(mCurrentPage, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)

            }
            3 -> {
                mPresenter?.getSide(mCurrentPage, BaseApp.getKey(), BaseConstant.CURRENTLANGUAGE)

            }
        }
    }

    override fun addItemDecoration(): RecyclerView.ItemDecoration = BaseItemDecoration(mContext)

    override fun getRecyclerViewId(): Int = R.id.recycler_smart_view

    override fun getLayoutId(): Int = R.layout.activity_system_msg_kotlin
    override fun getSuccess(l: List<SystemMsgKotlinBean>, isHas: Boolean) {
        isMore = isHas
        appendDatas(l)
    }


}