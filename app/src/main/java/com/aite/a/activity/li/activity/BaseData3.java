package com.aite.a.activity.li.activity;

import com.aite.a.activity.li.mvp.ErrorBean;

import java.io.Serializable;


/**
 * @author:TQX
 * @Date: 2019/4/15
 * @description:
 */
public class BaseData3<T extends ErrorBean> implements Serializable {


    /**
     * error_code : 10003
     * message : 用户名密码错误
     * datas :
     */
    private Object error_code;
    private T datas;
    private boolean isSuccessed;

    public boolean isSuccessed() {
        return !getError_code().toString().equals("0");
    }

    public void setSuccessed(boolean successed) {
        isSuccessed = successed;
    }


    private String errorMsg;


    public void setErrorMsg(String msg) {
        this.errorMsg = msg;
    }

    public Object getError_code() {
        return error_code;
    }

    public void setError_code(Object error_code) {
        this.error_code = error_code;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }


}
