package com.aite.awangdalibrary.ui.activity.transfer;

import com.valy.baselibrary.bean.ToolBarBean;

/**
 * @Auther: valy
 * @datetime: 2020/3/13
 * @desc:
 */
public class TransferUIBean extends ToolBarBean {
    private InformationBean informationBean;
    private String tips;
    private OutBean outBean;
    private ChocieTypeBean chocieTypeBean;
    private PasswordBean passwordBean;
    private PhoneUiBean phoneUiBean;

    public PhoneUiBean getPhoneUiBean() {
        return phoneUiBean;
    }

    public void setPhoneUiBean(PhoneUiBean phoneUiBean) {
        this.phoneUiBean = phoneUiBean;
    }

    public PasswordBean getPasswordBean() {
        return passwordBean;
    }

    public void setPasswordBean(PasswordBean passwordBean) {
        this.passwordBean = passwordBean;
    }


    public ChocieTypeBean getChocieTypeBean() {
        return chocieTypeBean;
    }

    public void setChocieTypeBean(ChocieTypeBean chocieTypeBean) {
        this.chocieTypeBean = chocieTypeBean;
    }


    public OutBean getOutBean() {
        return outBean;
    }

    public void setOutBean(OutBean outBean) {
        this.outBean = outBean;
    }


    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public InformationBean getInformationBean() {
        return informationBean;
    }

    public void setInformationBean(InformationBean informationBean) {
        this.informationBean = informationBean;
    }

    public static class PhoneUiBean {
        private String title;
        private String phone;
        private String sendText;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSendText() {
            return sendText;
        }

        public void setSendText(String sendText) {
            this.sendText = sendText;
        }

    }

    public static class ChocieTypeBean {
        private int type;
        private String title;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


    }

    public static class PasswordBean {
        private String title;
        private String hint;
        private String outStr;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getOutStr() {
            return outStr;
        }

        public void setOutStr(String outStr) {
            this.outStr = outStr;
        }
    }

    public static class OutBean {
        private String title;
        private String hint;
        private String outStr;

        public String getOutStr() {
            return outStr;
        }

        public void setOutStr(String outStr) {
            this.outStr = outStr;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }
    }

    public static class InformationBean {
        private String balance;
        private String gold;
        private String integral;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

    }
}
