package com.aite.a.activity.li.activity.logistics;

import java.io.Serializable;
import java.util.List;
import com.valy.baselibrary.bean.ToolBarBean;

/**
 * @Auther: valy
 * @datetime: 2020/3/10
 * @desc:
 */
public class LogisticsKotlinUIBean extends ToolBarBean {
    //配送员和电话号码用这个参数传递
    private String userInformation;
    private List<DeliverInfoBean> deliver_info;

    public String getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(String userInformation) {
        this.userInformation = userInformation;
    }

    public List<DeliverInfoBean> getDeliver_info() {
        return deliver_info;
    }

    public void setDeliver_info(List<DeliverInfoBean> deliver_info) {
        this.deliver_info = deliver_info;
    }

    public static class DeliverInfoBean implements Serializable {
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
}
