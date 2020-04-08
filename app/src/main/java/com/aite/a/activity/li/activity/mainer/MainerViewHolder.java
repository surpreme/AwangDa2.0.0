package com.aite.a.activity.li.activity.mainer;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.TextUtils;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.annotation.NonNull;

import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.base.Mark;
import com.valy.baselibrary.retrofit.RetrofitClients;
import com.valy.baselibrary.retrofit.RxScheduler;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Auther: lzy
 * @datetime: 2020-01-09
 * @desc:
 */
public class MainerViewHolder extends BaseViewModel {
    public MainerViewHolder(@NonNull Application application) {
        super(application);
        title.set("首页");


    }

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableInt isShowBarVisible = new ObservableInt();
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
                        LogUtils.e(logInBeanBaseData.getDatas().getError());
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(logInBean -> {
                    if (logInBean.getError() != null) {
                        LogUtils.e(logInBean.getError());
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(logInBean -> {
            Mark.State.UserKey = logInBean.getKey();
            Mark.State.UserName = logInBean.getUsername();
            Mark.State.Member_id = logInBean.getConfig().getMember_id();


        }, throwable -> {
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
                        LogUtils.e(logInBeanBaseData.getDatas().getError());
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(logInBean -> {
                    if (logInBean.getError() != null) {
                        LogUtils.e(logInBean.getError());
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(logInBean -> {
            Mark.State.UserKey = logInBean.getKey();
            Mark.State.UserName = logInBean.getUsername();
            Mark.State.Member_id = logInBean.getConfig().getMember_id();
        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
        });

    }


}
