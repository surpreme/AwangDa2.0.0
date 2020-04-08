package com.aite.awangdalibrary.ui.activity.coupon;

import com.valy.baselibrary.bean.ToolBarBean;

/**
 * @Auther: valy
 * @datetime: 2020/3/12
 * @desc:
 */
public class CouponUIBean extends ToolBarBean {
    private String couponTitle;
    private String overringTv;

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getOverringTv() {
        return overringTv;
    }

    public void setOverringTv(String overringTv) {
        this.overringTv = overringTv;
    }
}
