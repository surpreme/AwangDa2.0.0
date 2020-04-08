package com.aite.a.activity.li.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.valy.baselibrary.webJs.JsInterface;
import com.valy.baselibrary.webJs.RerashWebView;
import com.jiananshop.a.R;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-04
 * @desc:
 * @ Such as Down of Simple ，u can used start this activity for bundle or putExtra,and u can extends this activity to make your thinkers
 * Intent intent = new Intent();
 * intent.putExtra("title", mMineRuralPushListBean.get(position).getTheme_name());
 * intent.putExtra("webViewUrl", String.format("%scomefrom=app", mMineRuralPushListBean.get(position).getH5_url()));
 * intent.putExtra("isHideToolBar", "false");
 * intent.setAction("com.lzy.base.BaseWebViewActivity");
 * startActivity(intent);
 * <p>
 * <p>
 * ** @title
 * @ u need made isHideToolBar to false ,that it's title of toolbar
 * ** @webViewUrl
 * @ it's your want to show web pages's url
 * ** @isHideToolBar
 * @ it's because most web page possible add web toolbar ,there given a choice to used ,and don't put title with some things,because it's more things
 * <p>
 * <p>
 * !!!!!!!!!please add this Activity to your app or module of AndroidManifest.xml !!!!!!!!!!
 * if u want to extends this activity ,u can don't add this Activity name to file of your AndroidManifest.xml
 */
public class BaseWebViewActivity extends BaseActivity {
    protected WebView webView;
    protected ConstraintLayout toolbar_ll;

    @Override
    protected int getLayoutResId() {
        return R.layout.base_web_activity;
    }

    @Override
    protected void initView() {
        initWebView();
        StatusBarUtils.setColor(context, getResources().getColor(R.color.white));
        if (getIntent().getStringExtra("title") != null)
            initToolbar(getIntent().getStringExtra("title"));
        else {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                if (bundle.getString("title") != null) {
                    if (isStringEmpty(bundle.getString("title"))) initToolbar("网页详情");
                    else
                        initToolbar(bundle.getString("title"));
                }
            } else {
                initToolbar("网页详情");
            }
        }
        if (getIntent().getStringExtra("loadDataWithBaseURL")!=null){
            webView.loadDataWithBaseURL(null, getIntent().getStringExtra("loadDataWithBaseURL"), "text/html", "utf-8", null);
            RerashWebView.initWebView(webView);
            webView.addJavascriptInterface(new JsInterface(webView, this), "AndroidWebView");
            webView.setWebViewClient(new WebViewClient());

        }
        if (getIntent().getStringExtra("webViewUrl") != null) {
            webView.loadUrl(getIntent().getStringExtra("webViewUrl"));
            RerashWebView.initWebView(webView);
            /**
             *("webViewUrl", "https://api.instagram.com/oauth/authorize/?client_id=448547546064554&redirect_uri=https://daluxmall.com/account/inslogin_redirect.php&scope=user_profile,user_media&response_type=code");
             * 对ins跳转网页的截获返回的网址  截取code 然后通过code获取token
             * contains 包含 startsWith 开头包含
             */
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    LogUtils.e(request.getUrl().toString());
                    if (request.getUrl() != null && request.getUrl().toString().startsWith("https://daluxmall.com/account/inslogin_redirect.php")) {
                        String codeResult = request.getUrl().getQueryParameter("code");
                        LogUtils.d(codeResult);
                        if (codeResult != null) {
                            Intent intent = new Intent();
                            intent.putExtra("INSTAGRAM_CODE", codeResult);
                            intent.setClass(BaseWebViewActivity.this, LogInActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
            webView.addJavascriptInterface(new JsInterface(webView, this), "AndroidWebView");
        } else {
            Bundle webviewUrlBundle = getIntent().getExtras();
            if (webviewUrlBundle != null) {
                if (webviewUrlBundle.getString("webViewUrl") != null) {
                    webView.loadUrl(getIntent().getStringExtra("webViewUrl"));
                    webView.setWebViewClient(new WebViewClient());
                    RerashWebView.initWebView(webView);
                    webView.addJavascriptInterface(new JsInterface(webView, this), "AndroidWebView");
                }
            }
        }
        if (getIntent().getStringExtra("isHideToolBar") != null) {
            if (getIntent().getStringExtra("isHideToolBar").equals("true")) {
                toolbar_ll.setVisibility(View.GONE);
            } else if (getIntent().getStringExtra("isHideToolBar").equals("false")) {
                toolbar_ll.setVisibility(View.VISIBLE);
            } else {
                toolbar_ll.setVisibility(View.VISIBLE);
            }
        } else {
            Bundle isShowToolBarBundle = getIntent().getExtras();
            if (isShowToolBarBundle != null) {
                if (isShowToolBarBundle.getString("isHideToolBar").equals("true")) {
                    toolbar_ll.setVisibility(View.GONE);

                } else if (isShowToolBarBundle.getString("isHideToolBar").equals("false")) {
                    toolbar_ll.setVisibility(View.VISIBLE);

                } else {
                    toolbar_ll.setVisibility(View.VISIBLE);
                }
            } else {
                toolbar_ll.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    protected void initModel() {

    }

    protected boolean isStringEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private void initWebView() {
        webView = findViewById(R.id.webview);
        toolbar_ll = findViewById(R.id.toolbar_ll);
    }


}
