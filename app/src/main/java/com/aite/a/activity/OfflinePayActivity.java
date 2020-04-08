package com.aite.a.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.APPSingleton;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.a.adapter.BaseTextViewRecyAdapter;
import com.aite.a.base.Mark;
import com.aite.a.bean.PayUserListBean;
import com.aite.a.utils.ChoiceUtils;
import com.aite.a.utils.FileUtils;
import com.aite.a.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;
import com.zhihu.matisse.Matisse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @Auther: valy
 * @datetime: 2020-02-28
 * @desc:
 */
public class OfflinePayActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.top_line_view)
    View topLineView;
    @BindView(R.id.choice_bank_tv)
    TextView choiceBankTv;
    @BindView(R.id.bank_down_iv)
    ImageView bankDownIv;
    @BindView(R.id.choice_bank_line)
    View choiceBankLine;
    @BindView(R.id.user_tv)
    TextView userTv;
    @BindView(R.id.user_line)
    View userLine;
    @BindView(R.id.user_name_tv)
    TextView userNameTv;
    @BindView(R.id.user_name_line)
    View userNameLine;
    @BindView(R.id.pay_sure_title_tv)
    TextView paySureTitleTv;
    @BindView(R.id.add_photos_tv)
    TextView addPhotosTv;
    @BindView(R.id.submit_btn)
    Button submitBtn;
    @BindView(R.id.choice_iv)
    ImageView choiceIv;
    private Unbinder unbinder;
    private Context context;
    private List<PayUserListBean> payUserListBeans = new ArrayList<>();
    //图片选择
    private List<Uri> mSelected = new ArrayList<>();
    private Uri mSelectedPictrueUri = null;
    private PopupWindow mChocieUserPopupWindow;
    private String transfer_remark = "";
    private String pay_sn = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_offline_pay);
        unbinder = ButterKnife.bind(this);
        initEtras();
        initViews();
        initDatas();
    }

    private void initDatas() {
        getPayMoneyCard(Mark.State.UserKey, "zh_cn");

    }

    private void initViews() {
        tvTitle.setText("线下支付");

    }


    private void showChoiceAreaPopupwindow(final Context context, View ui, UserListRecyAdpter userListRecyAdpter) {
        @SuppressLint("InflateParams")
        View more_icon_view = LayoutInflater.from(context).inflate(R.layout.recycler_layout, null);
        mChocieUserPopupWindow = new PopupWindow(more_icon_view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mChocieUserPopupWindow.setContentView(more_icon_view);
        RecyclerView recyclerView = more_icon_view.findViewById(R.id.recycler_view);
        if (recyclerView == null) {
            ToastUtils.showToast(context, "数据错误");
            onBackPressed();
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userListRecyAdpter);
        mChocieUserPopupWindow.showAsDropDown(ui, 0, 0);
        mChocieUserPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {


            }
        });

    }

    @SuppressLint("CheckResult")
    public void getPayMoneyCard(String key, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostPayUserList(key, lang_type)
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
                            JSONArray jsonArray = jsonObject.optJSONArray("datas");
                            if (code.equals("200")) {
                                if (jsonArray != null) {
                                    LogUtils.d(jsonArray.toString());
                                    payUserListBeans =
                                            new Gson().fromJson(
                                                    String.valueOf(jsonArray),
                                                    new TypeToken<List<PayUserListBean>>() {
                                                    }.getType());
                                    if (payUserListBeans.isEmpty()) return;
                                    LogUtils.d(payUserListBeans.get(1).getName());


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

    @SuppressLint("CheckResult")
    public void postOfflinePayInformation(List<MultipartBody.Part> parts) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostOfflinePayInformation(parts)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(responseBody -> {
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    String code = jsonObject.optString("code");
                    String error_code = jsonObject.optString("error_code");
                    if (code != null) {
                        JSONObject datas = jsonObject.optJSONObject("datas");
                        String datasString = jsonObject.optString("datas");
                        if (code.equals("200")) {
                            if (datasString != null) {
                                LogUtils.d(datasString.toString());
                                Intent intent = new Intent(context, PaySuccessActivity.class);
                                intent.putExtra("others", datasString);
                                if (getIntent().getStringExtra("type") != null)
                                    intent.putExtra("type", "1");
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
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });

    }

    private void initEtras() {
        context = this;
        StatusBarUtils.setColor(context, getResources().getColor(R.color.white));
        if (getIntent().getStringExtra("pay_sn") != null) {
            pay_sn = getIntent().getStringExtra("pay_sn");
        } else {
            ToastUtils.showToast(context, "数据错误");
            onBackPressed();
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_back, R.id.choice_bank_tv, R.id.bank_down_iv, R.id.add_photos_tv, R.id.choice_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.add_photos_tv:
            case R.id.choice_iv:
                List<String> messages = new ArrayList<>();
                messages.add("拍照");
                messages.add("从相册选择");
                BaseTextViewRecyAdapter baseTextViewRecyAdapter = new BaseTextViewRecyAdapter(ActivityManager.getInstance().getCurrentActivity(), messages);
                baseTextViewRecyAdapter.setClickInterface(position -> {
                    if (position == 1) {
                        ChoiceUtils.openImg(this, 1, BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE);
                    } else if (position == 0) {
                        mSelectedPictrueUri = ChoiceUtils.takePhoto(this, BaseConstant.RESULT_CODE.REQUEST_CAMERA);

                    }
                    PopupWindowUtil.getmInstance().dismissPopWindow();

                });

                PopupWindowUtil.getmInstance().showBottomRecyAndCancelPopupWindow(
                        ActivityManager.getInstance().getCurrentActivity(),
                        baseTextViewRecyAdapter,
                        new LinearLayoutManager(APPSingleton.getContext(), LinearLayoutManager.VERTICAL, false),
                        R.color.coral);
                break;

            case R.id.choice_bank_tv:
            case R.id.bank_down_iv:
                UserListRecyAdpter userListRecyAdpter = new UserListRecyAdpter(this, payUserListBeans);
                userListRecyAdpter.setOnSelectInterface(position -> {
                    choiceBankTv.setText(payUserListBeans.get(position).getBank());
                    userTv.setText(String.format("账户：%s", payUserListBeans.get(position).getCard()));
                    userNameTv.setText(String.format("账户名：%s", payUserListBeans.get(position).getName()));
                    transfer_remark =
                            payUserListBeans.get(position).getBank() + "," +
                                    payUserListBeans.get(position).getCard() + "," +
                                    payUserListBeans.get(position).getName();
                    if (mChocieUserPopupWindow != null && mChocieUserPopupWindow.isShowing())
                        mChocieUserPopupWindow.dismiss();
                });
                if (!payUserListBeans.isEmpty())
                    showChoiceAreaPopupwindow(context, bankDownIv, userListRecyAdpter);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                mSelected = Matisse.obtainResult(data);
                if (mSelected.isEmpty()) return;
                Glide.with(this).load(mSelected.get(0)).into(choiceIv);
                mSelectedPictrueUri = mSelected.get(0);
            }
        } else if (requestCode == BaseConstant.RESULT_CODE.REQUEST_CAMERA && resultCode == RESULT_OK) {
            if (mSelectedPictrueUri == null) {
                ToastUtils.showToast(context, "请重新上传图片");
            } else {
                Glide.with(context).load(mSelectedPictrueUri).into(choiceIv);
            }
        }
    }

    @OnClick(R.id.submit_btn)
    public void onViewClicked() {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("key", Mark.State.UserKey);
        builder.addFormDataPart("pay_sn", pay_sn);
        if (transfer_remark.equals("")) {
            ToastUtils.showToast(context, "请选择账户");
            return;
        }
        builder.addFormDataPart("transfer_remark", transfer_remark);
        // 添加文件
        if (mSelectedPictrueUri != null) {
            File file = FileUtils.getFileByUri(context, mSelectedPictrueUri);
            if (file != null) {
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("pay_voucher_img", file.getName(), imageBody);

            }
        } else {
            ToastUtils.showToast(context, "请上传凭证图片");
            return;
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        postOfflinePayInformation(parts);

    }


    public static class UserListRecyAdpter extends RecyclerView.Adapter<UserListRecyAdpter.ViewHolder> {
        private List<PayUserListBean> payUserListBeans;
        private Context context;
        private LayoutInflater inflater;

        private UserListRecyAdpter(Context context, List<PayUserListBean> payUserListBeans) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.payUserListBeans = payUserListBeans;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = inflater.inflate(R.layout.item_recy_pay_user, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        public void appendData(List<PayUserListBean> paymentBeanList) {
            this.payUserListBeans.addAll(paymentBeanList);
            notifyDataSetChanged();
        }

        public void setOnSelectInterface(OnSelectInterface onSelectInterface) {
            this.onSelectInterface = onSelectInterface;
        }

        private OnSelectInterface onSelectInterface;


        public interface OnSelectInterface {
            void onSelect(int position);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.name_tv.setText(payUserListBeans.get(i).getBank());
            viewHolder.itemView.setOnClickListener(v -> {
                if (onSelectInterface != null) {
                    onSelectInterface.onSelect(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return payUserListBeans == null ? 0 : payUserListBeans.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.name_tv)
            TextView name_tv;

            ViewHolder(@NonNull View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
