package com.aite.a.activity.li.activity.registered;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.aite.a.activity.li.activity.login.AreaCodeAdapter;
import com.aite.a.activity.li.util.KeyBoardUtils;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.ActicityRegisteredBinding;

import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * @Auther: valy
 * @datetime: 2020-01-18
 * @desc: 注册
 */
public class RegisteredActivity extends BaseActivity<ActicityRegisteredBinding, RegisteredViewModel> {
    private Context context;
    /**
     * 倒计时
     */
    private CountDownTimer mCountDownTimer;

    @Override
    public void initParam() {
        context = this;
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.acticity_registered;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
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
                    ToastUtils.showShort("请检查输入的手机号码信息");
                }
            } else if (viewModel.registeredType.get().equals("2")) {
                if (viewModel.emailAdress.get() != null && !viewModel.emailAdress.get().equals("") && KeyBoardUtils.isEmail(viewModel.emailAdress.get())) {
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
                    ToastUtils.showShort("请检查输入的邮箱地址");
                }
            }

        });
        binding.phoneRegisterTv.setOnClickListener(v -> {
            binding.mailboxRegisterTv.setTextColor(getResources().getColor(R.color.gray));
            binding.phoneRegisterTv.setTextColor(getResources().getColor(R.color.red));
            binding.phoneGetLl.setVisibility(View.VISIBLE);
            binding.emailGetLl.setVisibility(View.GONE);
            viewModel.registeredTypeTitle.set("手机号码：");
            viewModel.registeredType.set("1");
        });
        binding.mailboxRegisterTv.setOnClickListener(v -> {
            binding.phoneRegisterTv.setTextColor(getResources().getColor(R.color.gray));
            binding.mailboxRegisterTv.setTextColor(getResources().getColor(R.color.red));
            binding.phoneGetLl.setVisibility(View.GONE);
            binding.emailGetLl.setVisibility(View.VISIBLE);
            viewModel.registeredTypeTitle.set("邮  箱：");
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
}
