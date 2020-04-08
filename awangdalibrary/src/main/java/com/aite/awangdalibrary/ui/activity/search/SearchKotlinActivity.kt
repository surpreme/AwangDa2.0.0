package com.aite.awangdalibrary.ui.activity.search

import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.aite.awangdalibrary.R
import com.aite.mainlibrary.basekotlin.BaseMVPActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.valy.baselibrary.dialogfragment.TalkDialogFragment
import com.valy.baselibrary.utils.LogUtils
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.utils.SystemUtil
import kotlinx.android.synthetic.main.activity_search_kotlin.*


/**

 * @Auther: valy

 * @datetime: 2020/3/25

 * @desc:

 */
class SearchKotlinActivity : BaseMVPActivity<SearchKotlinContract.View, SearchKotlinPresenter>(), SearchKotlinContract.View {
    override fun getLayoutId(): Int = R.layout.activity_search_kotlin
    var key = ""
    override fun initViews() {
        if (intent.getStringExtra("key") != null)
            key = intent.getStringExtra("key")
        if (key.isNotEmpty()) {
            showLoading()
            mPresenter?.getHistory(key, "zh_cn")
        }
        history_title_tv.visibility = View.GONE
        delete_iv.visibility = View.GONE
        setStatusBar(0)
        search_edit.setOnEditorActionListener { v, actionId, event ->
            if (EditorInfo.IME_ACTION_SEARCH == actionId) {
                search(v.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onClick() {
        super.onClick()
        search_iv.setOnClickListener {
            search(search_edit.text.toString())
        }
        search_tv.setOnClickListener {
            search(search_edit.text.toString())
        }
        iv_back.setOnClickListener {
            onBackPressed()
        }
        delete_iv.setOnClickListener {
            val mTalkDialogFragment = TalkDialogFragment().newInstance("确认要清除历史吗", "确定")
            mTalkDialogFragment?.show(supportFragmentManager, "TalkDialogFragment")
            mTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                mHhistory.clear()
                history_title_tv.visibility = View.GONE
                delete_iv.visibility = View.GONE
                history_FlowView.visibility = View.GONE
                nodatas_tv.visibility = View.VISIBLE
                val json = Gson().toJson(mHhistory)
                LogUtils.d(json)
                mPresenter?.saveHistory(key, json, "android", "zh_cn")
            }

        }
    }


    override fun initDatas() {

    }

    fun search(search_txt: String?) {
        if (!search_txt.isNullOrEmpty()) {
            showLoading()
            if (key.isNotEmpty()) {
                mHhistory.add(search_txt)
                val json = Gson().toJson(mHhistory)
                LogUtils.d(json)
                mPresenter?.saveHistory(key, json, "android", "zh_cn")
            } else {
                ARouter.getInstance().build("/jn/GoodsListActivity").withString("keyword", search_txt).navigation()
            }

        }
    }


    var mHhistory: MutableList<String> = mutableListOf()
    override fun getSuccess(history: MutableList<String>) {
        if (history.isNotEmpty()) {
            history_title_tv.visibility = View.VISIBLE
            delete_iv.visibility = View.VISIBLE
            nodatas_tv.visibility = View.GONE
        }
        mHhistory = history
        val mg = SystemUtil.dp2px(mContext, 6f)
        val pa = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        pa.setMargins(0, mg, mg * 2, 0)

        for (i in 0 until history.size) {
            val item = TextView(mContext)
            item.text = history[i]
            item.setBackgroundResource(R.drawable.gray_background_round)
            item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
            item.setPadding(mg, mg, mg, mg)
            item.setOnClickListener {
                search(item.text.toString())
                search_edit.setText(item.text)
            }
            history_FlowView.addView(item, pa)
        }
        closeLoading()
    }

    override fun onSaveSuccess() {
        closeLoading()
        if (key.isNotEmpty() && search_edit.text.toString().isNotEmpty()) {
            ARouter.getInstance().build("/jn/GoodsListActivity").withString("keyword", search_edit.text.toString()).navigation()
        }

    }
}