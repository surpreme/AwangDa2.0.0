package com.aite.a.activity

import android.content.Intent
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.APPSingleton
import com.aite.a.activity.li.activity.choicelanguage.ChoiceLanguageActivity
import com.aite.a.activity.li.activity.login.LogInActivity
import com.aite.a.activity.li.util.PopupWindowUtil
import com.aite.a.adapter.SettingUIRecyAdapter
import com.aite.a.bean.SettingFirstBean
import com.aite.a.utils.SystemFileUtil
import com.aite.a.utils.SystemUtil
import com.aite.mainlibrary.basekotlin.BaseActivity
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.utils.OnClickLstenerInterface
import kotlinx.android.synthetic.main.recycler_layout.*
import java.util.*

/**
 * @Auther: valy
 * @datetime: 2020/3/4
 * @desc:
 */
class SettingActivity : BaseActivity() {
    private var mSettingUIRecyAdapter: SettingUIRecyAdapter? = null
    private var listFirst = ArrayList<SettingFirstBean>()


    override fun getLayoutId(): Int {
        return R.layout.recycler_layout
    }

    override fun initViews() {
        setStatusBar(0)
        recycler_view.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mSettingUIRecyAdapter = SettingUIRecyAdapter(mContext, listFirst)
        mSettingUIRecyAdapter?.toolBarClickInterface = object : OnClickLstenerInterface.OnToolBarClickInterface {
            override fun rightClick(v: View?) {
                startActivity(FixPasswordActivity::class.java)
            }

            override fun back() {
                onBackPressed()
            }
        }
        mSettingUIRecyAdapter?.clickInterface = OnClickLstenerInterface.OnRecyClickInterface { position ->
            when (position) {
                1 -> {//从0开始 可是toolbar 也是1项
                    SystemFileUtil.clearAllCache(APPSingleton.getContext())
                    PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                            this,
                            getString(R.string.notice),
                            getString(R.string.clearsuccessed),
                            getString(R.string.sure)
                    ) { v: View? ->
                        PopupWindowUtil.getmInstance().dismissPopWindow()
                    }
                }
                2 -> {
                    val intent = Intent(this, GuideActivity::class.java)
                    startActivity(intent)
                }
                3 -> {
                    val intent33 = Intent(this, WebActivity::class.java)
                    intent33.putExtra("url", "http://aitecc.com/wap/index.php?act=article&op=article_show_index&article_id=22")
                    startActivity(intent33)
                }
                4 -> {
                    startActivity(Intent(this, ChoiceLanguageActivity::class.java))

                }
                5 -> {
                    val intent = Intent(this, LogInActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            }

        }
        recycler_view.adapter = mSettingUIRecyAdapter
        //设置分割线 默认1dp
        if (recycler_view.itemDecorationCount == 0) {
            recycler_view.addItemDecoration(object : BaseItemDecoration(mContext) {
                override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
                    //设置间距 从0开始 从上方开始算
                    if (position == count - 1) {
                        rect.top = SystemUtil.dp2px(mContext, 60f)
                        rect.left = SystemUtil.dp2px(mContext, 15f)
                        rect.right = SystemUtil.dp2px(mContext, 15f)
                    }
                    if (position == 0)
                        rect.bottom = SystemUtil.dp2px(mContext, 5f)

                }

                override fun doRule(position: Int, rect: Rect) {
                    //画线 从下方开始算 加上索引从0开始 所以+2 mAdapter可以获取当前recy的适配器     rect.left = rect.right 就不会绘制当前线
                    if (position == 0)
                        rect.bottom += SystemUtil.dp2px(mContext, 10f)
                }
            })
        }

    }

    override fun initDatas() {
        for (index in 0..5) {
            val mSettingFirstBean = SettingFirstBean()
            when (index) {
                0 -> {
                    mSettingFirstBean.centerBarTitle = getString(R.string.morel)
                    mSettingFirstBean.rightBarTitle = getString(R.string.changePassl)
                    mSettingFirstBean.rightBarTitleColor = "#ff6c00"
                }
                1 -> {
                    mSettingFirstBean.title = getString(R.string.clearCache)
                    mSettingFirstBean.imgId = R.drawable.m_m_1f
                }
                2 -> {
                    mSettingFirstBean.title = getString(R.string.welcomepage)
                    mSettingFirstBean.imgId = R.drawable.m_m_2
                }
                3 -> {
                    mSettingFirstBean.title = getString(R.string.aboutl)
                    mSettingFirstBean.imgId = R.drawable.m_m_5
                }
                4 -> {
                    mSettingFirstBean.title = getString(R.string.switchlanguage)
                    mSettingFirstBean.imgId = R.drawable.change_laguage_logo
                }
                5 -> {
                    mSettingFirstBean.bottomStr = getString(R.string.quitsafely)
                }
            }
            listFirst.add(mSettingFirstBean)
        }
        mSettingUIRecyAdapter?.notifyDataSetChanged()
    }

}