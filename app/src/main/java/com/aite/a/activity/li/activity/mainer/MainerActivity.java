package com.aite.a.activity.li.activity.mainer;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

import com.aite.a.APPSingleton;
import com.aite.a.utils.TextUtil;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.data.DataConstant;
import com.aite.a.activity.li.fragment.houseFragment.HousesFragment;
import com.aite.a.activity.li.fragment.locationFragment.LocationFragment;
import com.aite.a.activity.li.fragment.settingFragment.SettingFragment;
import com.aite.a.activity.li.fragment.shoppingCartFragment.ShoppingCartFragment;
import com.aite.a.activity.li.mvvm.MVVMBaseActivity;
import com.aite.a.activity.li.util.ChangeLauguageUtils;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.a.activity.li.util.TextEmptyUtils;
import com.aite.a.base.Mark;
import com.aite.awangdalibrary.ui.activity.collectlist.CollectListKotlinFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.ActicityMainerBinding;
import com.valy.baselibrary.basekotlin.BaseApp;

import java.util.Locale;

import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.bus.Messenger;


/**
 * liziyang
 * 20/01/10
 * mvvm
 * 注释 防止重叠
 */
@Route(path = "/jn/MainerActivity")
public class MainerActivity extends MVVMBaseActivity<ActicityMainerBinding, MainerViewHolder> {
    //首页
    private HousesFragment housesFragment;
    private CollectListKotlinFragment collectListKotlinFragment;
    private LocationFragment locationFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private SettingFragment settingFragment;
    private static final String[] FRAGMENT_TAG = {
            "HousesFragment", "collectListKotlinFragment",
            "LocationFragment", "ShoppingCartFragment",
            "SettingFragment",
    };
    protected String CODE_FRAGMENT_KEY = "fragment_tag";//key
    private int mFragmentTag_index = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.acticity_mainer;
    }

    @Override
    public int getViewModelType() {
        return BR.viewModel;
    }

    private FragmentManager fragmentManager;

    @Override
    public int initContentView(Bundle savedInstanceState) {

        if (fragmentManager == null)
            fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 0 && housesFragment == null) {
                housesFragment = (HousesFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
                selectFirst(transaction).commitAllowingStateLoss();
            }
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 1 && collectListKotlinFragment == null) {
                collectListKotlinFragment = (CollectListKotlinFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
                selectSencond(transaction).commitAllowingStateLoss();
            }
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 2 && locationFragment == null) {
                locationFragment = (LocationFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
                selectThrid(transaction).commitAllowingStateLoss();
            }
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 3 && shoppingCartFragment == null) {
                shoppingCartFragment = (ShoppingCartFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[3]);
                selectFour(transaction).commitAllowingStateLoss();
            }
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 4 && settingFragment == null) {
                settingFragment = (SettingFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[4]);
                selectFive(transaction).commitAllowingStateLoss();
            }
        } else {
            selectFirst(transaction).commitAllowingStateLoss();

        }
        return super.initContentView(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initChoiceFragmentIntent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Mark.State.UserKey != null) {
            BaseApp.setKey(Mark.State.UserKey);
        }
    }

    private void initChoiceFragmentIntent() {
        if (getIntent().getStringExtra("IsShowShopCard") != null) {
            if ("true".equals(getIntent().getStringExtra("IsShowShopCard"))) {
                binding.bottomNavigationTab.setSelectedItemId(R.id.main_shopCard);

            }
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CODE_FRAGMENT_KEY, mFragmentTag_index);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof CollectListKotlinFragment)
            collectListKotlinFragment = (CollectListKotlinFragment) fragment;
        else if (fragment instanceof HousesFragment)
            housesFragment = (HousesFragment) fragment;
        else if (fragment instanceof LocationFragment)
            locationFragment = (LocationFragment) fragment;
        else if (fragment instanceof ShoppingCartFragment)
            shoppingCartFragment = (ShoppingCartFragment) fragment;
        else if (fragment instanceof SettingFragment)
            settingFragment = (SettingFragment) fragment;
        super.onAttachFragment(fragment);
    }

    private SharePreferencesHelper userInforuserInforSharePreferencesUtils = new SharePreferencesHelper(APPSingleton.getContext(), "USER_INFO");
    private SharePreferencesHelper languageSharePreferencesHelper = new SharePreferencesHelper(APPSingleton.getContext(), "APP_LANGUAGE");

    @Override
    public void initData() {
        initFragment();
        initMessenger();
        applypermission();
        initLang();
    }

    void initLang() {
        if (languageSharePreferencesHelper.contain("NOW_LANGUAGE")) {
            if (String.valueOf(languageSharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("CHINESE")) {
                ChangeLauguageUtils.changeAppLanguage(this, Locale.CHINESE);
                BaseConstant.CURRENTLANGUAGE = "zh_cn";//
            } else if (String.valueOf(languageSharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("THAILAND")) {
                ChangeLauguageUtils.changeAppLanguage(this, new Locale("th", ""));
                BaseConstant.CURRENTLANGUAGE = "th";//
            } else if (String.valueOf(languageSharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("ENGLISH")) {
                ChangeLauguageUtils.changeAppLanguage(this, Locale.ENGLISH);
                BaseConstant.CURRENTLANGUAGE = "en";//
            }
        }
        if (Mark.State.UserKey != null && !TextUtils.isEmpty(Mark.State.UserKey)) return;
        String diviceID = Settings.System.getString(APPSingleton.getContext().getContentResolver(), Settings.System.ANDROID_ID);
        if (userInforuserInforSharePreferencesUtils.contain("USERNAME_PHONE") && userInforuserInforSharePreferencesUtils.contain("USER_PASSWORD_PHONE")
                || userInforuserInforSharePreferencesUtils.contain("USERNAME_ACOUNT") && userInforuserInforSharePreferencesUtils.contain("USER_PASSWORD_ACOUNT")) {
            BaseConstant.isAutomaticLogIn = true;
            if (userInforuserInforSharePreferencesUtils.getSharePreference("USER_LOGIN_AWAY", "").equals("account")) {
                viewModel.logIn(
                        String.valueOf(userInforuserInforSharePreferencesUtils.getSharePreference("USERNAME_ACOUNT", "aite")),
                        String.valueOf(userInforuserInforSharePreferencesUtils.getSharePreference("USER_PASSWORD_ACOUNT", "aite")),
                        "isAccount",
                        "android",
                        BaseConstant.CURRENTLANGUAGE,
                        "1",
                        TextEmptyUtils.getText(diviceID));
            } else {
                viewModel.logIn2(
                        String.valueOf(userInforuserInforSharePreferencesUtils.getSharePreference("USERNAME_PHONE", "aite")),
                        String.valueOf(userInforuserInforSharePreferencesUtils.getSharePreference("USER_PASSWORD_PHONE", "aite")),
                        String.valueOf(userInforuserInforSharePreferencesUtils.getSharePreference("USERNAME_PHONE_CODE", "0086")),
                        "isMobile",
                        "android",
                        BaseConstant.CURRENTLANGUAGE,
                        "1",
                        TextEmptyUtils.getText(diviceID));
            }

        }
    }

    private void initMessenger() {
        Messenger.getDefault().register(this, DataConstant.TOKEN_HIDE_BAR, String.class, s -> {
            viewModel.isShowBarVisible.set(s.equals("SHOW_BAR") ? View.VISIBLE : View.GONE);
        });
        Messenger.getDefault().register(this, DataConstant.TOKEN_JUMPMAINFRAGMENT_BAR, String.class, s -> {
            if (fragmentManager == null) {
                fragmentManager = getSupportFragmentManager();
            }

            if (s.equals("JUMP_TO_MAIN")) {
                binding.bottomNavigationTab.setSelectedItemId(R.id.main_house);
            } else if (s.equals("JUMP_TO_SHOPCARD")) {
                binding.bottomNavigationTab.setSelectedItemId(R.id.main_shopCard);

            }

        });
    }

    private void initFragment() {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        binding.bottomNavigationTab.setOnNavigationItemSelectedListener(menuItem -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            hideFragment(transaction);
            switch (menuItem.getItemId()) {
                case R.id.main_house:
                    selectFirst(transaction).commitAllowingStateLoss();
                    mFragmentTag_index = 0;
                    break;
                case R.id.main_chocie:
                    selectSencond(transaction).commitAllowingStateLoss();
                    mFragmentTag_index = 1;
                    break;
                case R.id.main_message:
                    selectThrid(transaction).commitAllowingStateLoss();
                    mFragmentTag_index = 2;
                    break;
                case R.id.main_shopCard:
                    selectFour(transaction).commitAllowingStateLoss();
                    mFragmentTag_index = 3;
                    break;
                case R.id.main_person:
                    selectFive(transaction).commitAllowingStateLoss();
                    mFragmentTag_index = 4;
                    break;
                default:
                    break;
            }
            initTitle();
            return true;
        });
    }

    private void initTitle() {
        switch (mFragmentTag_index) {
            case 0:
                viewModel.title.set(getString(R.string.Home));
                break;
            case 1:
                viewModel.title.set(getString(R.string.Favorite));
                break;
            case 2:
                viewModel.title.set("消息");
                break;
            case 3:
                viewModel.title.set("购物车");
                break;
            case 4:
                viewModel.title.set("我的");
                break;
            default:
                break;

        }
    }

    private FragmentTransaction selectFirst(FragmentTransaction transaction) {
        if (housesFragment == null) {
            housesFragment = new HousesFragment();
            transaction.add(R.id.frameLayout, housesFragment, FRAGMENT_TAG[0]);
        } else {
            transaction.show(housesFragment);
        }
        return transaction;

    }


    private FragmentTransaction selectSencond(FragmentTransaction transaction) {
        if (collectListKotlinFragment == null) {
            collectListKotlinFragment = new CollectListKotlinFragment();
            transaction.add(R.id.frameLayout, collectListKotlinFragment, FRAGMENT_TAG[1]);
        } else {
            transaction.show(collectListKotlinFragment);
        }
        return transaction;
    }

    private FragmentTransaction selectThrid(FragmentTransaction transaction) {
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
            transaction.add(R.id.frameLayout, locationFragment, FRAGMENT_TAG[2]);
        } else {
            transaction.show(locationFragment);
        }
        return transaction;
    }

    private FragmentTransaction selectFour(FragmentTransaction transaction) {
        if (shoppingCartFragment == null) {
            shoppingCartFragment = new ShoppingCartFragment();
            transaction.add(R.id.frameLayout, shoppingCartFragment, FRAGMENT_TAG[3]);
        } else {
            transaction.show(shoppingCartFragment);
        }
        return transaction;
    }

    private FragmentTransaction selectFive(FragmentTransaction transaction) {
        if (settingFragment == null) {
            settingFragment = new SettingFragment();
            transaction.add(R.id.frameLayout, settingFragment, FRAGMENT_TAG[4]);
        } else {
            transaction.show(settingFragment);
        }
        return transaction;
    }

    /**
     * 隐藏碎片 避免重叠
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
        if (housesFragment != null) {
            transaction.hide(housesFragment);
        }
        if (shoppingCartFragment != null) {
            transaction.hide(shoppingCartFragment);
        }
        if (locationFragment != null) {
            transaction.hide(locationFragment);
        }
        if (collectListKotlinFragment != null) {
            transaction.hide(collectListKotlinFragment);
        }

    }
}
