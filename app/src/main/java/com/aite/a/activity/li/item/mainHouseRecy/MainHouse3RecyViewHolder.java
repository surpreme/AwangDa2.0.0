package com.aite.a.activity.li.item.mainHouseRecy;

import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;
import com.aite.a.activity.li.fragment.houseFragment.HouseUIBean;
import com.aite.a.activity.li.fragment.houseFragment.MainHouseFragentViewHolder;

import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class MainHouse3RecyViewHolder extends ItemViewModel<MainHouseFragentViewHolder> {
    public MainHouse3RecyViewHolder(@NonNull MainHouseFragentViewHolder viewModel, HouseUIBean.Home3Bean.ItemBean itemBean) {
        super(viewModel);
        iconUrl.set(itemBean.getImage());
    }

    public ObservableField<String> iconUrl = new ObservableField<>();
}
