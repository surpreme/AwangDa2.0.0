package com.aite.a.fargment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.adapter.ShopMoreGoodsAdapter;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.bean.ShopHomeBean3;
import com.aite.a.bean.ShopHomeBean5;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;


/**
 * @Auther: valy
 * @datetime: 2020/2/29
 * @desc:
 */
public class ShopMoreFragments extends Fragment {
    @BindView(R.id.type_recy)
    RecyclerView typeRecy;
    @BindView(R.id.smartlayout)
    SmartRefreshLayout smartlayout;
    @BindView(R.id.composite_tv)
    TextView compositeTv;
    @BindView(R.id.sales_tv)
    TextView salesTv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.price_top_iv)
    ImageView priceTopIv;
    @BindView(R.id.price_down_iv)
    ImageView priceDownIv;
    @BindView(R.id.price_ll)
    LinearLayout priceLl;
    @BindView(R.id.news_tv)
    TextView newsTv;
    private Unbinder unbinder;
    private Context context;
    private ShopMoreFragments shopMainFragment;
    private String mStore_id = "";
    private List<ShopHomeBean5.GoodsListBean> mDatas = new ArrayList<>();
    private ShopMoreGoodsAdapter shopGoodsAdapter;
    private int mCurrentPage = 1;
    private boolean hasMore = false;
    private String type = "0";//类型: 1销量，2浏览量，3价格，4上架时间
    private String order = "0";//排序：1升序，默认降序
    private boolean isSort = false;
    private boolean isChecked = false;
    private List<ShopHomeBean3> shopHomeBean3List = new ArrayList<>();

    public ShopMoreFragments newInstance(String store_id) {
        if (shopMainFragment == null) {
            shopMainFragment = new ShopMoreFragments();
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
        View view = inflater.inflate(R.layout.fragment_shopmore, container, false);
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
        if (!isSort)
            getShopInformation5(mStore_id, type, 8, mCurrentPage, "zh_cn");
        else
            getShop2Information5Data(mStore_id, type, 8, mCurrentPage, order, "zh_cn");


    }


    @SuppressLint("CheckResult")
    private void getShopInformation5(String store_id, String key, int page, int curpage, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getShopInformation5Data(store_id, key, page, curpage, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code != null && code.equals("200")) {
                            JSONObject dataObject = jsonObject.optJSONObject("datas");
                            ShopHomeBean5 shopHomeBean5 = new Gson().fromJson(dataObject.toString(), ShopHomeBean5.class);
                            mDatas.addAll(shopHomeBean5.getGoods_list());
                            shopGoodsAdapter.notifyDataSetChanged();
                            boolean mHasmore = jsonObject.optBoolean("hasmore");
                            int page_total = jsonObject.optInt("page_total");
                            hasMore = mHasmore && page_total > mCurrentPage;
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
    private void getShop2Information5Data(String store_id, String key, int page, int curpage, String order, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getShop2Information5Data(store_id, key, page, curpage, order, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code != null && code.equals("200")) {
                            JSONObject dataObject = jsonObject.optJSONObject("datas");
                            ShopHomeBean5 shopHomeBean5 = new Gson().fromJson(dataObject.toString(), ShopHomeBean5.class);
                            mDatas.addAll(shopHomeBean5.getGoods_list());
                            shopGoodsAdapter.notifyDataSetChanged();
                            boolean mHasmore = jsonObject.optBoolean("hasmore");
                            int page_total = jsonObject.optInt("page_total");
                            hasMore = mHasmore && page_total > mCurrentPage;
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
        shopGoodsAdapter = new ShopMoreGoodsAdapter(getActivity(), mDatas);
        typeRecy.setAdapter(shopGoodsAdapter);
        typeRecy.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        smartlayout.setEnableAutoLoadMore(true);
        mCurrentPage = 1;
        smartlayout.setOnLoadMoreListener(refreshLayout -> {
            LogUtils.d("mCurrentPage" + mCurrentPage);
            if (hasMore) {
                mCurrentPage++;
                initDatas();
                smartlayout.finishLoadMore();
            } else {
                smartlayout.setNoMoreData(true);

            }

        });
        smartlayout.setOnRefreshListener(refreshLayout -> {
            initRefresh();
            smartlayout.finishRefresh();


        });

    }

    private void initRefresh() {
        mCurrentPage = 1;
        if (!mDatas.isEmpty()) {
            mDatas.clear();
            shopGoodsAdapter.notifyDataSetChanged();
        }
        hasMore = true;
        initDatas();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    private void clearTvColor() {
        compositeTv.setTextColor(getActivity().getResources().getColor(R.color.black));
        salesTv.setTextColor(getActivity().getResources().getColor(R.color.black));
        priceTv.setTextColor(getActivity().getResources().getColor(R.color.black));
        newsTv.setTextColor(getActivity().getResources().getColor(R.color.black));
    }

    //类型: 1销量，2浏览量，3价格，4上架时间
    @OnClick({R.id.composite_tv, R.id.sales_tv, R.id.price_tv, R.id.news_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.composite_tv:
                clearTvColor();
                compositeTv.setTextColor(getActivity().getResources().getColor(R.color.red));
                type = "0";
                isSort = false;
                smartlayout.setNoMoreData(false);
                initRefresh();
                break;
            case R.id.sales_tv:
                clearTvColor();
                salesTv.setTextColor(getActivity().getResources().getColor(R.color.red));
                type = "1";
                isSort = false;
                smartlayout.setNoMoreData(false);
                initRefresh();
                break;
            case R.id.price_tv:
                clearTvColor();
                priceTv.setTextColor(getActivity().getResources().getColor(R.color.red));
                type = "3";
                isSort = true;
                isChecked = !isChecked;
                order = isChecked ? "1" : "0";
                if (order.equals("1")) {
                    priceTopIv.setColorFilter(Color.RED);
                    priceDownIv.setColorFilter(Color.BLACK);
                } else {
                    priceTopIv.setColorFilter(Color.BLACK);
                    priceDownIv.setColorFilter(Color.RED);
                }
                smartlayout.setNoMoreData(false);
                initRefresh();
                break;
            case R.id.news_tv:
                clearTvColor();
                newsTv.setTextColor(getActivity().getResources().getColor(R.color.red));
                type = "4";
                isSort = false;
                smartlayout.setNoMoreData(false);
                initRefresh();
                break;
        }
    }
}
