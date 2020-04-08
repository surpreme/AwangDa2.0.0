package com.aite.a.activity.li.activity;

import android.content.Intent;
import android.widget.Toast;

import com.aite.a.activity.li.util.LogUtils;
import com.jiananshop.a.R;
import com.valy.baselibrary.bean.BaseConstant;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * @Auther: valy
 * @datetime: 2020-02-19
 * @desc:
 */
public class QrCodeActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.zxingview)
    ZXingView zXingView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activityqrcode_layout;
    }

    @Override
    protected void initView() {
        initToolbar("扫码");
        zXingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
        zXingView.startSpot();
        zXingView.setType(BarcodeType.ALL, null);
    }

    @Override
    protected void initModel() {

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        LogUtils.d(result);
        if (result != null && !result.equals("") && !result.equals("null")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(context, BaseWebViewActivity.class);
                    intent.putExtra("webViewUrl", String.format("%s&comefrom=app&lang_type=%s", result, BaseConstant.CURRENTLANGUAGE));
                    intent.putExtra("isHideToolBar", "true");
                    startActivity(intent);
                }
            });
        }


    }

//    @SuppressLint("CheckResult")
//    private void getQrData(String result) {
//        RetrofitClient.getInstance().getRetrofit().create(RetrofitInterface.class)
//                .onGetSettingQrData(Mark.State.UserKey, result, "zh_cn")
//                .compose(RxScheduler.Flo_io_main())
//                .subscribe(new Consumer<ResponseBody>() {
//                    @Override
//                    public void accept(ResponseBody responseBody) throws Exception {
//                        JSONObject jsonObject = new JSONObject(responseBody.string());
//                        String code = jsonObject.optString("error_code");
//                        if (code.equals("0")) {
//                            JSONArray datasArry = jsonObject.optJSONArray("datas");
//
//                        } else {
//                            JSONObject errorObject = jsonObject.optJSONObject("data");
//                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
//                            if (errorBean.getError() != null) {
//                                ToastUtils.showShort(errorBean.getError());
//                                LogUtils.e(errorBean.getError());
//                            }
//                        }
//                    }
//                }, throwable -> {
//                    ToastUtils.showShort(throwable.getMessage());
//                    LogUtils.e(throwable.getMessage());
//                });
//    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        String tipText = zXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            //在这里加入了根据传感器光线暗的时候自动打开闪光灯
            if (!tipText.contains(ambientBrightnessTip)) {
                zXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
//                zXingView.openFlashlight();
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                zXingView.getScanBoxView().setTipText(tipText);
            }
//            try {
//                zXingView.closeFlashlight();
//            } catch (Exception e) {
//                LogUtils.e(e);
//            }

        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(context, "系统繁忙", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }
}
