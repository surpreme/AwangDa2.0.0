package com.aite.a.activity.li.activity.choicelanguage;

import android.app.Activity;
import android.app.Application;

import androidx.databinding.ObservableBoolean;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;


import com.aite.a.APPSingleton;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.activity.mainer.MainerActivity;
import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.activity.li.util.ChangeLauguageUtils;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.aite.a.base.Mark;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @author:liziyang
 * @Date: 2020/01/07
 * @description:
 */
public class ChoiceLanguageViewModel extends BaseViewModel {
    public SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(ActivityManager.getInstance().getCurrentActivity(), "APP_LANGUAGE");

    public ChoiceLanguageViewModel(@NonNull Application application) {
        super(application);
        if (sharePreferencesHelper.contain("NOW_LANGUAGE")) {
            if (String.valueOf(sharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("CHINESE")) {
                chineseChecked.set(true);
                englishChecked.set(false);
                ThailandChecked.set(false);
            } else if (String.valueOf(sharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("ENGLISH")) {
                chineseChecked.set(false);
                englishChecked.set(true);
                ThailandChecked.set(false);
            } else if (String.valueOf(sharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("THAILAND")) {
                chineseChecked.set(false);
                englishChecked.set(false);
                ThailandChecked.set(true);
            }
        } else {
            if (ChangeLauguageUtils.getCurrLanguage(APPSingleton.getContext()).equals(Locale.ENGLISH.getLanguage())) {
                chineseChecked.set(false);
                ThailandChecked.set(false);
                englishChecked.set(true);
            } else if (ChangeLauguageUtils.getCurrLanguage(APPSingleton.getContext()).equals(Locale.CHINESE.getLanguage())) {
                chineseChecked.set(true);
                ThailandChecked.set(false);
                englishChecked.set(false);
            } else {
                chineseChecked.set(false);
                ThailandChecked.set(true);
                englishChecked.set(false);
            }

        }
//        chineseTv.set(App.getContext().getString(R.string.Chinese));
//        englishTv.set(App.getContext().getString(R.string.English));
    }

    public ObservableBoolean chineseChecked = new ObservableBoolean();
    public ObservableBoolean englishChecked = new ObservableBoolean();
    public ObservableBoolean ThailandChecked = new ObservableBoolean();

//    public ObservableField<String> chineseTv = new ObservableField<>();
//    public ObservableField<String> englishTv = new ObservableField<>();

    public BindingCommand chineseCardViewOnClick = new BindingCommand<>(() -> {
        chineseChecked.set(true);
        englishChecked.set(false);
        //设置app语言环境为中文
       /* ChangeLauguageUtils.changeAppLanguage(ActivityManager.getInstance().getCurrentActivity(), Locale.CHINESE);
        if (sharePreferencesHelper.contain("NOW_LANGUAGE")) {
            sharePreferencesHelper.remove("NOW_LANGUAGE");
            sharePreferencesHelper.put("NOW_LANGUAGE", "CHINESE");
        } else {
            sharePreferencesHelper.put("NOW_LANGUAGE", "CHINESE");
        }
        choiceLanguage(BaseConstant.CURRENTLANGUAGE);
        BaseConstant.CURRENTLANGUAGE = "zh_cn";
        ActivityManager.clearActivityTask(APPSingleton.getContext(), MainerActivity.class);*/


    });
    public BindingCommand englishCardViewOnClick = new BindingCommand<>(() -> {
        chineseChecked.set(false);
        englishChecked.set(true);
       /* ChangeLauguageUtils.changeAppLanguage(ActivityManager.getInstance().getCurrentActivity(), Locale.ENGLISH);

        if (sharePreferencesHelper.contain("NOW_LANGUAGE")) {
            sharePreferencesHelper.remove("NOW_LANGUAGE");
            sharePreferencesHelper.put("NOW_LANGUAGE", "ENGLISH");
        } else {
            sharePreferencesHelper.put("NOW_LANGUAGE", "ENGLISH");
        }
        choiceLanguage("en");
        BaseConstant.CURRENTLANGUAGE = "en";
        ActivityManager.clearActivityTask(APPSingleton.getContext(), MainerActivity.class);*/


    });
    public BindingCommand ThailandCardViewOnClick = new BindingCommand<>(() -> {
        chineseChecked.set(false);
        englishChecked.set(false);
        ThailandChecked.set(true);
       /* ChangeLauguageUtils.changeAppLanguage(ActivityManager.getInstance().getCurrentActivity(), new Locale("th", ""));
        if (sharePreferencesHelper.contain("NOW_LANGUAGE")) {
            sharePreferencesHelper.remove("NOW_LANGUAGE");
            sharePreferencesHelper.put("NOW_LANGUAGE", "THAILAND");
        } else {
            sharePreferencesHelper.put("NOW_LANGUAGE", "THAILAND");
        }
        choiceLanguage("th");
        BaseConstant.CURRENTLANGUAGE = "th";
        ActivityManager.clearActivityTask(APPSingleton.getContext(), MainerActivity.class);*/


    });
    public View.OnClickListener onBackOnClick = (v) -> {
        onBackPressed();
    };

    private String getCurSelectLanguage() {
        Log.e("Language", "English: " + englishChecked.get() + "     Chinese:    " + chineseChecked.get());
        if (englishChecked.get()) {
            if (sharePreferencesHelper.contain("NOW_LANGUAGE")) {
                sharePreferencesHelper.remove("NOW_LANGUAGE");
                sharePreferencesHelper.put("NOW_LANGUAGE", "ENGLISH");
            } else {
                sharePreferencesHelper.put("NOW_LANGUAGE", "ENGLISH");
            }
            return "en";
        } else if (chineseChecked.get()) {
            if (sharePreferencesHelper.contain("NOW_LANGUAGE")) {
                sharePreferencesHelper.remove("NOW_LANGUAGE");
                sharePreferencesHelper.put("NOW_LANGUAGE", "CHINESE");
            } else {
                sharePreferencesHelper.put("NOW_LANGUAGE", "CHINESE");
            }
            return "zh_cn";
        } else {
            return "en";
        }
    }

    public void saveLanguageChanged() {
        String languageType = getCurSelectLanguage();
        BaseConstant.CURRENTLANGUAGE = languageType;
//        ActivityManager.clearActivityTask(APPSingleton.getContext(), MainerActivity.class);
        HttpParams params = new HttpParams();
        params.put("member_id", Mark.member_id);
        params.put("member_role", BaseConstant.USERTYPE);
        params.put("type", 1);//1选择语言 0 返回语言
        params.put("language_type", BaseConstant.CURRENTLANGUAGE);
        params.put("lang_type", BaseConstant.CURRENTLANGUAGE);
        OkGo.<String>post("https://daluxmall.com/mobile/index.php?act=index&op=selectLanguage")
                .tag(getApplication())
                .params(params)
                .execute(new AbsCallback<String>() {
                    @Override
                    public String convertResponse(okhttp3.Response response) throws Throwable {
                        ActivityManager.getInstance().getCurrentActivity().runOnUiThread(() -> {
                            /*for (Activity activity : getAllActivitys()) {
//                                if (!activity.getClass().getSimpleName().equals(ChoiceLanguageActivity.class.getSimpleName()))
                                activity.recreate();
                            }*/
                            ActivityManager.clearActivityTask(APPSingleton.getContext(), MainerActivity.class);
                            onBackPressed();
                        });
                        return null;
                    }

                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

    public List<Activity> getAllActivitys() {
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

    public void choiceLanguage(String languageType) {
        HttpParams params = new HttpParams();
        params.put("member_id", Mark.member_id);
        params.put("member_role", BaseConstant.USERTYPE);
        params.put("type", 1);//1选择语言 0 返回语言
        if (languageType == null || languageType.equals("")) {
            params.put("language_type", "en");
            params.put("lang_type", "en");
        } else {
            params.put("language_type", languageType);
            params.put("lang_type", languageType);
        }
        OkGo.<String>post("https://daluxmall.com/mobile/index.php?act=index&op=selectLanguage")
                .tag(getApplication())
                .params(params)
                .execute(new AbsCallback<String>() {
                    @Override
                    public String convertResponse(okhttp3.Response response) throws Throwable {
                        ActivityManager.getInstance().getCurrentActivity().recreate();
                        return null;
                    }

                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }
}
