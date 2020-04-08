package com.aite.a.activity.li.fragment.settingFragment;

import android.content.Intent;

import androidx.databinding.ObservableField;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.aite.a.activity.IntegralShopActivity;
import com.aite.a.activity.li.activity.BaseWebViewActivity;
import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.base.Mark;
import com.jiananshop.a.R;
import com.valy.baselibrary.bean.BaseConstant;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class SettingPlatformApplicationItemViewModel extends ItemViewModel<SettingFragmentViewHolder> {
    private int mTitleId = 0;

    public SettingPlatformApplicationItemViewModel(@NonNull SettingFragmentViewHolder viewModel, int icon, int titleId) {
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
            if (mTitleId == R.string.Brand) {
                //
                Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), BaseWebViewActivity.class);
                intent.putExtra("webViewUrl",
                        "https://daluxmall.com/wap/index.php?act=brand&key=" + Mark.State.UserKey + "&comefrom=app" + "&" + BaseConstant.CURRENTLANGUAGE);
                intent.putExtra("isHideToolBar", "true");
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);

            } else if (mTitleId == R.string.integrals) {//积分
                Intent intent13 = new Intent(ActivityManager.getInstance().getCurrentActivity(), IntegralShopActivity.class);
                intent13.putExtra("person_in", "1");
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent13);
              /*  Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), BaseWebViewActivity.class);
                intent.putExtra("webViewUrl",
                        "https://daluxmall.com/wap/index.php?act=pointshop" + "&key=" + Mark.State
                                .UserKey + "&lang_type=zh_cn&comefrom=app");
                intent.putExtra("isHideToolBar", "true");
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);*/
            } else if (mTitleId == R.string.panic_buying) {//抢购
                Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), BaseWebViewActivity.class);
                intent.putExtra("webViewUrl",
                        "https://daluxmall.com/wap/?act=groupbuy&key=" + Mark.State.UserKey + "&comefrom=app" + "&" + BaseConstant.CURRENTLANGUAGE);
                intent.putExtra("isHideToolBar", "true");
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            } else if (mTitleId == R.string.goodsdatails_reminder12) {//秒杀
                Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), BaseWebViewActivity.class);
                intent.putExtra("webViewUrl",
                        "https://daluxmall.com/wap/index.php?act=miaosha&key=" + Mark.State.UserKey + "&comefrom=app" + "&lang_type=" + BaseConstant.CURRENTLANGUAGE);
                intent.putExtra("isHideToolBar", "true");
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            }
        }
    });
}
