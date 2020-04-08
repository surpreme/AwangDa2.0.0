package com.aite.awangdalibrary.ui.activity.findpassword

import android.graphics.Rect
import android.os.CountDownTimer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.ui.dailogfragment.SuccessDialogFragment
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.utils.SystemUtil

/**

 * @Auther: valy

 * @datetime: 2020/3/11

 * @desc:

 */
class FindPassWordKotlinActivity : BaseMVPListActivity<FindPasswordContract.View, FindPassWordPresenter, FindPassWordUIBean>() {
    override fun getLayoutId(): Int = R.layout.recycler_layout

    override fun initViews() {
        super.initViews()
        setStatusBar(1)

    }

    override fun initDatas() {
        for (index in 0..5) {
            val f = FindPassWordUIBean()
            when (index) {
                0 -> {
                    f.centerBarTitle = "找回密码"

                }
                1 -> {
                    f.phoneTitle = "手机号码"
                    f.phoneEditHint = "请输入手机号码"
                    f.phoneCode = "发送验证码"

                }
                2 -> {
                    f.title = "验证码"
                    f.editHint = "请输入短信验证码"

                }
                3 -> {
                    f.title = "新密码"
                    f.editHint = "请输入6-16位字符数字组合密码"

                }
                4 -> {
                    f.title = "确认密码"
                    f.editHint = "请再次输入密码"

                }
                5 -> {
                    f.bottomStr = "确认提交"


                }

            }
            mDatasList.add(f)

        }
        appendDatas()

    }

    override fun setAdapter(): BaseRecyAdapter<FindPassWordUIBean>? {
        val mFindPassWordUIAdapter = FindPassWordUIAdapter(mContext)
        mFindPassWordUIAdapter.mOnCommitInterface = OnClickLstenerInterface.OnCommitInterfaces { listFirst, view ->
            SuccessDialogFragment().newInstance("注册成功")?.show(
                    supportFragmentManager, "SuccessDialog")
        }

        return mFindPassWordUIAdapter

    }

    override fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)


    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
            if (position == 0) {
            } else if (position != count - 1 && position != 0) {
                rect.left = SystemUtil.dp2px(mContext, 45f)
                rect.right = SystemUtil.dp2px(mContext, 45f)
                rect.top = SystemUtil.dp2px(mContext, 23f)
            } else {
                rect.top = SystemUtil.dp2px(mContext, 32f)
                rect.left = SystemUtil.dp2px(mContext, 50f)
                rect.right = SystemUtil.dp2px(mContext, 50f)
            }
        }

        override fun doRule(position: Int, rect: Rect) {
            rect.bottom = rect.top
        }
    }

    override fun getRecyclerViewId(): Int = R.id.recycler_view

    override fun onDestroys() {
    }

}