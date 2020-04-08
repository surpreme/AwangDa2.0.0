package com.aite.a.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseWebViewActivity;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.a.base.Mark;
import com.aite.a.bean.PayListBean;
import com.aite.a.fargment.CardPayDialogFragment;
import com.aite.a.utils.SystemUtil;
import com.aite.a.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.valy.baselibrary.adpter.BaseItemDecoration;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * @Auther: valy
 * @datetime: 2020-02-27
 * @desc: 已迁移到 SurePayListKotlinActivity mvp
 */
public class SurePayListActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar_ll)
    RelativeLayout toolbarLl;
    @BindView(R.id.order_number_tv)
    TextView orderNumberTv;
    @BindView(R.id.order_number_line)
    View orderNumberLine;
    @BindView(R.id.all_price_tv)
    TextView allPriceTv;
    @BindView(R.id.all_price_line)
    View allPriceLine;
    @BindView(R.id.bank_price_tv)
    TextView bankPriceTv;
    @BindView(R.id.bank_price_line)
    View bankPriceLine;
    @BindView(R.id.pay_list_recy)
    RecyclerView payListRecy;
    @BindView(R.id.pay_image_iv)
    ImageView payImageIv;
    @BindView(R.id.pay_name_tv)
    TextView payNameTv;
    @BindView(R.id.sure_pay_btn)
    Button surePayBtn;
    @BindView(R.id.all_price_over_tv)
    TextView allPriceOverTv;
    private Context context;
    private Unbinder unbinder;
    private PayListRecyAdpter payListRecyAdpter;
    private List<PayListBean.PaymentBean> paymentBeanArrayList = new ArrayList<>();
    //   1 "predeposit" 2"transfer" 3"pipay"
    private int payTag = 1;
    private String payCode = "";
    private String pay_sn = "";
    private double mOrderd_amount = 0f;//总价

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order_three);
        unbinder = ButterKnife.bind(this);
        initEtras();
        initViews();
        initDatas();
    }

    private void initDatas() {
        if (getIntent().getStringExtra("PAY_SN") != null)
            getPayList(Mark.State.UserKey, pay_sn = getIntent().getStringExtra("PAY_SN"));
        else {
            onBackPressed();
        }

    }

    private void initViews() {
        ivBack.setOnClickListener(this);
        surePayBtn.setOnClickListener(this);
        bankPriceTv.setText(setSpannableStringBuilder("银行-第三方手续费：0%   $" + "0.00", 10, 0));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        payListRecy.setLayoutManager(gridLayoutManager);
        payListRecyAdpter = new PayListRecyAdpter(context, paymentBeanArrayList);
        payListRecy.setAdapter(payListRecyAdpter);
        if (payListRecy.getItemDecorationCount() == 0) {
            payListRecy.addItemDecoration(new BaseItemDecoration(
                    SystemUtil.dip2px(context, 1),
                    SystemUtil.dip2px(context, 1),
                    SystemUtil.dip2px(context, 1),
                    SystemUtil.dip2px(context, 1),
                    SystemUtil.dip2px(context, 1),
                    SystemUtil.dip2px(context, 1),
                    Color.parseColor("#EDEBEF"),
                    context,
                    2f,
                    "4:1"));
        }
        payListRecyAdpter.setOnSelectInterface(position -> {
            if (paymentBeanArrayList.get(position).getPayment_code() != null) {
                payCode = paymentBeanArrayList.get(position).getPayment_code();
                if (paymentBeanArrayList.get(position).getPayment_code().equals("predeposit")) {
                    payTag = 1;
                } else if (paymentBeanArrayList.get(position).getPayment_code().equals("transfer")) {
                    payTag = 2;
                } else if (paymentBeanArrayList.get(position).getPayment_code().equals("pipay")) {
                    payTag = 3;


                }
                if (!TextUtils.isEmpty(paymentBeanArrayList.get(position).getHandling_fee()) && !paymentBeanArrayList.get(position).getHandling_fee().equals("0")) {
                    try {
                        Double uu = mOrderd_amount * Double.valueOf(paymentBeanArrayList.get(position).getHandling_fee()) / 100;
                        bankPriceTv.setText(setSpannableStringBuilder("银行-第三方手续费：" + paymentBeanArrayList.get(position).getHandling_fee() + "%   $" + haveTwoDouble(uu), 10, 0));
                        allPriceOverTv.
                                setText(
                                        setSpannableStringBuilder(
                                                "总计： $" + haveTwoDouble(uu + mOrderd_amount),
                                                3,
                                                0));
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                } else {
                    bankPriceTv.setText(setSpannableStringBuilder("银行-第三方手续费： 0%   $0.00", 10, 0));
                    allPriceOverTv.
                            setText(
                                    setSpannableStringBuilder(
                                            "总计： $" + haveTwoDouble(mOrderd_amount),
                                            3,
                                            0));
                }
            }



        });

    }

    /**
     * 转换字符为小数后两位
     * 格式化，区小数后两位
     */
    private String haveTwoDouble(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            return df.format(d);
        } catch (Exception e) {
            LogUtils.e(d);
            return "";
        }
    }

    private void onGetPayListSuccess(PayListBean payListBean) {
        if (payListBean.getInfo().getPay_sn() != null)
            orderNumberTv.setText(String.format("订单编号：%s", payListBean.getInfo().getPay_sn()));
        if (payListBean.getInfo().getOrder_amount() != null) {
//            allPriceOverTv.setText(String.format("总价： $%s", payListBean.getInfo().getOrder_amount()));
            try {
                mOrderd_amount = Double.parseDouble(payListBean.getInfo().getOrder_amount());
            } catch (Exception e) {
                LogUtils.e(e);
            }
            allPriceTv.
                    setText(
                            setSpannableStringBuilder(
                                    "总价： $" + payListBean.getInfo().getOrder_amount(),
                                    3,
                                    0));
            allPriceOverTv.
                    setText(
                            setSpannableStringBuilder(
                                    "总计： $" + payListBean.getInfo().getOrder_amount(),
                                    3,
                                    0));

        }
        payListBean.getPayment().get(0).setSelect(true);
        payListRecyAdpter.appendData(paymentBeanArrayList = payListBean.getPayment());

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.sure_pay_btn:
                if (payTag == 1) {
                    showPasswordDailogFragment();
                } else if (payTag == 2) {
                    Intent intent = new Intent(context, OfflinePayActivity.class);
                    intent.putExtra("pay_sn", pay_sn);
                    startActivity(intent);
                } else if (payTag == 3) {
                    getPayPipay(Mark.State.UserKey, pay_sn, payCode, "zh_cn");
                }

                break;
        }
    }

    private void showPasswordDailogFragment() {
        CardPayDialogFragment cardPayDialogFragment = new CardPayDialogFragment();
        cardPayDialogFragment.setOnDialogListener(person -> {
            LogUtils.d("---------" + person);
            getPayMoneyCard(Mark.State.UserKey, pay_sn, person, "zh_cn");
        });
        cardPayDialogFragment.show(getSupportFragmentManager(), "payPasswordDialog");
    }
    //设置字体不同颜色

    private SpannableStringBuilder setSpannableStringBuilder(String text, int start, int end) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色DA2230
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#060606"));//黑
        ForegroundColorSpan blackSpan = new ForegroundColorSpan(Color.parseColor("#FF5000"));//橙
        builder.setSpan(redSpan, 0, start, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(blackSpan, start, end == 0 ? text.length() : end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return builder;
    }

    @SuppressLint("CheckResult")
    public void getPayList(String key, String pay_sn) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostPayList(key, pay_sn,"zh_cn")
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            if (code.equals("200")) {
                                if (datas != null) {
                                    PayListBean payListBean = new Gson().fromJson(datas.toString(), PayListBean.class);
                                    onGetPayListSuccess(payListBean);
                                }
                            } else {
                                if (datas != null) {
                                    String error = datas.optString("error");
                                    if (!TextUtils.isEmpty(error))
                                        ToastUtils.showToast(context, error);
                                }
                            }

                        }
                        if (error_code != null) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            if (datas != null) {
                                String error = datas.optString("error");
                                if (!TextUtils.isEmpty(error))
                                    ToastUtils.showToast(context, error);
                            }


                        }
                    }
                }, throwable -> {
//                    ToastUtils.showToast(context, throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });

    }

    //钱包支付
    @SuppressLint("CheckResult")
    public void getPayMoneyCard(String key, String pay_sn, String password, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostPayMoneyCard(key, pay_sn, password, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            String datasString = jsonObject.optString("datas");
                            if (code.equals("200")) {
                                if (datasString != null) {
                                    ToastUtils.showToast(context, datasString);
                                    Intent intent = new Intent(context, PaySuccessActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                if (datas != null) {
                                    String error = datas.optString("error");
                                    if (!TextUtils.isEmpty(error))
                                        ToastUtils.showToast(context, error);
                                }
                            }

                        }
                        if (error_code != null) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            if (datas != null) {
                                String error = datas.optString("error");
                                if (!TextUtils.isEmpty(error))
                                    ToastUtils.showToast(context, error);
                            }


                        }
                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }

    //pipay支付
    @SuppressLint("CheckResult")
    public void getPayPipay(String key, String pay_sn, String payment_code, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostPayPipay(key, pay_sn, payment_code, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String error_code = jsonObject.optString("error_code");
                        if (code != null) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            String datasString = jsonObject.optString("datas");
                            if (code.equals("200")) {
                                if (datasString != null) {
                                    LogUtils.d(datasString);
                                    Intent intent = new Intent(context, BaseWebViewActivity.class);
                                    intent.putExtra("isHideToolBar", "false");
                                    intent.putExtra("title", "");
                                    intent.putExtra("loadDataWithBaseURL", datasString);
                                    startActivity(intent);
                                    finish();

                                }
                            } else {
                                if (datas != null) {
                                    String error = datas.optString("error");
                                    if (!TextUtils.isEmpty(error))
                                        ToastUtils.showToast(context, error);
                                }
                            }

                        }
                        if (error_code != null) {
                            JSONObject datas = jsonObject.optJSONObject("datas");
                            if (datas != null) {
                                String error = datas.optString("error");
                                if (!TextUtils.isEmpty(error))
                                    ToastUtils.showToast(context, error);
                            }


                        }
                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }

    public static class PayListRecyAdpter extends RecyclerView.Adapter<PayListRecyAdpter.ViewHolder> {
        private List<PayListBean.PaymentBean> paymentBeans;
        private Context context;
        private LayoutInflater inflater;

        private PayListRecyAdpter(Context context, List<PayListBean.PaymentBean> paymentBeans) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.paymentBeans = paymentBeans;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = inflater.inflate(R.layout.item_recy_pay_order_three, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        public void appendData(List<PayListBean.PaymentBean> paymentBeanList) {
            this.paymentBeans.addAll(paymentBeanList);
            notifyDataSetChanged();
        }

        public void setOnSelectInterface(OnSelectInterface onSelectInterface) {
            this.onSelectInterface = onSelectInterface;
        }

        private OnSelectInterface onSelectInterface;

        public void setPaymentBeans(List<PayListBean.PaymentBean> paymentBeans) {
            this.paymentBeans = paymentBeans;
        }

        public interface OnSelectInterface {
            void onSelect(int position);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.recommendBackgroundIv.setVisibility(i == 0 ? View.VISIBLE : View.GONE);
            viewHolder.recommendTitleTv.setVisibility(i == 0 ? View.VISIBLE : View.GONE);
            viewHolder.selectIv.setVisibility(paymentBeans.get(i).isSelect() ? View.VISIBLE : View.GONE);
            Glide.with(context).load(paymentBeans.get(i).getPayment_image()).into(viewHolder.payImageIv);
            if (paymentBeans.get(i).getMember_info() != null) {
                if (paymentBeans.get(i).getMember_info().getAvailable_predeposit() != null) {
                    viewHolder.payNameTv.setText(
                            String.format("%s%s",
                                    paymentBeans.get(i).getPayment_name(),
                                    paymentBeans.get(i).getMember_info().getAvailable_predeposit()));

                } else viewHolder.payNameTv.setText(paymentBeans.get(i).getPayment_name());

            } else
                viewHolder.payNameTv.setText(paymentBeans.get(i).getPayment_name());
            viewHolder.itemView.setOnClickListener(v -> {
                if (onSelectInterface != null) {
                    for (int p = 0; p < paymentBeans.size(); p++) {
                        paymentBeans.get(p).setSelect(p == i);
                    }
                    notifyDataSetChanged();
                    onSelectInterface.onSelect(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return paymentBeans == null ? 0 : paymentBeans.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.recommend_background_iv)
            ImageView recommendBackgroundIv;
            @BindView(R.id.recommend_title_tv)
            TextView recommendTitleTv;
            @BindView(R.id.pay_image_iv)
            ImageView payImageIv;
            @BindView(R.id.pay_name_tv)
            TextView payNameTv;
            @BindView(R.id.select_iv)
            ImageView selectIv;

            ViewHolder(@NonNull View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }


}

