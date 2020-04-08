package com.aite.a.fargment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.aite.a.activity.PhoneCertificationActivity;
import com.aite.a.view.PayPwdEditText;
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
public class CardPayDialogFragment extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.close_iv)
    ImageView closeIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolbars_line)
    View toolbarsLine;
    @BindView(R.id.pay_password_ed)
    PayPwdEditText payPasswordEd;
    @BindView(R.id.forget_tv)
    TextView forgetTv;
    Unbinder unbinder;
    public OnDialogListener mlistener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View mBaseView = inflater.inflate(R.layout.dailog_card_pay_password, container, false);
        unbinder = ButterKnife.bind(this, mBaseView);
        initView();
        return mBaseView;
    }

    private void initView() {
        payPasswordEd.initStyle(R.drawable.juxing_gray_background, 6, 0.33f, R.color.gray, R.color.gray, 30);
        payPasswordEd.setOnTextFinishListener(str -> {
            if (mlistener != null) {
                mlistener.onDialogClick(str);
                dismiss();
            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();
        payPasswordEd.setFocus();

    }

    /**
     * 设置位置在底部 而且设置背景为透明
     */
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = 700;
        window.setAttributes(params);
        //设置背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.forget_tv, R.id.close_iv})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.forget_tv) {
            Intent intent = new Intent(getActivity(), PhoneCertificationActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.close_iv) {
            dismiss();

        }
    }

    public interface OnDialogListener {
        void onDialogClick(String person);
    }

    public void setOnDialogListener(OnDialogListener dialogListener) {
        this.mlistener = dialogListener;
    }


}
