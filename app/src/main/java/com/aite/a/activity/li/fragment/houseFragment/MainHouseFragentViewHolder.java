package com.aite.a.activity.li.fragment.houseFragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.a.activity.li.activity.MessageActivity;
import com.aite.a.activity.li.item.mainHouseRecy.MainHouse3RecyViewHolder;
import com.aite.a.activity.li.item.mainHouseRecy.MainHouseGoodsListRecyViewHolder;
import com.aite.a.activity.li.item.mainHouseRecy.MainHouseRecyViewHolder;
import com.aite.a.activity.li.item.mainHouseRecy.MainHouseTwoRecyViewHolder;
import com.aite.a.activity.li.mvp.RetrofitClient;
import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.base.Mark;
import com.aite.awangdalibrary.base.RetrofitBuilder;
import com.aite.awangdalibrary.ui.activity.search.SearchKotlinActivity;
import com.facebook.all.All;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.valy.baselibrary.retrofit.RxScheduler;
import com.valy.baselibrary.utils.GlideImageLoader;
import com.jiananshop.a.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import okhttp3.ResponseBody;


/**
 * @Auther: lzy
 * @datetime: 2020-01-10
 * @desc:
 */
public class MainHouseFragentViewHolder extends BaseViewModel<MvvMViewAdapter> {
    public static final String TOKEN_SHOW_HOUSE_DATA = "TOKEN_SHOW_HOUSE_DATA";

    public MainHouseFragentViewHolder(@NonNull Application application) {
        super(application);
    }

    public ObservableList<MainHouseRecyViewHolder> observableList = new ObservableArrayList<>();
    public ObservableList<MainHouseTwoRecyViewHolder> advertisementObservableList = new ObservableArrayList<>();
    public ObservableList<MainHouse3RecyViewHolder> mainHouse3RecyObservableList = new ObservableArrayList<>();
    public ObservableList<MainHouseGoodsListRecyViewHolder> goodListObservableList = new ObservableArrayList<>();

    public ObservableField<String> searchTv = new ObservableField<>();
    public ObservableField<String> three_one_Url = new ObservableField<>();
    public ObservableField<String> three_two_Url = new ObservableField<>();
    public ObservableField<String> three_three_Url = new ObservableField<>();
    public BindingCommand onLookSort = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChoiceActivity.class);
        }
    });
    public BindingCommand onToSearch = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), SearchKotlinActivity.class);
            if (Mark.State.UserKey != null)
                intent.putExtra("key", Mark.State.UserKey);
            ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
        }
    });

    public BindingCommand onMessageSort = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MessageActivity.class);
        }
    });
    /**
     * 添加图片的context 必须
     */
    @SuppressLint("StaticFieldLeak")
    public Context context = ActivityManager.getInstance().getCurrentActivity();

    public ArrayList<Bitmap> mBitmapList = new ArrayList<>();

    public ItemBinding<MainHouseRecyViewHolder> itemBinding = ItemBinding.of(new OnItemBind<MainHouseRecyViewHolder>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MainHouseRecyViewHolder item) {
            itemBinding.set(BR.viewModel, R.layout.item_mainfragment_top_icon);
        }
    });
    public ItemBinding<MainHouseTwoRecyViewHolder> advertisementItemBinding = ItemBinding.of(new OnItemBind<MainHouseTwoRecyViewHolder>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MainHouseTwoRecyViewHolder item) {
            itemBinding.set(BR.viewModel, R.layout.item_mainfragment_two_icon);
        }
    });
    public ItemBinding<MainHouse3RecyViewHolder> mainHouse3RecyItemBinding = ItemBinding.of(new OnItemBind<MainHouse3RecyViewHolder>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MainHouse3RecyViewHolder item) {
            itemBinding.set(BR.viewModel, R.layout.item_mainfragment_home3_icon);
        }
    });
    public ItemBinding<MainHouseGoodsListRecyViewHolder> goodlistItemBinding = ItemBinding.of(new OnItemBind<MainHouseGoodsListRecyViewHolder>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MainHouseGoodsListRecyViewHolder item) {
            itemBinding.set(BR.viewModel, R.layout.item_mainfragment_goods_list);
        }
    });


    public void initNavigation(HouseUIBean.NavigationBean advListBean) {
        for (HouseUIBean.NavigationBean.ItemBeanX navigationBean : advListBean.getItem()) {
            observableList.add(new MainHouseRecyViewHolder(MainHouseFragentViewHolder.this, navigationBean));
        }
    }

    public void initPushGoods(HouseUIBean.GoodsBean goodsBean) {
        for (HouseUIBean.GoodsBean.ItemBeanXX mGoodsBean : goodsBean.getItem()) {
            goodListObservableList.add(new MainHouseGoodsListRecyViewHolder(MainHouseFragentViewHolder.this, mGoodsBean));
        }
    }


    protected void initBanner(Banner banner) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);

    }
}
