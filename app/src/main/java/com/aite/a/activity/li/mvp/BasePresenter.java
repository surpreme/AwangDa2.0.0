package com.aite.a.activity.li.mvp;

/**
 * liziyang
 * 2020/01/09
 */

public interface  BasePresenter <V extends BaseView>{
    void attachView(V view);

    void detachView();
}
