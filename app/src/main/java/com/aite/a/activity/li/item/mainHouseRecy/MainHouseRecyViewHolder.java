package com.aite.a.activity.li.item.mainHouseRecy;

import android.content.Intent;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;
import com.aite.a.activity.li.activity.BaseWebViewActivity;
import com.aite.a.activity.li.fragment.houseFragment.HouseUIBean;
import com.aite.a.activity.li.fragment.houseFragment.MainHouseFragentViewHolder;
import com.aite.a.activity.li.util.ActivityManager;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class MainHouseRecyViewHolder extends ItemViewModel<MainHouseFragentViewHolder> {
    private HouseUIBean.NavigationBean.ItemBeanX navigationBean;

    public MainHouseRecyViewHolder(@NonNull MainHouseFragentViewHolder viewModel, HouseUIBean.NavigationBean.ItemBeanX navigationBean) {
        super(viewModel);
        this.navigationBean = navigationBean;
        iconUrl.set(navigationBean.getImage());
        goodTypetitle.set(navigationBean.getNavigation_name());
    }

    public ObservableField<String> iconUrl = new ObservableField<>();
    public ObservableField<String> goodTypetitle = new ObservableField<>();
    public BindingCommand onLookInformation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), BaseWebViewActivity.class);
            intent.putExtra("webViewUrl", String.format("%s&comefrom=app", navigationBean.getNavigation_data()));
            intent.putExtra("isHideToolBar", "true");
            ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
        }
    });
}
