package com.aite.a.bean;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020-02-27
 * @desc:
 */
public class PayListBean {


    /**
     * payment : [{"member_info":{"available_predeposit":"18713.88"},"payment_recommend":"1","payment_id":"7","payment_code":"predeposit","payment_name":"零钱包","handling_fee":"0","payment_image":"https://daluxmall.com/mall/templates/default/images/payment/predeposit_logo.png"},{"payment_recommend":"0","payment_id":"8","payment_code":"transfer","payment_name":"线下支付","handling_fee":"0","payment_image":"https://daluxmall.com/mall/templates/default/images/payment/transfer_logo.png"},{"payment_recommend":"0","payment_id":"9","payment_code":"pipay","payment_name":"pipay","handling_fee":"2","payment_image":"https://daluxmall.com/mall/templates/default/images/payment/pipay_logo.png"}]
     * info : {"pd_count":"18713.88","order_amount":10.86,"pay_sn":"750636147680356015"}
     */

    private InfoBean info;
    private List<PaymentBean> payment;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<PaymentBean> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentBean> payment) {
        this.payment = payment;
    }

    public static class InfoBean {
        /**
         * pd_count : 18713.88
         * order_amount : 10.86
         * pay_sn : 750636147680356015
         */

        private String pd_count;
        private String order_amount;
        private String pay_sn;

        public String getPd_count() {
            return pd_count;
        }

        public void setPd_count(String pd_count) {
            this.pd_count = pd_count;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }
    }

    public static class PaymentBean {
        /**
         * member_info : {"available_predeposit":"18713.88"}
         * payment_recommend : 1
         * payment_id : 7
         * payment_code : predeposit
         * payment_name : 零钱包
         * handling_fee : 0
         * payment_image : https://daluxmall.com/mall/templates/default/images/payment/predeposit_logo.png
         */

        private MemberInfoBean member_info;
        private String payment_recommend;
        private String payment_id;
        private String payment_code;
        private String payment_name;
        private String handling_fee;
        private String payment_image;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public MemberInfoBean getMember_info() {
            return member_info;
        }

        public void setMember_info(MemberInfoBean member_info) {
            this.member_info = member_info;
        }

        public String getPayment_recommend() {
            return payment_recommend;
        }

        public void setPayment_recommend(String payment_recommend) {
            this.payment_recommend = payment_recommend;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public String getHandling_fee() {
            return handling_fee;
        }

        public void setHandling_fee(String handling_fee) {
            this.handling_fee = handling_fee;
        }

        public String getPayment_image() {
            return payment_image;
        }

        public void setPayment_image(String payment_image) {
            this.payment_image = payment_image;
        }

        public static class MemberInfoBean {
            /**
             * available_predeposit : 18713.88
             */

            private String available_predeposit;

            public String getAvailable_predeposit() {
                return available_predeposit;
            }

            public void setAvailable_predeposit(String available_predeposit) {
                this.available_predeposit = available_predeposit;
            }
        }
    }
}
