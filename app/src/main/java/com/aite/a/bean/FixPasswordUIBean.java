package com.aite.a.bean;
import com.valy.baselibrary.bean.ToolBarBean;

/**
 * @Auther: valy
 * @datetime: 2020/3/4
 * @desc:
 */
public class FixPasswordUIBean extends ToolBarBean {
    private String hintTitle;
    private String hintContent;
    private String bottomStr;
    private String outStr;

    public String getHintTitle() {
        return hintTitle;
    }

    public String getOutStr() {
        return outStr;
    }

    public void setOutStr(String outStr) {
        this.outStr = outStr;
    }

    public void setHintTitle(String hintTitle) {
        this.hintTitle = hintTitle;
    }

    public String getHintContent() {
        return hintContent;
    }

    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }


    public String getBottomStr() {
        return bottomStr;
    }

    public void setBottomStr(String bottomStr) {
        this.bottomStr = bottomStr;
    }


}
