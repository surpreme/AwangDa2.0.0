package com.valy.baselibrary.webJs;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-25
 * @desc:
 */
public class JsInterface {
    private WebView webView;
    private Activity activity;

    public JsInterface(WebView webView, Activity activity) {
        this.webView = webView;
        this.activity = activity;
    }

    /**
     * 返回
     */
    @JavascriptInterface
    public void AppGoBack() {
        activity.runOnUiThread(() -> {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                activity.finish();
            }
        });

    }

    @JavascriptInterface
    public void go() {
        activity.runOnUiThread(() -> {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                activity.finish();
            }
        });

    }

    @JavascriptInterface
    public void AppToHome(int i) {
        activity.runOnUiThread(() -> {
            Intent intent = new Intent();
            switch (i) {
                case 1:
                    intent.setClassName(activity, "com.aite.a.activity.li.activity.mainer.MainerActivity");
                    break;
                case 2:
                    intent.setClassName(activity, "com.aite.a.activity.li.activity.ChoiceActivity");

                    break;
                default:
                    intent.setClassName(activity, "com.aite.a.activity.li.activity.mainer.MainerActivity");
                    break;
            }
            activity.startActivity(intent);
        });

    }

    @JavascriptInterface
    public void home() {
        activity.runOnUiThread(() -> {
            Intent intent = new Intent();
            intent.setClassName(activity, "com.aite.a.activity.li.activity.mainer.MainerActivity");
            activity.startActivity(intent);

        });

    }

    @JavascriptInterface
    public void details() {
        activity.runOnUiThread(() -> {
            Intent intent = new Intent();
            intent.setClassName(activity, "com.aite.a.activity.BuyerOrderFgActivity");
            activity.startActivity(intent);

        });

    }
}

