package com.aite.a.activity.li.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aite.a.activity.GoodsListActivity;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.aite.awangdalibrary.ui.activity.productdetails.CustomerServiceKotlinDailogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.base.Mark;
import com.aite.a.bean.ShopHomeBean;
import com.aite.a.fargment.ShopMainFragment;
import com.aite.a.fargment.ShopMoreFragments;
import com.aite.a.fargment.ShopTypeFragment;
import com.aite.a.utils.SoftKeyboardUtil;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;
@Route(path = "/jn/ShopHomeActivity")
public class ShopHomeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.rl_iv)
    ImageView rlLv;
    @BindView(R.id.store_image_iv)
    ImageView storeImageIv;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.collect_iv)
    ImageView collectIv;
    @BindView(R.id.collect_store_number_tv)
    TextView collectStoreNumberTv;
    @BindView(R.id.fragment_fl)
    FrameLayout fragmentFl;
    @BindView(R.id.house_tv)
    TextView houseTv;
    @BindView(R.id.house_ll)
    LinearLayout houseLl;
    @BindView(R.id.goods_tv)
    TextView goodsTv;
    @BindView(R.id.goods_ll)
    LinearLayout goodsLl;
    @BindView(R.id.type_tv)
    TextView typeTv;
    @BindView(R.id.type_ll)
    LinearLayout typeLl;
    @BindView(R.id.chat_tv)
    TextView chatTv;
    @BindView(R.id.house_chat_ll)
    LinearLayout houseChatLl;
    @BindView(R.id.search_et)
    EditText searchEt;
    private boolean isCollect = false;
    private ShopMainFragment shopMainFragment;
    private ShopMoreFragments shopMoreFragments;
    private ShopTypeFragment shopTypeFragment;
    private String mStore_id = "";

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.shop_all_layout;
    }

    @Override
    protected void initView() {
        iv_back.setOnClickListener(this);
        SoftKeyboardUtil.hideSoftKeyboard(searchEt);
        searchEt.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchEt.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (EditorInfo.IME_ACTION_SEARCH == i) {
                SoftKeyboardUtil.hideSoftKeyboard(searchEt);
                Bundle bundle = new Bundle();
                bundle.putString("keyword", textView.getText().toString());
                bundle.putString("store_id", mStore_id);
                startActivity(GoodsListActivity.class, bundle);
                return true;
            }
            return false;
        });

    }


    @Override
    protected void initModel() {
        if (getIntent().getStringExtra("store_id") != null) {
            this.mStore_id = getIntent().getStringExtra("store_id");
            showFragment(1);
        }
        getShopInformation(mStore_id, "zh_cn");


    }

    @SuppressLint("CheckResult")
    private void getShopInformation(String store_id, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getShopInformationData(store_id, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String error_code = jsonObject.optString("error_code");
                        JSONObject dataObject = jsonObject.optJSONObject("datas");
                        if (error_code != null && error_code.equals("0")) {
                            ShopHomeBean shopHomeBean = new Gson().fromJson(dataObject.toString(), ShopHomeBean.class);
                            runOnUiThread(() -> {
                                onGetShopInformationSuccess(shopHomeBean);

                            });

                        } else {
                            ErrorBean errorBean = new Gson().fromJson(dataObject.toString(), ErrorBean.class);
                            if (errorBean != null && errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                            }
                        }
                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }

    ////fav_id=3&fav_type=store&key=76f919703cb8a7f6937e4893af10baa6&lang_type=zh_cn
    @SuppressLint("CheckResult")
    private void collectShop(String fav_id, String fav_type, String key, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onCollectShop(fav_id, fav_type, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        JSONObject dataObject = jsonObject.optJSONObject("datas");
                        if (code != null && code.equals("200")) {
                            String data = jsonObject.optString("datas");
                            if (data.equals("1")) {
                                isCollect = true;
                                collectIv.setColorFilter(isCollect ? Color.RED : Color.GRAY);
                                PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                                        context,
                                        null,
                                        "收藏成功",
                                        null,
                                        v -> PopupWindowUtil.getmInstance().dismissPopWindow());
                            }


                        } else {
                            ErrorBean errorBean = new Gson().fromJson(dataObject.toString(), ErrorBean.class);
                            if (errorBean != null && errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                            }
                        }
                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }

    @SuppressLint("CheckResult")
    private void cancelCollectShop(String fav_id, String fav_type, String key, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onCancelCollectShop(fav_id, fav_type, key, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        JSONObject dataObject = jsonObject.optJSONObject("datas");
                        if (code != null && code.equals("200")) {
                            String data = jsonObject.optString("datas");
                            if (data.equals("1")) {
                                isCollect = false;
                                collectIv.setColorFilter(isCollect ? Color.RED : Color.GRAY);
                                PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                                        context,
                                        null,
                                        "取消收藏成功",
                                        null,
                                        v -> PopupWindowUtil.getmInstance().dismissPopWindow());
                            }


                        } else {
                            ErrorBean errorBean = new Gson().fromJson(dataObject.toString(), ErrorBean.class);
                            if (errorBean != null && errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                            }
                        }
                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }


    private void onGetShopInformationSuccess(ShopHomeBean shopHomeBean) {
        Glide.with(context).load(shopHomeBean.getStore_avatar()).into(storeImageIv);
        Glide.with(context).load(shopHomeBean.getStore_banner()).into(rlLv);
        tvStoreName.setText(shopHomeBean.getStore_name());
        collectStoreNumberTv.setText(getString(R.string.shop_collect,shopHomeBean.getFavorites_count()));
        isCollect = shopHomeBean.getIsStoreFavorites() == 1;
        collectIv.setColorFilter(shopHomeBean.getIsStoreFavorites() == 1 ? Color.RED : Color.GRAY);
    }

    private FragmentManager fragmentManager;

    private void showFragment(int i) {
        if (fragmentManager == null) fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (i) {
            case 1:
                if (shopMainFragment == null) {
                    shopMainFragment = new ShopMainFragment().newInstance(mStore_id);
                    transaction.add(R.id.fragment_fl, shopMainFragment);
                } else {
                    transaction.replace(R.id.fragment_fl, shopMainFragment);

                }

                break;
            case 2:
                if (shopMoreFragments == null) {
                    shopMoreFragments = new ShopMoreFragments().newInstance(mStore_id);
                    transaction.add(R.id.fragment_fl, shopMoreFragments);
                }
                transaction.replace(R.id.fragment_fl, shopMoreFragments);

                break;
            case 3:
                if (shopTypeFragment == null) {
                    shopTypeFragment = new ShopTypeFragment().newInstance(mStore_id);
                    transaction.add(R.id.fragment_fl, shopTypeFragment);
                } else {
                    transaction.replace(R.id.fragment_fl, shopTypeFragment);

                }
                break;
        }
        transaction.commit();

    }

    @OnClick({R.id.house_ll, R.id.goods_ll, R.id.type_ll, R.id.house_chat_ll, R.id.collect_iv, R.id.collect_store_number_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.house_ll:
                showFragment(1);
                break;
            case R.id.goods_ll:
                showFragment(2);
                break;
            case R.id.type_ll:
                showFragment(3);
                break;
            case R.id.house_chat_ll:
                CustomerServiceKotlinDailogFragment customerServiceDailogFragment = new CustomerServiceKotlinDailogFragment().newInstance(mStore_id);
                customerServiceDailogFragment.show(getSupportFragmentManager(), "CustomerServiceKotlinDailogFragment");
                break;
            case R.id.collect_iv:
            case R.id.collect_store_number_tv:
                if (!isCollect) collectShop(mStore_id, "store", Mark.State.UserKey, "zh_cn");
                else cancelCollectShop(mStore_id, "store", Mark.State.UserKey, "zh_cn");

                break;
        }
    }


}
