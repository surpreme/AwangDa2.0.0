package com.aite.a.bean;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/2
 * @desc:
 */
public class ShopHomeBean4 {


    /**
     * goods_list : [{"nc_distinct":"7,7","goods_id":"24","goods_name":"WOOSUNZE Womens Flounce Bell Sleeve Office Work Ca","goods_short_title":"","goods_jingle":"","store_id":"3","store_name":"jpzShop","goods_price":"22.95","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/2020/02/10/3_06346427963842840.jpg","goods_promotion_price":"22.95","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=24"},{"nc_distinct":"8,8","goods_id":"30","goods_name":"LITERRA Womens Ankle Socks Low Cut Athletic Sports","goods_short_title":"","goods_jingle":"I's good","store_id":"3","store_name":"jpzShop","goods_price":"7.98","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/2020/02/10/3_06346431608160757.jpg","goods_promotion_price":"7.98","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=30"},{"nc_distinct":"31,31","goods_id":"31","goods_name":"LITERRA Womens Ankle Socks Low Cut Athletic Sports","goods_short_title":"","goods_jingle":"I's good","store_id":"3","store_name":"jpzShop","goods_price":"0.00","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/2020/02/10/3_06346431608160757.jpg","goods_promotion_price":"7.98","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=31"},{"nc_distinct":"11,11","goods_id":"44","goods_name":"Urban CoCo Women's Color Block Shawl Wrap Open Fro","goods_short_title":"","goods_jingle":"So good","store_id":"3","store_name":"jpzShop","goods_price":"23.63","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/2020/02/10/3_06346470463015553.jpg","goods_promotion_price":"23.63","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=44"},{"nc_distinct":"14,14","goods_id":"51","goods_name":"SAMPEEL Womens Waffle Knit Tops V-Neck Casual T Shirt Tie Knot Loose Fitting Sweater Apricot S default","goods_short_title":"","goods_jingle":"This is good shop","store_id":"3","store_name":"jpzShop","goods_price":"10.82","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/2020/02/10/3_06346493572960663.jpg","goods_promotion_price":"10.82","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=51"},{"nc_distinct":"30,30","goods_id":"68","goods_name":"拼团活动参与商品","goods_short_title":"","goods_jingle":"","store_id":"3","store_name":"jpzShop","goods_price":"15.10","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/","goods_promotion_price":"15.10","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=68"},{"nc_distinct":"32,32","goods_id":"70","goods_name":"满即送参与商品","goods_short_title":"","goods_jingle":"","store_id":"3","store_name":"jpzShop","goods_price":"294.00","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/","goods_promotion_price":"294.00","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=70"},{"nc_distinct":"38,38","goods_id":"80","goods_name":"Theory H0101113 深紫红 00 默认","goods_short_title":"","goods_jingle":"","store_id":"3","store_name":"jpzShop","goods_price":"124.50","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/2020/02/11/3_06347541268035459.jpg","goods_promotion_price":"124.50","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=80"},{"nc_distinct":"40,40","goods_id":"85","goods_name":"测试商品1","goods_short_title":"","goods_jingle":"","store_id":"3","store_name":"jpzShop","goods_price":"100.00","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/","goods_promotion_price":"100.00","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=85"},{"nc_distinct":"47,47","goods_id":"100","goods_name":"zxZzxzx","goods_short_title":"","goods_jingle":"X","store_id":"3","store_name":"jpzShop","goods_price":"12.00","goods_labels_ids":null,"goods_image":"https://daluxmall.com/data/upload/shop/store/goods/3/","goods_promotion_price":"12.00","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=100"}]
     * page : {"hasmore":true,"page_total":2}
     */

    private PageBean page;
    private List<GoodsListBean> goods_list;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class PageBean {
        /**
         * hasmore : true
         * page_total : 2
         */

        private boolean hasmore;
        private int page_total;

        public boolean isHasmore() {
            return hasmore;
        }

        public void setHasmore(boolean hasmore) {
            this.hasmore = hasmore;
        }

        public int getPage_total() {
            return page_total;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }
    }

    public static class GoodsListBean {
        /**
         * nc_distinct : 7,7
         * goods_id : 24
         * goods_name : WOOSUNZE Womens Flounce Bell Sleeve Office Work Ca
         * goods_short_title :
         * goods_jingle :
         * store_id : 3
         * store_name : jpzShop
         * goods_price : 22.95
         * goods_labels_ids : null
         * goods_image : https://daluxmall.com/data/upload/shop/store/goods/3/2020/02/10/3_06346427963842840.jpg
         * goods_promotion_price : 22.95
         * groupbuy_info : null
         * xianshi_info : null
         * miaosha_info : []
         * spellgroup_info : []
         * goods_url : https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=24
         */

        private String nc_distinct;
        private String goods_id;
        private String goods_name;
        private String goods_short_title;
        private String goods_jingle;
        private String store_id;
        private String store_name;
        private String goods_price;
        private Object goods_labels_ids;
        private String goods_image;
        private String goods_promotion_price;
        private Object groupbuy_info;
        private Object xianshi_info;
        private String goods_url;
        private List<?> miaosha_info;
        private List<?> spellgroup_info;

        public String getNc_distinct() {
            return nc_distinct;
        }

        public void setNc_distinct(String nc_distinct) {
            this.nc_distinct = nc_distinct;
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

        public String getGoods_short_title() {
            return goods_short_title;
        }

        public void setGoods_short_title(String goods_short_title) {
            this.goods_short_title = goods_short_title;
        }

        public String getGoods_jingle() {
            return goods_jingle;
        }

        public void setGoods_jingle(String goods_jingle) {
            this.goods_jingle = goods_jingle;
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

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public Object getGoods_labels_ids() {
            return goods_labels_ids;
        }

        public void setGoods_labels_ids(Object goods_labels_ids) {
            this.goods_labels_ids = goods_labels_ids;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getGoods_promotion_price() {
            return goods_promotion_price;
        }

        public void setGoods_promotion_price(String goods_promotion_price) {
            this.goods_promotion_price = goods_promotion_price;
        }

        public Object getGroupbuy_info() {
            return groupbuy_info;
        }

        public void setGroupbuy_info(Object groupbuy_info) {
            this.groupbuy_info = groupbuy_info;
        }

        public Object getXianshi_info() {
            return xianshi_info;
        }

        public void setXianshi_info(Object xianshi_info) {
            this.xianshi_info = xianshi_info;
        }

        public String getGoods_url() {
            return goods_url;
        }

        public void setGoods_url(String goods_url) {
            this.goods_url = goods_url;
        }

        public List<?> getMiaosha_info() {
            return miaosha_info;
        }

        public void setMiaosha_info(List<?> miaosha_info) {
            this.miaosha_info = miaosha_info;
        }

        public List<?> getSpellgroup_info() {
            return spellgroup_info;
        }

        public void setSpellgroup_info(List<?> spellgroup_info) {
            this.spellgroup_info = spellgroup_info;
        }
    }
}
