package com.aite.a.activity.li.activity.finduserpassword;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;

import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.activity.login.AreaCodeAdapter;
import com.aite.a.activity.li.mvvm.MVVMBaseActivity;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.jiananshop.a.BR;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.ActicityFindUserPasswordBinding;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Auther: valy
 * @datetime: 2020-02-20
 * @desc:
 */
public class FindPasswordActivity extends MVVMBaseActivity<ActicityFindUserPasswordBinding, FindPasswordViewModel> {
    /**
     * 倒计时
     */
    private CountDownTimer mCountDownTimer;

    @Override
    protected int getLayoutResId() {
        return R.layout.acticity_find_user_password;
    }

    @Override
    public int getViewModelType() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();

    }

    @Override
    public void initData() {
        super.initData();
        viewModel.onGetAreaCode(BaseConstant.CURRENTLANGUAGE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.tvTitle.setText("找回密码");
        binding.ivReturn.setOnClickListener(v -> onBackPressed());
        initVerificationCode();
        binding.phoneTv.setOnClickListener(v -> {
            binding.emailTv.setTextColor(getResources().getColor(R.color.gray));
            binding.phoneTv.setTextColor(getResources().getColor(R.color.red));
            binding.phoneGetLl.setVisibility(View.VISIBLE);
            binding.emailGetLl.setVisibility(View.GONE);
            binding.phoneTitleTv.setText("手机号码:");
            viewModel.registeredType.set("1");
        });
        binding.emailTv.setOnClickListener(v -> {
            binding.phoneTv.setTextColor(getResources().getColor(R.color.gray));
            binding.emailTv.setTextColor(getResources().getColor(R.color.red));
            binding.phoneGetLl.setVisibility(View.GONE);
            binding.emailGetLl.setVisibility(View.VISIBLE);
            binding.phoneTitleTv.setText("邮  箱:");
            viewModel.registeredType.set("2");

        });
        binding.areaIconIv.setOnClickListener(view -> {
            showChoiceArea();
        });
        binding.areaNameTv.setOnClickListener(view -> {
            showChoiceArea();
        });
        binding.areaCodeTv.setOnClickListener(v -> {
            showChoiceArea();
        });
    }

    private void showChoiceArea() {
        if (viewModel.areaListBean == null) return;
        AreaCodeAdapter areaCodeAdapter = new AreaCodeAdapter(context, viewModel.areaListBean);
        areaCodeAdapter.setGetfixSenderInterface(new AreaCodeAdapter.GetfixSenderInterface() {
            @Override
            public void p(int postion) {
                LogUtils.d(viewModel.areaListBean.get(postion).getArea_name());
                viewModel.countryIconUrl.set(viewModel.areaListBean.get(postion).getIcon());
                viewModel.countryStr.set(viewModel.areaListBean.get(postion).getArea_name());
                viewModel.countryCodeStr.set(viewModel.areaListBean.get(postion).getCode());
                viewModel.countryCodeTag.set(viewModel.areaListBean.get(postion).getArea_code());
                PopupWindowUtil.getmInstance().dismissPopWindow();

            }
        });
        PopupWindowUtil.getmInstance().showChoiceAreaPopupwindow(
                this,
                binding.areaIconIv,
                areaCodeAdapter
        );
    }

    private void initVerificationCode() {
        binding.getProofCodeTv.setOnClickListener(v -> {
            if (viewModel.registeredType.get().equals("1")) {
                if (viewModel.phonePhone.get() != null && !viewModel.phonePhone.get().equals("")) {
                    viewModel.onPostPhoneSureCode(viewModel.phonePhone.get());
                    binding.getProofCodeTv.setEnabled(false);
                    binding.getProofCodeTv.setBackground(getResources().getDrawable(R.drawable.critle_round_background_gray));
                    if (mCountDownTimer == null) {
                        mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
                            @SuppressLint("DefaultLocale")
                            @Override
                            public void onTick(long millisUntilFinished) {
                                binding.getProofCodeTv.setText(String.format("%dS", (int) millisUntilFinished / 1000));
                            }

                            @Override
                            public void onFinish() {
                                binding.getProofCodeTv.setEnabled(true);
                                binding.getProofCodeTv.setText("发送验证码");
                                binding.getProofCodeTv.setBackground(getResources().getDrawable(R.drawable.critle_round_background_yellow));


                            }
                        };
                    }
                    mCountDownTimer.start();
                } else {
                    ToastUtils.showShort("请检查输入的信息");
                }
            } else if (viewModel.registeredType.get().equals("2")) {
                if (viewModel.emailAdress.get() != null && !viewModel.emailAdress.get().equals("")) {
                    viewModel.onPostEmailSureCode(viewModel.emailAdress.get());
                    binding.getProofCodeTv.setEnabled(false);
                    binding.getProofCodeTv.setBackground(getResources().getDrawable(R.drawable.critle_round_background_gray));
                    if (mCountDownTimer == null) {
                        mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
                            @SuppressLint("DefaultLocale")
                            @Override
                            public void onTick(long millisUntilFinished) {
                                binding.getProofCodeTv.setText(String.format("%dS", (int) millisUntilFinished / 1000));
                            }

                            @Override
                            public void onFinish() {
                                binding.getProofCodeTv.setEnabled(true);
                                binding.getProofCodeTv.setText("发送验证码");
                                binding.getProofCodeTv.setBackground(getResources().getDrawable(R.drawable.critle_round_background_yellow));


                            }
                        };
                    }
                    mCountDownTimer.start();
                } else {
                    ToastUtils.showShort("请检查输入的信息");
                }
            }

        });
    }
}
