package com.aite.a.activity.li.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: valy
 * @datetime: 2019-12-21
 * @desc:
 */
public class ActivityManager {
    private static ActivityManager sInstance = new ActivityManager();

    private WeakReference<Activity> sCurrentActivityWeakRef;


    private ActivityManager() {

    }

    public static ActivityManager getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

    public static void clearActivityTask(Context context, Class cls) {

        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
    }

    public static List<Activity> getAllActivitys() {
        List<Activity> list = new ArrayList<>();
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            //获取主线程对象
            Object activityThreadObject = currentActivityThread.invoke(null);
            Field mActivitiesField = activityThread.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Map<Object, Object> mActivities = (Map<Object, Object>) mActivitiesField.get(activityThreadObject);
            for (Map.Entry<Object, Object> entry : mActivities.entrySet()) {
                Object value = entry.getValue();
                Class<?> activityClientRecordClass = value.getClass();
                Field activityField = activityClientRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Object o = activityField.get(value);
                list.add((Activity) o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
