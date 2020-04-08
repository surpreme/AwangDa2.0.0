package com.aite.a.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.aite.a.APPSingleton;
import com.aite.a.AppManager;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.WelcomeActivity;
import com.aite.a.activity.li.activity.mainer.MainerActivity;
import com.aite.a.activity.li.bean.StartImgBean;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.a.activity.li.util.TextEmptyUtils;
import com.aite.a.base.Mark;
import com.aite.a.parse.NetRun;
import com.aite.a.utils.BeanConvertor;
import com.aite.mainlibrary.basekotlin.BaseActivity;
import com.bumptech.glide.Glide;
import com.jiananshop.a.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.valy.baselibrary.basekotlin.BaseApp;
import com.valy.baselibrary.bean.BaseConstant;
import com.valy.baselibrary.retrofit.RetrofitClients;
import com.valy.baselibrary.retrofit.RxScheduler;

import butterknife.BindView;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class SplashJNActivity extends BaseActivity {

    private Context context;

    @Override
    public int getLayoutId() {
        context = this;
        return R.layout.activity_splash;
    }

    @Override
    public void initViews() {
        StatusBarUtils.setTransparent(context);
    }

    @Override
    public void initDatas() {
        CountDownTimer mCountDownTimer = new CountDownTimer(2 * 1000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                SharedPreferences sharedPreferences = context.getSharedPreferences("share", MODE_PRIVATE);
                boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (isFirstRun) {
                    startActivity(WelcomeActivity.class);
                    editor.putBoolean("isFirstRun", false);
                    editor.apply();
                } else {
                    startActivity(MainerActivity.class);
                    finish();
                }
            }
        };
        mCountDownTimer.start();
    }


}
