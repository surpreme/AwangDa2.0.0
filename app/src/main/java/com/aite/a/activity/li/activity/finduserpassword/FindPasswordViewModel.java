package com.aite.a.activity.li.activity.finduserpassword;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import com.aite.a.APPSingleton;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.login.AreaCodeBean;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.google.gson.Gson;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONObject;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;

/**
 * @Auther: valy
 * @datetime: 2020-02-20
 * @desc:
 */
public class FindPasswordViewModel extends BaseViewModel {
    private SharePreferencesHelper sharePreferencesUtils = new SharePreferencesHelper(APPSingleton.getContext(), "USER_INFO");

    public FindPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> registeredType = new ObservableField<>("");
    public ObservableField<String> countryStr = new ObservableField<>("");
    public ObservableField<String> countryIconUrl = new ObservableField<>("");
    public ObservableField<String> countryCodeStr = new ObservableField<>("");
    public ObservableField<String> countryCodeTag = new ObservableField<>("");
    public ObservableField<String> phonePhone = new ObservableField<>("");
    public ObservableField<String> emailAdress = new ObservableField<>("");
    public ObservableField<String> phoneproofCode = new ObservableField<>("");
    public ObservableField<String> userpassword = new ObservableField<>("");
    public ObservableField<String> userConfirmPassword = new ObservableField<>("");
    public ObservableArrayList<AreaCodeBean.ListBean> areaListBean = new ObservableArrayList<>();

    public BindingCommand onPhoneSubmitOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (registeredType.get() == null) return;
            if (registeredType.get().equals("1")) {
                onPostPhoneMobileVerificationCode(BaseConstant.CURRENTLANGUAGE, "Ismobilereg", phonePhone.get(), phoneproofCode.get());
            } else if (registeredType.get().equals("2")) {
                onPostEmailVerificationCode(BaseConstant.CURRENTLANGUAGE, "Isemailreg", emailAdress.get(), phoneproofCode.get());

            }


        }
    });

    /**
     * 验证 手机
     *
     * @param lang_type
     * @param regtype
     * @param username
     * @param mobile_code
     */
    @SuppressLint("CheckResult")
    public void onPostPhoneMobileVerificationCode(String lang_type, String regtype, String username, String mobile_code) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostPhoneVerificationCode(lang_type, regtype, username, mobile_code)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code.equals("0")) {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            if (errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                                dismissDialog();
                            }

                        } else if (code.equals("200")) {
                            ToastUtils.showShort("密码已发送您的手机短信");
                            onBackPressed();
//                            String datas = jsonObject.optString("datas");
//                            ToastUtils.showShort(datas);

                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });


    }

    /**
     * 验证 email
     */
    @SuppressLint("CheckResult")
    public void onPostEmailVerificationCode(String lang_type, String regtype, String email, String mobile_code) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostEmailVerificationCode(lang_type, regtype, email, mobile_code)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code.equals("0")) {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            if (errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                                dismissDialog();
                            }

                        } else if (code.equals("200")) {
                            ToastUtils.showShort("密码已发送您的邮箱");
                            onBackPressed();
//                            String datas = jsonObject.optString("datas");
//                            ToastUtils.showShort(datas);

                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });


    }

    /**
     * {
     * "code": 0,
     * "datas": {
     * "error": "同一IP同一操作每天获取验证码只能5次，请明天再来"
     * }
     * }
     * {
     * "code": 0,
     * "datas": "发送成功"
     * }
     *
     * @param phoneNumber
     */
    @SuppressLint("CheckResult")
    public void onPostPhoneSureCode(String phoneNumber) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostFindPasswordPhoneCode(countryCodeTag.get() + phoneNumber, "zh_cn")
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code.equals("0")) {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            if (errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                                dismissDialog();
                            }

                        } else if (code.equals("200")) {
                            String datas = jsonObject.optString("datas");
                            ToastUtils.showShort(datas);

                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });


    }

    @SuppressLint("CheckResult")
    public void onPostEmailSureCode(String emailAdress) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPostFindPasswordEmailCode(emailAdress, "zh_cn")
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        if (code.equals("0")) {
                            JSONObject errorObject = jsonObject.optJSONObject("datas");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            if (errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                                dismissDialog();
                            }

                        } else if (code.equals("200")) {
                            String datas = jsonObject.optString("datas");
                            ToastUtils.showShort(datas);

                        }

                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });


    }

    @SuppressLint("CheckResult")
    public void onGetAreaCode(String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .getAreaCode(lang_type)
                .compose(RxScheduler.Flo_io_main())
                .filter(areaCodeBeanBaseData -> {
                    if (!areaCodeBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(areaCodeBeanBaseData.getDatas().getError());
                        LogUtils.e(areaCodeBeanBaseData.getDatas().getError());
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(areaCodeBeanBaseData -> {
                    if (areaCodeBeanBaseData.getError() != null) {
                        ToastUtils.showShort(areaCodeBeanBaseData.getError());
                        LogUtils.e(areaCodeBeanBaseData.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(areaCodeBean -> {
            areaListBean.addAll(areaCodeBean.getList());
            if (sharePreferencesUtils.contain("USERNAME_PHONE_CODE")) {
                String code = String.valueOf(sharePreferencesUtils.getSharePreference("USERNAME_PHONE_CODE", "0086"));
                if (code != null) {
                    for (int i = 0; i < areaCodeBean.getList().size(); i++) {
                        if (areaCodeBean.getList().get(i).getArea_code().equals(code)) {
                            countryCodeStr.set(areaCodeBean.getList().get(i).getCode());
                            countryStr.set(areaCodeBean.getList().get(i).getArea_name());
                            countryIconUrl.set(areaCodeBean.getList().get(i).getIcon());
                            countryCodeTag.set(areaCodeBean.getList().get(i).getArea_code());
                        }
                    }
                } else {
                    countryCodeStr.set(areaCodeBean.getList().get(0).getCode());
                    countryStr.set(areaCodeBean.getList().get(0).getArea_name());
                    countryIconUrl.set(areaCodeBean.getList().get(0).getIcon());
                    countryCodeTag.set(areaCodeBean.getList().get(0).getArea_code());

                }
            } else {
                countryCodeStr.set(areaCodeBean.getList().get(0).getCode());
                countryStr.set(areaCodeBean.getList().get(0).getArea_name());
                countryIconUrl.set(areaCodeBean.getList().get(0).getIcon());
                countryCodeTag.set(areaCodeBean.getList().get(0).getArea_code());
            }


        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });


    }
}
