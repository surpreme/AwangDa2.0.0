package com.aite.a.activity.li.activity.addaddress;

import com.valy.baselibrary.bean.ToolBarBean;

/**
 * @Auther: valy
 * @datetime: 2020/3/10
 * @desc:
 */
public class AddAddressUIBean extends ToolBarBean {
    private String hintTitle;
    private String hintContent;
    private String bottomStr;
    private String outStr;
    private ClickLocationBean clickLocationBean;
    private PhoneAddressChoiceBean phoneAddressChoiceBean;
    private CityChoiceBean cityChoiceBean;

    public CityChoiceBean getCityChoiceBean() {
        return cityChoiceBean;
    }

    public void setCityChoiceBean(CityChoiceBean cityChoiceBean) {
        this.cityChoiceBean = cityChoiceBean;
    }


    public PhoneAddressChoiceBean getPhoneAddressChoiceBean() {
        return phoneAddressChoiceBean;
    }

    public void setPhoneAddressChoiceBean(PhoneAddressChoiceBean phoneAddressChoiceBean) {
        this.phoneAddressChoiceBean = phoneAddressChoiceBean;
    }


    public ClickLocationBean getClickLocationBean() {
        return clickLocationBean;
    }

    public void setClickLocationBean(ClickLocationBean clickLocationBean) {
        this.clickLocationBean = clickLocationBean;
    }


    public String getOutStr() {
        return outStr;
    }

    public void setOutStr(String outStr) {
        this.outStr = outStr;
    }


    public String getHintTitle() {
        return hintTitle;
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

    public static class CityChoiceBean {
        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }

    public static class PhoneAddressChoiceBean {
        private String title;
        private String content;
        private String code;
        private String outStr;
        private String area_code;
        private String icon;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }


        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOutStr() {
            return outStr;
        }

        public void setOutStr(String outStr) {
            this.outStr = outStr;
        }


    }

    public static class ClickLocationBean {
        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }
}
