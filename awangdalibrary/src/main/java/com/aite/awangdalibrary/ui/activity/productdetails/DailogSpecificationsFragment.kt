package com.aite.awangdalibrary.ui.activity.productdetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.aite.awangdalibrary.R
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.valy.baselibrary.basekotlin.BaseDialogFragment
import com.valy.baselibrary.utils.OnClickLstenerInterface
import kotlinx.android.synthetic.main.dialog_goodsspec.*

/**

 * @Auther: valy

 * @datetime: 2020/3/24

 * @desc:

 */
open class DailogSpecificationsFragment : BaseDialogFragment() {
    var mBuyNum = 1
    var type = ""//1 加入购物车 2立即购买
    var goods_id = ""

    var mGoodsInfoBean: GoodsInformationBean.GoodsInfoBean? = null
    fun newInstance(mGoodsInfoBean: GoodsInformationBean.GoodsInfoBean, type: String): DailogSpecificationsFragment {
        val mDailogSpecificationsFragment = DailogSpecificationsFragment()
        val bundle = Bundle()
        bundle.putSerializable("goodsInfo", mGoodsInfoBean)
        bundle.putString("type", type)
        mDailogSpecificationsFragment.arguments = bundle
        return mDailogSpecificationsFragment
    }

    open var monCommitInterface: onCommitInterface? = null

    interface onCommitInterface {
        fun commit(goods_id: String, number: String)
    }

    override fun setContentView(): Int = R.layout.dialog_goodsspec
    @SuppressLint("SetTextI18n")
    override fun initViews(view: View) {
        if (arguments?.getSerializable("goodsInfo") == null) {
            dismiss()
            return
        }
        val mGoodsInfo = arguments?.getSerializable("goodsInfo") as GoodsInformationBean.GoodsInfoBean
        mGoodsInfoBean = mGoodsInfo
        type = arguments?.getString("type")!!
        goods_id = mGoodsInfo.goods_id
        val goodsSpecAdapter = GoodsSpecKotlinAdapter(activity, mGoodsInfoBean!!.goods_spec_info)
        goodsSpecAdapter.idInterface = GoodsSpecKotlinAdapter.IdInterface {
            if (it == null) return@IdInterface
            if (it.size == mGoodsInfoBean?.goods_spec_info?.size) {
                val map = it as Map<String, String>
                val str = map["id"]
//                name = map["name"]
                for (i in mGoodsInfoBean?.spec_list!!.indices) { //遍历规格ID
                    if (str == mGoodsInfoBean?.spec_list!![i].id) {
                        goods_id = mGoodsInfoBean?.spec_list!![i].goods_id
                        break
                    }
                }

            }
        }
        labels_view.adapter = goodsSpecAdapter
        Glide.with(mContext!!).load(mGoodsInfo.goods_image_primary).into(goods_iv)
        price_tv.text = "价格：$${mGoodsInfo.goods_price}"
        house_num_tv.text = "库存：${mGoodsInfo.goods_storage}件"
        name_tv.text = mGoodsInfo.goods_name
        close_iv.setOnClickListener {
            dismiss()
        }
        submit_btn.setOnClickListener {
            if (type == "2") {
                if (goods_id.isNotEmpty()) {
                    ARouter
                            .getInstance()
                            .build("/jn/OrderSureActivity")
                            .withString("cart_id", "$goods_id|1")
                            .withString("ifcart", "0")
                            .withString("isfcode", mGoodsInfoBean?.is_fcode)
                            .navigation()
                    dismiss()
                }

            } else {
                if (monCommitInterface != null) {
                    monCommitInterface?.commit(goods_id, tv_number.text.toString())
                }
            }
        }
        tv_less.setOnClickListener {
            if (mBuyNum > 1) {
                mBuyNum--
            }
            tv_number.text = mBuyNum.toString()

        }
        tv_plus.setOnClickListener {
            mBuyNum++
            tv_number.text = mBuyNum.toString()
        }
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