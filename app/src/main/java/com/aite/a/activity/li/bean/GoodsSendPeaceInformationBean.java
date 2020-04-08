package com.aite.a.activity.li.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020-02-21
 * @desc:
 */
public class GoodsSendPeaceInformationBean {


    /**
     * state : success
     * allow_offpay : 0
     * content : {"3":"1.00"}
     * delivery : {"3":2.09}
     * allow_offpay_batch : {"3":""}
     * number_days : {"3":0}
     * offpay_hash : 8CVCo_g64AYnwLF8MV3KkXFcpfTZgTZldMbJr8m
     * offpay_hash_batch : w4BNiLugj85Kw_nzueXR8zbSYBxSA75F5Hs7Wb9g_iyuVVcxcYl
     * stores : [{"store_id":3,"content":"1.00","delivery":2.09,"allow_offpay_batch":"","number_days":0}]
     */

    private String state;
    private int allow_offpay;
    private ContentBean content;
    private DeliveryBean delivery;
    private AllowOffpayBatchBean allow_offpay_batch;
    private NumberDaysBean number_days;
    private String offpay_hash;
    private String offpay_hash_batch;
    private List<StoresBean> stores;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAllow_offpay() {
        return allow_offpay;
    }

    public void setAllow_offpay(int allow_offpay) {
        this.allow_offpay = allow_offpay;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public DeliveryBean getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryBean delivery) {
        this.delivery = delivery;
    }

    public AllowOffpayBatchBean getAllow_offpay_batch() {
        return allow_offpay_batch;
    }

    public void setAllow_offpay_batch(AllowOffpayBatchBean allow_offpay_batch) {
        this.allow_offpay_batch = allow_offpay_batch;
    }

    public NumberDaysBean getNumber_days() {
        return number_days;
    }

    public void setNumber_days(NumberDaysBean number_days) {
        this.number_days = number_days;
    }

    public String getOffpay_hash() {
        return offpay_hash;
    }

    public void setOffpay_hash(String offpay_hash) {
        this.offpay_hash = offpay_hash;
    }

    public String getOffpay_hash_batch() {
        return offpay_hash_batch;
    }

    public void setOffpay_hash_batch(String offpay_hash_batch) {
        this.offpay_hash_batch = offpay_hash_batch;
    }

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class ContentBean {
        /**
         * 3 : 1.00
         */

        @SerializedName("3")
        private String _$3;

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }
    }

    public static class DeliveryBean {
        /**
         * 3 : 2.09
         */

        @SerializedName("3")
        private double _$3;

        public double get_$3() {
            return _$3;
        }

        public void set_$3(double _$3) {
            this._$3 = _$3;
        }
    }

    public static class AllowOffpayBatchBean {
        /**
         * 3 :
         */

        @SerializedName("3")
        private String _$3;

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }
    }

    public static class NumberDaysBean {
        /**
         * 3 : 0
         */

        @SerializedName("3")
        private int _$3;

        public int get_$3() {
            return _$3;
        }

        public void set_$3(int _$3) {
            this._$3 = _$3;
        }
    }

    public static class StoresBean {
        /**
         * store_id : 3
         * content : 1.00
         * delivery : 2.09
         * allow_offpay_batch :
         * number_days : 0
         */

        private int store_id;
        private String content;
        private String delivery;
        private String allow_offpay_batch;
        private String number_days;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String getAllow_offpay_batch() {
            return allow_offpay_batch;
        }

        public void setAllow_offpay_batch(String allow_offpay_batch) {
            this.allow_offpay_batch = allow_offpay_batch;
        }

        public String getNumber_days() {
            return number_days;
        }

        public void setNumber_days(String number_days) {
            this.number_days = number_days;
        }
    }
}
