package chat.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.jiananshop.a.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mayn on 2018/12/7.
 */
public class TimeUtil {
    /**
     * 仿QQ，微信聊天时间格式化
     *
     * @param time 时间戳
     * @return
     */
    public static String QQFormatTime(long time,Context mcontext) {
        Date date = new Date();
        date.setTime(time);
        if (isSameYear(date)) { //同一年 显示MM-dd HH:mm
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
            if (isSameDay(date)) { //同一天 显示HH:mm
                int minute = minutesAgo(time);
                if (minute < 60) {//1小时之内 显示n分钟前
                    if (minute <= 1) {//一分钟之内，显示刚刚
                        return null;
                    } else {
                        return minute + mcontext.getString(R.string.goodsdatails_reminder49);
                    }
                } else {
                    return simpleDateFormat.format(date);
                }
            } else {
                if (isYesterday(date)) {//昨天，显示昨天+HH:mm
                    return mcontext.getString(R.string.goodsdatails_reminder50) + simpleDateFormat.format(date);
                } else if (isSameWeek(date)) {//本周,显示周几+HH:mm
                    String weekday = null;
                    if (date.getDay() == 1) {
                        weekday = mcontext.getString(R.string.goodsdatails_reminder51);
                    }
                    if (date.getDay() == 2) {
                        weekday = mcontext.getString(R.string.goodsdatails_reminder52);
                    }
                    if (date.getDay() == 3) {
                        weekday = mcontext.getString(R.string.goodsdatails_reminder53);
                    }
                    if (date.getDay() == 4) {
                        weekday = mcontext.getString(R.string.goodsdatails_reminder54);
                    }
                    if (date.getDay() == 5) {
                        weekday = mcontext.getString(R.string.goodsdatails_reminder55);
                    }
                    if (date.getDay() == 6) {
                        weekday = mcontext.getString(R.string.goodsdatails_reminder56);
                    }
                    if (date.getDay() == 0) {
                        weekday = mcontext.getString(R.string.goodsdatails_reminder57);
                    }
                    return weekday + " " + simpleDateFormat.format(date);
                } else {//同一年 显示MM-dd HH:mm
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
                    return sdf.format(date);
                }
            }
        } else {//不是同一年 显示完整日期yyyy-MM-dd HH:mm
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            return sdf.format(date);
        }
    }

    /**
     * 几分钟前
     *
     * @param time
     * @return
     */
    public static int minutesAgo(long time) {
        return (int) ((System.currentTimeMillis() - time) / (60000));
    }


    /**
     * 与当前时间是否在同一周
     * 先判断是否在同一年，然后根据Calendar.DAY_OF_YEAR判断所得的周数是否一致
     *
     * @param date
     * @return
     */
    public static boolean isSameWeek(Date date) {
        if (isSameYear(date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int a = calendar.get(Calendar.DAY_OF_YEAR);

            Date now = new Date();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(now);
            int b = calendar1.get(Calendar.DAY_OF_WEEK);
            return a == b;
        } else {
            return false;
        }
    }

    /**
     * 是否是当前时间的昨天
     * 获取指定时间的后一天的日期，判断与当前日期是否是同一天
     *
     * @param date
     * @return
     */
    public static boolean isYesterday(Date date) {
        Date yesterday = getNextDay(date, 1);
        return isSameDay(yesterday);
    }

    /**
     * 判断与当前日期是否是同一天
     *
     * @param date
     * @return
     */
    public static boolean isSameDay(Date date) {
        return isEquals(date, "yyyy-MM-dd");
    }

    /**
     * 判断与当前日期是否是同一月
     *
     * @param date
     * @return
     */
    public static boolean isSameMonth(Date date) {
        return isEquals(date, "yyyy-MM");
    }

    /**
     * 判断与当前日期是否是同一年
     *
     * @param date
     * @return
     */
    public static boolean isSameYear(Date date) {
        return isEquals(date, "yyyy");
    }


    /**
     * 格式化Date，判断是否相等
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    private static boolean isEquals(Date date, String format) {
        //当前时间
        Date now = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sf = new SimpleDateFormat(format);
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 获取某个date第n天的日期
     * n<0 表示前n天
     * n=0 表示当天
     * n>1 表示后n天
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getNextDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        date = calendar.getTime();
        return date;
    }
}
