package com.aite.a.fargment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.GoodsListActivity;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.adapter.ShopMoreGoodsAdapter2;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.valy.baselibrary.utils.GlideImageLoader;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.adapter.GridViewIconAdapter;
import com.aite.a.bean.ShopHomeBean;
import com.aite.a.bean.ShopHomeBean2;
import com.aite.a.bean.ShopHomeBean3;
import com.aite.a.bean.ShopHomeBean4;
import com.aite.a.view.CustomGridView;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiananshop.a.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.valy.baselibrary.retrofit.RxScheduler;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;


/**
 * @Auther: valy
 * @datetime: 2020/2/29
 * @desc:
 */
public class ShopMainFragment extends Fragment implements OnBannerListener {
    @BindView(R.id.type_tabLayout)
    TabLayout typeTabLayout;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.type_recy)
    RecyclerView typeRecy;
    @BindView(R.id.smartlayout)
    SmartRefreshLayout smartlayout;
    //    @BindView(R.id.top_adv_recy)
//    RecyclerView topTypeRecy;
    @BindView(R.id.top_type_gridview)
    CustomGridView topTypeGridview;
    @BindView(R.id.top_1_iv)
    ImageView top1Iv;
    @BindView(R.id.top_2_iv)
    ImageView top2Iv;
    @BindView(R.id.top_adv_iv_ll)
    LinearLayout topAdvIvLl;
    private Unbinder unbinder;
    private Context context;
    private ShopMainFragment shopMainFragment;
    private String mStore_id = "";
    private List<String> list_img = new ArrayList<>();
    private List<ShopHomeBean4.GoodsListBean> mDatas = new ArrayList<>();
    private ShopMoreGoodsAdapter2 shopGoodsAdapter;
    private int mCurrentPage = 1;
    private boolean hasMore = false;
    private String type = "";
    private List<ShopHomeBean3> shopHomeBean3List = new ArrayList<>();

    public ShopMainFragment newInstance(String store_id) {
        if (shopMainFragment == null) {
            shopMainFragment = new ShopMainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("store_id", store_id);
            shopMainFragment.setArguments(bundle);
        }
        return shopMainFragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_shopmain, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        initDatas();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    private void initDatas() {
        getShopInformation(mStore_id, "zh_cn");//顶部分类
        getShopInformation2(mStore_id, "zh_cn");//顶部广告
        getShopInformation3(mStore_id, "zh_cn");//分类tablayout

    }

    private List<String> topImgList = new ArrayList<>();

    private void onGetShopInformationSuccess(ShopHomeBean shopHomeBean) {
        for (ShopHomeBean.MbSwiperBean mbSwiperBean : shopHomeBean.getMb_swiper()) {
            list_img.add(mbSwiperBean.getStore_mb_slide_img());
        }
        for (ShopHomeBean.AdSwiperBean adSwiperBean : shopHomeBean.getAd_swiper()) {
            topImgList.add(adSwiperBean.getImage_path());
        }
        if (!topImgList.isEmpty()) {
            if (topImgList.get(0) != null)
                Glide.with(context).load(topImgList.get(0)).into(top1Iv);
            if (topImgList.get(1) != null)
                Glide.with(context).load(topImgList.get(1)).into(top2Iv);

        } else {
            topAdvIvLl.setVisibility(View.GONE);
        }
        if (!list_img.isEmpty()) {
            banner.setVisibility(View.VISIBLE);
            banner.setImages(list_img);
            banner.startAutoPlay();
            banner.start();
        } else banner.setVisibility(View.GONE);


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
                            getActivity().runOnUiThread(() -> {
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

    private List<String> names = new ArrayList<>();
    private List<String> imgs = new ArrayList<>();

    @SuppressLint("CheckResult")
    private void getShopInformation2(String store_id, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getShopInformation2Data(store_id, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String error_code = jsonObject.optString("error_code");
                        if (error_code != null && error_code.equals("0")) {
                            JSONArray dataObject = jsonObject.optJSONArray("datas");

                            List<ShopHomeBean2> shopHomeBean2List = new Gson().fromJson(dataObject.toString(), new TypeToken<List<ShopHomeBean2>>() {
                            }.getType());
                            getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < shopHomeBean2List.size(); i++) {
                                    names.add(shopHomeBean2List.get(i).getStc_name());
                                    imgs.add(shopHomeBean2List.get(i).getStc_image());
                                }
                                gridViewIconAdapter.notifyDataSetChanged();
                            });

                        } else {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
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
    private void getShopInformation3(String store_id, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getShopInformation3Data(store_id, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String error_code = jsonObject.optString("error_code");
                        if (error_code != null && error_code.equals("0")) {
                            JSONArray dataObject = jsonObject.optJSONArray("datas");

                            List<ShopHomeBean3> shopHomeBean3Listssss = new Gson().fromJson(dataObject.toString(), new TypeToken<List<ShopHomeBean3>>() {
                            }.getType());
                            if (!shopHomeBean3List.isEmpty()) shopHomeBean3List.clear();
                            shopHomeBean3List.addAll(shopHomeBean3Listssss);
                            getActivity().runOnUiThread(() -> {
                                for (int i = 0; i < shopHomeBean3List.size(); i++) {
                                    typeTabLayout.addTab(typeTabLayout.newTab().setText(shopHomeBean3List.get(i).getGc_name()));


                                }
                                type = shopHomeBean3List.get(0).getGc_id();
                                getShopInformation4(mStore_id, shopHomeBean3List.get(0).getGc_id(), mCurrentPage, "zh_cn");//分类tablayout


                            });

                        } else {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
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
    private void getShopInformation4(String store_id, String gc_id, int curpage, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getShopInformation4Data(store_id, gc_id, curpage, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String error_code = jsonObject.optString("error_code");
                        if (error_code != null && error_code.equals("0")) {
                            JSONObject dataObject = jsonObject.optJSONObject("datas");
                            ShopHomeBean4 shopHomeBean4 = new Gson().fromJson(dataObject.toString(), ShopHomeBean4.class);
                            mDatas.addAll(shopHomeBean4.getGoods_list());
                            shopGoodsAdapter.notifyDataSetChanged();
                            hasMore = shopHomeBean4.getPage().isHasmore();
                        } else {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
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

    private void onGetShopInformationSuccess2(ShopHomeBean shopHomeBean) {

    }

    //初始化banner
    protected void initBanner(Banner banner) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);

    }

    private GridViewIconAdapter gridViewIconAdapter;

    private void initViews() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            if (bundle.get("store_id") != null) {
                mStore_id = bundle.getString("store_id");
            } else PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                    getActivity(),
                    null,
                    "链接服务器超时 请稍后再试",
                    null,
                    v -> PopupWindowUtil.getmInstance().dismissPopWindow());
//                ToastUtils.showToast(context, "链接服务器超时 请稍后再试");
        }
        initBanner(banner);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this);
        shopGoodsAdapter = new ShopMoreGoodsAdapter2(getActivity(), mDatas);
        typeRecy.setAdapter(shopGoodsAdapter);
        typeRecy.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        topTypeGridview.setAdapter(gridViewIconAdapter = new GridViewIconAdapter(context, imgs, names));
        topTypeGridview.setOnItemClickListener((parent, view, position, id) -> {
            if (names.get(position) == null) return;
            Bundle bundle = new Bundle();
            bundle.putString("keyword", names.get(position));
            bundle.putString("store_id", mStore_id);
            startActivity(new Intent(getActivity(), GoodsListActivity.class).putExtras(bundle));
        });
        smartlayout.setEnableAutoLoadMore(true);
        mCurrentPage = 1;
        smartlayout.setOnLoadMoreListener(refreshLayout -> {
            LogUtils.d("mCurrentPage" + mCurrentPage);
            if (hasMore) {
                mCurrentPage++;
//                smartlayout.setNoMoreData(false);
                getShopInformation4(mStore_id, type, mCurrentPage, "zh_cn");//分类tablayout
                smartlayout.finishLoadMore();
            } else {
                smartlayout.setNoMoreData(true);

            }

        });
        typeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                type = shopHomeBean3List.get(tab.getPosition()).getGc_id();
//                if (tab.getPosition() == 0) {
                mCurrentPage = 1;
                smartlayout.setNoMoreData(false);
                hasMore = true;
                mDatas.clear();
                getShopInformation4(mStore_id, type, mCurrentPage, "zh_cn");


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
