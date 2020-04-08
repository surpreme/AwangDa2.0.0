package com.aite.a.activity.li.fragment.shoppingCartFragment;

import com.aite.a.activity.li.mvp.ErrorBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020-01-14
 * @desc:
 */
public class ShoppingCardBean extends ErrorBean implements Serializable {

    /**
     * cart_list : [{"store_id":"1","store_name":"jpz01","goods_list":[{"cart_id":"53","buyer_id":"30","store_id":"1","store_name":"jpz01","goods_id":"1","goods_name":"West Lake 2019 New Tea Longjing Tea Authentic Yuqi","goods_price":"59.90","goods_num":"1","goods_image":"2019/12/23/1_06304393060433115.jpg","bl_id":"0","goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/23/1_06304393060433115_240.jpg","goods_sum":"59.90","goods_spec":"","this_state":false}]}]
     * like_goods : [{"goods_id":"44","goods_name":"Genuine jeans blue 180","goods_jingle":"","goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/2/2020/01/04/2_06314536321490333_240.jpg","goods_url":"https://daluxmall.com/wap/index.php?act=goods&op=goods_detail&goods_id=44","store_id":"2","goods_price":"6.05","goods_marketprice":"10.00","goods_salenum":"0"},{"goods_id":"9","goods_name":"ខោកូនប្រុស Boy jeans korea fashion","goods_jingle":"商品卖点 商品卖点 商品卖点","goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/3/2019/12/24/3_06305183482736902_240.png","goods_url":"https://daluxmall.com/wap/index.php?act=goods&op=goods_detail&goods_id=9","store_id":"3","goods_price":"10.00","goods_marketprice":"20.00","goods_salenum":"7"},{"goods_id":"4","goods_name":"柬埔寨商品","goods_jingle":"","goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/24/1_06304946516027303_240.jpg","goods_url":"https://daluxmall.com/wap/index.php?act=goods&op=goods_detail&goods_id=4","store_id":"1","goods_price":"1.00","goods_marketprice":"1.00","goods_salenum":"0"},{"goods_id":"2","goods_name":"2019新茶叶特级安溪铁观音清香型乌龙茶浓香型小包散装礼盒装500g","goods_jingle":"","goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/23/1_06304394630420616_240.png","goods_url":"https://daluxmall.com/wap/index.php?act=goods&op=goods_detail&goods_id=2","store_id":"1","goods_price":"0.01","goods_marketprice":"20.00","goods_salenum":"21"}]
     * sum : 59.90
     */

    private String sum;
    private List<CartListBean> cart_list;
    private List<LikeGoodsBean> like_goods;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<CartListBean> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<CartListBean> cart_list) {
        this.cart_list = cart_list;
    }

    public List<LikeGoodsBean> getLike_goods() {
        return like_goods;
    }

    public void setLike_goods(List<LikeGoodsBean> like_goods) {
        this.like_goods = like_goods;
    }

    public static class CartListBean {
        /**
         * store_id : 1
         * store_name : jpz01
         * goods_list : [{"cart_id":"53","buyer_id":"30","store_id":"1","store_name":"jpz01","goods_id":"1","goods_name":"West Lake 2019 New Tea Longjing Tea Authentic Yuqi","goods_price":"59.90","goods_num":"1","goods_image":"2019/12/23/1_06304393060433115.jpg","bl_id":"0","goods_image_url":"https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/23/1_06304393060433115_240.jpg","goods_sum":"59.90","goods_spec":"","this_state":false}]
         */

        private String store_id;
        private String store_name;
        private List<GoodsListBean> goods_list;
        public boolean ispick=false;


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

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * cart_id : 53
             * buyer_id : 30
             * store_id : 1
             * store_name : jpz01
             * goods_id : 1
             * goods_name : West Lake 2019 New Tea Longjing Tea Authentic Yuqi
             * goods_price : 59.90
             * goods_num : 1
             * goods_image : 2019/12/23/1_06304393060433115.jpg
             * bl_id : 0
             * goods_image_url : https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/23/1_06304393060433115_240.jpg
             * goods_sum : 59.90
             * goods_spec :
             * this_state : false
             */
            public boolean ispick=false;

            private String cart_id;
            private String buyer_id;
            private String store_id;
            private String store_name;
            private String goods_id;
            private String goods_name;
            private String goods_price;
            private String goods_num;
            private String goods_image;
            private String bl_id;
            private String goods_image_url;
            private String goods_sum;
            private String goods_spec;
            private boolean this_state;

            public String getCart_id() {
                return cart_id;
            }

            public void setCart_id(String cart_id) {
                this.cart_id = cart_id;
            }

            public String getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(String buyer_id) {
                this.buyer_id = buyer_id;
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

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getBl_id() {
                return bl_id;
            }

            public void setBl_id(String bl_id) {
                this.bl_id = bl_id;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }

            public String getGoods_sum() {
                return goods_sum;
            }

            public void setGoods_sum(String goods_sum) {
                this.goods_sum = goods_sum;
            }

            public String getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(String goods_spec) {
                this.goods_spec = goods_spec;
            }

            public boolean isThis_state() {
                return this_state;
            }

            public void setThis_state(boolean this_state) {
                this.this_state = this_state;
            }
        }
    }

    public static class LikeGoodsBean {
        /**
         * goods_id : 44
         * goods_name : Genuine jeans blue 180
         * goods_jingle :
         * goods_image_url : https://daluxmall.com/data/upload/shop/store/goods/2/2020/01/04/2_06314536321490333_240.jpg
         * goods_url : https://daluxmall.com/wap/index.php?act=goods&op=goods_detail&goods_id=44
         * store_id : 2
         * goods_price : 6.05
         * goods_marketprice : 10.00
         * goods_salenum : 0
         */

        private String goods_id;
        private String goods_name;
        private String goods_jingle;
        private String goods_image_url;
        private String goods_url;
        private String store_id;
        private String goods_price;
        private String goods_marketprice;
        private String goods_salenum;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_jingle() {
            return goods_jingle;
        }

        public void setGoods_jingle(String goods_jingle) {
            this.goods_jingle = goods_jingle;
        }

        public String getGoods_image_url() {
            return goods_image_url;
        }

        public void setGoods_image_url(String goods_image_url) {
            this.goods_image_url = goods_image_url;
        }

        public String getGoods_url() {
            return goods_url;
        }

        public void setGoods_url(String goods_url) {
            this.goods_url = goods_url;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_marketprice() {
            return goods_marketprice;
        }

        public void setGoods_marketprice(String goods_marketprice) {
            this.goods_marketprice = goods_marketprice;
        }

        public String getGoods_salenum() {
            return goods_salenum;
        }

        public void setGoods_salenum(String goods_salenum) {
            this.goods_salenum = goods_salenum;
        }
    }
}
