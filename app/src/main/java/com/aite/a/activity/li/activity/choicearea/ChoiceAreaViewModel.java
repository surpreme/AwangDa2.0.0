package com.aite.a.activity.li.activity.choicearea;

import android.app.Application;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.annotation.NonNull;
import com.jiananshop.a.R;

import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * @Auther: valy
 * @datetime: 2020-02-19
 * @desc:
 */
public class ChoiceAreaViewModel extends BaseViewModel {
    public ChoiceAreaViewModel(@NonNull Application application) {
        super(application);
        title.set("选择地区");
    }

    public ObservableField<String> title = new ObservableField<>("");

    public BindingCommand onBackClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onBackPressed();
        }
    });

    public ObservableList<ChoiceAreaItemViewModel> choiceAreaItemList = new ObservableArrayList<>();
    public ItemBinding<ChoiceAreaItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<ChoiceAreaItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, ChoiceAreaItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_settingfragment_order_icon);
        }
    });
}
