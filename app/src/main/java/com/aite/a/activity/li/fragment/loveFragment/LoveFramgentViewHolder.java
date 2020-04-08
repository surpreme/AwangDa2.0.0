package com.aite.a.activity.li.fragment.loveFragment;

import android.app.Application;
import androidx.annotation.NonNull;
import com.aite.a.activity.li.fragment.houseFragment.MvvMViewAdapter;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class LoveFramgentViewHolder extends BaseViewModel<MvvMViewAdapter> {
    public LoveFramgentViewHolder(@NonNull Application application) {
        super(application);
    }

    public ArrayList<String> tabList = new ArrayList<>();
    public BindingCommand onGoodsClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
        }
    });
    public BindingCommand onShopsClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
        }
    });
}
