package com.aite.a.activity.li.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aite.a.activity.li.util.LogUtils;
import com.jiananshop.a.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected Context context;
    protected abstract int getLayoutResId();
    protected abstract void initView();
    protected abstract void initModel();
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());
        context=this;
        unbinder= ButterKnife.bind(this);
        initView();
        initModel();
    }



    @Override
    public void onClick(View v) {

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
    public void startActivity(Class<?> clz,String tag,String extra) {
        Intent intent=new Intent(this,clz);
        intent.putExtra(tag,extra);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
