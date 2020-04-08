package com.aite.a.activity.li.fragment.houseFragment;

import android.annotation.SuppressLint;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aite.a.activity.GoodsListActivity;
import com.aite.a.activity.WebActivity;
import com.blankj.rxbus.RxBus;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseWebViewActivity;
import com.aite.a.activity.li.data.DataConstant;
import com.aite.a.activity.li.mvp.RetrofitClient;
import com.aite.a.base.Mark;
import com.lzy.okgo.model.HttpParams;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.ActivityManager;
import com.valy.baselibrary.utils.ClutterUtils;
import com.valy.baselibrary.utils.GlideImageLoader;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.adapter.GridViewIconAdapter;
import com.aite.a.utils.SystemUtil;
import com.aite.a.view.MyGridView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.FragmentHouseMainBinding;
import com.valy.baselibrary.adpter.BaseItemDecoration;
import com.valy.baselibrary.retrofit.RxScheduler;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import level3.util.WheelUtils;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.http.NetworkUtil;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class HousesFragment extends BaseFragment<FragmentHouseMainBinding, MainHouseFragentViewHolder> {
    private Context context;
    //banner datalist

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_house_main;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainHouseFragentViewHolder initViewModel() {
        return ViewModelProviders.of(this).get(MainHouseFragentViewHolder.class);
    }

    @Override
    public void initParam() {

    }

    @Override
    public void initData() {
        if (!NetworkUtil.isNetworkAvailable(context)) {
            ToastUtils.showShort("请检查网络");
            return;
        }

        //  viewModel.
        getHouseUI(BaseConstant.CURRENTLANGUAGE, "json");
        Messenger.getDefault().send("HIDE_BAR", DataConstant.TOKEN_HIDE_BAR);
//        viewModel.initBanner(binding.advertisementBanner);
        binding.mainRecyView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, R.color.gray), context,
                        5f, "7.1:10"));

        binding.goodsListRecyView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, R.color.gray), context,
                        2f, null));
        binding.searchTv.setOnClickListener(v -> {
            search();
//
        });
//        binding.etSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
//            if (i == EditorInfo.IME_ACTION_SEND || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//                if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                    search();
//                }
//                return true;
//            }
//            return false;
//        });
        badge = new QBadgeView(context).
                bindTarget(binding.messageIv).
                setBadgeBackgroundColor(getResources().getColor(R.color.red));
    }

    private Badge badge;

    //初始化banner
    protected void initBanner(Banner banner) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(getScreenWidth(), SystemUtil.dp2px(context, 140));
        params.height = SystemUtil.dp2px(context, 150);
        banner.setLayoutParams(params);

    }

    private void initBanner(HouseUIBean.AdvListBean advListBean) {
        if ((advListBean != null && advListBean.getItem() != null && advListBean.getItem().size() > 0)) {
            List<String> list_img = new ArrayList<>();
            for (HouseUIBean.AdvListBean.ItemBean itemBean : (advListBean.getItem())) {
                list_img.add(itemBean.getImage());
            }
            LayoutInflater inflater = LayoutInflater
                    .from(context);
            View view = inflater.inflate(
                    R.layout.item_banner, null);
            final Banner banner = view
                    .findViewById(R.id.item_banner);
//            Banner banner = new Banner(context);
            initBanner(banner);
            banner.setImages(list_img);
            banner.startAutoPlay();
            banner.start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(context, BaseWebViewActivity.class);
                    intent.putExtra("webViewUrl", advListBean.getItem().get(position).getData());
                    intent.putExtra("isHideToolBar", "true");
                    startActivity(intent);
                }
            });
            binding.fatherAdvLl.addView(banner);

        }
    }

    //tokenJson responseBody.string()只能使用一次 用完则会会销毁
    @SuppressLint("CheckResult")
    public void getHouseUI(String lang_type, String type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetHouseData2(lang_type, type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            String code = jsonObject.optString("code");
                            if (code.equals("200")) {
                                JSONArray datasArry = jsonObject.optJSONArray("datas");
                                for (int i = 0; i < datasArry.length(); i++) {
                                    JSONObject innObj = datasArry.getJSONObject(i);
                                    JSONObject home1ListObject = innObj.optJSONObject("home1");
                                    JSONObject home2ListObject = innObj.optJSONObject("home2");
                                    JSONObject home3ListObject = innObj.optJSONObject("home3");
                                    JSONObject home4ListObject = innObj.optJSONObject("home4");
                                    JSONObject home5ListObject = innObj.optJSONObject("home5");
                                    JSONObject home6ListObject = innObj.optJSONObject("home6");
                                    JSONObject goodsListObject = innObj.optJSONObject("goods");
                                    JSONObject advListObject = innObj.optJSONObject("adv_list");
                                    JSONObject navigationListObject = innObj.optJSONObject("navigation");
                                    if (advListObject != null) {
                                        HouseUIBean.AdvListBean advListBean = new Gson().fromJson(advListObject.toString(), HouseUIBean.AdvListBean.class);
                                        initBanner(advListBean);
                                    }
                                    if (navigationListObject != null) {
                                        HouseUIBean.NavigationBean advListBean = new Gson().fromJson(navigationListObject.toString(), HouseUIBean.NavigationBean.class);
//                                        viewModel.initNavigation(advListBean);
                                        initNavigation(advListBean);
                                    }
                                    if (home1ListObject != null) {
                                        HouseUIBean.Home1Bean home1Bean = new Gson().fromJson(home1ListObject.toString(), HouseUIBean.Home1Bean.class);
//                                            viewModel.advertisementObservableList.add(new MainHouseTwoRecyViewHolder(MainHouseFragentViewHolder.this, home1Bean));
                                        initHome1(home1Bean);
                                    }
                                    if (home2ListObject != null) {
                                        HouseUIBean.Home2Bean home2Bean = new Gson().fromJson(home2ListObject.toString(), HouseUIBean.Home2Bean.class);
//                                            viewModel.three_one_Url.set(home2Bean.getSquare_image());
//                                            viewModel.three_two_Url.set(home2Bean.getRectangle1_image());
//                                            viewModel.three_three_Url.set(home2Bean.getRectangle2_image());
                                        initHome2(home2Bean);
                                    }
                                    if (home3ListObject != null) {
                                        HouseUIBean.Home3Bean home3Bean = new Gson().fromJson(home3ListObject.toString(), HouseUIBean.Home3Bean.class);
//                                            for (int p = 0; p < home3Bean.getItem().size(); p++) {
//                                                viewModel.mainHouse3RecyObservableList.add(new MainHouse3RecyViewHolder(MainHouseFragentViewHolder.this, home3Bean.getItem().get(p)));
//
//                                            }
                                        initHome3(home3Bean);
                                    }
                                    if (home4ListObject != null) {
                                        HouseUIBean.Home4Bean home4Bean = new Gson().fromJson(home4ListObject.toString(), HouseUIBean.Home4Bean.class);
//                                            for (int p = 0; p < home3Bean.getItem().size(); p++) {
//                                                viewModel.mainHouse3RecyObservableList.add(new MainHouse3RecyViewHolder(MainHouseFragentViewHolder.this, home3Bean.getItem().get(p)));
//
//                                            }
                                        initHome4(home4Bean);
                                    }
                                    if (home5ListObject != null) {
                                        HouseUIBean.Home5Bean home5Bean = new Gson().fromJson(home5ListObject.toString(), HouseUIBean.Home5Bean.class);
//                                            for (int p = 0; p < home3Bean.getItem().size(); p++) {
//                                                viewModel.mainHouse3RecyObservableList.add(new MainHouse3RecyViewHolder(MainHouseFragentViewHolder.this, home3Bean.getItem().get(p)));
//
//                                            }
                                        initHome5(home5Bean);
                                    }
                                    if (home6ListObject != null) {
                                        HouseUIBean.Home6Bean home6Bean = new Gson().fromJson(home6ListObject.toString(), HouseUIBean.Home6Bean.class);
                                        initHome6(home6Bean);
                                    }
                                    if (goodsListObject != null) {
                                        HouseUIBean.GoodsBean goodsBean = new Gson().fromJson(goodsListObject.toString(), HouseUIBean.GoodsBean.class);
                                        viewModel.initPushGoods(goodsBean);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }

                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }

    private void initNavigation(HouseUIBean.NavigationBean advListBean) {
        List<String> titls = new ArrayList<>();
        List<String> icons = new ArrayList<>();
        for (int i = 0; i < advListBean.getItem().size(); i++) {
            titls.add(advListBean.getItem().get(i).getNavigation_name());
            icons.add(advListBean.getItem().get(i).getImage());
        }
        GridView gridView = new GridView(context);
        gridView.setNumColumns(5);
        GridViewIconAdapter mGridViewIconAdapter = new GridViewIconAdapter(context, icons, titls);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), BaseWebViewActivity.class);
                intent.putExtra("webViewUrl", String.format("%s&comefrom=app", advListBean.getItem().get(i).getNavigation_data()));
                intent.putExtra("isHideToolBar", "true");
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            }
        });
        gridView.setAdapter(mGridViewIconAdapter);
        binding.fatherAdvLl.addView(gridView);

    }

    private void initHome6(HouseUIBean.Home6Bean home6Bean) {
        LayoutInflater inflater10 = LayoutInflater
                .from(context);
        View convertView10 = inflater10.inflate(
                R.layout.item_custom_home6, null);
        ImageView iv_home6t1 = convertView10
                .findViewById(R.id.iv_home6t1);
        ImageView iv_home6t2 = convertView10
                .findViewById(R.id.iv_home6t2);
        ImageView iv_home6t3 = convertView10
                .findViewById(R.id.iv_home6t3);
        ImageView iv_home6t4 = convertView10
                .findViewById(R.id.iv_home6t4);
        final LinearLayout ll_home6item = convertView10
                .findViewById(R.id.ll_home6item);
        if (home6Bean.getItem() != null) {
            if (home6Bean.getItem().size() > 0) {
                // 获取图片真正的宽高
                Glide.with(context).asBitmap()
                        .load(home6Bean.getItem().get(0).getImage())// 强制Glide返回一个Bitmap对象
                        .into(new SimpleTarget<Bitmap>() {

                            @Override
                            public void onResourceReady(Bitmap bitmap,
                                                        Transition<? super Bitmap> arg1) {
                                float width = bitmap.getWidth();
                                float height = bitmap.getHeight();
                                float bl = height / width;
                                float he = (getScreenWidth() / 4) * bl;
                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_home6item
                                        .getLayoutParams();
                                layoutParams.height = (int) (he);
                                ll_home6item.setLayoutParams(layoutParams);
                            }
                        });
                Glide.with(context)
                        .load(home6Bean.getItem().get(0).getImage()).into(iv_home6t1);
                iv_home6t1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent6 = new Intent(context,
                                WebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", home6Bean.getItem().get(0).getData());
                        bundle.putString("title", home6Bean.getTitle());
                        intent6.putExtras(bundle);
                        startActivity(intent6);
                    }
                });
            }
            if (home6Bean.getItem().size() > 1) {
                Glide.with(context)
                        .load(home6Bean.getItem().get(1).getImage()).into(iv_home6t2);
                iv_home6t2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent6 = new Intent(context,
                                WebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", home6Bean.getItem().get(1).getData());
                        bundle.putString("title", home6Bean.getTitle());
                        intent6.putExtras(bundle);
                        startActivity(intent6);
                    }
                });
            }
            if (home6Bean.getItem().size() > 2) {
                Glide.with(context)
                        .load(home6Bean.getItem().get(2).getImage()).into(iv_home6t3);
                iv_home6t3.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent6 = new Intent(context,
                                WebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", home6Bean.getItem().get(2).getData());
                        bundle.putString("title", home6Bean.getTitle());
                        intent6.putExtras(bundle);
                        startActivity(intent6);
                    }
                });
            }
            if (home6Bean.getItem().size() > 3) {
                Glide.with(context)
                        .load(home6Bean.getItem().get(3).getImage()).into(iv_home6t4);
                iv_home6t4.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent6 = new Intent(context,
                                WebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url", home6Bean.getItem().get(3).getData());
                        bundle.putString("title", home6Bean.getTitle());
                        intent6.putExtras(bundle);
                        startActivity(intent6);
                    }
                });
            }
        }
        binding.fatherAdvLl.addView(convertView10);
    }

    private void initHome4(HouseUIBean.Home4Bean home4Bean) {
        LayoutInflater inflater6 = LayoutInflater
                .from(context);
        View convertView6 = inflater6.inflate(
                R.layout.item_custom_home4, null);
        ImageView iv_img4 = convertView6
                .findViewById(R.id.iv_img4);
        ImageView iv_img5 = convertView6
                .findViewById(R.id.iv_img5);
        ImageView iv_img6 = convertView6
                .findViewById(R.id.iv_img6);
        LinearLayout ll_home4 = convertView6
                .findViewById(R.id.ll_home4);
        LinearLayout ll_home4item = convertView6
                .findViewById(R.id.ll_home4item);
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) ll_home4item.getLayoutParams();
        layoutParams4.height = (int) (getScreenWidth() / 2.46);
        ll_home4item.setLayoutParams(layoutParams4);

        Glide.with(context)
                .load(home4Bean.getRectangle1_image()).into(iv_img4);
        Glide.with(context)
                .load(home4Bean.getRectangle2_image()).into(iv_img5);
        Glide.with(context)
                .load(home4Bean.getRectangle3_image()).into(iv_img6);
        iv_img4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (home4Bean.getRectangle1_data() != null
                        && home4Bean.getRectangle1_data().length() != 0) {
                    Intent intent6 = new Intent(
                            context, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url",
                            home4Bean.getRectangle1_data());
                    bundle.putString("title", home4Bean.getTitle());
                    intent6.putExtras(bundle);
                    startActivity(intent6);
                }
            }
        });
        iv_img5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (home4Bean.getRectangle2_data() != null
                        && home4Bean.getRectangle2_data().length() != 0) {
                    Intent intent6 = new Intent(
                            context, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url",
                            home4Bean.getRectangle2_data());
                    bundle.putString("title", home4Bean.getTitle());
                    intent6.putExtras(bundle);
                    startActivity(intent6);
                }
            }
        });
        iv_img6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (home4Bean.getSquare_data() != null
                        && home4Bean.getSquare_data().length() != 0) {
                    Intent intent6 = new Intent(
                            context, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", home4Bean.getSquare_data());
                    bundle.putString("title", home4Bean.getTitle());
                    intent6.putExtras(bundle);
                    startActivity(intent6);
                }
            }
        });
        binding.fatherAdvLl.addView(convertView6);
    }

    private void initHome5(HouseUIBean.Home5Bean home5Bean) {
        LayoutInflater inflater8 = LayoutInflater
                .from(context);
        View convertView8 = inflater8.inflate(
                R.layout.item_custom_home5, null);
        ImageView iv_home5t1 = convertView8
                .findViewById(R.id.iv_home5t1);
        ImageView iv_home5t2 = convertView8
                .findViewById(R.id.iv_home5t2);
        ImageView iv_home5t3 = convertView8
                .findViewById(R.id.iv_home5t3);
        final LinearLayout ll_home5item = convertView8
                .findViewById(R.id.ll_home5item);
        // 获取图片真正的宽高
        if (home5Bean.getItem() != null) {
            if (home5Bean.getItem().size() > 0) {
                Glide.with(context).asBitmap()
                        .load(home5Bean.getItem().get(0).getImage())// 强制Glide返回一个Bitmap对象
                        .into(new SimpleTarget<Bitmap>() {

                            @Override
                            public void onResourceReady(Bitmap bitmap,
                                                        Transition<? super Bitmap> arg1) {
                                float width = bitmap.getWidth();
                                float height = bitmap.getHeight();
                                float bl = height / width;
                                float he = (getScreenWidth() / 3) * bl;
                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_home5item
                                        .getLayoutParams();
                                layoutParams.height = (int) (he);
                                ll_home5item.setLayoutParams(layoutParams);
                            }
                        });
                Glide.with(context)
                        .load(home5Bean.getItem().get(0).getImage()).into(iv_home5t1);
                iv_home5t1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (home5Bean.getItem().get(0).getData() != null
                                && home5Bean.getItem().get(0).getData().length() != 0) {
                            Intent intent6 = new Intent(
                                    context, WebActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("url", home5Bean.getItem().get(0).getData());
                            bundle.putString("title", home5Bean.getItem().get(0).getType());
                            intent6.putExtras(bundle);
                            startActivity(intent6);
                        }
                    }
                });
            }
            if (home5Bean.getItem().size() > 1) {
                Glide.with(context)
                        .load(home5Bean.getItem().get(1).getImage()).into(iv_home5t2);
                iv_home5t2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (home5Bean.getItem().get(1).getData() != null
                                && home5Bean.getItem().get(1).getData().length() != 0) {
                            Intent intent6 = new Intent(
                                    context, WebActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("url", home5Bean.getItem().get(1).getData());
                            bundle.putString("title", home5Bean.getItem().get(1).getType());
                            intent6.putExtras(bundle);
                            startActivity(intent6);
                        }
                    }
                });
            }
            if (home5Bean.getItem().size() > 2) {
                Glide.with(context)
                        .load(home5Bean.getItem().get(2).getImage()).into(iv_home5t3);
                iv_home5t3.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (home5Bean.getItem().get(2).getData() != null
                                && home5Bean.getItem().get(2).getData().length() != 0) {
                            Intent intent6 = new Intent(
                                    context, WebActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("url", home5Bean.getItem().get(2).getData());
                            bundle.putString("title", home5Bean.getItem().get(2).getType());
                            intent6.putExtras(bundle);
                            startActivity(intent6);
                        }
                    }
                });
            }
        }
        binding.fatherAdvLl.addView(convertView8);
    }

    private void initHome3(HouseUIBean.Home3Bean home3Bean) {
        LayoutInflater inflater5 = LayoutInflater
                .from(context);
        View convertView5 = inflater5.inflate(
                R.layout.item_custom_home3, null);
        MyGridView gv_img = convertView5
                .findViewById(R.id.gv_img);

        TwoImageAdapters TwoImage = new TwoImageAdapters(
                home3Bean.getItem(), context, getScreenWidth());
        gv_img.setAdapter(TwoImage);
        binding.fatherAdvLl.addView(convertView5);
    }

    private void initHome2(HouseUIBean.Home2Bean home2Bean) {
        LayoutInflater inflater4 = LayoutInflater
                .from(context);
        View convertView4 = inflater4.inflate(
                R.layout.item_custom_home2, null);
        ImageView iv_img1 = convertView4
                .findViewById(R.id.iv_img1);
        ImageView iv_img2 = convertView4
                .findViewById(R.id.iv_img2);
        ImageView iv_img3 = convertView4
                .findViewById(R.id.iv_img3);
        LinearLayout ll_home2item = convertView4
                .findViewById(R.id.ll_home2item);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) ll_home2item.getLayoutParams();
        layoutParams2.height = (int) (getScreenWidth() / 2.46);
        ll_home2item.setLayoutParams(layoutParams2);
        Glide.with(context)
                .load(home2Bean.getSquare_image()).into(iv_img1);
        Glide.with(context)
                .load(home2Bean.getRectangle1_image()).into(iv_img2);
        Glide.with(context)
                .load(home2Bean.getRectangle2_image()).into(iv_img3);
        iv_img1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initHome2OnClick(home2Bean.getSquare_data(), home2Bean.getTitle());
            }
        });
        iv_img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initHome2OnClick(home2Bean.getRectangle1_data(), home2Bean.getTitle());
            }
        });
        iv_img3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initHome2OnClick(home2Bean.getRectangle2_data(), home2Bean.getTitle());
            }
        });
        binding.fatherAdvLl.addView(convertView4);
    }

    private void initHome2OnClick(String url, String title) {

        if (url != null
                && title.length() != 0) {
            Intent intent6 = new Intent(
                    getActivity(), WebActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            bundle.putString("title", title);
            intent6.putExtras(bundle);
            startActivity(intent6);
        }
    }

    private void initHome1(HouseUIBean.Home1Bean home1Bean) {
        LayoutInflater inflater3 = LayoutInflater
                .from(context);
        View convertView3 = inflater3.inflate(
                R.layout.item_custom_home1, null);
        final ImageView iv_img = convertView3
                .findViewById(R.id.iv_img);
        // 获取图片真正的宽高
        Glide.with(context).asBitmap()
                .load(home1Bean.getImage())// 强制Glide返回一个Bitmap对象
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> arg1) {
                        float width = bitmap.getWidth();
                        float height = bitmap.getHeight();
                        float bl = height / width;   //使图片的宽高始终适配手机的宽
                        float he = getScreenWidth() * bl;
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv_img
                                .getLayoutParams();
                        layoutParams.width = getScreenWidth();
                        layoutParams.height = (int) (he);
                        iv_img.setLayoutParams(layoutParams);
                        Glide.with(context)
                                .load(home1Bean.getImage()).into(iv_img);
                    }
                });
        iv_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initHome2OnClick(home1Bean.getData(), home1Bean.getTitle());

            }
        });
        binding.fatherAdvLl.addView(convertView3);
    }

    protected int getScreenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    protected int getScreenHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Mark.State.UserKey != null && !Mark.State.UserKey.isEmpty())
            getUnReadMsgBean();
    }

    public void search() {
        Bundle bundle = new Bundle();
        bundle.putString("keyword", viewModel.searchTv.get());
        if (!viewModel.searchTv.get().equals("")) {
            startActivity(GoodsListActivity.class, bundle);
        }
//        binding.etSearch.setText("");
    }

    /**
     * 返回字段	类型	说明
     * datas.total_num	整型	新接收到的总消息数量
     * datas.newcommon	整型	新接收到的普通消息数量
     * datas.newsystem	整型	新接收到的系统消息数量
     * datas.newpersonal	整型	新接收到的站内消息
     */
    @SuppressLint("CheckResult")
    public void getUnReadMsgBean() {
        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetMsgNum(Mark.State.UserKey, "1").
                compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (jsonObject != null) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            if (datas != null) {
                                String error = datas.optString("error");
                                if (error != null && !error.isEmpty()) {
                                    ToastUtils.showShort(error);
                                } else {
                                    AllUnReadMsgLessBean allUnReadMsgLessBean = new Gson().fromJson(datas.toString(), AllUnReadMsgLessBean.class);
                                    if (allUnReadMsgLessBean != null) {
                                        if (allUnReadMsgLessBean.getTotal_num() == null) return;
                                        if (ClutterUtils.isInteger(allUnReadMsgLessBean.getTotal_num())) {
                                            if ((Integer.parseInt(allUnReadMsgLessBean.getTotal_num())) > 99)
                                                badge.setBadgeText("99+");
                                            else if (Integer.parseInt(allUnReadMsgLessBean.getTotal_num()) != 0)
//                                                Messenger.getDefault().send(allUnReadMsgLessBean.getTotal_num(), MainViewModel.TOKEN_CHATNOTIFY_REFRESH);
//                                            else {
                                                //getTotal_num全部
                                                badge.setBadgeText(String.valueOf(Integer.parseInt(allUnReadMsgLessBean.getTotal_num())));

                                        }
                                    }
                                }
                            }
                        }

                    }
                });


    }

}
