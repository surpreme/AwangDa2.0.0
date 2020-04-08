package com.aite.a.utils;

import android.content.Context;

/**
 * @Auther: valy
 * @datetime: 2020-02-27
 * @desc:
 */
public class SystemUtil {
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
