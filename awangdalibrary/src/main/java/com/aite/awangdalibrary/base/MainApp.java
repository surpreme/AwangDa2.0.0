package com.aite.awangdalibrary.base;

import android.content.Context;

public class MainApp {
    private static Context mContext;

    /**
     * 子模块和主模块需要共享全局上下文，故需要在app module初始化时传入
     */
    public static void init(Context appContext) {
        if (mContext == null) {
            mContext = appContext.getApplicationContext();

        }

    }


    public static Context getContext() {
        return mContext;
    }

}
