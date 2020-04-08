package com.aite.a.activity.li.activity.login;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import android.content.Intent;
import android.provider.Settings;

import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import com.aite.a.APPSingleton;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.finduserpassword.FindPasswordActivity;
import com.aite.a.activity.li.activity.registered.RegisteredActivity;
import com.aite.a.activity.li.activity.mainer.MainerActivity;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.mvp.RetrofitClient2;
import com.aite.a.activity.li.mvp.RetrofitClient3;
import com.aite.a.activity.li.mvvm.BindingBaseViewModelAdapter;
import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.aite.a.activity.li.util.TextEmptyUtils;
import com.aite.a.base.Mark;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;

/**
 * @Auther: valy
 * @datetime: 2020-01-09
 * @desc:
 */
public class LogInViewHolder extends BaseViewModel<BindingBaseViewModelAdapter> {

    private SharePreferencesHelper sharePreferencesUtils = new SharePreferencesHelper(APPSingleton.getContext(), "USER_INFO");


    public LogInViewHolder(@NonNull Application application) {
        super(application);
        title.set("DaluxMall");
//        logInAwayTips.set(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginOther));
//        userName.set("wly789");
//        passWord.set("123456");
        onGetAreaCode(BaseConstant.CURRENTLANGUAGE);

    }

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> logInAwayTips = new ObservableField<>("");
    public ObservableField<String> countryStr = new ObservableField<>("");
    public ObservableField<String> countryCodeStr = new ObservableField<>("");
    public ObservableField<String> countryCodeTag = new ObservableField<>("");
    public ObservableField<String> countryIconUrl = new ObservableField<>("");
    public ObservableField<String> userPhoneEdit = new ObservableField<>("");
    public ObservableField<String> facebookuserName = new ObservableField<>("");
    public ObservableField<String> facebookId = new ObservableField<>("");
    public ObservableField<String> weChatUnionid = new ObservableField<>("");
    public ObservableField<String> weChatUserName = new ObservableField<>("");
    public ObservableField<String> weChatIconUrl = new ObservableField<>("");
    public ObservableField<String> facebookIconUrl = new ObservableField<>("");
    public ObservableField<Boolean> isRememberPassword = new ObservableField<>(false);
    public ObservableInt hideAcount = new ObservableInt();
    public ObservableInt hidePhone = new ObservableInt();
    public List<AreaCodeBean.ListBean> areaListBean = new ArrayList<>();

    //账号登录
    public ObservableField<String> userName = new ObservableField<>("");
    public ObservableField<String> passWord = new ObservableField<>("");
    //切换登录方式
    public BindingCommand switchLogInAwayOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginOther))) {
                logInAwayTips.set(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginMobile));
                hideAcount.set(View.VISIBLE);
                hidePhone.set(View.GONE);
//                initShowPasswords();
                initAcountRemember();
            } else {
                logInAwayTips.set(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginOther));
                hideAcount.set(View.GONE);
                hidePhone.set(View.VISIBLE);
                initPhoneRemember();

            }


        }
    });
    public BindingCommand registeredOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(RegisteredActivity.class);


        }
    });
    public BindingCommand findKeyOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FindPasswordActivity.class);


        }
    });
    //登录按钮的点击事件
    public BindingCommand loginOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            LogUtils.d(userName.get() + passWord.get());
            initRememberPassword();
            String diviceID = Settings.System.getString(APPSingleton.getContext().getContentResolver(), Settings.System.ANDROID_ID);
            if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginOther))) {
                logIn2(
                        userPhoneEdit.get(),
                        passWord.get(),
                        countryCodeTag.get(),
                        "isMobile",
                        "android",
                        "zh_cn",
                        "1",
                        TextEmptyUtils.getText(diviceID)
                );
            } else {
                logIn(
                        userName.get(),
                        passWord.get(),
                        "isAccount",
                        "android",
                        "zh_cn",
                        "1",
                        TextEmptyUtils.getText(diviceID)
                );
            }


        }
    });

    private void initRememberPassword() {
        if (isRememberPassword.get()) {
            if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginOther))) {
                sharePreferencesUtils.put("USERNAME_PHONE", userPhoneEdit.get());
                sharePreferencesUtils.put("USERNAME_PHONE_CODE", countryCodeTag.get());
                sharePreferencesUtils.put("USER_PASSWORD_PHONE", passWord.get());
                sharePreferencesUtils.put("USER_LOGIN_AWAY", "phone");
            } else if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginMobile))) {
                sharePreferencesUtils.put("USERNAME_ACOUNT", userName.get());
                sharePreferencesUtils.put("USER_PASSWORD_ACOUNT", passWord.get());
                sharePreferencesUtils.put("USER_LOGIN_AWAY", "account");

            }


        } else {
            if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginOther))) {
                sharePreferencesUtils.put("USER_LOGIN_AWAY", "phone");
            } else if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginMobile))) {
                sharePreferencesUtils.put("USER_LOGIN_AWAY", "account");

            }

        }
    }

    public CompoundButton.OnCheckedChangeListener rememberPasswordListener = (buttonView, isChecked) -> {
        isRememberPassword.set(isChecked);

    };

    private void initShowPasswords() {
        if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginMobile))) {
            initAcountRemember();
        } else if (logInAwayTips.get().equals(ActivityManager.getInstance().getCurrentActivity().getString(R.string.LoginOther))) {
            initPhoneRemember();
        }

    }

    private void clearDatas() {
        if (!userName.get().equals("")) {
            userName.set("");
        }
        if (!passWord.get().equals("")) {
            passWord.set("");
        }
        if (!userPhoneEdit.get().equals("")) {
            userPhoneEdit.set("");
        }

    }

    private void initAcountRemember() {
        clearDatas();
        if (sharePreferencesUtils.contain("USERNAME_ACOUNT") && sharePreferencesUtils.contain("USER_PASSWORD_ACOUNT")) {
            userName.set(String.valueOf(sharePreferencesUtils.getSharePreference("USERNAME_ACOUNT", "aite")));
            passWord.set(String.valueOf(sharePreferencesUtils.getSharePreference("USER_PASSWORD_ACOUNT", "aite")));
            isRememberPassword.set(true);
        } else {
            isRememberPassword.set(false);
        }
    }

    private void initPhoneRemember() {
        clearDatas();
        if (sharePreferencesUtils.contain("USERNAME_PHONE") && sharePreferencesUtils.contain("USER_PASSWORD_PHONE")) {
            userPhoneEdit.set(String.valueOf(sharePreferencesUtils.getSharePreference("USERNAME_PHONE", "aite")));
            passWord.set(String.valueOf(sharePreferencesUtils.getSharePreference("USER_PASSWORD_PHONE", "aite")));
            isRememberPassword.set(true);
        } else {
            isRememberPassword.set(false);
        }
    }

    private void onLogInSuccess(LogInBean logInBean) {
        dismissDialog();
        if (logInBean.getKey() != null) {
            LogUtils.d(logInBean.getKey());
            LogUtils.d("登录成功");
            Mark.State.UserKey = logInBean.getKey();
            Mark.State.UserName = logInBean.getUsername();
            Mark.State.Member_id = logInBean.getConfig().getMember_id();
            Mark.State.isUnLogin = false;
            Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), MainerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
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
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(logInBean -> {
                    if (logInBean.getError() != null) {
                        ToastUtils.showShort(logInBean.getError());
                        LogUtils.e(logInBean.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(logInBean -> {
            onLogInSuccess(logInBean);
        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
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
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(logInBean -> {
                    if (logInBean.getError() != null) {
                        ToastUtils.showShort(logInBean.getError());
                        LogUtils.e(logInBean.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(logInBean -> {
            onLogInSuccess(logInBean);
        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });

    }

    /**
     * facebook
     *
     * @param type
     * @param client
     * @param lang_type
     * @param facebook_id
     * @param device_id
     */
    @SuppressLint("CheckResult")
    public void logIn3(String type, String client, String lang_type, String facebook_id, String device_id) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostFeceBookLogInAccount(type, client, lang_type, facebook_id, device_id)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            if (code.equals("200")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            }

                        }
                        if (error_code != null) {
                            String message = jsonObject.optString("message");
                            if (message != null) {
                                if (error_code.equals("10004") || message.equals("未关联账号")) {
                                    PopupWindowUtil.getmInstance().showbindingPopupWindow(
                                            ActivityManager.getInstance().getCurrentActivity(),
                                            new PopupWindowUtil.OnBindingUserChoiceBackInterface() {
                                                @Override
                                                public void onGetWay(String way) {
                                                    if (way.equals("BINDEROLDER")) {
                                                        ActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                PopupWindowUtil.getmInstance().showBindingEditMsgPop(ActivityManager.getInstance().getCurrentActivity(), new PopupWindowUtil.OnBindingEditMsgBackInterface() {
                                                                    @Override
                                                                    public void onGetAcount(String acount, String password) {
                                                                        if (acount != null && password != null) {
                                                                            faceBookBindingAccount(BaseConstant.CURRENTLANGUAGE, acount, password, "3", facebookId.get(), "android");
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });


                                                    } else if (way.equals("NEWSUSERS")) {
                                                        String name = facebookuserName.get() + System.currentTimeMillis();
                                                        faceBookAutomaticLogInAccount(BaseConstant.CURRENTLANGUAGE, name, facebookIconUrl.get(), "3", facebookId.get(), "android");
                                                    }

                                                }

                                            });


                                }

                            }
                        }
                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * wechat
     *
     * @param type
     * @param client
     * @param lang_type
     * @param unionid
     * @param device_id
     */
    @SuppressLint("CheckResult")
    public void logIn5(String type, String client, String lang_type, String unionid, String device_id) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostWeChatLogInAccount(type, client, lang_type, unionid, device_id)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            if (code.equals("200")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            }

                        }
                        if (error_code != null) {
                            String message = jsonObject.optString("message");
                            if (message != null) {
                                if (error_code.equals("10004") || message.equals("未关联账号")) {
                                    PopupWindowUtil.getmInstance().showbindingPopupWindow(
                                            ActivityManager.getInstance().getCurrentActivity(),
                                            new PopupWindowUtil.OnBindingUserChoiceBackInterface() {
                                                @Override
                                                public void onGetWay(String way) {
                                                    if (way.equals("BINDEROLDER")) {
                                                        ActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                PopupWindowUtil.getmInstance().showBindingEditMsgPop(ActivityManager.getInstance().getCurrentActivity(), new PopupWindowUtil.OnBindingEditMsgBackInterface() {
                                                                    @Override
                                                                    public void onGetAcount(String acount, String password) {
                                                                        if (acount != null && password != null) {
                                                                            weChatBindingAccount(BaseConstant.CURRENTLANGUAGE, acount, password, "1", weChatUnionid.get(), "android");
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });


                                                    } else if (way.equals("NEWSUSERS")) {
                                                        String name = weChatUserName.get() + System.currentTimeMillis();
                                                        weChatAutomaticLogInAccount(BaseConstant.CURRENTLANGUAGE, name, weChatIconUrl.get(), "1", weChatUnionid.get(), "android");
                                                    }

                                                }


                                            });


                                }

                            }
                        }
                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * ins
     *
     * @param type
     * @param client
     * @param lang_type
     * @param instagram_id
     * @param user_name
     * @param device_id
     */
    @SuppressLint("CheckResult")
    public void logIn4(String type, String client, String lang_type, String instagram_id, String user_name, String device_id) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostInstagramLogInAccount(type, client, lang_type, instagram_id, device_id)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            if (code.equals("200")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            }

                        }
                        if (error_code != null) {
                            String message = jsonObject.optString("message");
                            if (message != null) {
                                if (error_code.equals("10004") || message.equals("未关联账号")) {
                                    PopupWindowUtil.getmInstance().showbindingPopupWindow(
                                            ActivityManager.getInstance().getCurrentActivity(),
                                            new PopupWindowUtil.OnBindingUserChoiceBackInterface() {
                                                @Override
                                                public void onGetWay(String way) {
                                                    if (way.equals("BINDEROLDER")) {
                                                        ActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                PopupWindowUtil.getmInstance().showBindingEditMsgPop(ActivityManager.getInstance().getCurrentActivity(), new PopupWindowUtil.OnBindingEditMsgBackInterface() {
                                                                    @Override
                                                                    public void onGetAcount(String acount, String password) {
                                                                        if (acount != null && password != null) {
                                                                            instagramBindingAccount(BaseConstant.CURRENTLANGUAGE, acount, password, "4", instagram_id, "android");
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });


                                                    } else if (way.equals("NEWSUSERS")) {
                                                        String name = user_name + System.currentTimeMillis();
                                                        instagramAutomaticLogInAccount(BaseConstant.CURRENTLANGUAGE, name, user_name, "4", instagram_id, "android");
                                                    }

                                                }

                                            });


                                }

                            }
                        }
                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * * @param lang_type
     * * @param username
     * * @param avatar_url
     * * @param type
     * * @param unionid
     * * @param client
     * facebook自动注册
     */
    @SuppressLint("CheckResult")
    private void weChatAutomaticLogInAccount(String lang_type, String username, String avatar_url, String type, String unionid, String client) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostWeChatAutomaticLogInAccount(lang_type, username, avatar_url, type, unionid, client)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (error_code != null) {
                            if (error_code.equals("0")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            } else {
                                ToastUtils.showShort("自动注册失败");
                            }
                        } else ToastUtils.showShort("自动注册失败");

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * * @param lang_type
     * * @param username
     * * @param avatar_url
     * * @param type
     * * @param facebook_id
     * * @param client
     * facebook自动注册
     */
    @SuppressLint("CheckResult")
    private void faceBookAutomaticLogInAccount(String lang_type, String username, String avatar_url, String type, String facebook_id, String client) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostFeceBookAutomaticLogInAccount(lang_type, username, avatar_url, type, facebook_id, client)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (error_code != null) {
                            if (error_code.equals("0")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            } else {
                                ToastUtils.showShort("自动注册失败");
                            }
                        } else ToastUtils.showShort("自动注册失败");

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * * @param lang_type
     * * @param username
     * * @param avatar_url
     * * @param type
     * * @param instagram_id
     * * @param client
     * instagram自动注册
     */
    @SuppressLint("CheckResult")
    private void instagramAutomaticLogInAccount(String lang_type, String username, String avatar_url, String type, String instagram_id, String client) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostInstagramAutomaticLogInAccount(lang_type, username, avatar_url, type, instagram_id, client)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (error_code != null) {
                            if (error_code.equals("0")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            } else {
                                ToastUtils.showShort("自动注册失败");
                            }
                        } else ToastUtils.showShort("自动注册失败");

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * 通过ins后台获取token
     *
     * @return {"access_token": "IGQVJYMzIyMHFUbXNCLTE4bjZAIS29UVC1kcVR6Y3d5ZA2pRTmt5R21pM3I5eUJqajFGdFA0ZAE1yTU9LenBKSWxEcFpockp4MlJmYlk3ZA0JaTktYSExXT3NNSDU5bl9pNGtZAX3NLb0I4cm9JcjNLaFUzdlRENkpNNXVpZAS1J", "user_id": 17841430127968194}
     * @Field("client_id") String client_id,
     * @Field("client_secret") String client_secret,
     * @Field("grant_type") String grant_type,
     * @Field("redirect_uri") String redirect_uri,
     * @Field("code") String code
     */
    @SuppressLint("CheckResult")
    public void instagramConfiguration(String client_id, String client_secret, String grant_type, String redirect_uri, String code) {
        RetrofitClient2.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostInstagramConfiguration(client_id, client_secret, grant_type, redirect_uri, code)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String access_token = jsonObject.optString("access_token");
                        String user_id = jsonObject.optString("user_id");
                        if (access_token != null && user_id != null) {
                            ActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    instagramConfiguration2(user_id, access_token);

                                }
                            });

                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * * 获取用户登录id name
     * * @param userId
     * * @param accessToken
     * * @return
     */

    @SuppressLint("CheckResult")
    public void instagramConfiguration2(String userId, String accessToken) {
        RetrofitClient3.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetInstagramConfiguration(userId, accessToken)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String username = jsonObject.optString("username");
                        String id = jsonObject.optString("id");
                        if (username != null && id != null) {
                            logIn4(
                                    "4",
                                    "android",
                                    "zh_cn",
                                    id,
                                    username,
                                    TextEmptyUtils.getText(Settings.System.getString(APPSingleton.getContext().getContentResolver(), Settings.System.ANDROID_ID)));
                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * 绑定账号
     *
     * @param lang_type
     * @param username
     * @param password
     * @param type
     * @param facebook_id
     * @param client
     * @return
     */
    @SuppressLint("CheckResult")
    private void faceBookBindingAccount(String lang_type, String username, String password, String type, String facebook_id, String client) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostFeceBookBindingAccount(lang_type, username, password, type, facebook_id, client)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            if (code.equals("200")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            }
                        }
                        if (error_code != null) {
                            String msg = jsonObject.optString("message");
                            if (msg != null)
                                ToastUtils.showShort(msg);
                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    @SuppressLint("CheckResult")
    private void weChatBindingAccount(String lang_type, String username, String password, String type, String unionid, String client) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostWeChatBindingAccount(lang_type, username, password, type, unionid, client)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            if (code.equals("200")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            }
                        }
                        if (error_code != null) {
                            String msg = jsonObject.optString("message");
                            if (msg != null)
                                ToastUtils.showShort(msg);
                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * 绑定账号
     *
     * @param lang_type
     * @param username
     * @param password
     * @param type
     * @param instagram_id
     * @param client
     * @return
     */
    @SuppressLint("CheckResult")
    private void instagramBindingAccount(String lang_type, String username, String password, String type, String instagram_id, String client) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostInstagramBindingAccount(lang_type, username, password, type, instagram_id, client)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            if (code.equals("200")) {
                                JSONObject datas = jsonObject.optJSONObject("datas");
                                if (datas != null) {
                                    LogInBean logInBean = new Gson().fromJson(datas.toString(), LogInBean.class);
                                    onLogInSuccess(logInBean);
                                }
                            }
                        }
                        if (error_code != null) {
                            String msg = jsonObject.optString("message");
                            if (msg != null)
                                ToastUtils.showShort(msg);
                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    /**
     * @param lang_type
     * @param username
     * @param avatar_url
     * @param type
     * @param facebook_id
     * @param client
     */
    @SuppressLint("CheckResult")
    private void faceBookBindingLogInAccount(String lang_type, String username, String avatar_url, String type, String facebook_id, String client) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostFeceBookAutomaticLogInAccount(lang_type, username, avatar_url, type, facebook_id, client)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        LogUtils.e(responseBody.string());

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    @SuppressLint("CheckResult")
    public void onGetAreaCode(String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getAreaCode(lang_type)
                .compose(RxScheduler.Flo_io_main())
                .filter(areaCodeBeanBaseData -> {
                    if (!areaCodeBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(areaCodeBeanBaseData.getDatas().getError());
                        LogUtils.e(areaCodeBeanBaseData.getDatas().getError());
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(areaCodeBeanBaseData -> {
                    if (areaCodeBeanBaseData.getError() != null) {
                        ToastUtils.showShort(areaCodeBeanBaseData.getError());
                        LogUtils.e(areaCodeBeanBaseData.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(areaCodeBean -> {
            areaListBean.addAll(areaCodeBean.getList());
            if (sharePreferencesUtils.contain("USERNAME_PHONE_CODE")) {
                String code = String.valueOf(sharePreferencesUtils.getSharePreference("USERNAME_PHONE_CODE", "0086"));
                if (code != null) {
                    for (int i = 0; i < areaCodeBean.getList().size(); i++) {
                        if (areaCodeBean.getList().get(i).getArea_code().equals(code)) {
                            countryCodeStr.set(areaCodeBean.getList().get(i).getCode());
                            countryStr.set(areaCodeBean.getList().get(i).getArea_name());
                            countryIconUrl.set(areaCodeBean.getList().get(i).getIcon());
                            countryCodeTag.set(areaCodeBean.getList().get(i).getArea_code());
                        }
                    }
                } else {
                    countryCodeStr.set(areaCodeBean.getList().get(0).getCode());
                    countryStr.set(areaCodeBean.getList().get(0).getArea_name());
                    countryIconUrl.set(areaCodeBean.getList().get(0).getIcon());
                    countryCodeTag.set(areaCodeBean.getList().get(0).getArea_code());

                }
            } else {
                countryCodeStr.set(areaCodeBean.getList().get(0).getCode());
                countryStr.set(areaCodeBean.getList().get(0).getArea_name());
                countryIconUrl.set(areaCodeBean.getList().get(0).getIcon());
                countryCodeTag.set(areaCodeBean.getList().get(0).getArea_code());
            }


        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });


    }

}
