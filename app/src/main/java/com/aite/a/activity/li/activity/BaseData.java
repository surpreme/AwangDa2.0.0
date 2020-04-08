package com.aite.a.activity.li.activity;

import com.aite.a.activity.li.mvp.ErrorBean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * @author:TQX
 * @Date: 2019/4/15
 * @description:
 */
public class BaseData<T extends ErrorBean> implements Serializable {


    /**
     * error_code : 10003
     * message : 用户名密码错误
     * datas :
     */
    private Object code;
    private T datas;
    private boolean isSuccessed;
    private String login;
    private String token_expired;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken_expired() {
        return token_expired;
    }

    public void setToken_expired(String token_expired) {
        this.token_expired = token_expired;
    }

    public boolean isSuccessed() {
        return !getCode().toString().equals("200");
    }

    public void setSuccessed(boolean successed) {
        isSuccessed = successed;
    }


    private String errorMsg;


    public void setErrorMsg(String msg) {
        this.errorMsg = msg;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }


}
