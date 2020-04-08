package com.aite.a.activity.li.fragment.locationFragment;

import com.aite.a.activity.li.mvp.ErrorBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class LocationAdvBean extends ErrorBean implements Serializable {

    private List<SwiperBean> swiper;
    private List<AdvBean> adv;

    public List<SwiperBean> getSwiper() {
        return swiper;
    }

    public void setSwiper(List<SwiperBean> swiper) {
        this.swiper = swiper;
    }

    public List<AdvBean> getAdv() {
        return adv;
    }

    public void setAdv(List<AdvBean> adv) {
        this.adv = adv;
    }

    public static class SwiperBean {
        /**
         * adv_pic : https://aitecc.com/data/upload/shop/adv/06197183845083670.png
         * adv_pic_url :
         */

        private String adv_pic;
        private String adv_pic_url;

        public String getAdv_pic() {
            return adv_pic;
        }

        public void setAdv_pic(String adv_pic) {
            this.adv_pic = adv_pic;
        }

        public String getAdv_pic_url() {
            return adv_pic_url;
        }

        public void setAdv_pic_url(String adv_pic_url) {
            this.adv_pic_url = adv_pic_url;
        }
    }

    public static class AdvBean {
        /**
         * title : 优惠专区
         * list : [{"adv_pic":"https://aitecc.com/data/upload/shop/adv/06197185816654575.png","adv_pic_url":""},{"adv_pic":"https://aitecc.com/data/upload/shop/adv/06197185564770156.png","adv_pic_url":""}]
         */

        private String title;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * adv_pic : https://aitecc.com/data/upload/shop/adv/06197185816654575.png
             * adv_pic_url :
             */

            private String adv_pic;
            private String adv_pic_url;

            public String getAdv_pic() {
                return adv_pic;
            }

            public void setAdv_pic(String adv_pic) {
                this.adv_pic = adv_pic;
            }

            public String getAdv_pic_url() {
                return adv_pic_url;
            }

            public void setAdv_pic_url(String adv_pic_url) {
                this.adv_pic_url = adv_pic_url;
            }
        }
    }
}