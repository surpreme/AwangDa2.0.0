package com.valy.baselibrary.webJs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Auther: liziyang
 * @datetime: 2019-12-25
 * @desc:
 */
public class RerashWebView {
    @SuppressLint("SetJavaScriptEnabled")
    public static void initWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 缩放操作
        webSettings.setSupportZoom(true); // 支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); // 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); // 隐藏原生的缩放控件
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("温馨提示")
                        .setMessage(message)
                        .setPositiveButton("确定", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setNeutralButton("取消", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        });
                builder.setOnCancelListener(new AlertDialog.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        result.cancel();
                    }
                });

                // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
                builder.setOnKeyListener(new AlertDialog.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
                        return true;
                    }
                });
                // 禁止响应按back键的事件
                // builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final android.webkit.JsResult result) {
                Log.i("------------------", "提示   " + message);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("温馨提示").setMessage(message).setPositiveButton("确定", null);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                result.confirm();
                return true;
            }

        });
    }

}
