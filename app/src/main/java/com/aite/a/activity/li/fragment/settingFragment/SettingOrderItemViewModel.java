package com.aite.a.activity.li.fragment.settingFragment;

import android.content.Intent;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import android.view.View;

import com.aite.a.activity.BuyerOrderFgActivity;
import com.aite.a.activity.RefundActivity;
import com.aite.a.activity.WebActivity;
import com.aite.a.activity.li.allorder.AllOrderKotlinActivity;
import com.aite.a.activity.li.data.DataConstant;
import com.aite.a.activity.li.util.ActivityManager;
import com.jiananshop.a.R;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class SettingOrderItemViewModel extends ItemViewModel<SettingFragmentViewHolder> {
    private int mTitleId = 0;

    public SettingOrderItemViewModel(@NonNull SettingFragmentViewHolder viewModel, int iconId, int titleId, String num) {
        super(viewModel);
        iconDrawable.set(ActivityManager.getInstance().getCurrentActivity().getResources().getDrawable(iconId));
        iconTitle.set(ActivityManager.getInstance().getCurrentActivity().getString(titleId));
        mTitleId = titleId;
        if (num != null) {
            if (!num.equals("") && !num.equals("null") && !num.equals("0")) {
                msgNum.set(num);
                msgNumVisable.set(View.VISIBLE);
            } else {
                msgNumVisable.set(View.GONE);
            }
        } else {
            msgNumVisable.set(View.GONE);

        }
    }

    public ObservableField<Drawable> iconDrawable = new ObservableField();
    public ObservableField<String> iconTitle = new ObservableField();
    public ObservableInt msgNumVisable = new ObservableInt();
    public ObservableField<String> msgNum = new ObservableField();
    public BindingCommand onLookInformation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
//            Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), BuyerOrderFgActivity.class);
            Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), AllOrderKotlinActivity.class);
            if (mTitleId == (R.string.distribution_center46)) {//待支付
                intent.putExtra("viewPager", 1);
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            } else if (mTitleId == R.string.storehome39) {//待发货
                intent.putExtra("viewPager", 2);
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            } else if (mTitleId == R.string.delivered) {//待收货
                intent.putExtra("viewPager", 3);
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            } else if (mTitleId == R.string.member_centre6) {//待评价
                intent.putExtra("viewPager", 4);
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            } else if (mTitleId == R.string.member_centre7) {//退换/售后
                Intent intent29 = new Intent(ActivityManager.getInstance().getCurrentActivity(), RefundActivity.class);
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent29);
            } else if (mTitleId == R.string.my_shopping_cart) {//购物车
                Messenger.getDefault().send("JUMP_TO_SHOPCARD", DataConstant.TOKEN_JUMPMAINFRAGMENT_BAR);
            } else if (mTitleId == R.string.buyer_orders) {//全部订单
                ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
            } else if (mTitleId == R.string.virtualorders) {//虚拟订单
                Intent intentvr = new Intent(ActivityManager.getInstance().getCurrentActivity(), WebActivity.class);
                intentvr.putExtra("url", "http://aitecc.com/wap/index.php?act=member_vr_order");
                ActivityManager.getInstance().getCurrentActivity().startActivity(intentvr);
            }

        }
    });
}
