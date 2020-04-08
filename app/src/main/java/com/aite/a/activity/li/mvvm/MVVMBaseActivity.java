package com.aite.a.activity.li.mvvm;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.util.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-09
 * @desc: , T extends BaseView, B extends BasePresenterImpl<T>
 * implements IBaseView
 */
public abstract class MVVMBaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity<V, VM> {
    protected abstract int getLayoutResId();

    protected Context context;


    public abstract int getViewModelType();

    @Override
    public void initParam() {
        context = this;

    }


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return getLayoutResId();
    }

    @Override
    public int initVariableId() {
        return getViewModelType();
    }
    @SuppressLint("CheckResult")
    public void applypermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            RxPermissions rxPermissions = new RxPermissions(this);
            //多个权限处理
            rxPermissions
                    .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.CAMERA)
                    .subscribe(permission -> { // will emit 2 Permission objects
                        if (permission.granted) {
                            // `permission.name` is granted !
                            LogUtils.d("权限全部同意");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + this.getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("权限被拒绝");

                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + this.getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("权限被拒绝");
                        }
                    });

//            int checkpermission = ContextCompat.checkSelfPermission(getApplicationContext(),
//                    Arrays.toString(requestPermissions));
//            if (checkpermission != PackageManager.PERMISSION_GRANTED) {//没有给权限
//                ActivityCompat.requestPermissions((Activity) context, requestPermissions, 1);
//            }
        }
    }

}
