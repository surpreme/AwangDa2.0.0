package com.aite.awangdalibrary.ui.fragment;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/13
 * @desc:
 */
public class MemberCenterUIBean {
    private String tips;
    private InformationBean informationBean;
    private List<OrderBean> orderBean;
    private List<MemberBean> memberBean;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<OrderBean> getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(List<OrderBean> orderBean) {
        this.orderBean = orderBean;
    }

    public List<MemberBean> getMemberBean() {
        return memberBean;
    }

    public void setMemberBean(List<MemberBean> memberBean) {
        this.memberBean = memberBean;
    }



    public InformationBean getInformationBean() {
        return informationBean;
    }

    public void setInformationBean(InformationBean informationBean) {
        this.informationBean = informationBean;
    }




    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public static class InformationBean {
        private String iconUrl;
        private String name;
        private String balance;

        private String integral;
        private String gold;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }


    }

    public static class OrderBean {
        private int iconId;
        private String name;

        public int getIconId() {
            return iconId;
        }

        public void setIconId(int iconId) {
            this.iconId = iconId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class MemberBean {
        private int iconId;
        private String name;

        public int getIconId() {
            return iconId;
        }

        public void setIconId(int iconId) {
            this.iconId = iconId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
