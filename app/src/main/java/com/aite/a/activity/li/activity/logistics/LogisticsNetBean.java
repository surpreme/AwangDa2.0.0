package com.aite.a.activity.li.activity.logistics;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/10
 * @desc:
 */
public class LogisticsNetBean {

    /**
     * deliver_type : 2
     * express_name : null
     * shipping_code : null
     * order_info : {"order_state":"30"}
     * delivery_staff_info : {"member_id":"1962","staff_name":"良性循环","staff_phone":"13642923960"}
     * deliver_info : [{"time":"1583478639","context":"发出了货物","status":"30"},{"time":"1583478621","context":"完成了付款","status":"20"}]
     */

    private int deliver_type;
    private String express_name;
    private String shipping_code;
    private OrderInfoBean order_info;
    private List<DeliverInfoBean> deliver_info;
    private DeliveryStaffInfoBean delivery_staff_info;

    public int getDeliver_type() {
        return deliver_type;
    }

    public void setDeliver_type(int deliver_type) {
        this.deliver_type = deliver_type;
    }

    public String getExpress_name() {
        return express_name;
    }

    public void setExpress_name(String express_name) {
        this.express_name = express_name;
    }

    public String getShipping_code() {
        return shipping_code;
    }

    public void setShipping_code(String shipping_code) {
        this.shipping_code = shipping_code;
    }

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }



    public List<DeliverInfoBean> getDeliver_info() {
        return deliver_info;
    }

    public void setDeliver_info(List<DeliverInfoBean> deliver_info) {
        this.deliver_info = deliver_info;
    }

    public DeliveryStaffInfoBean getDelivery_staff_info() {
        return delivery_staff_info;
    }

    public void setDelivery_staff_info(DeliveryStaffInfoBean delivery_staff_info) {
        this.delivery_staff_info = delivery_staff_info;
    }

    public static class OrderInfoBean {
        /**
         * order_state : 30
         */

        private String order_state;

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }
    }

    public static class DeliverInfoBean implements Serializable{
        /**
         * time : 1583478639
         * context : 发出了货物
         * status : 30
         */

        private String time;
        private String context;
        private String status;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class DeliveryStaffInfoBean implements Serializable {
        /**
         * member_id : 1962
         * staff_name : 良性循环
         * staff_phone : 13642923960
         */

        private String member_id;
        private String staff_name;
        private String staff_phone;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getStaff_name() {
            return staff_name;
        }

        public void setStaff_name(String staff_name) {
            this.staff_name = staff_name;
        }

        public String getStaff_phone() {
            return staff_phone;
        }

        public void setStaff_phone(String staff_phone) {
            this.staff_phone = staff_phone;
        }
    }
}
