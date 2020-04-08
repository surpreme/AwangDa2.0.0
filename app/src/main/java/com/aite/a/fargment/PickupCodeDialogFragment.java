package com.aite.a.fargment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.jiananshop.a.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * @Auther: valy
 * @datetime: 2020/2/29
 * @desc:
 */
public class PickupCodeDialogFragment extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.qr_iv)
    ImageView qrIv;
    @BindView(R.id.close_iv)
    ImageView closeIv;
    @BindView(R.id.qr_number_tv)
    TextView qrNumberTv;
    private Unbinder unbinder;


    public static PickupCodeDialogFragment newInstance(String qr_number) {
        PickupCodeDialogFragment fragment = new PickupCodeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("qr_number", qr_number);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View mBaseView = inflater.inflate(R.layout.dailog_fragment_get_thing_qr_code, container, false);
        unbinder = ButterKnife.bind(this, mBaseView);
        initView();
        return mBaseView;
    }

    private void initView() {
        if (getActivity() != null && getArguments() != null && getArguments().getString("qr_number") != null) {
            qrNumberTv.setText(getArguments().getString("qr_number"));
            Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(
                    getArguments().getString("qr_number"),
                    BGAQRCodeUtil.dp2px(getActivity(), 100),
                    Color.BLACK, Color.WHITE,
                    BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.app_icon));
            if (bitmap != null) qrIv.setImageBitmap(bitmap);
            else {
                dismiss();
                this.onDestroy();
            }

        } else {
            dismiss();
            this.onDestroy();
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.close_iv)
    public void onViewClicked() {
        dismiss();
    }
}
