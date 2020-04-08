package com.aite.a.activity.li.activity.addaddress

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aite.a.activity.ChoiceAddressActivity
import com.valy.baselibrary.bean.BaseConstant
import com.aite.a.activity.li.activity.GoogleMapLocationActivity
import com.aite.a.activity.li.activity.login.AreaCodeAdapter
import com.aite.a.activity.li.activity.login.AreaCodeAdapter.GetfixSenderInterface
import com.aite.a.activity.li.activity.login.AreaCodeBean
import com.aite.a.activity.li.basekotlin.BaseMVPListActivity
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.a.activity.li.util.LogUtils
import com.aite.a.activity.li.util.PopupWindowUtil
import com.aite.a.base.Mark
import com.aite.a.utils.SystemUtil
import com.jiananshop.a.R
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.utils.OnClickLstenerInterface
import java.util.*

/**

 * @Auther: valy

 * @datetime: 2020/3/10

 * @desc: 地址

 */
class AddAddressKotlinActivity : BaseMVPListActivity<AddAddressContract.View, AddAddressPresenter, AddAddressUIBean>(), AddAddressContract.View {
    private val RESULT_CODE = 12520
    private var latitude = ""
    private var longitude = ""
    private var areaLng = ""
    private var mCountry_id = "0"
    private var mProvince_id = "0"
    private var mCity_id = "0"
    private var mArea_id = "0"
    private var code = ""
    private var mBaseAreaCodeBean = ArrayList<AreaCodeBean.ListBean>()


    override fun initViews() {
        super.initViews()
        recycler_view?.setBackgroundColor(Color.WHITE)
        setStatusBar(0)
    }

    override fun initDatas() {
        for (index in 0..6) {
            val f = AddAddressUIBean()
            when (index) {
                0 -> {
                    f.centerBarTitle = getString(R.string.addreceivingaddress)

                }
                1 -> {
                    f.hintTitle = "*${getString(R.string.Receiver)}"
                    f.hintContent = getString(R.string.receiverhint)

                }
                2 -> {
                    val mPhoneAddressChoiceBean = AddAddressUIBean.PhoneAddressChoiceBean()
                    mPhoneAddressChoiceBean.area_code = "00855"
                    mPhoneAddressChoiceBean.title = "*柬埔寨"
                    mPhoneAddressChoiceBean.content = getString(R.string.mobilehint)
                    mPhoneAddressChoiceBean.code = "+855"
                    f.phoneAddressChoiceBean = mPhoneAddressChoiceBean

                }
                3 -> {
                    val mCityChoiceBean = AddAddressUIBean.CityChoiceBean()
                    mCityChoiceBean.title = "*${getString(R.string.receivingaddress)}:"
                    mCityChoiceBean.content = getString(R.string.selecttips)
                    f.cityChoiceBean = mCityChoiceBean

                }
                4 -> {
                    val clickLocationBean = AddAddressUIBean.ClickLocationBean()
                    clickLocationBean.title = "*${getString(R.string.addressdetail)}:"
                    clickLocationBean.content = getString(R.string.pinlocation)
                    f.clickLocationBean = clickLocationBean

                }
                5 -> {
                    f.hintTitle = "${getString(R.string.Note)}:"
                    f.hintContent = getString(R.string.adddresstip)
                }
                6 -> {
                    f.bottomStr = getString(R.string.saveaddress)
                }
            }
            mDatasList.add(f)

        }
        appendDatas()
        mPresenter?.onGetAreaCode(BaseConstant.CURRENTLANGUAGE)
    }

    override fun setAdapter(): BaseRecyAdapter<AddAddressUIBean>? {
        val mAddAddressUIRecyAdapter = AddAddressUIRecyAdapter(mContext)

        mAddAddressUIRecyAdapter.clickLstenerInterface = OnClickLstenerInterface.OnRecyClicksInterface { view: View, position: Int ->
            if (position == 4) {
                val intent = Intent(mContext, GoogleMapLocationActivity::class.java)
                startActivityForResult(intent, RESULT_CODE)

            } else if (position == 2) {
                showChoiceCodePop(view)

            } else if (position == 3) {
                val intent = Intent(mContext, ChoiceAddressActivity::class.java)
                startActivityForResult(intent, BaseConstant.RESULT_CODE.REQUEST_ETRAS)

            }
        }
        mAddAddressUIRecyAdapter.mOnCommitInterface = OnClickLstenerInterface.OnCommitInterface {
            mPresenter?.onCommitData(
                    Mark.State.UserKey,
                    Mark.State.Member_id,
                    mAdapter?.mDatas?.get(1)?.outStr,
                    mCountry_id,
                    mProvince_id,
                    mCity_id,
                    mArea_id,
                    mAdapter?.mDatas?.get(3)?.cityChoiceBean?.content,
                    mAdapter?.mDatas?.get(4)?.clickLocationBean?.content,
                    mAdapter?.mDatas?.get(2)?.phoneAddressChoiceBean?.outStr,
                    code,
                    areaLng,
                    BaseConstant.CURRENTLANGUAGE)
        }
        return mAddAddressUIRecyAdapter
    }

    private fun showChoiceCodePop(view: View) {
        val areaCodeAdapter = AreaCodeAdapter(mContext, mBaseAreaCodeBean)
        areaCodeAdapter.getfixSenderInterface = GetfixSenderInterface { postion ->
            LogUtils.d(mBaseAreaCodeBean.get(postion).getArea_name())
            PopupWindowUtil.getmInstance().dismissPopWindow()
            onChoiceCode(postion)
        }
        PopupWindowUtil.getmInstance().showChoiceAreaPopupwindow(
                mContext,
                view,
                areaCodeAdapter
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            if (requestCode == RESULT_CODE && resultCode == Activity.RESULT_OK) {
                if (data.getStringExtra("LocationName") != null && data.getStringExtra("Latitude") != null && data.getStringExtra("Longitude") != null) {
                    val LocationName = data.getStringExtra("LocationName")
                    latitude = data.getStringExtra("Latitude")
                    longitude = data.getStringExtra("Longitude")
                    mDatasList[4].clickLocationBean.content = LocationName
                    mAdapter?.notifyDataSetChanged()
                    areaLng = "${longitude},${latitude}"
                }
            } else if (requestCode == BaseConstant.RESULT_CODE.REQUEST_ETRAS && resultCode == Activity.RESULT_OK) {
                if (data.getStringExtra("country_id") != null) mCountry_id = data.getStringExtra("country_id") //国家
                if (data.getStringExtra("province_id") != null) mProvince_id = data.getStringExtra("province_id") //省
                if (data.getStringExtra("city_id") != null) mCity_id = data.getStringExtra("city_id") //市
                if (data.getStringExtra("area_id") != null) mArea_id = data.getStringExtra("area_id") //区choice_end_name
                if (data.getStringExtra("choice_end_name") != null) {
                    mDatasList[3].cityChoiceBean.content = data.getStringExtra("choice_end_name")
                    mAdapter?.notifyDataSetChanged()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun setLayoutManager(): LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

    override fun addItemDecoration(): BaseItemDecoration = object : BaseItemDecoration(mContext) {
        override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
            if (position == count - 1) {
                rect.top = SystemUtil.dp2px(mContext, 60f)
                rect.left = SystemUtil.dp2px(mContext, 20f)
                rect.right = SystemUtil.dp2px(mContext, 20f)
            } /*else if (position == 1 || position == count - 2) {
                rect.left = SystemUtil.dip2px(mContext, 30f)
            }*/ else {
                rect.left = SystemUtil.dip2px(mContext, 10f)
            }
        }

        override fun doRule(position: Int, rect: Rect) {
//            rect.bottom = rect.top
        }
    }

    override fun getRecyclerViewId(): Int = R.id.recycler_view
    override fun getLayoutId(): Int = R.layout.recycler_layout

    override fun onDestroys() {
        mAdapter?.clearDatas()

    }

    fun onChoiceCode(i: Int) {
        code = mBaseAreaCodeBean[i].area_code
        val mMap = mapOf(
                "x" to mBaseAreaCodeBean[i].icon,
                "y" to mBaseAreaCodeBean[i].area_name,
                "z" to mBaseAreaCodeBean[i].code)
        mAdapter?.fixData(mMap)
    }

    /**
     * area_code : 00855
     * code : +855
     * icon : https://daluxmall.com/data/upload/aitemanger/icon/06278176746942420.png
     * area_name : 柬埔寨
     * area_id : 85917
     */
    override fun onGetAreaCodeSuccess(mAreaCodeBean: AreaCodeBean) {
        mBaseAreaCodeBean.addAll(mAreaCodeBean.list)
        onChoiceCode(0)
    }

    override fun onCommitDataSuccess(i: String) {
        PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                this,
                null,
                "添加成功",
                null
        ) { v: View? ->
            PopupWindowUtil.getmInstance().dismissPopWindow()
            onBackPressed()
        }

    }
}