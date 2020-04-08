package com.aite.awangdalibrary.ui.activity.findpassword;
import com.valy.baselibrary.bean.ToolBarBean;

/**
 * @Auther: valy
 * @datetime: 2020/3/11
 * @desc:
 */
public class FindPassWordUIBean extends ToolBarBean {
    private String title;
    private String editHint;
    private String outStr;
    private String bottomStr;
    private String phoneCode;

    private String phoneTitle;
    private String phoneEditHint;
    private String phoneEditOutStr;
    public String getPhoneTitle() {
        return phoneTitle;
    }

    public void setPhoneTitle(String phoneTitle) {
        this.phoneTitle = phoneTitle;
    }

    public String getPhoneEditHint() {
        return phoneEditHint;
    }

    public void setPhoneEditHint(String phoneEditHint) {
        this.phoneEditHint = phoneEditHint;
    }

    public String getPhoneEditOutStr() {
        return phoneEditOutStr;
    }

    public void setPhoneEditOutStr(String phoneEditOutStr) {
        this.phoneEditOutStr = phoneEditOutStr;
    }


    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getBottomStr() {
        return bottomStr;
    }

    public void setBottomStr(String bottomStr) {
        this.bottomStr = bottomStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditHint() {
        return editHint;
    }

    public void setEditHint(String editHint) {
        this.editHint = editHint;
    }

    public String getOutStr() {
        return outStr;
    }

    public void setOutStr(String outStr) {
        this.outStr = outStr;
    }
}
