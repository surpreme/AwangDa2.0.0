package com.aite.a.bean;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/2
 * @desc:
 */
public class ShopHomeBean {

    /**
     * store_id : 3
     * store_name : jpzShop
     * store_label : https://daluxmall.com/data/upload/shop/store/06347377689314197.jpg
     * store_banner : https://daluxmall.com/data/upload/shop/store/06347486752405385.jpg
     * store_avatar : https://daluxmall.com/data/upload/shop/store/06362331634522483_sm.png
     * store_mb_banner : 06347451855046129.jpg
     * store_collect : 3
     * goods_count : 21
     * store_credit : {"store_desccredit":{"text":"描述相符","credit":4.7},"store_servicecredit":{"text":"服务态度","credit":4.7},"store_deliverycredit":{"text":"发货速度","credit":4.8}}
     * store_credit_average : 4.7
     * store_credit_percent : 94
     * favorites_count : 3
     * isStoreFavorites : 0
     * ad_swiper : [{"image_path":"https://daluxmall.com/data/upload/shop/store/06347451855060391.jpg","url":null},{"image_path":"https://daluxmall.com/data/upload/shop/store/06347451855073519.jpg","url":null}]
     * store_mb_banner_path : https://daluxmall.com/data/upload/shop/store/06347451855046129.jpg
     * mb_swiper : [{"store_mb_slide_img":"https://daluxmall.com/data/upload/shop/store/slide/06347511669577077.jpg","store_mb_slide_url":"http://","store_mb_slide":"06347511669577077.jpg"},{"store_mb_slide_img":"https://daluxmall.com/data/upload/shop/store/slide/06347511699908904.jpg","store_mb_slide_url":"http://","store_mb_slide":"06347511699908904.jpg"},{"store_mb_slide_img":"https://daluxmall.com/data/upload/shop/store/slide/06347511753462659.jpg","store_mb_slide_url":"http://","store_mb_slide":"06347511753462659.jpg"},{"store_mb_slide_img":"https://daluxmall.com/data/upload/shop/store/slide/06347511782996016.jpg","store_mb_slide_url":"http://","store_mb_slide":"06347511782996016.jpg"},{"store_mb_slide_img":"https://daluxmall.com/data/upload/shop/store/slide/06347511996440473.jpg","store_mb_slide_url":"http://","store_mb_slide":"06347511996440473.jpg"}]
     */

    private String store_id;
    private String store_name;
    private String store_label;
    private String store_banner;
    private String store_avatar;
    private String store_mb_banner;
    private String store_collect;
    private String goods_count;
    private StoreCreditBean store_credit;
    private double store_credit_average;
    private int store_credit_percent;
    private String favorites_count;
    private int isStoreFavorites;
    private String store_mb_banner_path;
    private List<AdSwiperBean> ad_swiper;
    private List<MbSwiperBean> mb_swiper;

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

    public String getStore_label() {
        return store_label;
    }

    public void setStore_label(String store_label) {
        this.store_label = store_label;
    }

    public String getStore_banner() {
        return store_banner;
    }

    public void setStore_banner(String store_banner) {
        this.store_banner = store_banner;
    }

    public String getStore_avatar() {
        return store_avatar;
    }

    public void setStore_avatar(String store_avatar) {
        this.store_avatar = store_avatar;
    }

    public String getStore_mb_banner() {
        return store_mb_banner;
    }

    public void setStore_mb_banner(String store_mb_banner) {
        this.store_mb_banner = store_mb_banner;
    }

    public String getStore_collect() {
        return store_collect;
    }

    public void setStore_collect(String store_collect) {
        this.store_collect = store_collect;
    }

    public String getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(String goods_count) {
        this.goods_count = goods_count;
    }

    public StoreCreditBean getStore_credit() {
        return store_credit;
    }

    public void setStore_credit(StoreCreditBean store_credit) {
        this.store_credit = store_credit;
    }

    public double getStore_credit_average() {
        return store_credit_average;
    }

    public void setStore_credit_average(double store_credit_average) {
        this.store_credit_average = store_credit_average;
    }

    public int getStore_credit_percent() {
        return store_credit_percent;
    }

    public void setStore_credit_percent(int store_credit_percent) {
        this.store_credit_percent = store_credit_percent;
    }

    public String getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(String favorites_count) {
        this.favorites_count = favorites_count;
    }

    public int getIsStoreFavorites() {
        return isStoreFavorites;
    }

    public void setIsStoreFavorites(int isStoreFavorites) {
        this.isStoreFavorites = isStoreFavorites;
    }

    public String getStore_mb_banner_path() {
        return store_mb_banner_path;
    }

    public void setStore_mb_banner_path(String store_mb_banner_path) {
        this.store_mb_banner_path = store_mb_banner_path;
    }

    public List<AdSwiperBean> getAd_swiper() {
        return ad_swiper;
    }

    public void setAd_swiper(List<AdSwiperBean> ad_swiper) {
        this.ad_swiper = ad_swiper;
    }

    public List<MbSwiperBean> getMb_swiper() {
        return mb_swiper;
    }

    public void setMb_swiper(List<MbSwiperBean> mb_swiper) {
        this.mb_swiper = mb_swiper;
    }

    public static class StoreCreditBean {
        /**
         * store_desccredit : {"text":"描述相符","credit":4.7}
         * store_servicecredit : {"text":"服务态度","credit":4.7}
         * store_deliverycredit : {"text":"发货速度","credit":4.8}
         */

        private StoreDesccreditBean store_desccredit;
        private StoreServicecreditBean store_servicecredit;
        private StoreDeliverycreditBean store_deliverycredit;

        public StoreDesccreditBean getStore_desccredit() {
            return store_desccredit;
        }

        public void setStore_desccredit(StoreDesccreditBean store_desccredit) {
            this.store_desccredit = store_desccredit;
        }

        public StoreServicecreditBean getStore_servicecredit() {
            return store_servicecredit;
        }

        public void setStore_servicecredit(StoreServicecreditBean store_servicecredit) {
            this.store_servicecredit = store_servicecredit;
        }

        public StoreDeliverycreditBean getStore_deliverycredit() {
            return store_deliverycredit;
        }

        public void setStore_deliverycredit(StoreDeliverycreditBean store_deliverycredit) {
            this.store_deliverycredit = store_deliverycredit;
        }

        public static class StoreDesccreditBean {
            /**
             * text : 描述相符
             * credit : 4.7
             */

            private String text;
            private double credit;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public double getCredit() {
                return credit;
            }

            public void setCredit(double credit) {
                this.credit = credit;
            }
        }

        public static class StoreServicecreditBean {
            /**
             * text : 服务态度
             * credit : 4.7
             */

            private String text;
            private double credit;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public double getCredit() {
                return credit;
            }

            public void setCredit(double credit) {
                this.credit = credit;
            }
        }

        public static class StoreDeliverycreditBean {
            /**
             * text : 发货速度
             * credit : 4.8
             */

            private String text;
            private double credit;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public double getCredit() {
                return credit;
            }

            public void setCredit(double credit) {
                this.credit = credit;
            }
        }
    }

    public static class AdSwiperBean {
        /**
         * image_path : https://daluxmall.com/data/upload/shop/store/06347451855060391.jpg
         * url : null
         */

        private String image_path;
        private Object url;

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }

    public static class MbSwiperBean {
        /**
         * store_mb_slide_img : https://daluxmall.com/data/upload/shop/store/slide/06347511669577077.jpg
         * store_mb_slide_url : http://
         * store_mb_slide : 06347511669577077.jpg
         */

        private String store_mb_slide_img;
        private String store_mb_slide_url;
        private String store_mb_slide;

        public String getStore_mb_slide_img() {
            return store_mb_slide_img;
        }

        public void setStore_mb_slide_img(String store_mb_slide_img) {
            this.store_mb_slide_img = store_mb_slide_img;
        }

        public String getStore_mb_slide_url() {
            return store_mb_slide_url;
        }

        public void setStore_mb_slide_url(String store_mb_slide_url) {
            this.store_mb_slide_url = store_mb_slide_url;
        }

        public String getStore_mb_slide() {
            return store_mb_slide;
        }

        public void setStore_mb_slide(String store_mb_slide) {
            this.store_mb_slide = store_mb_slide;
        }
    }
}
