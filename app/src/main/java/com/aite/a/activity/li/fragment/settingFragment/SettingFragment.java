package com.aite.a.activity.li.fragment.settingFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.activity.QrCodeActivity;
import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.data.DataConstant;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.base.Mark;
import com.jiananshop.a.BR;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.FragmentSettingMainBinding;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.valy.baselibrary.adpter.BaseItemDecoration;

import level3.util.WheelUtils;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.bus.Messenger;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class SettingFragment extends BaseFragment<FragmentSettingMainBinding, SettingFragmentViewHolder> {
    private Context context;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_setting_main;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void initData() {
        if (Mark.State.UserKey == null) {
            PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                    getActivity(),
                    null,
                    "您还没有登录 请登录！",
                    null, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PopupWindowUtil.getmInstance().dismissPopWindow();
                            Intent intent = new Intent(getActivity(), LogInActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
            );
            return;
        }
        viewModel.getGuessLikeDatas(Mark.State.UserKey);
        viewModel.getSettingDatas(Mark.State.UserKey);
        binding.qrIv.setOnClickListener(v -> {
            applycamerapermission();
        });
        binding.mineMemberBenefitsRecyclerView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 5), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 10), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, android.R.color.darker_gray), context,
                        4f, null));
        binding.mineOrderRecyclerView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 5), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 10), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, android.R.color.darker_gray), context,
                        4f, null));
        binding.mineSellCenterRecyclerView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 5), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 10), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, android.R.color.darker_gray), context,
                        4f, null));
        binding.minePlatformApplicationRecyclerView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 5), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 10), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, android.R.color.darker_gray), context,
                        4f, null));
        binding.minePlatformServiceRecyclerView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 5), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 10), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, android.R.color.darker_gray), context,
                        4f, null));
        Messenger.getDefault().register(
                this,
                DataConstant.TOKEN_GET_SETTING_DATA_SUCCESS,
                SettingDataBean.class,
                settingDataBean -> {
                    viewModel.userName.set(settingDataBean.getMember_info().getNickname());
                    viewModel.userIconBackGroundUrl.set(settingDataBean.getMember_info().getPersonal_bg());
                    viewModel.userIconUrl.set(settingDataBean.getMember_info().getAvator());
                    //余额
                    binding.tvBalance.setText(settingDataBean.getMember_info().getPredepoit());
                    //冻结
                    binding.tvFreeze.setText(settingDataBean.getMember_info().getFreeze_predeposit());
                    //积分
                    binding.tvIntegral.setText(settingDataBean.getMember_info().getPoint());
                    //代金券
                    binding.tvVoucher.setText(String.format("%d张", settingDataBean.getMember_info().getVouchercount()));

                });
    }

    @SuppressLint("CheckResult")
    protected void applycamerapermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.setLogging(true);
            rxPermissions
                    .requestEach(Manifest.permission.CAMERA)
                    .subscribe(permission -> { // will emit 2 Permission objects
                        if (permission.granted) {
                            // `permission.name` is granted !
                            LogUtils.d("CAMERA权限同意");
                            applyperssionbody();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("CAMERA权限被拒绝");

                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("CAMERA权限被拒绝");
                        }
                    });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Mark.State.UserKey == null) {
            PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                    getActivity(),
                    null,
                    "您还没有登录 请登录！",
                    null, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(LogInActivity.class);

                        }
                    }
            );
            return;
        }
        viewModel.getSettingDatas(Mark.State.UserKey);
        viewModel.onPeopleInformation(BaseConstant.CURRENTLANGUAGE, Mark.State.UserKey);
    }

    private void applyperssionbody() {
        startActivity(QrCodeActivity.class);
    }
}
