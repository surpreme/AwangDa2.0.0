package com.aite.a.activity.li.fragment.locationFragment;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import androidx.annotation.NonNull;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.BaseData3;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.base.Mark;
import com.jiananshop.a.R;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.valy.baselibrary.retrofit.RxScheduler;

import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc: 请参考MvvMSmartViewAdapter 文件自定义方法
 */
public class LocationFragmentViewHolder extends BaseViewModel<MvvMSmartViewAdapter> {
    public LocationFragmentViewHolder(@NonNull Application application) {
        super(application);
        autoRefresh.set(true);
        isHasMore.set(true);
        city_id.set("");
        area_id.set("");
        class_id.set("");
        sort_type.set("");
        sort_dis_type.set("");
        area_name.set("");
    }

    public int mCurrentPage = 1;
    public ObservableField<String> points = new ObservableField<>();
    public ObservableField<String> city_id = new ObservableField<>();
    public ObservableField<String> area_id = new ObservableField<>();
    public ObservableField<String> class_id = new ObservableField<>();
    public ObservableField<String> sort_type = new ObservableField<>();
    public ObservableField<String> sort_dis_type = new ObservableField<>();
    public ObservableField<String> area_name = new ObservableField<>();

//    public String points = "";
//    public String city_id = "";
//    public String area_id = "";
//    public String class_id = "";
//    public String sort_type = "";
//    public String sort_dis_type = "";

    public ObservableField<String> topImageUrl = new ObservableField<>();
    public ObservableField<String> top1_1url = new ObservableField<>();
    public ObservableField<String> top1_2url = new ObservableField<>();
    public ObservableField<String> top2_1url = new ObservableField<>();
    public ObservableField<String> top2_2url = new ObservableField<>();
    public ObservableField<String> top1Title = new ObservableField<>();
    public ObservableField<String> top2Title = new ObservableField<>();
    /**
     * 判断是否有下一页 决定取消下拉刷新
     */
    public ObservableBoolean isHasMore = new ObservableBoolean();
    /**
     * 是否打开第一次加载 自动下拉刷新
     */
    public ObservableBoolean autoRefresh = new ObservableBoolean();
    //附近商家
    public ObservableList<LocationShopListItemViewModel> orderItemList = new ObservableArrayList<>();
    public ItemBinding<LocationShopListItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<LocationShopListItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, LocationShopListItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_near_sop_mvvm);
        }
    });

    @SuppressLint("CheckResult")
    public void onGetAroundShopData(String key) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetAroundShopData(key)
                .compose(RxScheduler.Flo_io_main())
                .filter(locationAdvBeanBaseData -> {
                    if (!locationAdvBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(locationAdvBeanBaseData.getDatas().getError());
                        LogUtils.e(locationAdvBeanBaseData.getDatas().getError());
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData3::getDatas)
                .filter(locationAdvBeanBaseData -> {
                    if (locationAdvBeanBaseData.getError() != null) {
                        ToastUtils.showShort(locationAdvBeanBaseData.getError());
                        LogUtils.e(locationAdvBeanBaseData.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(locationAdvBean -> {
            dismissDialog();
            initViews(locationAdvBean);


        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });

    }

    /**
     * @param key
     * @Query("key") String key,
     * @Query("curpage") int curpage,
     * @Query("points") String points,
     * @Query("city_id") String city_id,
     * @Query("area_id") String area_id,
     * @Query("class_id") String class_id,
     * @Query("sort_type") String sort_type,
     * @Query("sort_dis_type") String sort_dis_type
     * String points, String city_id, String area_id, String class_id, String sort_type, String sort_dis_type
     */
    @SuppressLint("CheckResult")
    public void onGetShopListData(String key, String points) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetAroundShopListShopData(key, mCurrentPage, points, city_id.get(), area_id.get(), class_id.get(), sort_type.get(), sort_dis_type.get())
                .compose(RxScheduler.Flo_io_main())
                .filter(nearByShopListBeanBaseData -> {
                    if (!nearByShopListBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(nearByShopListBeanBaseData.getDatas().getError());
                        LogUtils.e(nearByShopListBeanBaseData.getDatas().getError());
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(nearByShopListBeanBaseData -> {
                    if (nearByShopListBeanBaseData.getError() != null) {
                        ToastUtils.showShort(nearByShopListBeanBaseData.getError());
                        LogUtils.e(nearByShopListBeanBaseData.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(nearByShopListBean -> {
            dismissDialog();
            for (NearByShopListBean.ListBean listBean : nearByShopListBean.getList()) {
                orderItemList.add(new LocationShopListItemViewModel(LocationFragmentViewHolder.this, listBean));
            }
            /**
             * 防止后台给的hasMore一直为true
             */
//            if (!nearByShopListBean.getPage().isHasmore()) isHasMore.set(false);
//            else isHasMore.set(true);
            isHasMore.set((nearByShopListBean.getList().size() > 0));


        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });

    }

    /**
     * if (mCurrentPage == 3)
     * isHasMore.set(false);
     */
    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> {
        mCurrentPage++;
        refreshLayout.finishLoadMore(1000);
        LogUtils.d("LoadMore");
        onGetShopListData(Mark.State.UserKey, points.get());

    };


    public OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            refreshLayout.finishRefresh(1000);
            refresh();

        }
    };

    public void refresh() {
        mCurrentPage = 1;
        LogUtils.d("Refreshing");
        if (!orderItemList.isEmpty()) orderItemList.clear();
        onGetShopListData(Mark.State.UserKey, points.get());

    }

    @SuppressLint("StaticFieldLeak")
    public WaterDropHeader waterDropHeader = new WaterDropHeader(ActivityManager.getInstance().getCurrentActivity());
    @SuppressLint("StaticFieldLeak")
    public ClassicsFooter classicsFooter = new ClassicsFooter(ActivityManager.getInstance().getCurrentActivity());

    private void initViews(LocationAdvBean locationAdvBean) {
        top1_1url.set(locationAdvBean.getAdv().get(0).getList().get(0).getAdv_pic());
        top1_2url.set(locationAdvBean.getAdv().get(0).getList().get(1).getAdv_pic());
        top2_1url.set(locationAdvBean.getAdv().get(1).getList().get(0).getAdv_pic());
        top2_2url.set(locationAdvBean.getAdv().get(1).getList().get(0).getAdv_pic());
        top1Title.set(locationAdvBean.getAdv().get(0).getTitle());
        top2Title.set(locationAdvBean.getAdv().get(1).getTitle());
        topImageUrl.set(locationAdvBean.getSwiper().get(0).getAdv_pic());
    }
}
