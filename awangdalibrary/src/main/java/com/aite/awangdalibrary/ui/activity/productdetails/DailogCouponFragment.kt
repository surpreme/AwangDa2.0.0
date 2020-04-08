package com.aite.awangdalibrary.ui.activity.productdetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aite.awangdalibrary.R
import com.aite.awangdalibrary.base.RetrofitBuilder
import com.aite.awangdalibrary.ui.activity.coupon.CouponUIAdapter
import com.valy.baselibrary.adpter.BaseItemDecoration
import com.valy.baselibrary.basekotlin.BaseApp
import com.valy.baselibrary.basekotlin.BaseDialogFragment
import com.valy.baselibrary.retrofit.BaseConsumer
import com.valy.baselibrary.retrofit.RxScheduler
import com.valy.baselibrary.utils.LogUtils
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.utils.SystemUtil
import org.json.JSONObject

/**

 * @Auther: valy

 * @datetime: 2020/3/24

 * @desc:

 */
class DailogCouponFragment : BaseDialogFragment() {
    var recycler_view: RecyclerView? = null
    var mCouponUIAdapter: CouponUIAdapter? = null
    var mGoodsInfoBean = ArrayList<GoodsInformationBean.VouchersBean>()
    override fun setContentView(): Int = R.layout.dialog_goods_coupon


    fun newInstance(mGoodsInfoBean: ArrayList<GoodsInformationBean.VouchersBean>): DailogCouponFragment {
        val mDailogCouponFragment = DailogCouponFragment()
        val bundle = Bundle()
        bundle.putSerializable("goodsInfo", mGoodsInfoBean)
        mDailogCouponFragment.arguments = bundle
        return mDailogCouponFragment
    }

    override fun initViews(view: View) {
        if (arguments?.getSerializable("goodsInfo") == null) {
            dismiss()
            return
        }
        mGoodsInfoBean = arguments?.getSerializable("goodsInfo") as ArrayList<GoodsInformationBean.VouchersBean>
        recycler_view = view.findViewById(R.id.recycler_view)
        if (recycler_view?.itemDecorationCount == 0) {
            recycler_view?.addItemDecoration(object : BaseItemDecoration(mContext) {
                override fun configExtraSpace(position: Int, count: Int, rect: Rect) {
                    rect.left = SystemUtil.dip2px(mContext, 20f)
                    rect.right = SystemUtil.dip2px(mContext, 20.1f)
                }

                override fun doRule(i: Int, rect: Rect) {
                    rect.top = rect.bottom
                }
            })
        }
        mCouponUIAdapter = CouponUIAdapter(mContext)
        mCouponUIAdapter?.clickLstenerInterface = OnClickLstenerInterface.OnRecyClicksInterface { v, s ->
            receive(s)
        }
        recycler_view?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler_view?.adapter = mCouponUIAdapter
        mCouponUIAdapter?.appendDatas(mGoodsInfoBean)

    }

    //领劵
    @SuppressLint("CheckResult")
    fun receive(index: Int) {
        RetrofitBuilder.build().receiveCoupon(BaseApp.getKey(), mGoodsInfoBean[index].voucher_t_id)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(object : BaseConsumer() {
                    override fun error(error: String) {
                        LogUtils.e(error)

                    }

                    override fun success(datas: JSONObject) {
                        val mMap = mapOf(
                                "x" to index.toString())
                        mCouponUIAdapter?.fixData(mMap)


                    }

                })
    }

    /**
     * 设置位置在底部 而且设置背景为透明
     */
    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
        params?.gravity = Gravity.BOTTOM
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = (mContext?.resources?.displayMetrics?.heightPixels!! / 4) * 3
        window?.attributes = params
        //设置背景透明
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}