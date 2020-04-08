package com.aite.a.activity.li.activity.registered;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.databinding.ObservableField;

import androidx.annotation.NonNull;

import com.aite.a.AppManager;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.login.AreaCodeBean;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.activity.li.util.KeyBoardUtils;
import com.aite.a.activity.li.util.LogUtils;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;

/**
 * @Auther: valy
 * @datetime: 2020-01-18
 * @desc:
 */
public class RegisteredViewModel extends BaseViewModel {
    public RegisteredViewModel(@NonNull Application application) {
        super(application);
        title.set("注册");
        onGetAreaCode(BaseConstant.CURRENTLANGUAGE);
        //1手机2邮箱
        registeredType.set("1");
        registeredTypeTitle.set("手机号码:");

    }


    public ObservableField<String> registeredTypeTitle = new ObservableField<>("");
    public ObservableField<String> registeredType = new ObservableField<>("");
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> username = new ObservableField<>("");
    public ObservableField<String> userpassword = new ObservableField<>("");
    public ObservableField<String> userConfirmPassword = new ObservableField<>("");
    public ObservableField<String> phoneproofCode = new ObservableField<>("");
    public ObservableField<String> phonePhone = new ObservableField<>("");
    public ObservableField<String> emailAdress = new ObservableField<>("");
    public ObservableField<String> countryStr = new ObservableField<>("");
    public ObservableField<String> countryIconUrl = new ObservableField<>("");
    public ObservableField<String> countryCodeStr = new ObservableField<>("");
    public ObservableField<String> countryCodeTag = new ObservableField<>("");
    public List<AreaCodeBean.ListBean> areaListBean = new ArrayList<>();

    public BindingCommand onBackOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onBackPressed();

        }
    });
    /**
     * * 参数名字    提交方式	类型	是否必须	默认值	其他	说明	test
     * * lang_type	get	字符串	可选			语言code en英文 zh_cn简体 zh繁体 th泰文 km柬埔寨文
     * * vercode	post	字符串	可选			验证码（短信或邮箱）
     * * regtype	post	字符串	必须	Isaccountreg		注册方式:Isaccountreg Ismobilereg Isemailreg 三选一
     * * username	post	字符串	可选			手机号
     * * password	post	字符串	必须			密码
     * * password_confirm	post	字符串	必须			确认密码
     * * email	post	字符串	可选			邮箱
     * * client	post	字符串	必须			客户端类型 android wap wechat ios 四选一
     * * account_name	post	字符串	必须			用户名
     *
     * @Field("vercode") String vercode,
     * @Field("regtype") String regtype,
     * @Field("username") String username,
     * @Field("password") String password,
     * @Field("client") String client,
     * @Field("account_name") String account_name,
     * @Field("password_confirm") String password_confirm,
     * @Field("lang_type") String lang_type
     */
    public BindingCommand onPhoneSubmitOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (registeredType.get().equals("1")) {
                onPhoneSubmit(phoneproofCode.get(),
                        "Ismobilereg",
                        countryCodeTag.get() + phonePhone.get(),
                        userpassword.get(),
                        "android",
                        username.get(),
                        userConfirmPassword.get()
                );
            } else if (registeredType.get().equals("2")) {
                if (!KeyBoardUtils.isEmail(emailAdress.get())) {
                    ToastUtils.showShort("请检查邮箱格式");
                    return;
                }
                onEmailSubmit(phoneproofCode.get(),
                        "Isemailreg",
                        emailAdress.get(),
                        userpassword.get(),
                        "android",
                        username.get(),
                        userConfirmPassword.get()
                );
            }


        }
    });

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
            countryCodeStr.set(areaCodeBean.getList().get(0).getCode());
            countryStr.set(areaCodeBean.getList().get(0).getArea_name());
            countryIconUrl.set(areaCodeBean.getList().get(0).getIcon());
            countryCodeTag.set(areaCodeBean.getList().get(0).getArea_code());

        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
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
                .onPostPhoneCode(countryCodeTag.get() + phoneNumber, "zh_cn")
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
                .onPostEmailCode(emailAdress, "zh_cn")
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

    /**
     * @param =
     * @Field("vercode") String vercode,
     * @Field("regtype") String regtype,
     * @Field("username") String username,
     * @Field("password") String password,
     * @Field("client") String client,
     * @Field("account_name") String account_name,
     * @Field("password_confirm") String password_confirm,
     * @Field("lang_type") String lang_type
     */
    @SuppressLint("CheckResult")
    public void onPhoneSubmit(String vercode, String regtype, String username, String password, String client, String account_name, String password_confirm) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onRegistrationPhoneSubmission(vercode, regtype, username, password, client, account_name, password_confirm, "zh_cn")
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
//                            String datas = jsonObject.optString("datas");
                            ToastUtils.showShort(ActivityManager.getInstance().getCurrentActivity().getString(R.string.register_success));
                            onBackPressed();

                        }

                    }
                }, throwable -> {
//                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                });


    }

    /**
     * @param =
     * @Field("vercode") String vercode,
     * @Field("regtype") String regtype,
     * @Field("email") String email,
     * @Field("password") String password,
     * @Field("client") String client,
     * @Field("account_name") String account_name,
     * @Field("password_confirm") String password_confirm,
     * @Field("lang_type") String lang_type
     */
    @SuppressLint("CheckResult")
    public void onEmailSubmit(String vercode, String regtype, String email, String password, String client, String account_name, String password_confirm) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onRegistrationEmailSubmission(vercode, regtype, email, password, client, account_name, password_confirm, "zh_cn")
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
//                            Mark.State.UserKey = jsonObject.optString("key");
//                            Mark.State.UserName = jsonObject.optString("username");
//                            startActivity(MainerActivity.class);
                            ToastUtils.showShort(AppManager.getInstance().getTopActivity().getString(R.string.register_success));
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

}
