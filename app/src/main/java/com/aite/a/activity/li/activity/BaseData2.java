package com.aite.a.activity.li.activity;

import com.aite.a.activity.li.mvp.ErrorBean;

import java.io.Serializable;
import java.util.List;


/**
 * @author:TQX
 * @Date: 2019/4/15
 * @description:
 */
public class BaseData2<T extends ErrorBean> implements Serializable {


    /**
     * code : 200
     * datas :[]
     * lang_type:en
     */
    private Object code;
    private List<T> datas;
    private boolean isSuccessed;
    private String lang_type;

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


    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public String getLang_type() {
        return lang_type;
    }

    public void setLang_type(String lang_type) {
        this.lang_type = lang_type;
    }
}
