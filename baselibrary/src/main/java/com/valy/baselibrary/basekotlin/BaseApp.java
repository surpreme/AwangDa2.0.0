package com.valy.baselibrary.basekotlin;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * @Auther: valy
 * @datetime: 2020/3/11
 * @desc:
 */
public class BaseApp extends BaseApplication {
    private static Application context;
    private static String key="";

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        BaseApp.key = key;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(context); // 尽可能早，推荐在Application中初始化
    }

    public static Context getContext() {
        return context;
    }
}
