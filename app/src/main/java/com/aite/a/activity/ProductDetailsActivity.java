package com.aite.a.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.a.activity.li.activity.ShopHomeActivity;
import com.aite.a.activity.li.activity.mainer.MainerActivity;
import com.aite.a.activity.li.bean.ShopCardNumberBean;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.Bit2FileUtils;
import com.valy.baselibrary.utils.GlideImageLoader;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.adapter.GoodsEvaluateAdapter;
import com.aite.a.adapter.RecommendedProductAdapter;
import com.aite.a.base.BaseActivity;
import com.aite.a.model.GoodsDetailsInfo;
import com.aite.a.parse.NetRun;
import com.aite.a.utils.BooleanLogin;
import com.aite.a.view.GoodsParameterPopu;
import com.aite.a.view.GoodsSpecPopu;
import com.aite.a.view.GoodsSpecPopup;
import com.aite.a.view.ListeningScrollView;
import com.aite.a.view.MyAdGallery;
import com.aite.a.view.MyGridView;
import com.aite.a.view.MyListView;
import com.aite.a.view.ServicePopu;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.community.utils.ClutterUtils;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import chat.activity.DialogueActivity;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * 产品详情 商品详情
 * Created by mayn on 2018/12/11.
 */
public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener, ListeningScrollView.ScrollYListener {
    @BindView(R.id.shopCard_iv)
    ImageView shopCardIv;
    @BindView(R.id.share_iv)
    ImageView shareIv;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.guess_like_recy)
    RecyclerView guessLikeRecy;
    private ImageView iv_return, iv_collection, iv_storeimg;
    private TextView tv_goods, tv_datas, tv_evaluation, tv_buy, tv_addcart, tv_imgnumber, tv_price1, tv_activitytype, tv_price2, tv_goodsname, tv_expressdelivery, tv_monthlysales,
            tv_address, tv_spec, tv_parameter2, tv_storename, tv_allgoods, tv_tostore, tv_offer, tv_description, tv_service, tv_logistics,
            tv_into_shop, tv_conllect_shop, tv_youhui, tv_tuijian_group, all_goods_tv, shop_tv;
    private RelativeLayout rl_avditem, rl_srec, rl_parameter, rl_offer, rl_topjall, rl_allgoods, rl_store;
    private LinearLayout ll_jimi, ll_store, ll_love, ll_share, rl_pj, ll_goods, go_shopStore_ll;
    private MyAdGallery adgallery;
    private MyListView lv_pj;
    private Banner goods_banner;
    private WebView graphic_introduction;
    private GoodsEvaluateAdapter goodsEvaluateAdapter;//评价
    private RecommendedProductAdapter recommendedProductAdapter;//推荐商品
    private MyGridView gv_goods;
    private ListeningScrollView sc_scroll;
    private View v_sliding1, v_sliding2, v_sliding3;

    private GoodsDetailsInfo detailsInfo;
    public String goods_id, spec_goods_id;
    private int lastindex = 1, menu2top = 0, menu3top = 0;
    private boolean isshoucang = false;
    private NetRun netRun;
    private boolean noAttri = true;  //商品有没有设置颜色 尺码 登属性 ,true 时显示属性布局.
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case goods_details_id://商品详情goods_evaluate_list
                    if (msg.obj != null) {
                        detailsInfo = (GoodsDetailsInfo) msg.obj;
                        noAttri = detailsInfo.goods_info.goods_spec_info == null || detailsInfo.goods_info.goods_spec_info.size() == 0;
                        if (detailsInfo.spec_goods_list == null || detailsInfo.spec_goods_list.size() == 0) {
                            spec_goods_id = goods_id;
                        }
                        if (detailsInfo.isFavorites.equals("1")) {//是否收藏
                            isshoucang = true;
                            String s=isshoucang?"s":"d";
                            iv_collection.setImageResource(R.drawable.yes_favorites);
                        } else {
                            isshoucang = false;
                            iv_collection.setImageResource(R.drawable.collection);
                        }
                        //如果有商店编号就显示商店
                        if (detailsInfo.store_info.store_id != null && !detailsInfo.store_info.store_id.equals("") && !detailsInfo.store_info.store_id.equals("null")) {
                            ll_store.setVisibility(View.VISIBLE);
                        }
                        if (goodsSpecPopu != null) {
                            goodsSpecPopu.setFocusable(true);
                            if (goodsSpecPopu.isShowing()) {
                                goodsSpecPopu.uploadOrderInfo(detailsInfo);
                            }
                        }
                        init();
                    }
                    break;
                case goods_details_err:
                    Toast.makeText(ProductDetailsActivity.this, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                    break;
                case add_cart_id://添加购物车
                    if (msg.obj.equals("1")) {
                        Toast.makeText(ProductDetailsActivity.this, getString(R.string.add_success), Toast.LENGTH_SHORT).show();
                    } else if (msg.obj.equals("0")) {
                        Toast.makeText(ProductDetailsActivity.this, getString(R.string.add_fail), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductDetailsActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case add_cart_err:
                    Toast.makeText(ProductDetailsActivity.this, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                    break;
                case collectibles_id://添加收藏
                    if (msg.obj.equals("1")) {
                        isshoucang = true;
                        iv_collection.setImageResource(R.drawable.yes_favorites);
                        Toast.makeText(ProductDetailsActivity.this, getString(R.string.collection_success), Toast.LENGTH_SHORT).show();
                    } else {
                        isshoucang = false;
                        iv_collection.setImageResource(R.drawable.collection);
                        Toast.makeText(ProductDetailsActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case collectibles_del_id://删除收藏
                    if (msg.obj.equals("1")) {
                        isshoucang = false;
                        iv_collection.setImageResource(R.drawable.collection);
                        Toast.makeText(ProductDetailsActivity.this, getString(R.string.cancel_collection_success), Toast.LENGTH_SHORT).show();
                    } else {
                        isshoucang = true;
                        iv_collection.setImageResource(R.drawable.yes_favorites);
                        Toast.makeText(ProductDetailsActivity.this, getString(R.string.cancel_collection_fail), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private View Comment_fl;
    private Unbinder unbinder;
    //消息红点标记
    private Badge badge;
    private int selectedNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        unbinder = ButterKnife.bind(this);
        findViewById();
    }

    @Override
    protected void findViewById() {
        iv_return = findViewById(R.id.iv_return);
        tv_goods = findViewById(R.id.tv_goods);
        tv_datas = findViewById(R.id.tv_datas);
        tv_evaluation = findViewById(R.id.tv_evaluation);
        tv_buy = findViewById(R.id.tv_buy);
        tv_addcart = findViewById(R.id.tv_addcart);
        iv_collection = findViewById(R.id.iv_collection);
        ll_jimi = findViewById(R.id.ll_jimi);
        ll_store = findViewById(R.id.ll_store);
        ll_love = findViewById(R.id.ll_love);
        rl_avditem = findViewById(R.id.rl_avditem);
        rl_srec = findViewById(R.id.rl_srec);
        rl_parameter = findViewById(R.id.rl_parameter);
        adgallery = findViewById(R.id.adgallery);
        goods_banner = findViewById(R.id.goods_banner);
        tv_imgnumber = findViewById(R.id.tv_imgnumber);
        tv_price1 = findViewById(R.id.tv_price1);
        tv_activitytype = findViewById(R.id.tv_activitytype);
        tv_price2 = findViewById(R.id.tv_price2);
        tv_goodsname = findViewById(R.id.tv_goodsname);
        tv_expressdelivery = findViewById(R.id.tv_expressdelivery);
        tv_monthlysales = findViewById(R.id.tv_monthlysales);
        tv_address = findViewById(R.id.tv_address);
        tv_spec = findViewById(R.id.tv_spec);
        tv_parameter2 = findViewById(R.id.tv_parameter2);
        tv_storename = findViewById(R.id.tv_storename);
        tv_allgoods = findViewById(R.id.tv_allgoods);
        tv_tostore = findViewById(R.id.tv_tostore);
        tv_into_shop = findViewById(R.id.tv_into_shop);
        tv_conllect_shop = findViewById(R.id.tv_conllect_shop);
        tv_youhui = findViewById(R.id.tv_youhui);
        tv_tuijian_group = findViewById(R.id.tv_tuijian_group);
        ll_share = findViewById(R.id.ll_share);
        lv_pj = findViewById(R.id.lv_pj);
        iv_storeimg = findViewById(R.id.iv_storeimg);
        rl_offer = findViewById(R.id.rl_offer);
        tv_offer = findViewById(R.id.tv_offer);
        tv_description = findViewById(R.id.tv_description);
        tv_service = findViewById(R.id.tv_service);
        tv_logistics = findViewById(R.id.tv_logistics);
        Comment_fl = findViewById(R.id.Comment_fl);
        rl_pj = findViewById(R.id.rl_pj);
        rl_topjall = findViewById(R.id.rl_topjall);
        rl_allgoods = findViewById(R.id.rl_allgoods);
        gv_goods = findViewById(R.id.gv_goods);
        graphic_introduction = findViewById(R.id.graphic_introduction);
        sc_scroll = findViewById(R.id.sc_scroll);
        rl_store = findViewById(R.id.rl_store);
        v_sliding1 = findViewById(R.id.v_sliding1);
        v_sliding2 = findViewById(R.id.v_sliding2);
        v_sliding3 = findViewById(R.id.v_sliding3);
        ll_goods = findViewById(R.id.ll_goods);
        go_shopStore_ll = findViewById(R.id.go_shopStore_ll);
        all_goods_tv = findViewById(R.id.all_goods_tv);
        shop_tv = findViewById(R.id.shop_tv);

        all_goods_tv.setOnClickListener(this);
        shop_tv.setOnClickListener(this);
        iv_return.setOnClickListener(this);
        tv_goods.setOnClickListener(this);
        tv_datas.setOnClickListener(this);
        tv_evaluation.setOnClickListener(this);
        tv_addcart.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        tv_into_shop.setOnClickListener(this);
        tv_tuijian_group.setOnClickListener(this);
        tv_conllect_shop.setOnClickListener(this);
        tv_youhui.setOnClickListener(this);
        ll_store.setOnClickListener(this);
        ll_jimi.setOnClickListener(this);
        ll_love.setOnClickListener(this);
        rl_srec.setOnClickListener(this);
        rl_parameter.setOnClickListener(this);
        tv_allgoods.setOnClickListener(this);
        tv_tostore.setOnClickListener(this);
        rl_topjall.setOnClickListener(this);
        rl_allgoods.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        sc_scroll.setScrollYViewListener(this);
        ll_goods.setFocusableInTouchMode(true);
        ll_goods.requestFocus();

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rl_avditem.getLayoutParams();
        layoutParams.height = ClutterUtils.getScreenWidth(this);
        rl_avditem.setLayoutParams(layoutParams);
        initView();
    }

    @Override
    protected void initView() {
        netRun = new NetRun(this, handler);
        goods_id = getIntent().getStringExtra("goods_id");
        initData();
        goods_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        goods_banner.setImageLoader(new GlideImageLoader());
        goods_banner.setBannerAnimation(Transformer.Default);
        goods_banner.setDelayTime(3000);
        goods_banner.isAutoPlay(true);
        goods_banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    @Override
    protected void initData() {
        netRun.getProductDetails(goods_id);
        initShopCardNumber(State.UserKey);
    }

    private void initBadge() {
        badge = new QBadgeView(this).
                bindTarget(shopCardIv)//view
                .setBadgeBackgroundColor(getResources().getColor(R.color.red));
        badge.setBadgeTextSize(30, false);
//        badge.setGravityOffset((enter_iv.getX() / 4) , enter_iv.getY() / 4, false);
        badge.setShowShadow(true);
    }

    @SuppressLint("CheckResult")
    private void initShopCardNumber(String key) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostShopCardNumberData(key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code.equals("0")) {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            if (errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                            }

                        } else if (code.equals("200")) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            ShopCardNumberBean shopCardNumberBean = new Gson().fromJson(datas.toString(), ShopCardNumberBean.class);
                            try {
                                if (shopCardNumberBean.getCart_goods_num() == null) return;
                                if (isInteger(shopCardNumberBean.getCart_goods_num())) {
                                    if (badge == null) initBadge();
                                    if (Integer.valueOf(shopCardNumberBean.getCart_goods_num()) > 99) {
                                        badge.setBadgeText("99+");
                                    } else if (Integer.valueOf(shopCardNumberBean.getCart_goods_num()) == 0) {
                                    } else {
                                        badge.setBadgeText(shopCardNumberBean.getCart_goods_num());

                                    }
                                }
                            } catch (Exception e) {
                                LogUtils.e(e);
                            }
//                            ToastUtils.showShort(datas);

                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_goods:
                // 商品
                sc_scroll.scrollTo(0, 0);
                lastindex = 1;
                setMenu(1);
                break;
            case R.id.tv_datas:
                // 详情
                sc_scroll.scrollTo(0, menu3top);
                lastindex = 3;
                setMenu(3);
                break;
            case R.id.tv_evaluation:
                // 评价
                sc_scroll.scrollTo(0, menu2top);
                lastindex = 2;
                setMenu(2);
                break;
            case R.id.tv_addcart://添加购物车
               /* if (noAttri) {
                    if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                        netRun.addInCart(spec_goods_id, "1");
                    }
                    Toast.makeText(this,getString(R.string.noGoodAttr),Toast.LENGTH_SHORT).show();
                } else {*/
                showSpecPopu("1");
                break;
            case R.id.tv_buy://立即购买
               /* if (noAttri) {
                    if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                        if (detailsInfo.goods_info.is_virtual.equals("1")) {//虚拟商品
                            Intent intent = new Intent(ProductDetailsActivity.this, VirtualConfirmOrderActivity.class);
                            intent.putExtra("cart_id", spec_goods_id);
//                        intent.putExtra("cart_id", detailsInfo.goods_info.goods_id);
                            intent.putExtra("quantity", "1");
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(ProductDetailsActivity.this, OrderSureActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("cart_id", spec_goods_id + "|" + "1");
                            bundle.putString("ifcart", "0");
                            bundle.putString("isfcode", detailsInfo.goods_info.is_fcode);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    Toast.makeText(this,getString(R.string.noGoodAttr),Toast.LENGTH_SHORT).show();
                } else {*/
                showSpecPopu("2");
                break;
            case R.id.all_goods_tv:
                Bundle bundle = new Bundle();
                bundle.putString("store_id", detailsInfo.store_info.store_id);
                startActivity(GoodsListActivity.class, bundle);
                break;
            case R.id.ll_store://店铺
            case R.id.shop_tv:

//                if (detailsInfo.store_info.store_id == null || detailsInfo.store_info.store_id.equals("null") || detailsInfo.store_info.store_id.equals("")) {
////                    Toast.makeText(ProductDetailsActivity.this, getString(R.string.noinformation), Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Intent intent = new Intent(ProductDetailsActivity.this, StoreDetailsActivity.class);
//                Intent intent = new Intent(ProductDetailsActivity.this, ShopHomeActivity.class);
                if (detailsInfo != null)
                    if (detailsInfo.store_info != null)
                        if (detailsInfo.store_info.store_id != null)
                            startActivity(ShopHomeActivity.class, "store_id", detailsInfo.store_info.store_id);
                break;
            case R.id.ll_jimi://客服
                if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                    if (detailsInfo.store_callcenter != null && !detailsInfo.store_callcenter.isEmpty()) {
                        boolean ishave = false;
                        for (int i = 0; i < detailsInfo.store_callcenter.size(); i++) {
                            if (detailsInfo.store_callcenter.get(i).callcenter_list != null && !detailsInfo.store_callcenter.get(i).callcenter_list.isEmpty())
                                ishave = true;

                        }
                        if (!ishave) {
                            PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                                    this,
                                    null,
                                    "本店铺暂无客服",
                                    null,
                                    v -> PopupWindowUtil.getmInstance().dismissPopWindow());
                        } else showpopupw();

                    } else PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                            this,
                            null,
                            "本店铺暂无客服",
                            null,
                            v -> PopupWindowUtil.getmInstance().dismissPopWindow());
                }
                break;
            case R.id.ll_love://收藏
                if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                    if (isshoucang) {
                        netRun.cancelGoodsFavorite(goods_id, "goods");
                    } else {
                        netRun.addFavorites(goods_id, "goods");
                    }
                }
                break;
            case R.id.rl_srec://规格
                showSpecPopu("0");
                break;
            case R.id.rl_parameter://参数
                showParameterPopu();
                break;
            case R.id.tv_allgoods://全部商品
                Intent intentallgoods = new Intent(ProductDetailsActivity.this, GoodsListActivity.class);
                intentallgoods.putExtra("store_id", detailsInfo.store_info.store_id);
                startActivity(intentallgoods);
                break;
            case R.id.rl_allgoods://全部商品
                Intent intent2 = new Intent(ProductDetailsActivity.this, GoodsListActivity.class);
                intent2.putExtra("store_id", detailsInfo.store_info.store_id);
                startActivity(intent2);
                break;
            case R.id.tv_into_shop:
            case R.id.tv_tostore://去店铺
//                Intent intent3 = new Intent(ProductDetailsActivity.this, StoreDetailsActivity.class);
//                Bundle bundle3 = new Bundle();
//                bundle3.putString("store_id", detailsInfo.store_info.store_id);
//                intent3.putExtras(bundle3);
//                startActivity(intent3);
                if (detailsInfo != null)
                    if (detailsInfo.store_info != null)
                        if (detailsInfo.store_info.store_id != null)
                            startActivity(ShopHomeActivity.class, "store_id", detailsInfo.store_info.store_id);
                break;
            case R.id.rl_topjall://全部评价
                Intent intent4 = new Intent(ProductDetailsActivity.this, GoodsEvaluateActivity.class);
                intent4.putExtra("goods_id", detailsInfo.goods_info.goods_id);
                startActivity(intent4);
                break;
            case R.id.ll_share://分享
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, detailsInfo.goods_info.goods_url);
                startActivity(Intent.createChooser(textIntent, getString(R.string.order_reminder90)));
                break;
            case R.id.tv_conllect_shop:
                break;
            case R.id.tv_youhui:
                break;
            case R.id.tv_tuijian_group:
                break;
        }
    }

    private void showpopupw() {
        ServicePopu servicePopu = new ServicePopu(ProductDetailsActivity.this, detailsInfo.store_callcenter);
        servicePopu.setmenu(new ServicePopu.menu() {
            @Override
            public void onItemClick(GoodsDetailsInfo.store_callcenter.callcenter_list callcenter_list) {
                if (callcenter_list.type.equals("qq")) {
                    if (isQQClientAvailable(ProductDetailsActivity.this)) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + callcenter_list.num)));
                    } else {
                        Toast.makeText(ProductDetailsActivity.this, getString(R.string.nocustomerservice2), Toast.LENGTH_SHORT).show();
                    }
                } else if (callcenter_list.type.equals("im")) {
                    Log.i("--------------", callcenter_list.num + " " + State.UserId);
                    if (callcenter_list.num.equals(State.UserId)) {
                        Toast.makeText(appSingleton, getString(R.string.goodsdatails_reminder66), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent1 = new Intent(ProductDetailsActivity.this, DialogueActivity.class);
                    intent1.putExtra("member_id", callcenter_list.num);
                    intent1.putExtra("goods_image", detailsInfo.goods_info.goods_image_primary);
                    intent1.putExtra("goods_name", detailsInfo.goods_info.goods_name);
                    intent1.putExtra("goods_price", detailsInfo.goods_info.goods_price);
                    intent1.putExtra("goods_sales", detailsInfo.goods_info.goods_salenum);
                    intent1.putExtra("goods_id", detailsInfo.goods_info.goods_id);
                    startActivity(intent1);
                } else if (callcenter_list.type.equals("ww")) {
                    Toast.makeText(ProductDetailsActivity.this, "开发中", Toast.LENGTH_SHORT).show();
                }
            }
        });
        servicePopu.showAtLocation(rl_srec, Gravity.CENTER, 0, 0);
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void onScrollChanged(int y) {
        int index = 1;
        if (y < menu2top) {
            index = 1;
        } else if (y >= menu2top && y < menu3top) {
            index = 2;
        } else if (y >= menu3top) {
            index = 3;
        }
//        System.out.println("------------------   y=" + y + "  menu2top=" + menu2top + "   menu3top=" + menu3top + "  index=" + index);
        if (lastindex != index) {
            setMenu(index);
            lastindex = index;
        }
    }

    /**
     * 修改选中
     *
     * @param type
     */
    private void setMenu(int type) {
        v_sliding1.setVisibility(View.GONE);
        v_sliding2.setVisibility(View.GONE);
        v_sliding3.setVisibility(View.GONE);
        switch (type) {
            case 1:
                v_sliding1.setVisibility(View.VISIBLE);
                break;
            case 2:
               /* if (detailsInfo.goods_evaluate_list == null || detailsInfo.goods_evaluate_list.size() == 0) {
                    v_sliding3.setVisibility(View.VISIBLE);
                } else {
                    v_sliding2.setVisibility(View.VISIBLE);
                }*/
                v_sliding2.setVisibility(View.VISIBLE);
                break;
            case 3:
                v_sliding3.setVisibility(View.VISIBLE);
                break;
        }
    }

    private GoodsSpecPopup goodsSpecPopu;

    /**
     * 规格
     */
    private void showSpecPopu(String go) {
        boolean noAttri = detailsInfo.goods_info.goods_spec_info == null || detailsInfo.goods_info.goods_spec_info.size() == 0;
        goodsSpecPopu = new GoodsSpecPopup(ProductDetailsActivity.this, detailsInfo, goods_id, noAttri);
        goodsSpecPopu.setmenu(new GoodsSpecPopup.menu() {
            @Override
            public void onItemClick(int type, String id, String name, int selectedNum, String goodsName) {
                Log.i("---------------", "规格  " + type + "    " + id);
                if (name != null && name.length() != 0) {
                    tv_spec.setText(name);
                }
                ProductDetailsActivity.this.selectedNum = selectedNum;

                if (!id.equals(goods_id)) {
                    netRun.getProductDetails(goods_id);
                    goods_id = id;
                }

                if (tv_goodsname != null)
                    tv_goodsname.setText(goodsName);

                if (type == 2) {
                    if (go.equals("1")) {
                        if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                            netRun.addInCart(spec_goods_id, "" + selectedNum);
                        }
                    } else if (go.equals("2")) {
                        if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                            if (detailsInfo.goods_info.is_virtual.equals("1")) {//虚拟商品
                                Intent intent = new Intent(ProductDetailsActivity.this, VirtualConfirmOrderActivity.class);
                                intent.putExtra("cart_id", goods_id);
//                        intent.putExtra("cart_id", detailsInfo.goods_info.goods_id);
                                intent.putExtra("quantity", "" + selectedNum);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(ProductDetailsActivity.this, OrderSureActivity.class);
                                Bundle bundle = new Bundle();
                                LogUtils.d(goods_id + "|" + "" + selectedNum);
                                bundle.putString("cart_id", goods_id + "|" + "" + selectedNum);
                                bundle.putString("ifcart", "0");
                                bundle.putString("isfcode", detailsInfo.goods_info.is_fcode);
                                LogUtils.d(detailsInfo.goods_info.is_fcode);

                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }
                    }
//                    selectSpecOk = true;
                }
            }
        });
        goodsSpecPopu.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        goodsSpecPopu.uploadOrderInfo(detailsInfo);
    }

    /**
     * 规格
     */
    private void showSpecPopu() {
        GoodsSpecPopu goodsSpecPopu = new GoodsSpecPopu(ProductDetailsActivity.this, detailsInfo, spec_goods_id, noAttri);
        goodsSpecPopu.setmenu(new GoodsSpecPopu.menu() {
            @Override
            public void onItemClick(int type, String id, String name, int selectedNum) {
                Log.i("---------------", "规格  " + type + "    " + id);
                if (name != null && name.length() != 0) {
                    tv_spec.setText(name);
                }
                switch (type) {
                    case 1://加入购物车
                        if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                            netRun.addInCart(spec_goods_id, "" + selectedNum);
                        }
                        break;
                    case 2://确认购买
                        if (BooleanLogin.getInstance().hasLogin(ProductDetailsActivity.this)) {
                            if (detailsInfo.goods_info.is_virtual.equals("1")) {//虚拟商品
                                Intent intent = new Intent(ProductDetailsActivity.this, VirtualConfirmOrderActivity.class);
                                intent.putExtra("cart_id", spec_goods_id);
//                        intent.putExtra("cart_id", detailsInfo.goods_info.goods_id);
                                intent.putExtra("quantity", "" + selectedNum);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(ProductDetailsActivity.this, OrderSureActivity.class);
                                Bundle bundle = new Bundle();
                                LogUtils.d(spec_goods_id + "|" + "" + selectedNum);
                                bundle.putString("cart_id", spec_goods_id + "|" + "" + selectedNum);
                                bundle.putString("ifcart", "0");
                                bundle.putString("isfcode", detailsInfo.goods_info.is_fcode);
                                LogUtils.d(detailsInfo.goods_info.is_fcode);

                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }
                        break;
                    case 3://选中规格
                        spec_goods_id = id;
                        break;
                }
            }
        });
        goodsSpecPopu.showAtLocation(rl_srec, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 参数
     */
    private void showParameterPopu() {
        GoodsParameterPopu goodsSpecPopu = new GoodsParameterPopu(ProductDetailsActivity.this, detailsInfo.goods_info.goods_param_info);
        goodsSpecPopu.showAtLocation(rl_srec, Gravity.BOTTOM, 0, 0);
    }

    private void init() {
//        tv_price1.setText(detailsInfo.goods_info.goods_price);    //原价
        if (detailsInfo.goods_info.promotion_type == null || detailsInfo.goods_info.promotion_type.length() == 0) {
//            tv_price1.setText("¥" + detailsInfo.goods_info.goods_price + detailsInfo.goods_info.goods_unit);
            tv_price1.setText(detailsInfo.goods_info.goods_price);    //无优惠显示原价
            tv_price2.setText(getString(R.string.g_price) + "¥" + detailsInfo.goods_info.goods_marketprice);
            tv_activitytype.setVisibility(View.GONE);
        } else if (detailsInfo.goods_info.promotion_type.equals("groupbuy")) { //抢购  显示优惠价及优惠说明
//            tv_price1.setText(getString(R.string.order_reminder169) + detailsInfo.goods_info.promotion_price);
            tv_price1.setText(detailsInfo.goods_info.goods_promotion_price);
            tv_price2.setText(getString(R.string.order_reminder171) + detailsInfo.goods_info.goods_price);
            tv_activitytype.setVisibility(View.VISIBLE);
            tv_activitytype.setText(getString(R.string.panic_buying));
        } else if (detailsInfo.goods_info.promotion_type.equals("xianshi")) {//限时
            tv_price1.setText(detailsInfo.goods_info.goods_promotion_price);
//            tv_price1.setText(getString(R.string.order_reminder169) + detailsInfo.goods_info.promotion_price);
            tv_price2.setText(getString(R.string.order_reminder171) + detailsInfo.goods_info.goods_price);
            tv_activitytype.setVisibility(View.VISIBLE);
            tv_activitytype.setText(getString(R.string.goodsdatails_reminder11));
        } else if (detailsInfo.goods_info.promotion_type.equals("miaosha")) {//秒杀
            tv_price1.setText(detailsInfo.goods_info.goods_promotion_price);
//            tv_price1.setText(getString(R.string.order_reminder169) + detailsInfo.goods_info.promotion_price);
            tv_price2.setText(getString(R.string.order_reminder171) + detailsInfo.goods_info.goods_price);
            tv_activitytype.setVisibility(View.VISIBLE);
            tv_activitytype.setText(getString(R.string.goodsdatails_reminder12));
        }
        tv_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_goodsname.setText(detailsInfo.goods_info.goods_name);
        tv_expressdelivery.setText(getString(R.string.find_reminder338) + ":" + detailsInfo.goods_info.goods_freight + getString(R.string.yuan));//快递费
        tv_monthlysales.setText(getString(R.string.goodsdatails_reminder1) + ":" + detailsInfo.goods_info.goods_salenum);
        tv_address.setText(detailsInfo.goods_info.area_info);
        if (detailsInfo.mansong_info == null) {
            rl_offer.setVisibility(View.VISIBLE);
        } else {
            rl_offer.setVisibility(View.VISIBLE);
            tv_offer.setText(detailsInfo.mansong_info.mansong_name);
        }
        Glide.with(this).load(detailsInfo.store_info.avatar).into(iv_storeimg);
        tv_storename.setText(detailsInfo.store_info.store_name);

        tv_description.setText(detailsInfo.store_info.store_credit_info.store_desccredit.credit);
        tv_service.setText(detailsInfo.store_info.store_credit_info.store_servicecredit.credit);
        tv_logistics.setText(detailsInfo.store_info.store_credit_info.store_deliverycredit.credit);
        StringTokenizer st = new StringTokenizer(detailsInfo.goods_image, ",");
        List<String> list2 = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String i = st.nextToken();
            list2.add(i);
        }

//        setAD(list2);
        goods_banner.setImages(list2);
        goods_banner.startAutoPlay();
        goods_banner.start();

        //控制ScrollView模块显示与隐藏
        if (detailsInfo.goods_evaluate_list == null || detailsInfo.goods_evaluate_list.size() == 0) {
            rl_pj.setVisibility(View.GONE);
        } else {
            rl_pj.setVisibility(View.VISIBLE);
            goodsEvaluateAdapter = new GoodsEvaluateAdapter(ProductDetailsActivity.this, detailsInfo.goods_evaluate_list);
//            guessLikeRecy.setLayoutManager(new GridLayoutManager(mContexts,3));
            lv_pj.setAdapter(goodsEvaluateAdapter);

        }
        if (detailsInfo.goods_info.goods_spec_info == null || detailsInfo.goods_info.goods_spec_info.size() == 0) {
            rl_srec.setVisibility(View.GONE);
        } else {
            rl_srec.setVisibility(View.VISIBLE);
        }
        if (detailsInfo.goods_info.goods_param_info == null || detailsInfo.goods_info.goods_param_info.size() == 0) {
            rl_parameter.setVisibility(View.GONE);
        } else {
            rl_parameter.setVisibility(View.VISIBLE);
            tv_parameter2.setText(detailsInfo.goods_info.goods_param_info.get(0).data.get(0).param_name + " "
                    + detailsInfo.goods_info.goods_param_info.get(0).data.get(0).param_value + "...");
        }
        recommendedProductAdapter = new RecommendedProductAdapter(ProductDetailsActivity.this, detailsInfo.goods_commend_list);
        gv_goods.setAdapter(recommendedProductAdapter);

        String strURL = goods_body + "&goods_id=" + goods_id;
        Log.i("------------", "strURL=" + strURL);
        graphic_introduction.loadUrl(strURL);
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        graphic_introduction.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = graphic_introduction.getSettings();
        webSettings.setDefaultFontSize(52);// 设置字体大小
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);// 关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }

        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
         * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Comment_fl.post(new Runnable() {
            @Override
            public void run() {
                menu2top = Comment_fl.getTop();
            }
        });
        rl_store.post(new Runnable() {
            @Override
            public void run() {
                menu3top = rl_store.getBottom();
            }
        });
    }

    /**
     * 设置顶部图
     *
     * @param obj
     */
    protected void setAD(final List<String> obj) {
        tv_imgnumber.setText("1/" + obj.size());
        final String[] ADurl = obj.toArray(new String[obj.size()]);
        if (adgallery.mUris == null)
            adgallery.start(ProductDetailsActivity.this, ADurl, null, 0,
                    null, 0, 0, false);
        adgallery.setImageIndex(new MyAdGallery.ImageIndex() {
            @Override
            public void onImageIndex(int curIndex) {
                tv_imgnumber.setText((curIndex + 1) + "/" + obj.size());
            }
        });
        if (!ADurl[0].equals("")) saveUrlToFlie(ADurl[0], "shareIns.jpg");
    }


    @Override
    public void ReGetData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.shopCard_iv, R.id.share_iv, R.id.search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopCard_iv:
                startActivity(MainerActivity.class, "IsShowShopCard", "true");
//                Messenger.getDefault().send("JUMP_TO_SHOPCARD", DataConstant.TOKEN_JUMPMAINFRAGMENT_BAR);
                break;
            case R.id.share_iv:
                if (detailsInfo != null)
                    if (detailsInfo.goods_info != null) {
                        if (detailsInfo.goods_info.goods_url != null && detailsInfo.goods_info.goods_name != null) {
                            if (adgallery.mUris[0] != null) {
                                PopupWindowUtil.getmInstance().showShareGoodsPopupWindow(ProductDetailsActivity.
                                        this, new PopupWindowUtil.OnBindingUserChoiceBackInterface() {
                                    @Override
                                    public void onGetWay(String way) {
                                        if (way.equals("facebook")) {
                                            ShareLinkContent content = new ShareLinkContent.Builder()
                                                    .setContentUrl(Uri.parse(detailsInfo.goods_info.goods_url))
//                                        .setContentTitle(detailsInfo.goods_info.goods_name)
//                                        .setImageUrl(Uri.parse(adgallery.mUris[0]))
//                                        .setContentDescription(detailsInfo.goods_info.goods_name)
                                                    .build();
                                            //调用分享弹窗
                                            ShareDialog.show(ProductDetailsActivity.this, content);
                                        } else if (way.equals("ins_story")) {
//https://developers.facebook.com/docs/instagram/sharing-to-stories ins文档
                                            if (destFile != null && destFile.exists()) {
                                                Uri backgroundAssetUri = getImageContentUri(mContexts, destFile);
                                                String attributionLinkUrl = detailsInfo.goods_info.goods_url;
                                                // Instantiate implicit intent with ADD_TO_STORY action,
                                                // background asset, and attribution link
                                                Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
                                                intent.setDataAndType(backgroundAssetUri, "image/*");
                                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                                intent.putExtra("content_url", attributionLinkUrl);

                                                // Instantiate activity and verify it will resolve implicit intent
                                                if (getPackageManager().resolveActivity(intent, 0) != null) {
                                                    startActivityForResult(intent, 0);
                                                } else {
                                                    Toast.makeText(mContexts, "您没有此app可分享", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(mContexts, "分享失败", Toast.LENGTH_SHORT).show();
                                            }


                                        } else if (way.equals("ins_feed")) {
                                            if (destFile != null && destFile.exists()) {
                                                String type = "image/*";
                                                Uri backgroundAssetUri = getImageContentUri(mContexts, destFile);
                                                createInstagramIntent(type, backgroundAssetUri);
                                            } else
                                                Toast.makeText(mContexts, "分享失败", Toast.LENGTH_SHORT).show();


                                        }
                                    }
                                });

                            }

                        }
                    }


                break;
            case R.id.search_tv:
                startActivity(ChoiceActivity.class);
                break;
        }
    }

    //分享至ins feed
    private void createInstagramIntent(String type, Uri uri) {
        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);

        // Create the URI from the media
        //File media = new File(mediaPath);
        //  Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);
        Intent intent = Intent.createChooser(share, "Share to");
        if (getPackageManager().resolveActivity(intent, 0) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(mContexts, "您没有此app可分享", Toast.LENGTH_SHORT).show();
        }
        // Broadcast the Intent.
    }

    private String fileName = "";
    private File destFile;

    //由于ins分享只能分享本地图片 需要保存到本地
    //保存网络照片显示
    private void saveUrlToFlie(String photourl, String photoName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!TextUtils.isEmpty(photourl)) {
                        File photo = Glide.with(ProductDetailsActivity.this)
                                .load(photourl)
                                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get();
//获取到下载得到的图片，进行本地保存
                        File pictureFolder = Environment.getExternalStorageDirectory();
                        //第二个参数为你想要保存的目录名称
                        File appDir = new File(pictureFolder, "your_picture_save_path");
                        if (!appDir.exists()) {
                            appDir.mkdirs();
                        }


                        if (photoName != null && !photoName.equals("") && !photoName.equals("null")) {
                            fileName = photoName;

                        } else {
                            if (photoName.endsWith(".jpg")) {
                                photoName.replace(".jpg", "");
                            }
                            fileName = System.currentTimeMillis() + ".jpg";
                        }

                        destFile = new File(appDir, fileName);
                        //把gilde下载得到图片复制到定义好的目录中去
                        Bit2FileUtils.copy(photo, destFile);
                        //RxBus.getDefault().post(destFile, "spacePhoto");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

}
