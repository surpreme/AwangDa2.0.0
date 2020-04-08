package com.aite.a.activity.li.mvp;

import com.aite.a.APPSingleton;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.valy.baselibrary.retrofit.InterceptorUtil;
import com.valy.baselibrary.retrofit.SSLSocketClient;

import java.net.NoRouteToHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ins ----11111
 */
public class RetrofitClient2 {
    private static final String API_HOST = "https://api.instagram.com/";
    private static RetrofitClient2 instance;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private RetrofitClient2() {

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(APPSingleton.getContext()));

        try {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .cookieJar(cookieJar)
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .addInterceptor(InterceptorUtil.logInterceptor())
                    .addInterceptor(InterceptorUtil.headerInterceptor())
                    .build();
        } catch (NoRouteToHostException e) {
            e.printStackTrace();
        }


        retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())   // 使用 gson 解析器解析 json
                .client(okHttpClient)
                .build();
    }

//    private RetrofitClient(boolean isUseMineBaseUrl, String url) {
//
//        ClearableCookieJar cookieJar =
//                new PersistentCookieJar(new SetCookieCache(),
//                        new SharedPrefsCookiePersistor(APPSingleton.getContext()));
//
//        try {
//            okHttpClient = new OkHttpClient.Builder()
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(15, TimeUnit.SECONDS)
//                    .cookieJar(cookieJar)
//                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
//                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//                    .addInterceptor(InterceptorUtil.logInterceptor())
//                    .addInterceptor(InterceptorUtil.headerInterceptor())
//                    .build();
//        } catch (NoRouteToHostException e) {
//            e.printStackTrace();
//        }
//
//        if (isUseMineBaseUrl&&url!=null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(url)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())   // 使用 gson 解析器解析 json
//                    .client(okHttpClient)
//                    .build();
//        } else {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(API_HOST)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())   // 使用 gson 解析器解析 json
//                    .client(okHttpClient)
//                    .build();
//        }
//
//    }


    public static RetrofitClient2 getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient2.class) {
                if (instance == null) {
                    instance = new RetrofitClient2();
                }
            }
        }
        return instance;
    }

//    public static RetrofitClient getInstance(boolean isUseMineBaseUrl, String url) {
//        if (instance == null) {
//            synchronized (RetrofitClient.class) {
//                if (instance == null) {
//                    instance = new RetrofitClient(isUseMineBaseUrl, url);
//                }
//            }
//        }
//        return instance;
//    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}