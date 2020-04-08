package com.aite.a.fargment.orderitemkotlin;

import java.util.List;

/**
 * 订单实体类
 *
 * @author CAOYOU
 */
public class OrderItemBean {


    /**
     * order_list : [{"order_id":"775","order_sn":"3000000000076401","pay_sn":"150638712108664119","store_id":"4","store_name":"tgShop","buyer_id":"119","buyer_name":"abc","buyer_email":"","add_time":"1585368108","payment_code":"online","payment_time":"0","finnshed_time":"0","goods_amount":"333.33","order_amount":"351.00","shipping_fee":"10.00","shipping_code":"","order_state":"0","order_type":"1","lock_state":"0","evaluation_state":"0","delete_state":"0","refund_state":"0","delay_time":"0","state_desc":"Canceled","refund_desc":"No refund","payment_name":"Online payment","extend_order_common":{"order_id":"775","store_id":"4","shipping_time":"0","shipping_express_id":"0","evaluation_time":"0","evalseller_state":"0","evalseller_time":"0","order_message":"","order_pointscount":"0","has_refund_points":"0","voucher_price":null,"voucher_code":null,"platform_voucher_price":null,"platform_voucher_code":null,"deliver_explain":null,"daddress_id":"0","reciver_name":"jiaming","reciver_info":{"phone":"+8616574488587,16574488587","mob_phone":"0008616574488587","tel_phone":"16574488587","address":"柬埔寨金边马卡拉区 Tuel Net Rd, 柬埔寨","area":"柬埔寨金边马卡拉区","street":"Tuel Net Rd, 柬埔寨","points":"105.041418,11.4719001","country_id":85917,"province_id":85918,"city_id":85919,"area_id":85917},"reciver_province_id":"85918","reciver_city_id":"85919","invoice_info":[],"promotion_info":"","dlyo_pickup_code":null,"reciver_area_id":"0","citysend_info":" ","reciver_country_id":"85917","evalseller_country_id":"85763","reciver_points":""},"extend_order_goods":[{"rec_id":"799","order_id":"775","goods_id":"515","goods_commonid":"378","goods_name":"Vaseline","goods_price":"333.33","goods_costprice":"0.00","goods_num":"1","goods_image":"2020/03/19/4_06379474088411081_240.jpg","goods_pay_price":"333.33","store_id":"4","buyer_id":"119","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"23","start_time":"0","end_time":"0","dates":null,"days":"0","price_items":null,"hotel_msg":null,"store_points_offset":"0","store_points_offset_amount":"0.00","platform_points_offset":"0","platform_points_offset_amount":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"exchange_rate":"30.0000","goods_spec_bak":"","goods_spec":"","refund":0,"goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/4/2020/03/19/4_06379474088411081_240.jpg"}],"if_cancel":false,"if_refund_cancel":false,"if_complain":false,"if_receive":false,"if_lock":false,"if_deliver":false,"if_evaluation":false,"if_delete":true,"if_drop":false,"if_restore":false,"write_off_code":""}]
     * add_time : 1585368108
     * order_sn : 3000000000076401
     * pay_sn : 150638712108664119
     */

    private String add_time;
    private String order_sn;
    private String pay_sn;
    private List<OrderListBean> order_list;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getPay_sn() {
        return pay_sn;
    }

    public void setPay_sn(String pay_sn) {
        this.pay_sn = pay_sn;
    }

    public List<OrderListBean> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderListBean> order_list) {
        this.order_list = order_list;
    }

    public static class OrderListBean {
        /**
         * order_id : 775
         * order_sn : 3000000000076401
         * pay_sn : 150638712108664119
         * store_id : 4
         * store_name : tgShop
         * buyer_id : 119
         * buyer_name : abc
         * buyer_email :
         * add_time : 1585368108
         * payment_code : online
         * payment_time : 0
         * finnshed_time : 0
         * goods_amount : 333.33
         * order_amount : 351.00
         * shipping_fee : 10.00
         * shipping_code :
         * order_state : 0
         * order_type : 1
         * lock_state : 0
         * evaluation_state : 0
         * delete_state : 0
         * refund_state : 0
         * delay_time : 0
         * state_desc : Canceled
         * refund_desc : No refund
         * payment_name : Online payment
         * extend_order_common : {"order_id":"775","store_id":"4","shipping_time":"0","shipping_express_id":"0","evaluation_time":"0","evalseller_state":"0","evalseller_time":"0","order_message":"","order_pointscount":"0","has_refund_points":"0","voucher_price":null,"voucher_code":null,"platform_voucher_price":null,"platform_voucher_code":null,"deliver_explain":null,"daddress_id":"0","reciver_name":"jiaming","reciver_info":{"phone":"+8616574488587,16574488587","mob_phone":"0008616574488587","tel_phone":"16574488587","address":"柬埔寨金边马卡拉区 Tuel Net Rd, 柬埔寨","area":"柬埔寨金边马卡拉区","street":"Tuel Net Rd, 柬埔寨","points":"105.041418,11.4719001","country_id":85917,"province_id":85918,"city_id":85919,"area_id":85917},"reciver_province_id":"85918","reciver_city_id":"85919","invoice_info":[],"promotion_info":"","dlyo_pickup_code":null,"reciver_area_id":"0","citysend_info":" ","reciver_country_id":"85917","evalseller_country_id":"85763","reciver_points":""}
         * extend_order_goods : [{"rec_id":"799","order_id":"775","goods_id":"515","goods_commonid":"378","goods_name":"Vaseline","goods_price":"333.33","goods_costprice":"0.00","goods_num":"1","goods_image":"2020/03/19/4_06379474088411081_240.jpg","goods_pay_price":"333.33","store_id":"4","buyer_id":"119","goods_type":"1","promotions_id":"0","commis_rate":"200","gc_id":"23","start_time":"0","end_time":"0","dates":null,"days":"0","price_items":null,"hotel_msg":null,"store_points_offset":"0","store_points_offset_amount":"0.00","platform_points_offset":"0","platform_points_offset_amount":"0.00","is_visit_comm":"0","is_Independent_comm":"0","comm_rule":null,"exchange_rate":"30.0000","goods_spec_bak":"","goods_spec":"","refund":0,"goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/4/2020/03/19/4_06379474088411081_240.jpg"}]
         * if_cancel : false
         * if_refund_cancel : false
         * if_complain : false
         * if_receive : false
         * if_lock : false
         * if_deliver : false
         * if_evaluation : false
         * if_delete : true
         * if_drop : false
         * if_restore : false
         * write_off_code :
         */

        private String order_id;
        private String order_sn;
        private String pay_sn;
        private String store_id;
        private String store_name;
        private String buyer_id;
        private String buyer_name;
        private String buyer_email;
        private String add_time;
        private String payment_code;
        private String payment_time;
        private String finnshed_time;
        private String goods_amount;
        private String order_amount;
        private String shipping_fee;
        private String shipping_code;
        private String order_state;
        private String order_type;
        private String lock_state;
        private String evaluation_state;
        private String delete_state;
        private String refund_state;
        private String delay_time;
        private String state_desc;
        private String refund_desc;
        private String payment_name;
        private ExtendOrderCommonBean extend_order_common;
        private boolean if_cancel;
        private String refund;
        private boolean if_refund_cancel;
        private boolean if_complain;
        private boolean if_receive;
        private boolean if_lock;
        private boolean if_deliver;
        private boolean if_evaluation;
        private boolean if_delete;
        private boolean if_pay;
        private boolean if_drop;
        private boolean if_restore;
        private String write_off_code;
        private List<ExtendOrderGoodsBean> extend_order_goods;

        public boolean isIf_pay() {
            return if_pay;
        }

        public void setIf_pay(boolean if_pay) {
            this.if_pay = if_pay;
        }


        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }


        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_name() {
            return buyer_name;
        }

        public void setBuyer_name(String buyer_name) {
            this.buyer_name = buyer_name;
        }

        public String getBuyer_email() {
            return buyer_email;
        }

        public void setBuyer_email(String buyer_email) {
            this.buyer_email = buyer_email;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public String getFinnshed_time() {
            return finnshed_time;
        }

        public void setFinnshed_time(String finnshed_time) {
            this.finnshed_time = finnshed_time;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getLock_state() {
            return lock_state;
        }

        public void setLock_state(String lock_state) {
            this.lock_state = lock_state;
        }

        public String getEvaluation_state() {
            return evaluation_state;
        }

        public void setEvaluation_state(String evaluation_state) {
            this.evaluation_state = evaluation_state;
        }

        public String getDelete_state() {
            return delete_state;
        }

        public void setDelete_state(String delete_state) {
            this.delete_state = delete_state;
        }

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
        }

        public String getDelay_time() {
            return delay_time;
        }

        public void setDelay_time(String delay_time) {
            this.delay_time = delay_time;
        }

        public String getState_desc() {
            return state_desc;
        }

        public void setState_desc(String state_desc) {
            this.state_desc = state_desc;
        }

        public String getRefund_desc() {
            return refund_desc;
        }

        public void setRefund_desc(String refund_desc) {
            this.refund_desc = refund_desc;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public ExtendOrderCommonBean getExtend_order_common() {
            return extend_order_common;
        }

        public void setExtend_order_common(ExtendOrderCommonBean extend_order_common) {
            this.extend_order_common = extend_order_common;
        }

        public boolean isIf_cancel() {
            return if_cancel;
        }

        public void setIf_cancel(boolean if_cancel) {
            this.if_cancel = if_cancel;
        }

        public boolean isIf_refund_cancel() {
            return if_refund_cancel;
        }

        public void setIf_refund_cancel(boolean if_refund_cancel) {
            this.if_refund_cancel = if_refund_cancel;
        }

        public boolean isIf_complain() {
            return if_complain;
        }

        public void setIf_complain(boolean if_complain) {
            this.if_complain = if_complain;
        }

        public boolean isIf_receive() {
            return if_receive;
        }

        public void setIf_receive(boolean if_receive) {
            this.if_receive = if_receive;
        }

        public boolean isIf_lock() {
            return if_lock;
        }

        public void setIf_lock(boolean if_lock) {
            this.if_lock = if_lock;
        }

        public boolean isIf_deliver() {
            return if_deliver;
        }

        public void setIf_deliver(boolean if_deliver) {
            this.if_deliver = if_deliver;
        }

        public boolean isIf_evaluation() {
            return if_evaluation;
        }

        public void setIf_evaluation(boolean if_evaluation) {
            this.if_evaluation = if_evaluation;
        }

        public boolean isIf_delete() {
            return if_delete;
        }

        public void setIf_delete(boolean if_delete) {
            this.if_delete = if_delete;
        }

        public boolean isIf_drop() {
            return if_drop;
        }

        public void setIf_drop(boolean if_drop) {
            this.if_drop = if_drop;
        }

        public boolean isIf_restore() {
            return if_restore;
        }

        public void setIf_restore(boolean if_restore) {
            this.if_restore = if_restore;
        }

        public String getWrite_off_code() {
            return write_off_code;
        }

        public void setWrite_off_code(String write_off_code) {
            this.write_off_code = write_off_code;
        }

        public List<ExtendOrderGoodsBean> getExtend_order_goods() {
            return extend_order_goods;
        }

        public void setExtend_order_goods(List<ExtendOrderGoodsBean> extend_order_goods) {
            this.extend_order_goods = extend_order_goods;
        }

        public static class ExtendOrderCommonBean {
            /**
             * order_id : 775
             * store_id : 4
             * shipping_time : 0
             * shipping_express_id : 0
             * evaluation_time : 0
             * evalseller_state : 0
             * evalseller_time : 0
             * order_message :
             * order_pointscount : 0
             * has_refund_points : 0
             * voucher_price : null
             * voucher_code : null
             * platform_voucher_price : null
             * platform_voucher_code : null
             * deliver_explain : null
             * daddress_id : 0
             * reciver_name : jiaming
             * reciver_info : {"phone":"+8616574488587,16574488587","mob_phone":"0008616574488587","tel_phone":"16574488587","address":"柬埔寨金边马卡拉区 Tuel Net Rd, 柬埔寨","area":"柬埔寨金边马卡拉区","street":"Tuel Net Rd, 柬埔寨","points":"105.041418,11.4719001","country_id":85917,"province_id":85918,"city_id":85919,"area_id":85917}
             * reciver_province_id : 85918
             * reciver_city_id : 85919
             * invoice_info : []
             * promotion_info :
             * dlyo_pickup_code : null
             * reciver_area_id : 0
             * citysend_info :
             * reciver_country_id : 85917
             * evalseller_country_id : 85763
             * reciver_points :
             */

            private String order_id;
            private String store_id;
            private String shipping_time;
            private String shipping_express_id;
            private String evaluation_time;
            private String evalseller_state;
            private String evalseller_time;
            private String order_message;
            private String order_pointscount;
            private String has_refund_points;
            private Object voucher_price;
            private Object voucher_code;
            private Object platform_voucher_price;
            private Object platform_voucher_code;
            private Object deliver_explain;
            private String daddress_id;
            private String reciver_name;
            private ReciverInfoBean reciver_info;
            private String reciver_province_id;
            private String reciver_city_id;
            private String promotion_info;
            private Object dlyo_pickup_code;
            private String reciver_area_id;
            private String citysend_info;
            private String reciver_country_id;
            private String evalseller_country_id;
            private String reciver_points;
            private List<?> invoice_info;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public String getShipping_express_id() {
                return shipping_express_id;
            }

            public void setShipping_express_id(String shipping_express_id) {
                this.shipping_express_id = shipping_express_id;
            }

            public String getEvaluation_time() {
                return evaluation_time;
            }

            public void setEvaluation_time(String evaluation_time) {
                this.evaluation_time = evaluation_time;
            }

            public String getEvalseller_state() {
                return evalseller_state;
            }

            public void setEvalseller_state(String evalseller_state) {
                this.evalseller_state = evalseller_state;
            }

            public String getEvalseller_time() {
                return evalseller_time;
            }

            public void setEvalseller_time(String evalseller_time) {
                this.evalseller_time = evalseller_time;
            }

            public String getOrder_message() {
                return order_message;
            }

            public void setOrder_message(String order_message) {
                this.order_message = order_message;
            }

            public String getOrder_pointscount() {
                return order_pointscount;
            }

            public void setOrder_pointscount(String order_pointscount) {
                this.order_pointscount = order_pointscount;
            }

            public String getHas_refund_points() {
                return has_refund_points;
            }

            public void setHas_refund_points(String has_refund_points) {
                this.has_refund_points = has_refund_points;
            }

            public Object getVoucher_price() {
                return voucher_price;
            }

            public void setVoucher_price(Object voucher_price) {
                this.voucher_price = voucher_price;
            }

            public Object getVoucher_code() {
                return voucher_code;
            }

            public void setVoucher_code(Object voucher_code) {
                this.voucher_code = voucher_code;
            }

            public Object getPlatform_voucher_price() {
                return platform_voucher_price;
            }

            public void setPlatform_voucher_price(Object platform_voucher_price) {
                this.platform_voucher_price = platform_voucher_price;
            }

            public Object getPlatform_voucher_code() {
                return platform_voucher_code;
            }

            public void setPlatform_voucher_code(Object platform_voucher_code) {
                this.platform_voucher_code = platform_voucher_code;
            }

            public Object getDeliver_explain() {
                return deliver_explain;
            }

            public void setDeliver_explain(Object deliver_explain) {
                this.deliver_explain = deliver_explain;
            }

            public String getDaddress_id() {
                return daddress_id;
            }

            public void setDaddress_id(String daddress_id) {
                this.daddress_id = daddress_id;
            }

            public String getReciver_name() {
                return reciver_name;
            }

            public void setReciver_name(String reciver_name) {
                this.reciver_name = reciver_name;
            }

            public ReciverInfoBean getReciver_info() {
                return reciver_info;
            }

            public void setReciver_info(ReciverInfoBean reciver_info) {
                this.reciver_info = reciver_info;
            }

            public String getReciver_province_id() {
                return reciver_province_id;
            }

            public void setReciver_province_id(String reciver_province_id) {
                this.reciver_province_id = reciver_province_id;
            }

            public String getReciver_city_id() {
                return reciver_city_id;
            }

            public void setReciver_city_id(String reciver_city_id) {
                this.reciver_city_id = reciver_city_id;
            }

            public String getPromotion_info() {
                return promotion_info;
            }

            public void setPromotion_info(String promotion_info) {
                this.promotion_info = promotion_info;
            }

            public Object getDlyo_pickup_code() {
                return dlyo_pickup_code;
            }

            public void setDlyo_pickup_code(Object dlyo_pickup_code) {
                this.dlyo_pickup_code = dlyo_pickup_code;
            }

            public String getReciver_area_id() {
                return reciver_area_id;
            }

            public void setReciver_area_id(String reciver_area_id) {
                this.reciver_area_id = reciver_area_id;
            }

            public String getCitysend_info() {
                return citysend_info;
            }

            public void setCitysend_info(String citysend_info) {
                this.citysend_info = citysend_info;
            }

            public String getReciver_country_id() {
                return reciver_country_id;
            }

            public void setReciver_country_id(String reciver_country_id) {
                this.reciver_country_id = reciver_country_id;
            }

            public String getEvalseller_country_id() {
                return evalseller_country_id;
            }

            public void setEvalseller_country_id(String evalseller_country_id) {
                this.evalseller_country_id = evalseller_country_id;
            }

            public String getReciver_points() {
                return reciver_points;
            }

            public void setReciver_points(String reciver_points) {
                this.reciver_points = reciver_points;
            }

            public List<?> getInvoice_info() {
                return invoice_info;
            }

            public void setInvoice_info(List<?> invoice_info) {
                this.invoice_info = invoice_info;
            }

            public static class ReciverInfoBean {
                /**
                 * phone : +8616574488587,16574488587
                 * mob_phone : 0008616574488587
                 * tel_phone : 16574488587
                 * address : 柬埔寨金边马卡拉区 Tuel Net Rd, 柬埔寨
                 * area : 柬埔寨金边马卡拉区
                 * street : Tuel Net Rd, 柬埔寨
                 * points : 105.041418,11.4719001
                 * country_id : 85917
                 * province_id : 85918
                 * city_id : 85919
                 * area_id : 85917
                 */

                private String phone;
                private String mob_phone;
                private String tel_phone;
                private String address;
                private String area;
                private String street;
                private String points;
                private int country_id;
                private int province_id;
                private int city_id;
                private int area_id;

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getMob_phone() {
                    return mob_phone;
                }

                public void setMob_phone(String mob_phone) {
                    this.mob_phone = mob_phone;
                }

                public String getTel_phone() {
                    return tel_phone;
                }

                public void setTel_phone(String tel_phone) {
                    this.tel_phone = tel_phone;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getStreet() {
                    return street;
                }

                public void setStreet(String street) {
                    this.street = street;
                }

                public String getPoints() {
                    return points;
                }

                public void setPoints(String points) {
                    this.points = points;
                }

                public int getCountry_id() {
                    return country_id;
                }

                public void setCountry_id(int country_id) {
                    this.country_id = country_id;
                }

                public int getProvince_id() {
                    return province_id;
                }

                public void setProvince_id(int province_id) {
                    this.province_id = province_id;
                }

                public int getCity_id() {
                    return city_id;
                }

                public void setCity_id(int city_id) {
                    this.city_id = city_id;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }
            }
        }

        public static class ExtendOrderGoodsBean {
            /**
             * rec_id : 799
             * order_id : 775
             * goods_id : 515
             * goods_commonid : 378
             * goods_name : Vaseline
             * goods_price : 333.33
             * goods_costprice : 0.00
             * goods_num : 1
             * goods_image : 2020/03/19/4_06379474088411081_240.jpg
             * goods_pay_price : 333.33
             * store_id : 4
             * buyer_id : 119
             * goods_type : 1
             * promotions_id : 0
             * commis_rate : 200
             * gc_id : 23
             * start_time : 0
             * end_time : 0
             * dates : null
             * days : 0
             * price_items : null
             * hotel_msg : null
             * store_points_offset : 0
             * store_points_offset_amount : 0.00
             * platform_points_offset : 0
             * platform_points_offset_amount : 0.00
             * is_visit_comm : 0
             * is_Independent_comm : 0
             * comm_rule : null
             * exchange_rate : 30.0000
             * goods_spec_bak :
             * goods_spec :
             * refund : 0
             * goods_image_url : https://daluxmall.com/data/upload/shop/store/goods/4/2020/03/19/4_06379474088411081_240.jpg
             */

            private String rec_id;
            private String order_id;
            private String goods_id;
            private String goods_commonid;
            private String goods_name;
            private String goods_price;
            private String goods_costprice;
            private String goods_num;
            private String goods_image;
            private String goods_pay_price;
            private String store_id;
            private String buyer_id;
            private String goods_type;
            private String promotions_id;
            private String commis_rate;
            private String gc_id;
            private String start_time;
            private String end_time;
            private Object dates;
            private String days;
            private Object price_items;
            private Object hotel_msg;
            private String store_points_offset;
            private String store_points_offset_amount;
            private String platform_points_offset;
            private String platform_points_offset_amount;
            private String is_visit_comm;
            private String is_Independent_comm;
            private Object comm_rule;
            private String exchange_rate;
            private String goods_spec_bak;
            private String goods_spec;
            private int refund;
            private String goods_image_url;

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_commonid() {
                return goods_commonid;
            }

            public void setGoods_commonid(String goods_commonid) {
                this.goods_commonid = goods_commonid;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_costprice() {
                return goods_costprice;
            }

            public void setGoods_costprice(String goods_costprice) {
                this.goods_costprice = goods_costprice;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_pay_price() {
                return goods_pay_price;
            }

            public void setGoods_pay_price(String goods_pay_price) {
                this.goods_pay_price = goods_pay_price;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(String buyer_id) {
                this.buyer_id = buyer_id;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getPromotions_id() {
                return promotions_id;
            }

            public void setPromotions_id(String promotions_id) {
                this.promotions_id = promotions_id;
            }

            public String getCommis_rate() {
                return commis_rate;
            }

            public void setCommis_rate(String commis_rate) {
                this.commis_rate = commis_rate;
            }

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public Object getDates() {
                return dates;
            }

            public void setDates(Object dates) {
                this.dates = dates;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public Object getPrice_items() {
                return price_items;
            }

            public void setPrice_items(Object price_items) {
                this.price_items = price_items;
            }

            public Object getHotel_msg() {
                return hotel_msg;
            }

            public void setHotel_msg(Object hotel_msg) {
                this.hotel_msg = hotel_msg;
            }

            public String getStore_points_offset() {
                return store_points_offset;
            }

            public void setStore_points_offset(String store_points_offset) {
                this.store_points_offset = store_points_offset;
            }

            public String getStore_points_offset_amount() {
                return store_points_offset_amount;
            }

            public void setStore_points_offset_amount(String store_points_offset_amount) {
                this.store_points_offset_amount = store_points_offset_amount;
            }

            public String getPlatform_points_offset() {
                return platform_points_offset;
            }

            public void setPlatform_points_offset(String platform_points_offset) {
                this.platform_points_offset = platform_points_offset;
            }

            public String getPlatform_points_offset_amount() {
                return platform_points_offset_amount;
            }

            public void setPlatform_points_offset_amount(String platform_points_offset_amount) {
                this.platform_points_offset_amount = platform_points_offset_amount;
            }

            public String getIs_visit_comm() {
                return is_visit_comm;
            }

            public void setIs_visit_comm(String is_visit_comm) {
                this.is_visit_comm = is_visit_comm;
            }

            public String getIs_Independent_comm() {
                return is_Independent_comm;
            }

            public void setIs_Independent_comm(String is_Independent_comm) {
                this.is_Independent_comm = is_Independent_comm;
            }

            public Object getComm_rule() {
                return comm_rule;
            }

            public void setComm_rule(Object comm_rule) {
                this.comm_rule = comm_rule;
            }

            public String getExchange_rate() {
                return exchange_rate;
            }

            public void setExchange_rate(String exchange_rate) {
                this.exchange_rate = exchange_rate;
            }

            public String getGoods_spec_bak() {
                return goods_spec_bak;
            }

            public void setGoods_spec_bak(String goods_spec_bak) {
                this.goods_spec_bak = goods_spec_bak;
            }

            public String getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(String goods_spec) {
                this.goods_spec = goods_spec;
            }

            public int getRefund() {
                return refund;
            }

            public void setRefund(int refund) {
                this.refund = refund;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }
        }
    }
}
