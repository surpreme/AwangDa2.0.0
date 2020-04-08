package com.aite.a.activity.li.activity.login;


import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.aite.a.APPSingleton;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.activity.BaseWebViewActivity;
import com.aite.a.activity.li.mvvm.MVVMBaseActivity;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.activity.li.util.SharePreferencesHelper;
import com.aite.a.activity.li.util.TextEmptyUtils;
import com.aite.a.utils.ThreeElseLogInUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.jiananshop.a.BR;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.ActivityLoginLayoutBinding;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.Arrays;


/**
 * liziyang
 * 20/01/09
 */
@Route(path = "/jn/LogInActivity")
public class LogInActivity extends MVVMBaseActivity<ActivityLoginLayoutBinding, LogInViewHolder> {
    private SharePreferencesHelper userInforSharePreferencesUtils = new SharePreferencesHelper(APPSingleton.getContext(), "USER_INFO");
    private SharePreferencesHelper languageSharePreferencesHelper = new SharePreferencesHelper(APPSingleton.getContext(), "APP_LANGUAGE");

    private CallbackManager callbackManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login_layout;
    }

    @Override
    public int getViewModelType() {
        return BR.viewModel;
    }

    @Override
    public LogInViewHolder initViewModel() {
        return ViewModelProviders.of(this).get(LogInViewHolder.class);
    }

    @Override
    public void initParam() {
        super.initParam();

     /*   if (languageSharePreferencesHelper.contain("NOW_LANGUAGE")) {
            if (String.valueOf(languageSharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("CHINESE")) {
                ChangeLauguageUtils.changeAppLanguage(this, Locale.CHINESE);
                BaseConstant.CURRENTLANGUAGE = "zh_cn";//
            } else if (String.valueOf(languageSharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("THAILAND")) {
                ChangeLauguageUtils.changeAppLanguage(this, new Locale("th", ""));
                BaseConstant.CURRENTLANGUAGE = "th";//
            } else if (String.valueOf(languageSharePreferencesHelper.getSharePreference("NOW_LANGUAGE", "")).equals("ENGLISH")) {
                ChangeLauguageUtils.changeAppLanguage(this, Locale.ENGLISH);
                BaseConstant.CURRENTLANGUAGE = "en";//
            }
        }*/
    }

    @Override
    public void initData() {
        /**
         * 初始化 放在vm不能正常使用 生命周期问题
         */
        viewModel.isRememberPassword.set(false);
//        binding.rememberPasswordCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            viewModel.isRememberPassword.set(isChecked);
//
//        });
        binding.areaIconIv.setOnClickListener(view -> {
            showChoiceArea();
        });
        binding.areaNameTv.setOnClickListener(view -> {
            showChoiceArea();
        });
        binding.areaCodeTv.setOnClickListener(v -> {
            showChoiceArea();
        });
        initFaceBookLogIn();
        binding.facebookLoginButton.setOnClickListener(v -> {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        });
        initInstagramLogIn();
        /**
         * wechat
         * @param type
         * @param client
         * @param lang_type
         * @param unionid
         * @param device_id
         */
        binding.wechatLoginIv.setOnClickListener(v -> {
            ThreeElseLogInUtils.authorization(SHARE_MEDIA.WEIXIN, this, map -> {
                String UNIONID = map.get("unionid");
                String NICKNAME = map.get("name");
                String HEADIMGURL = map.get("iconurl");
                viewModel.weChatUnionid.set(map.get("unionid"));
                viewModel.weChatUserName.set(map.get("name"));
                viewModel.weChatIconUrl.set(map.get("iconurl"));
                LogUtils.d(UNIONID + NICKNAME);
                viewModel.logIn5(
                        "1",
                        "android",
                        "zh_cn",
                        map.get("unionid"),
                        TextEmptyUtils.getText(Settings.System.getString(APPSingleton.getContext().getContentResolver(),
                                Settings.System.ANDROID_ID)));
            });
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initAutomaticLogin();
    }

    /**
     * 缓存中记录是否自动登录过 第二次则不自动登录
     */
    private void initAutomaticLogin() {
        if (!BaseConstant.isAutomaticLogIn && userInforSharePreferencesUtils.contain("USER_LOGIN_AWAY") != null) {
            String diviceID = Settings.System.getString(APPSingleton.getContext().getContentResolver(), Settings.System.ANDROID_ID);
            if (userInforSharePreferencesUtils.getSharePreference("USER_LOGIN_AWAY", "").equals("account") && userInforSharePreferencesUtils.contain("USERNAME_ACOUNT") && userInforSharePreferencesUtils.contain("USER_PASSWORD_ACOUNT")) {
                viewModel.logIn(
                        String.valueOf(userInforSharePreferencesUtils.getSharePreference("USERNAME_ACOUNT", "aite")),
                        String.valueOf(userInforSharePreferencesUtils.getSharePreference("USER_PASSWORD_ACOUNT", "aite")),
                        "isAccount",
                        "android",
                        "zh_cn",
                        "1",
                        TextEmptyUtils.getText(diviceID)
                );
            } else if (userInforSharePreferencesUtils.getSharePreference("USER_LOGIN_AWAY", "").equals("phone") && userInforSharePreferencesUtils.contain("USERNAME_PHONE") && userInforSharePreferencesUtils.contain("USERNAME_PHONE_CODE") && userInforSharePreferencesUtils.contain("USER_PASSWORD_PHONE")) {
                viewModel.logIn2(
                        String.valueOf(userInforSharePreferencesUtils.getSharePreference("USERNAME_PHONE", "aite")),
                        String.valueOf(userInforSharePreferencesUtils.getSharePreference("USER_PASSWORD_PHONE", "aite")),
                        String.valueOf(userInforSharePreferencesUtils.getSharePreference("USERNAME_PHONE_CODE", "0086")),
                        "isMobile",
                        "android",
                        "zh_cn",
                        "1",
                        TextEmptyUtils.getText(diviceID));
            }
            BaseConstant.isAutomaticLogIn = true;
        }
    }

    /**
     * ins登录 https://developers.facebook.com/docs/instagram-basic-display-api/getting-started（文档）
     * 在下方构建授权窗口网址，使用您 Instagram 应用的编号（应用面板 > 产品 > Instagram > 基本显示 > Instagram 应用编号栏）替换 {app-id}，然后使用您在第 2 步中提供的网站网址（“有效的 OAuth 重定向 URI”）来替换 {redirect-uri}。网址必须完全相同。
     * <p>
     * https://api.instagram.com/oauth/authorize
     * ?client_id={app-id}
     * &redirect_uri={redirect-uri}
     * &scope=user_profile,user_media
     * &response_type=code
     * 例如：
     * <p>
     * https://api.instagram.com/oauth/authorize
     * ?client_id=684477648739411
     * &redirect_uri=https://socialsizzle.herokuapp.com/auth/
     * &scope=user_profile,user_media
     * &response_type=code
     * 打开新的浏览器窗口，然后加载授权窗口网址。其中应该会显示您的 Instagram 用户的名称、应用名称以及对应用正在请求的权限的描述。
     * <p>
     * <p>
     * Instagram 应用编号、Instagram 应用密钥、重定向 URI
     * * @Field("client_id") String client_id,
     * * @Field("client_secret") String client_secret,
     * * @Field("grant_type") String grant_type,
     * * @Field("redirect_uri") String redirect_uri,
     * * @Field("code") String code
     * https://developers.facebook.com/apps/2744719835567597/instagram/basic-display/
     */
    private void initInstagramLogIn() {
        binding.instagramLogInBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, BaseWebViewActivity.class);
            intent.putExtra("webViewUrl", "https://api.instagram.com/oauth/authorize/?client_id=448547546064554&redirect_uri=https://daluxmall.com/account/inslogin_redirect.php&scope=user_profile,user_media&response_type=code");
            intent.putExtra("isHideToolBar", "false");
            intent.putExtra("title", "instagram授权登录");
            startActivity(intent);
        });
        String instagram_code = getIntent().getStringExtra("INSTAGRAM_CODE");
        if (instagram_code != null) {
            viewModel.
                    instagramConfiguration(
                            "448547546064554",
                            "87d0355f5713d0a0fb3b7ba18c606bfc",
                            "authorization_code",
                            "https://daluxmall.com/account/inslogin_redirect.php",
                            instagram_code
                    );

        }
    }

    private void initFaceBookLogIn() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        LogUtils.e("facebook", loginResult);
                        getFbLoginInfo(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        LogUtils.e("facebook", "cancel");

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        LogUtils.e("facebook", "onError" + exception.toString());

                    }
                });
    }

    /**
     * 获取Facebook个人信息
     */
    public void getFbLoginInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {
                    LogUtils.d(object.toString());
                    String id = object.optString("id");
                    String name = object.optString("name");
                    String gender = object.optString("gender");  //性别
                    String email = object.optString("email");
                    //获取用户头像
                    JSONObject object_pic = object.optJSONObject("picture");
                    JSONObject object_data = object_pic.optJSONObject("data");
                    String photo = object_data.optString("url");
                    //获取地域信息
                    String locale = object.optString("locale");   //zh_CN 代表中文简体
                    viewModel.facebookuserName.set(name);
                    viewModel.facebookIconUrl.set(photo);
                    viewModel.facebookId.set(id);
                    viewModel.logIn3("3", "android", "zh_cn", id, TextEmptyUtils.getText(Settings.System.getString(APPSingleton.getContext().getContentResolver(), Settings.System.ANDROID_ID)));

                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,gender,birthday,email,picture,locale,updated_time,timezone,age_range,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRememberPassword();

    }


    /**
     * facebook login
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initRememberPassword() {
//        USER_LOGIN_AWAY
        if (userInforSharePreferencesUtils.contain("USER_LOGIN_AWAY") != null) {
            if (userInforSharePreferencesUtils.getSharePreference("USER_LOGIN_AWAY", "").equals("account")) {
                viewModel.logInAwayTips.set(getString(R.string.LoginMobile));
                viewModel.hideAcount.set(View.VISIBLE);
                viewModel.hidePhone.set(View.GONE);

            } else {
                viewModel.logInAwayTips.set(getString(R.string.LoginOther));
                viewModel.hideAcount.set(View.GONE);
                viewModel.hidePhone.set(View.VISIBLE);
            }
        }
        if (viewModel.logInAwayTips.get().equals(getString(R.string.LoginMobile))) {
            initAcountRemember();
        } else if (viewModel.logInAwayTips.get().equals(getString(R.string.LoginOther))) {
            initPhoneRemember();
        }

    }


    private void initAcountRemember() {
        if (userInforSharePreferencesUtils.contain("USERNAME_ACOUNT") && userInforSharePreferencesUtils.contain("USER_PASSWORD_ACOUNT")) {
            viewModel.userName.set(String.valueOf(userInforSharePreferencesUtils.getSharePreference("USERNAME_ACOUNT", "aite")));
            viewModel.passWord.set(String.valueOf(userInforSharePreferencesUtils.getSharePreference("USER_PASSWORD_ACOUNT", "aite")));
            viewModel.isRememberPassword.set(true);
        } else {
            viewModel.isRememberPassword.set(false);
        }
    }

    private void initPhoneRemember() {
        if (userInforSharePreferencesUtils.contain("USERNAME_PHONE") && userInforSharePreferencesUtils.contain("USERNAME_PHONE_CODE") && userInforSharePreferencesUtils.contain("USER_PASSWORD_PHONE")) {
            viewModel.countryCodeStr.set(String.valueOf(userInforSharePreferencesUtils.getSharePreference("USERNAME_PHONE_CODE", "0086")));
            viewModel.userPhoneEdit.set(String.valueOf(userInforSharePreferencesUtils.getSharePreference("USERNAME_PHONE", "aite")));
            viewModel.passWord.set(String.valueOf(userInforSharePreferencesUtils.getSharePreference("USER_PASSWORD_PHONE", "aite")));
            viewModel.isRememberPassword.set(true);
        } else {
            viewModel.isRememberPassword.set(false);
        }
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
