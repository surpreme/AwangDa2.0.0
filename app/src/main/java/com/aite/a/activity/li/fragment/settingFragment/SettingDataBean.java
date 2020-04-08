package com.aite.a.activity.li.fragment.settingFragment;

import com.aite.a.activity.li.mvp.ErrorBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class SettingDataBean extends ErrorBean implements Serializable {

    /**
     * member_info : {"user_name":"wly789","nickname":"dufhhgh","is_vip":null,"member_rank":"0","member_role":null,"designer_type":null,"member_mobile":"0008618588254448","avator":"https://daluxmall.com/data/upload/shop/avatar/avatar_30.png","point":"1004595","predepoit":"996829.51","freeze_predeposit":"2369.00","personal_bg":"https://daluxmall.com/data/upload/shop/adv/05044416846523667.jpg","store_id":"6","wx_member_count":0,"goods_favorites_count":3,"store_favorites_count":1,"wx_levels":[{"id":"1","level_name":"一级","ordinary_comm":"3.00","senior_comm":"4.00","privilege_comm":"5.00","status":"1"},{"id":"2","level_name":"二级","ordinary_comm":"2.00","senior_comm":"3.00","privilege_comm":"4.00","status":"1"},{"id":"3","level_name":"三级","ordinary_comm":"1.00","senior_comm":"2.00","privilege_comm":"3.00","status":"1"}],"payed_order":0,"unpay_order":0,"cancel_order":0,"ORDER_STATE_NEW":"6","ORDER_STATE_PAY":"1","ORDER_STATE_SEND":"6","ORDER_STATE_SUCCESS":"3","redpackage_count":null,"vouchercount":2,"cart_count":"1"}
     */

    private MemberInfoBean member_info;

    public MemberInfoBean getMember_info() {
        return member_info;
    }

    public void setMember_info(MemberInfoBean member_info) {
        this.member_info = member_info;
    }

    public static class MemberInfoBean {
        /**
         * user_name : wly789
         * nickname : dufhhgh
         * is_vip : null
         * member_rank : 0
         * member_role : null
         * designer_type : null
         * member_mobile : 0008618588254448
         * avator : https://daluxmall.com/data/upload/shop/avatar/avatar_30.png
         * point : 1004595
         * predepoit : 996829.51
         * freeze_predeposit : 2369.00
         * personal_bg : https://daluxmall.com/data/upload/shop/adv/05044416846523667.jpg
         * store_id : 6
         * wx_member_count : 0
         * goods_favorites_count : 3
         * store_favorites_count : 1
         * wx_levels : [{"id":"1","level_name":"一级","ordinary_comm":"3.00","senior_comm":"4.00","privilege_comm":"5.00","status":"1"},{"id":"2","level_name":"二级","ordinary_comm":"2.00","senior_comm":"3.00","privilege_comm":"4.00","status":"1"},{"id":"3","level_name":"三级","ordinary_comm":"1.00","senior_comm":"2.00","privilege_comm":"3.00","status":"1"}]
         * payed_order : 0
         * unpay_order : 0
         * cancel_order : 0
         * ORDER_STATE_NEW : 6
         * ORDER_STATE_PAY : 1
         * ORDER_STATE_SEND : 6
         * ORDER_STATE_SUCCESS : 3
         * redpackage_count : null
         * vouchercount : 2
         * cart_count : 1
         */

        private String user_name;
        private String nickname;
        private Object is_vip;
        private String member_rank;
        private Object member_role;
        private Object designer_type;
        private String member_mobile;
        private String avator;
        private String point;
        private String predepoit;
        private String freeze_predeposit;
        private String personal_bg;
        private String store_id;
        private int wx_member_count;
        private int goods_favorites_count;
        private int store_favorites_count;
        private int payed_order;
        private int unpay_order;
        private int cancel_order;
        private String ORDER_STATE_NEW;
        private String ORDER_STATE_PAY;
        private String ORDER_STATE_SEND;
        private String ORDER_STATE_SUCCESS;
        private Object redpackage_count;
        private int vouchercount;
        private String cart_count;
        private List<WxLevelsBean> wx_levels;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(Object is_vip) {
            this.is_vip = is_vip;
        }

        public String getMember_rank() {
            return member_rank;
        }

        public void setMember_rank(String member_rank) {
            this.member_rank = member_rank;
        }

        public Object getMember_role() {
            return member_role;
        }

        public void setMember_role(Object member_role) {
            this.member_role = member_role;
        }

        public Object getDesigner_type() {
            return designer_type;
        }

        public void setDesigner_type(Object designer_type) {
            this.designer_type = designer_type;
        }

        public String getMember_mobile() {
            return member_mobile;
        }

        public void setMember_mobile(String member_mobile) {
            this.member_mobile = member_mobile;
        }

        public String getAvator() {
            return avator;
        }

        public void setAvator(String avator) {
            this.avator = avator;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getPredepoit() {
            return predepoit;
        }

        public void setPredepoit(String predepoit) {
            this.predepoit = predepoit;
        }

        public String getFreeze_predeposit() {
            return freeze_predeposit;
        }

        public void setFreeze_predeposit(String freeze_predeposit) {
            this.freeze_predeposit = freeze_predeposit;
        }

        public String getPersonal_bg() {
            return personal_bg;
        }

        public void setPersonal_bg(String personal_bg) {
            this.personal_bg = personal_bg;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public int getWx_member_count() {
            return wx_member_count;
        }

        public void setWx_member_count(int wx_member_count) {
            this.wx_member_count = wx_member_count;
        }

        public int getGoods_favorites_count() {
            return goods_favorites_count;
        }

        public void setGoods_favorites_count(int goods_favorites_count) {
            this.goods_favorites_count = goods_favorites_count;
        }

        public int getStore_favorites_count() {
            return store_favorites_count;
        }

        public void setStore_favorites_count(int store_favorites_count) {
            this.store_favorites_count = store_favorites_count;
        }

        public int getPayed_order() {
            return payed_order;
        }

        public void setPayed_order(int payed_order) {
            this.payed_order = payed_order;
        }

        public int getUnpay_order() {
            return unpay_order;
        }

        public void setUnpay_order(int unpay_order) {
            this.unpay_order = unpay_order;
        }

        public int getCancel_order() {
            return cancel_order;
        }

        public void setCancel_order(int cancel_order) {
            this.cancel_order = cancel_order;
        }

        public String getORDER_STATE_NEW() {
            return ORDER_STATE_NEW;
        }

        public void setORDER_STATE_NEW(String ORDER_STATE_NEW) {
            this.ORDER_STATE_NEW = ORDER_STATE_NEW;
        }

        public String getORDER_STATE_PAY() {
            return ORDER_STATE_PAY;
        }

        public void setORDER_STATE_PAY(String ORDER_STATE_PAY) {
            this.ORDER_STATE_PAY = ORDER_STATE_PAY;
        }

        public String getORDER_STATE_SEND() {
            return ORDER_STATE_SEND;
        }

        public void setORDER_STATE_SEND(String ORDER_STATE_SEND) {
            this.ORDER_STATE_SEND = ORDER_STATE_SEND;
        }

        public String getORDER_STATE_SUCCESS() {
            return ORDER_STATE_SUCCESS;
        }

        public void setORDER_STATE_SUCCESS(String ORDER_STATE_SUCCESS) {
            this.ORDER_STATE_SUCCESS = ORDER_STATE_SUCCESS;
        }

        public Object getRedpackage_count() {
            return redpackage_count;
        }

        public void setRedpackage_count(Object redpackage_count) {
            this.redpackage_count = redpackage_count;
        }

        public int getVouchercount() {
            return vouchercount;
        }

        public void setVouchercount(int vouchercount) {
            this.vouchercount = vouchercount;
        }

        public String getCart_count() {
            return cart_count;
        }

        public void setCart_count(String cart_count) {
            this.cart_count = cart_count;
        }

        public List<WxLevelsBean> getWx_levels() {
            return wx_levels;
        }

        public void setWx_levels(List<WxLevelsBean> wx_levels) {
            this.wx_levels = wx_levels;
        }

        public static class WxLevelsBean {
            /**
             * id : 1
             * level_name : 一级
             * ordinary_comm : 3.00
             * senior_comm : 4.00
             * privilege_comm : 5.00
             * status : 1
             */

            private String id;
            private String level_name;
            private String ordinary_comm;
            private String senior_comm;
            private String privilege_comm;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLevel_name() {
                return level_name;
            }

            public void setLevel_name(String level_name) {
                this.level_name = level_name;
            }

            public String getOrdinary_comm() {
                return ordinary_comm;
            }

            public void setOrdinary_comm(String ordinary_comm) {
                this.ordinary_comm = ordinary_comm;
            }

            public String getSenior_comm() {
                return senior_comm;
            }

            public void setSenior_comm(String senior_comm) {
                this.senior_comm = senior_comm;
            }

            public String getPrivilege_comm() {
                return privilege_comm;
            }

            public void setPrivilege_comm(String privilege_comm) {
                this.privilege_comm = privilege_comm;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
