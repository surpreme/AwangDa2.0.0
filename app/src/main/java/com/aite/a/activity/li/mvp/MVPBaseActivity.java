package com.aite.a.activity.li.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.jiananshop.a.R;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * liziyang
 * 2020/01/09
 */

public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView, View.OnClickListener {
    public T mPresenter;

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void initModel();

    protected Context context;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        try {
            mPresenter = getInstance(this, 1);
            mPresenter.attachView((V) this);
        } catch (Exception e) {
            LogUtils.e(e.getClass() + e.toString());
        }

        context = this;
        unbinder = ButterKnife.bind((Activity) context);
        StatusBarUtils.setColor(context, getResources().getColor(R.color.white));
        initView();
        initModel();
    }

    protected boolean isEditTextEmpty(EditText editText) {
        return editText.getText() == null ||
                editText.getText().toString().length() == 0 ||
                editText.getText().toString().trim().equals("");
    }

    protected String getEditString(EditText editText) {
        if (isEditTextEmpty(editText)) {
            LogUtils.d("请检查输入的信息");
            return "";
        }
        try {
            return editText.getText().toString().trim();
        } catch (Exception e) {
            LogUtils.e(e);
            Toast.makeText(context, "请检查输入的信息", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void initToolbar(String title) {
        try {
            ImageView backImg = this.findViewById(R.id.iv_back);
            backImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            TextView titleTv = this.findViewById(R.id.tv_title);
            titleTv.setText(title);
        } catch (Exception e) {
            LogUtils.e("initToolbar-fail" + e);
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz, String tag, String extra) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(tag, extra);
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
