package com.aite.a.bean;
import com.valy.baselibrary.bean.ToolBarBean;

/**
 * @Auther: valy
 * @datetime: 2020/3/4
 * @desc:
 */
public class SettingFirstBean extends ToolBarBean {
    private int imgId;
    private String title;
    private String bottomStr;

    public String getBottomStr() {
        return bottomStr;
    }

    public void setBottomStr(String bottomStr) {
        this.bottomStr = bottomStr;
    }


    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
