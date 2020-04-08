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
public class MainHouseTwoRecyViewHolder extends ItemViewModel<MainHouseFragentViewHolder> {
    public MainHouseTwoRecyViewHolder(@NonNull MainHouseFragentViewHolder viewModel, HouseUIBean.Home1Bean home1Bean) {
        super(viewModel);
        iconUrl.set(home1Bean.getImage());
    }
    public ObservableField<String> iconUrl = new ObservableField<>();
}
