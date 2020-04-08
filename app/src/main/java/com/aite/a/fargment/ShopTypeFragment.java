package com.aite.a.fargment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.adapter.ShopGoodsAdapter;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.bean.ShopHomeBean3;
import com.aite.a.bean.ShopHomeBean4;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiananshop.a.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.valy.baselibrary.adpter.BaseItemDecoration;
import com.valy.baselibrary.retrofit.RxScheduler;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import level3.util.WheelUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;


/**
 * @Auther: valy
 * @datetime: 2020/2/29
 * @desc:
 */
public class ShopTypeFragment extends Fragment implements OnBannerListener {
    @BindView(R.id.type_tabLayout)
    TabLayout typeTabLayout;
    @BindView(R.id.type_recy)
    RecyclerView typeRecy;
    @BindView(R.id.smartlayout)
    SmartRefreshLayout smartlayout;
    private Unbinder unbinder;
    private Context context;
    private ShopTypeFragment shopMainFragment;
    private String mStore_id = "";
    private List<String> list_img = new ArrayList<>();
    private List<ShopHomeBean4.GoodsListBean> mDatas = new ArrayList<>();
    private ShopGoodsAdapter shopGoodsAdapter;
    private int mCurrentPage = 1;
    private boolean hasMore = false;
    private String type = "";
    private List<ShopHomeBean3> shopHomeBean3List = new ArrayList<>();

    public ShopTypeFragment newInstance(String store_id) {
        if (shopMainFragment == null) {
            shopMainFragment = new ShopTypeFragment();
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
        View view = inflater.inflate(R.layout.fragment_shoptype, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initDatas();
    }

    private void initDatas() {
        getShopInformation3(mStore_id, "zh_cn");//分类tablayout

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
        typeRecy.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 5), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 10), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, android.R.color.darker_gray), context,
                        4f, "1:1.5"));
        shopGoodsAdapter = new ShopGoodsAdapter(getActivity(), mDatas);
        typeRecy.setAdapter(shopGoodsAdapter);
        typeRecy.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        smartlayout.setEnableAutoLoadMore(true);
        mCurrentPage = 1;
        smartlayout.setOnLoadMoreListener(refreshLayout -> {
            LogUtils.d("mCurrentPage" + mCurrentPage);
            if (hasMore) {
                mCurrentPage++;
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
                mCurrentPage = 1;
                smartlayout.setNoMoreData(false);
                hasMore = true;
                if (!mDatas.isEmpty()) {
                    mDatas.clear();
                    shopGoodsAdapter.notifyDataSetChanged();
                }
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
