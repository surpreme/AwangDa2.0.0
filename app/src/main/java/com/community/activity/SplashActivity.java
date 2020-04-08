package com.community.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;

import com.aite.a.APPSingleton;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.activity.mainer.MainerActivity;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.a.activity.li.util.TextEmptyUtils;
import com.aite.a.base.BaseActivity;
import com.aite.a.base.Mark;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;

import me.goldze.mvvmhabit.utils.ToastUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setTransparent(this);
        setContentView(R.layout.activity_splash);
        SharePreferencesHelper sharePreferencesUtils = new SharePreferencesHelper(APPSingleton.getContext(), "USER_INFO");
        String diviceID = Settings.System.getString(APPSingleton.getContext().getContentResolver(), Settings.System.ANDROID_ID);
        if (sharePreferencesUtils.contain("USERNAME_PHONE") && sharePreferencesUtils.contain("USER_PASSWORD_PHONE")
                || sharePreferencesUtils.contain("USERNAME_ACOUNT") && sharePreferencesUtils.contain("USER_PASSWORD_ACOUNT")) {
            BaseConstant.isAutomaticLogIn = true;
            if (sharePreferencesUtils.getSharePreference("USER_LOGIN_AWAY", "").equals("account")) {
                logIn(
                        String.valueOf(sharePreferencesUtils.getSharePreference("USERNAME_ACOUNT", "aite")),
                        String.valueOf(sharePreferencesUtils.getSharePreference("USER_PASSWORD_ACOUNT", "aite")),
                        "isAccount",
                        "android",
                        "zh_cn",
                        "1",
                        TextEmptyUtils.getText(diviceID));
            } else {
                logIn2(
                        String.valueOf(sharePreferencesUtils.getSharePreference("USERNAME_PHONE", "aite")),
                        String.valueOf(sharePreferencesUtils.getSharePreference("USER_PASSWORD_PHONE", "aite")),
                        String.valueOf(sharePreferencesUtils.getSharePreference("USERNAME_PHONE_CODE", "0086")),
                        "isMobile",
                        "android",
                        "zh_cn",
                        "1",
                        TextEmptyUtils.getText(diviceID));
            }

        } else {
            startActivity(new Intent(this, LogInActivity.class));
            finish();
        }

    }


    @SuppressLint("CheckResult")
    public void logIn(String username, String password, String loginType, String client, String lang_type, String login_role, String device_id) {
        BaseConstant.USERNAME = username;
        BaseConstant.USERPASSWORD = password;
        BaseConstant.LOGINAWAY = "isAccount";
        BaseConstant.DEVICEID = !TextUtils.isEmpty(device_id) ? device_id : "123456";

        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostLogInAccount(username, password, loginType, client, lang_type, login_role, device_id)
                .compose(RxScheduler.Flo_io_main())
                .filter(logInBeanBaseData -> {
                    if (!logInBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(logInBeanBaseData.getDatas().getError());
                        LogUtils.e(logInBeanBaseData.getDatas().getError());
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(logInBean -> {
                    if (logInBean.getError() != null) {
                        ToastUtils.showShort(logInBean.getError());
                        LogUtils.e(logInBean.getError());
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(logInBean -> {
            Mark.State.UserKey = logInBean.getKey();
            Mark.State.UserName = logInBean.getUsername();
            Mark.State.Member_id = logInBean.getConfig().getMember_id();
            startActivity(new Intent(this, MainerActivity.class));
            finish();

        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
        });

    }

    @SuppressLint("CheckResult")
    public void logIn2(String username, String password, String code, String loginType, String client, String lang_type, String login_role, String device_id) {
        BaseConstant.AREACODE = code;
        BaseConstant.LOGINAWAY = "isMobile";
        BaseConstant.USERNAME = username;
        BaseConstant.USERPASSWORD = password;
        BaseConstant.DEVICEID = !TextUtils.isEmpty(device_id) ? device_id : "123456";
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostLogInAccount2(username, password, code, loginType, client, lang_type, login_role, device_id)
                .compose(RxScheduler.Flo_io_main())
                .filter(logInBeanBaseData -> {
                    if (!logInBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(logInBeanBaseData.getDatas().getError());
                        LogUtils.e(logInBeanBaseData.getDatas().getError());
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(logInBean -> {
                    if (logInBean.getError() != null) {
                        ToastUtils.showShort(logInBean.getError());
                        LogUtils.e(logInBean.getError());
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(logInBean -> {
            Mark.State.UserKey = logInBean.getKey();
            Mark.State.UserName = logInBean.getUsername();
            Mark.State.Member_id = logInBean.getConfig().getMember_id();
            startActivity(new Intent(this, MainerActivity.class));
            finish();
        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
        });

    }


    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void ReGetData() {

    }
}
