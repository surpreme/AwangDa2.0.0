package com.aite.a.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aite.a.activity.li.activity.mainer.MainerActivity;
import com.aite.a.activity.li.activity.onlinerecharge.OnlineRechargeKotlinActivity;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.jiananshop.a.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Auther: valy
 * @datetime: 2020-02-28
 * @desc:
 */
public class PaySuccessActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar_ll)
    RelativeLayout toolbarLl;
    @BindView(R.id.pay_success_logo_iv)
    ImageView paySuccessLogoIv;
    @BindView(R.id.pay_price_tv)
    TextView payPriceTv;
    @BindView(R.id.look_order_btn)
    Button lookOrderBtn;
    @BindView(R.id.back_main_btn)
    Button backMainBtn;
    private Unbinder unbinder;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        unbinder = ButterKnife.bind(this);
        initEtras();
        initViews();
        initDatas();
    }

    private void initDatas() {

    }

    private void initViews() {
        if (getIntent().getStringExtra("others") != null) {
            payPriceTv.setText(getIntent().getStringExtra("others"));
        } else payPriceTv.setText("订单支付成功");
        if (getIntent().getStringExtra("type") != null) {
            tvTitle.setText("审核");
            lookOrderBtn.setOnClickListener(v -> {
                startActivity(new Intent(context, OnlineRechargeKotlinActivity.class));
                this.finish();
            });
        }


    }

    private void initEtras() {
        context = this;
        StatusBarUtils.setColor(context, Color.parseColor("#FF5000"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_back, R.id.look_order_btn, R.id.back_main_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                this.finish();
                break;
            case R.id.look_order_btn:
                startActivity(new Intent(context, BuyerOrderFgActivity.class));
                this.finish();
                break;
            case R.id.back_main_btn:
                startActivity(new Intent(context, MainerActivity.class));
                this.finish();
                break;
        }
    }
}
