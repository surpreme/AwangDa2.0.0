package com.aite.a.bean;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/2
 * @desc:
 */
public class ChoiceAddressBean {

    private List<AreaListBean> area_list;

    public List<AreaListBean> getArea_list() {
        return area_list;
    }

    public void setArea_list(List<AreaListBean> area_list) {
        this.area_list = area_list;
    }

    public static class AreaListBean {
        /**
         * area_id : 85918
         * area_name : 金边
         */

        private String area_id;
        private String area_name;

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }
    }
}
