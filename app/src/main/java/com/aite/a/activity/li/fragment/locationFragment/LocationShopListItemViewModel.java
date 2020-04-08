package com.aite.a.activity.li.fragment.locationFragment;

import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import com.aite.a.activity.StoreDetailsActivity;
import com.aite.a.activity.li.activity.ShopHomeActivity;
import com.aite.a.activity.li.util.ActivityManager;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class LocationShopListItemViewModel extends ItemViewModel<LocationFragmentViewHolder> {

    public LocationShopListItemViewModel(@NonNull LocationFragmentViewHolder viewModel, NearByShopListBean.ListBean nearShopListBean) {
        super(viewModel);
        iconUrl.set(nearShopListBean.getStore_label());
        iconTitle.set(nearShopListBean.getStore_name());
        store_id.set(nearShopListBean.getStore_id());
        if (nearShopListBean.getStore_address() != null)
            shopAdress.set(nearShopListBean.getStore_address());
        if (nearShopListBean.getArea_info() != null)
            shopMsg.set(nearShopListBean.getStore_zy());
        if (nearShopListBean.getDistance() != null) {
            if (!nearShopListBean.getDistance().equals("")) {
                long away = (Long.valueOf(nearShopListBean.getDistance()) / 1000);

                String io = away + "km";
                if (nearShopListBean.getDistance().length() > 3) {
                    String p = nearShopListBean.getDistance().substring(0, nearShopListBean.getDistance().length() - 3);
                    String o = nearShopListBean.getDistance().substring(nearShopListBean.getDistance().length() - 3, nearShopListBean.getDistance().length() - 1);
                    distanceString.set(String.format("%s.%skm", p, o));

                } else {
                    distanceString.set(io);

                }
//                if (away < 500)
//                    io = "<1km";
//                if (away < 2000 && away > 1000)
//                    io = "<2km";
//                if (away > 2000 && away < 5000)
//                    io = "<5km";
//                if (away > 5000)
//                    io = ">5km";
            }

        }


    }

    public ObservableField<String> iconUrl = new ObservableField();
    public ObservableField<String> store_id = new ObservableField();
    public ObservableField<String> iconTitle = new ObservableField();
    public ObservableField<String> shopMsg = new ObservableField();
    public ObservableField<String> shopAdress = new ObservableField();
    public ObservableField<String> distanceString = new ObservableField();
    public BindingCommand onLookInformation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), ShopHomeActivity.class);
            intent.putExtra("store_id", store_id.get());
            ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
        }
    });
}
