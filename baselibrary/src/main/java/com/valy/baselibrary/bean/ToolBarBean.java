package com.valy.baselibrary.bean;

/**
 * @Auther: valy
 * @datetime: 2020/3/5
 * @desc:
 */
public class ToolBarBean {
    private String centerBarTitle;
    private String rightBarTitle;
    private String rightBarTitleColor;//这里为了精确只使用色码 不使用color 的id

    public String getRightBarTitleColor() {
        return rightBarTitleColor;
    }

    public void setRightBarTitleColor(String rightBarTitleColor) {
        this.rightBarTitleColor = rightBarTitleColor;
    }


    public String getCenterBarTitle() {
        return centerBarTitle;
    }

    public void setCenterBarTitle(String centerBarTitle) {
        this.centerBarTitle = centerBarTitle;
    }

    public String getRightBarTitle() {
        return rightBarTitle;
    }

    public void setRightBarTitle(String rightBarTitle) {
        this.rightBarTitle = rightBarTitle;
    }
}
