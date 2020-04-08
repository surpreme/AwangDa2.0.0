package com.aite.a.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.ImageView;

import com.aite.a.APPSingleton;
import com.aite.a.AppManager;
import com.aite.a.HomeTabActivity;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.WelcomeActivity;
import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.activity.mainer.MainerActivity;
import com.aite.a.activity.li.bean.StartImgBean;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.a.activity.li.util.TextEmptyUtils;
import com.aite.a.base.BaseActivity;
import com.aite.a.base.Mark;
import com.aite.a.parse.NetRun;
import com.aite.a.utils.BeanConvertor;
import com.community.activity.TabActivity;
import com.jiananshop.a.R;
import com.bumptech.glide.Glide;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.valy.baselibrary.basekotlin.BaseApp;
import com.valy.baselibrary.bean.BaseConstant;
import com.valy.baselibrary.retrofit.RetrofitClients;
import com.valy.baselibrary.retrofit.RxScheduler;

import me.goldze.mvvmhabit.utils.ToastUtils;
//import com.tencent.android.tpush.XGPushManager;
//import com.tencent.android.tpush.service.XGPushService;

/**
 * 开屏页
 *
 * @author Administrator
 */
public class SplashActivity extends BaseActivity {
    private Intent intent1, intent2;
    private SharedPreferences sp;
    private ImageView open_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppManager.getInstance().addActivity(this);
        Context context = getApplicationContext();
//        XGPushManager.registerPush(context);
//         2.36（不包括）之前的版本需要调用以下2行代码
//        Intent service = new Intent(context, XGPushService.class);
//        context.startService(service);
        findViewById();
        StatusBarUtils.setTransparent(this);
        WaitSplash splash = new WaitSplash();
        splash.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private class WaitSplash extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            //跳转向导页面
            intent1 = new Intent(SplashActivity.this, WelcomeActivity.class);
            //跳转主界面
            intent2 = new Intent(SplashActivity.this, MainerActivity.class);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(1500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            sp = getSharedPreferences("config", MODE_PRIVATE);

            boolean update = sp.getBoolean("guide", true);
            if (update == true) {
                startActivity(intent1);
                overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
                finish();
            } else {
                startActivity(intent2);
                overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
            }
            SplashActivity.this.finish();
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case login_id:
//				//TODO  自动登录后处理
//				break;
//			}
        }

        ;
    };
    private String client = "android";
    private NetRun netRun = new NetRun(SplashActivity.this, handler);

    protected void initData() {
//		SharedPreferences spf = getSharedPreferences("LoginActivity", MODE_PRIVATE);
//		boolean b = spf.getBoolean("isautomatic", false);
//		if (b) {
//			String username = spf.getString("username", "");
//			String password = spf.getString("password", "");
//			netRun.login(username, password, client);
//		}
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

        }
        NetRun netRun = new NetRun(this);
        netRun.OnWelcome(new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                StartImgBean startImgBean = BeanConvertor.convertBean(responseInfo.result, StartImgBean.class);
//                LogUtils.d(responseInfo.result);
                if (!SplashActivity.this.isDestroyed())
                    Glide.with(SplashActivity.this).load(startImgBean.getDatas().getUpload_images().get(0).getImg_path()).into(open_img);

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });



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
            BaseApp.setKey(logInBean.getKey());

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
            BaseApp.setKey(logInBean.getKey());
        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void findViewById() {
        open_img = findViewById(R.id.open_img);


    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void ReGetData() {
    }
}
