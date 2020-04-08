package com.aite.a.activity.li.fragment.settingFragment;

import android.content.Intent;
import androidx.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.aite.a.activity.MyCalendarActivity;
import com.aite.a.activity.li.util.ActivityManager;
import com.jiananshop.a.R;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class SettingMemberBenfitsItemViewModel extends ItemViewModel<SettingFragmentViewHolder> {
    private int mTitleId = 0;

    public SettingMemberBenfitsItemViewModel(@NonNull SettingFragmentViewHolder viewModel, int icon, int titleId) {
        super(viewModel);
        iconDrawable.set(ActivityManager.getInstance().getCurrentActivity().getResources().getDrawable(icon));
        iconTitle.set(ActivityManager.getInstance().getCurrentActivity().getString(titleId));
        mTitleId = titleId;
    }

    public ObservableField<Drawable> iconDrawable = new ObservableField();
    public ObservableField<String> iconTitle = new ObservableField();
    public BindingCommand onLookInformation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (mTitleId == R.string.pointsStep) {
                Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), MyCalendarActivity.class);
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            }


        }
    });
}
