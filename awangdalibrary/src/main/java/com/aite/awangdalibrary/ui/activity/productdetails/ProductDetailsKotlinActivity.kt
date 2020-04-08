package com.aite.awangdalibrary.ui.activity.productdetails

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.aite.a.activity.li.basekotlin.BaseRecyAdapter
import com.aite.awangdalibrary.R
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.valy.baselibrary.bean.BaseConstant
import com.valy.baselibrary.dialog.XDialog
import com.valy.baselibrary.dialogfragment.SureTalkDialogFragment
import com.valy.baselibrary.file.GlideBuilder
import com.valy.baselibrary.utils.LogUtils
import com.valy.baselibrary.utils.OnClickLstenerInterface
import com.valy.baselibrary.webJs.JsInterface
import com.valy.baselibrary.webJs.RerashWebView
import kotlinx.android.synthetic.main.activity_product_details_kotlin.*
import java.io.File
import java.lang.Exception

/**

 * @Auther: valy

 * @datetime: 2020/3/23

 * @desc:

 */
class ProductDetailsKotlinActivity : BaseProductDetailsKotlinActivity<ProductDetailsKotlinContract.View, ProductDetailsKotlinPresenter, GuessLikeBean>(), ProductDetailsKotlinContract.View {
    private var goods_id = ""
    private var key = ""
    var mBaseGoodsInformationBean: GoodsInformationBean? = null
    var isCollect = false

    override fun setAdapter(): BaseRecyAdapter<GuessLikeBean>? {
        val mGuessLikeKotlinAdapter = GuessLikeKotlinAdapter(mContext)
        mGuessLikeKotlinAdapter.clickLstenerInterface = OnClickLstenerInterface.OnRecyClicksInterface { v, position ->
            val intent = Intent(mContext, ProductDetailsKotlinActivity::class.java)
            intent.putExtra("goods_id", mDatasList[position].goods_id)
            intent.putExtra("key", key)
            startActivity(intent)
        }
        return mGuessLikeKotlinAdapter
    }


    @SuppressLint("AddJavascriptInterface")
    override fun initViews() {
        super.initViews()
        if (intent.getStringExtra("goods_id") == null) {
            showToast("数据错误")
            onBackPressed()
            return
        }
        goods_id = intent.getStringExtra("goods_id")
        try {
            key = intent.getStringExtra("key")
            if (key.isEmpty()) {
                val mSureTalkDialogFragment = SureTalkDialogFragment().newInstance("您还未登录", "确定")
                mSureTalkDialogFragment?.mlistener = OnClickLstenerInterface.OnThingClickInterface {
                    ARouter.getInstance().build("/jn/LogInActivity").navigation()
                    mSureTalkDialogFragment?.dismiss()
                }
                mSureTalkDialogFragment?.show(supportFragmentManager, "mSureTalkDialogFragment")
            }
        } catch (e: Exception) {
            LogUtils.e(e)
        }

        setStatusBar(0)
        initBanner(goods_banner)

        //*****
        //https://daluxmall.com/mobile/index.php?act=goods&op=goods_body&goods_id=392&lang_type=zh_cn
        content_webview.loadUrl("https://daluxmall.com/mobile/index.php?act=goods&op=goods_body&goods_id=$goods_id&lang_type=zh_cn")
        content_webview.webViewClient = WebViewClient()
        RerashWebView.initWebView(content_webview)
        content_webview.addJavascriptInterface(JsInterface(content_webview, this@ProductDetailsKotlinActivity), "AndroidWebViews")

        //*** **
        //******
        other_price_tv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        //tab
        val tabList = mutableListOf<String>(getString(R.string.product), getString(R.string.details), getString(R.string.comment))
        initTabLayout(type_tab_ll, tabList)
        //********
        //滚动
        initNestedScroll(nested_scrollView, type_tab_ll, content_webview, evaluation_title_tv)

    }

    override fun getRecyclerViewId(): Int = R.id.guesslike_recy
    override fun getLayoutId(): Int = R.layout.activity_product_details_kotlin
    override fun onClick() {
        super.onClick()
        iv_return.setOnClickListener {
            onBackPressed()
        }
        //DailogCouponFragment
        coupon_tv.setOnClickListener {
            val mDailogCouponFragment = DailogCouponFragment().newInstance(mBaseGoodsInformationBean!!.vouchers)
            mDailogCouponFragment.show(supportFragmentManager, "mDailogCouponFragment")
        }
        tv_addcart.setOnClickListener {
            val mDailogSpecificationsFragment = DailogSpecificationsFragment().newInstance(mBaseGoodsInformationBean!!.goods_info, "1")
            mDailogSpecificationsFragment.monCommitInterface = object : DailogSpecificationsFragment.onCommitInterface {
                override fun commit(goods_id: String, number: String) {
                    mPresenter?.addGoodsToCard(BaseConstant.CURRENTLANGUAGE, goods_id, number, key)
                    mDailogSpecificationsFragment.dismiss()
                }

            }
            mDailogSpecificationsFragment.show(supportFragmentManager, "DailogSpecificationsFragment")
        }
        tv_buy.setOnClickListener {
            val mDailogSpecificationsFragment = DailogSpecificationsFragment().newInstance(mBaseGoodsInformationBean!!.goods_info, "2")
            mDailogSpecificationsFragment.show(supportFragmentManager, "DailogSpecificationsFragment")
        }
        search_tv.setOnClickListener {
            ARouter.getInstance().build("/jn/ChoiceActivity").navigation()
        }
        all_goods_tv.setOnClickListener {
            ARouter.getInstance().build("/jn/GoodsListActivity").withString("store_id", mBaseGoodsInformationBean?.store_info?.store_id).navigation()
        }
        go_shop_tv.setOnClickListener {
            ARouter.getInstance().build("/jn/ShopHomeActivity").withString("store_id", mBaseGoodsInformationBean?.store_info?.store_id).navigation()
        }
        ll_store.setOnClickListener {
            ARouter.getInstance().build("/jn/ShopHomeActivity").withString("store_id", mBaseGoodsInformationBean?.store_info?.store_id).navigation()
        }
        shopCard_iv.setOnClickListener {
            ARouter.getInstance().build("/jn/MainerActivity").withString("IsShowShopCard", "true").navigation()
        }
        ll_love.setOnClickListener {
            if (!isCollect)
                mPresenter?.addCollect(key, goods_id, "goods")
            else
                mPresenter?.cancelCollect(key, goods_id)
        }
        ll_chat.setOnClickListener {
            val mCustomerServiceKotlinDailogFragment =
                    CustomerServiceKotlinDailogFragment().newInstance(mBaseGoodsInformationBean?.store_info?.store_id)
            mCustomerServiceKotlinDailogFragment.show(supportFragmentManager, "CustomerServiceKotlinDailogFragment")

        }

        share_iv.setOnClickListener {
            val mShareOthersDialog = ShareOthersDialog(mContext!!)
            mShareOthersDialog.onShareInterface = object : ShareOthersDialog.OnShareInterface {
                override fun onSelected(s: String) {
                    if (s == "facebook") {
                        val content = ShareLinkContent.Builder()
                                .setContentUrl(Uri.parse(mBaseGoodsInformationBean?.goods_info?.goods_url))
                                // .setContentTitle(detailsInfo.goods_info.goods_name)
                                //.setImageUrl(Uri.parse(adgallery.mUris[0]))
                                //.setContentDescription(detailsInfo.goods_info.goods_name)
                                .build()
                        ShareDialog.show(this@ProductDetailsKotlinActivity, content)
                    } else if (s == "ins_story") {
                        val file = File(Environment.getExternalStorageDirectory().toString() + "/jnIns.png")
                        if (file.exists()) {
                            val backgroundAssetUri: Uri = GlideBuilder.getImageContentUri(mContext!!, file)!!
                            val attributionLinkUrl: String = imageUrl
                            val intent = Intent("com.instagram.share.ADD_TO_STORY")
                            intent.setDataAndType(backgroundAssetUri, "image/*")
                            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                            intent.putExtra("content_url", attributionLinkUrl)
                            if (packageManager.resolveActivity(intent, 0) != null) {
                                startActivityForResult(intent, 0)
                            } else {
                                showToast("您没有此app可分享")
                            }
                        } else {
                            showToast("分享失败")
                        }

                    } else if (s == "ins_feed") {
                        val file = File(Environment.getExternalStorageDirectory().toString() + "/jnIns.png")
                        if (file.exists()) {
                            val type = "image/*"
                            val backgroundAssetUri: Uri = GlideBuilder.getImageContentUri(mContext!!, file)!!
                            createInstagramIntent(type, backgroundAssetUri)
                        } else showToast("分享失败")
                    }
                }

            }
            XDialog
                    .Builder(mContext!!)
                    .setContentView(R.layout.pop_share_goods)
                    .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                    .setIsDarkWindow(true)
                    .asCustom(mShareOthersDialog)
                    .show(Gravity.BOTTOM)

        }

    }

    override fun initDatas() {
        showLoading()
        mPresenter?.getDatas(goods_id, BaseConstant.CURRENTLANGUAGE, key)
        mPresenter?.getShopCardNum(key)
    }

    var imageUrl = ""
    @SuppressLint("SetTextI18n", "AddJavascriptInterface")
    override fun getDatasSuccess(mGoodsInformationBean: GoodsInformationBean) {
        mBaseGoodsInformationBean = mGoodsInformationBean
        startShowBanner(goods_banner, mGoodsInformationBean.goods_image.split(","))
        imageUrl = mGoodsInformationBean.goods_image.split(",")[0]
        saveUrlToFlie(imageUrl)
        goods_name_tv.text = mGoodsInformationBean.goods_info.goods_name
        express_delivery_price_tv.text = getString(R.string.delivery)
        sell_num_tv.text = getString(R.string.sales) + mGoodsInformationBean.goods_info.goods_salenum
        isCollect = mGoodsInformationBean.isFavorites == "1"
        if (mGoodsInformationBean.isFavorites == "1") {
            iv_collection.setImageDrawable(resources.getDrawable(R.drawable.yes_favorites))
        } else {
            iv_collection.setImageDrawable(resources.getDrawable(R.drawable.collection))
        }
        if (mGoodsInformationBean.goods_evaluate_count.toInt() > 0) {
            no_datas_tv.visibility = View.GONE
            evaluation_ll.visibility = View.VISIBLE
            evaluation_ll.setOnClickListener {

            }
            talk_information_tv.visibility = View.VISIBLE
            Glide.with(mContext!!).load(mGoodsInformationBean.goods_evaluate_list[0].geval_member_avatar).into(talk_user_icon)
            talk_first_content_tv.text = mGoodsInformationBean.goods_evaluate_list[0].geval_content
            talk_name_tv.text = mGoodsInformationBean.goods_evaluate_list[0].geval_frommembername
            evaluation_title_tv.setOnClickListener {
                ARouter.getInstance().build("/jn/GoodsEvaluateActivity").withString("goods_id", mBaseGoodsInformationBean?.goods_info?.goods_id).navigation()
            }
            evaluation_ll.setOnClickListener {
                ARouter.getInstance().build("/jn/GoodsEvaluateActivity").withString("goods_id", mBaseGoodsInformationBean?.goods_info?.goods_id).navigation()
            }
            evaluation_title_tv.text = "${getString(R.string.itemcomment)}(${mGoodsInformationBean.goods_evaluate_count})"
        } else {
            no_datas_tv.visibility = View.VISIBLE
            evaluation_ll.visibility = View.GONE
            talk_information_tv.visibility = View.GONE
        }
        /**
         * price
         */
        if (mGoodsInformationBean.goods_info.promotion_type.isNullOrEmpty()) {
            goods_price_tv.text = "$${mGoodsInformationBean.goods_info.goods_promotion_price}"
            other_price_tv.text = getString(R.string.marketprice) + mGoodsInformationBean.goods_info.goods_marketprice
        } else if (
                mGoodsInformationBean.goods_info.promotion_type == "groupbuy" ||
                mGoodsInformationBean.goods_info.promotion_type == "xianshi" ||
                mGoodsInformationBean.goods_info.promotion_type == "miaosha") {
            //抢购 限时 秒杀
            goods_price_tv.text = mGoodsInformationBean.goods_info.goods_promotion_price
            other_price_tv.text = "$${mGoodsInformationBean.goods_info.goods_price}"
        }
        store_name_tv.text = mGoodsInformationBean.store_info.store_name
        Glide.with(mContext!!).load(mGoodsInformationBean.store_info.avatar).into(store_iv)
        evaluation_num_tv.text = "${mGoodsInformationBean.store_info.store_credit_info.store_desccredit.text}${mGoodsInformationBean.store_info.store_credit_info.store_desccredit.credit}"
        service_num_tv.text = "${mGoodsInformationBean.store_info.store_credit_info.store_servicecredit.text}${mGoodsInformationBean.store_info.store_credit_info.store_servicecredit.credit}"
        send_num_tv.text = "${mGoodsInformationBean.store_info.store_credit_info.store_deliverycredit.text}${mGoodsInformationBean.store_info.store_credit_info.store_deliverycredit.credit}"
        appendDatas(mGoodsInformationBean.goods_commend_list)

        LogUtils.d(goods_id)

    }

    override fun getShopCardNumSuccess(s: String) {
        if (isInteger(s)) {
            if (badge == null) initBadge(shopCard_iv)
            if (Integer.valueOf(s) > 99) {
                badge!!.badgeText = "99+"
            } else if (Integer.valueOf(s) == 0) {
            } else {
                badge!!.badgeText = s
            }
        }
    }

    override fun addGoodsToCardSuccess() {
        showToast("添加成功")

    }

    override fun collectSuccess() {
        isCollect = true
        iv_collection.setImageDrawable(resources.getDrawable(if (isCollect) R.drawable.collection else R.drawable.yes_favorites))

    }

    override fun cancelCollectSuccess() {
        isCollect = false
        iv_collection.setImageDrawable(resources.getDrawable(if (isCollect) R.drawable.collection else R.drawable.yes_favorites))

    }


    /**
     * popupwindow
     * //https://developers.facebook.com/docs/instagram/sharing-to-stories ins文档
     */


}