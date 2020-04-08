package com.aite.a.bean;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/2
 * @desc:
 */
public class ShopChatBean {

    /**
     * type_name : 售前客服
     * callcenter_list : [{"name":"售前2","type":"im","num":"14","img":"https://daluxmall.com/data/resource/img/type3.png"}]
     */

    private String type_name;
    private List<CallcenterListBean> callcenter_list;
    private boolean isSelected;
    private int cuurentNum = 0;

    public int getCuurentNum() {
        return cuurentNum;
    }

    public void setCuurentNum(int cuurentNum) {
        this.cuurentNum = cuurentNum;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<CallcenterListBean> getCallcenter_list() {
        return callcenter_list;
    }

    public void setCallcenter_list(List<CallcenterListBean> callcenter_list) {
        this.callcenter_list = callcenter_list;
    }

    public static class CallcenterListBean {
        /**
         * name : 售前2
         * type : im
         * num : 14
         * img : https://daluxmall.com/data/resource/img/type3.png
         */

        private String name;
        private String type;
        private String num;
        private String img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
