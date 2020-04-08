package com.aite.a.activity.li.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.DisplayMetrics;
import java.util.Locale;

/**
 * @Auther: valy
 * @datetime: 2020-01-06
 * @desc:
 */
public class ChangeLauguageUtils {
    /**
     * 修改APP语言设置
     *
     * @param locale 如Locale.CHINESE、Locale.ENGLISH等
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void changeAppLanguage(Activity activity, Locale locale) {
//        Resources res = context.getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        //conf.locale = locale;
//        conf.setLocale(locale);
//        res.updateConfiguration(conf, dm);

        // 获得res资源对象
        Resources resources = activity.getResources();
// 获得屏幕参数：主要是分辨率，像素等。
        DisplayMetrics metrics = resources.getDisplayMetrics();
// 获得配置对象
        Configuration config = resources.getConfiguration();
//区别17版本（其实在17以上版本通过 config.locale设置也是有效的，不知道为什么还要区别）
//在这里设置需要转换成的语言，也就是选择用哪个values目录下的strings.xml文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);//设置简体中文
            //config.setLocale(Locale.ENGLISH);//设置英文
        } else {
            config.locale = locale;//设置简体中文
            //config.locale = Locale.ENGLISH;//设置英文
        }
        resources.updateConfiguration(config, metrics);
    }
    /**
     * 获取当前设置的语言
     * @return 当前的语言，可将结果与Locale.ENGLISH.getLanguage()比较进行判断
     *     //判断当前的语言是否是英文
     *     Locale.ENGLISH.getLanguage().equals(getCurrLanguage(context));
     *
     *
     *     String country = locale.getCountry(); //可以借助该方法进行更精细的设置或判断
     */
    public static String getCurrLanguage(Context context) {
        Locale locale = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale.getLanguage();
    }


}
