package com.aite.a.activity.li.fragment.houseFragment;

import com.aite.a.activity.li.mvp.ErrorBean;

import java.util.List;

/**
 * @author:lzy
 * @Date: 2019/11/12
 * @description:
 */
public class HouseUIBean extends ErrorBean {


    /**
     * adv_list : {"style":"","item":[{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292379223362351.jpg","type":"keyword","data":""},{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292379300174820.jpg","type":"keyword","data":""}]}
     * navigation : {"style":"1","item":[{"navigation_image_name":"s0_06292398631801769.png","navigation_name":"积分","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=pointprod&op=plist","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292398631801769.png"},{"navigation_image_name":"s0_06292400105653879.jpg","navigation_name":"代金券","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=pointshop","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400105653879.jpg"},{"navigation_image_name":"s0_06292400200534515.gif","navigation_name":"全部商品","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=goods&op=goods_list","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400200534515.gif"},{"navigation_image_name":"s0_06292400311908756.jpg","navigation_name":"店铺街","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=near_store&op=SelectCity","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400311908756.jpg"},{"navigation_image_name":"s0_06292400421491928.jpg","navigation_name":"分类","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=goods_class&op=index","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400421491928.jpg"}]}
     * home1 : {"title":"艾特头条","style":"","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292391541829360.jpg","type":"special","data":""}
     * home2 : {"title":"艾特头条","style":"艾特头条","square_image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292375891278913.jpg","square_type":"","square_data":"","rectangle1_image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292371386264324.jpg","rectangle1_type":"","rectangle1_data":"","rectangle2_image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292371586246158.jpg","rectangle2_type":"","rectangle2_data":""}
     * goods : {"title":"","style":"","item":[{"goods_id":"6","goods_name":"柬埔寨商品","goods_promotion_type":"0","goods_promotion_price":"1.00","goods_image":"https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/24/1_06304950184013577_240.jpg","goods_marketprice":"1.00","level_0_price":"1.00","level_1_price":"1.00","level_2_price":"1.00","level_3_price":"1.00","level_0_auth_price":"1.00","level_1_auth_price":"1.00","level_2_auth_price":"1.00","level_3_auth_price":"1.00","goods_price":"1.00","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=6"},{"goods_id":"34","goods_name":"第三方","goods_promotion_type":"0","goods_promotion_price":"0.05","goods_image":"https://daluxmall.com/data/upload/shop/common/default_goods_image_240.gif","goods_marketprice":"0.05","level_0_price":"0.00","level_1_price":"0.00","level_2_price":"0.00","level_3_price":"0.00","level_0_auth_price":"0.00","level_1_auth_price":"0.00","level_2_auth_price":"0.00","level_3_auth_price":"0.00","goods_price":"0.05","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=34"},{"goods_id":"33","goods_name":"第三方","goods_promotion_type":"0","goods_promotion_price":"0.05","goods_image":"https://daluxmall.com/data/upload/shop/common/default_goods_image_240.gif","goods_marketprice":"0.05","level_0_price":"0.00","level_1_price":"0.00","level_2_price":"0.00","level_3_price":"0.00","level_0_auth_price":"0.00","level_1_auth_price":"0.00","level_2_auth_price":"0.00","level_3_auth_price":"0.00","goods_price":"0.05","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=33"},{"goods_id":"32","goods_name":"第三方","goods_promotion_type":"0","goods_promotion_price":"0.05","goods_image":"https://daluxmall.com/data/upload/shop/common/default_goods_image_240.gif","goods_marketprice":"0.05","level_0_price":"0.00","level_1_price":"0.00","level_2_price":"0.00","level_3_price":"0.00","level_0_auth_price":"0.00","level_1_auth_price":"0.00","level_2_auth_price":"0.00","level_3_auth_price":"0.00","goods_price":"0.05","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=32"}]}
     */

    private AdvListBean adv_list;
    private NavigationBean navigation;
    private Home1Bean home1;
    private Home2Bean home2;
    private Home3Bean home3;
    private Home4Bean home4;
    private Home5Bean home5;
    private GoodsBean goods;

    public AdvListBean getAdv_list() {
        return adv_list;
    }

    public void setAdv_list(AdvListBean adv_list) {
        this.adv_list = adv_list;
    }

    public NavigationBean getNavigation() {
        return navigation;
    }

    public void setNavigation(NavigationBean navigation) {
        this.navigation = navigation;
    }

    public Home1Bean getHome1() {
        return home1;
    }

    public void setHome1(Home1Bean home1) {
        this.home1 = home1;
    }

    public Home2Bean getHome2() {
        return home2;
    }

    public void setHome2(Home2Bean home2) {
        this.home2 = home2;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class AdvListBean {
        /**
         * style :
         * item : [{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292379223362351.jpg","type":"keyword","data":""},{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292379300174820.jpg","type":"keyword","data":""}]
         */

        private String style;
        private List<ItemBean> item;

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06292379223362351.jpg
             * type : keyword
             * data :
             */

            private String image;
            private String type;
            private String data;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }

    public static class NavigationBean {
        /**
         * style : 1
         * item : [{"navigation_image_name":"s0_06292398631801769.png","navigation_name":"积分","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=pointprod&op=plist","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292398631801769.png"},{"navigation_image_name":"s0_06292400105653879.jpg","navigation_name":"代金券","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=pointshop","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400105653879.jpg"},{"navigation_image_name":"s0_06292400200534515.gif","navigation_name":"全部商品","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=goods&op=goods_list","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400200534515.gif"},{"navigation_image_name":"s0_06292400311908756.jpg","navigation_name":"店铺街","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=near_store&op=SelectCity","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400311908756.jpg"},{"navigation_image_name":"s0_06292400421491928.jpg","navigation_name":"分类","navigation_type":"url","navigation_data":"https://aitecc.com/wap/index.php?act=goods_class&op=index","image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06292400421491928.jpg"}]
         */

        private String style;
        private List<ItemBeanX> item;

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public List<ItemBeanX> getItem() {
            return item;
        }

        public void setItem(List<ItemBeanX> item) {
            this.item = item;
        }

        public static class ItemBeanX {
            /**
             * navigation_image_name : s0_06292398631801769.png
             * navigation_name : 积分
             * navigation_type : url
             * navigation_data : https://aitecc.com/wap/index.php?act=pointprod&op=plist
             * image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06292398631801769.png
             */

            private String navigation_image_name;
            private String navigation_name;
            private String navigation_type;
            private String navigation_data;
            private String image;

            public String getNavigation_image_name() {
                return navigation_image_name;
            }

            public void setNavigation_image_name(String navigation_image_name) {
                this.navigation_image_name = navigation_image_name;
            }

            public String getNavigation_name() {
                return navigation_name;
            }

            public void setNavigation_name(String navigation_name) {
                this.navigation_name = navigation_name;
            }

            public String getNavigation_type() {
                return navigation_type;
            }

            public void setNavigation_type(String navigation_type) {
                this.navigation_type = navigation_type;
            }

            public String getNavigation_data() {
                return navigation_data;
            }

            public void setNavigation_data(String navigation_data) {
                this.navigation_data = navigation_data;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }

    public static class Home1Bean {
        /**
         * title : 艾特头条
         * style :
         * image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06292391541829360.jpg
         * type : special
         * data :
         */

        private String title;
        private String style;
        private String image;
        private String type;
        private String data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


    public static class Home3Bean {
        /**
         * title :
         * style :
         * item : [{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06347454115237305.png","type":"","data":""},{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06347454235063577.png","type":"","data":""},{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06347454359831231.png","type":"","data":""},{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06347454489484786.png","type":"","data":""}]
         */

        private String title;
        private String style;
        private List<ItemBean> item;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06347454115237305.png
             * type :
             * data :
             */

            private String image;
            private String type;
            private String data;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }

    public static class Home4Bean {


        /**
         * title : 模型版块布局D
         * style :
         * rectangle1_image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06365735859544812.jpg
         * rectangle1_type :
         * rectangle1_data :
         * rectangle2_image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06365735922699473.jpg
         * rectangle2_type :
         * rectangle2_data :
         * square_image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06365736149307945.jpeg
         * square_type :
         * square_data :
         */

        private String title;
        private String style;
        private String rectangle1_image;
        private String rectangle1_type;
        private String rectangle1_data;
        private String rectangle2_image;
        private String rectangle3_image;
        private String rectangle2_type;

        public String getRectangle3_image() {
            return rectangle3_image;
        }

        public void setRectangle3_image(String rectangle3_image) {
            this.rectangle3_image = rectangle3_image;
        }

        public String getRectangle3_type() {
            return rectangle3_type;
        }

        public void setRectangle3_type(String rectangle3_type) {
            this.rectangle3_type = rectangle3_type;
        }

        public String getRectangle3_data() {
            return rectangle3_data;
        }

        public void setRectangle3_data(String rectangle3_data) {
            this.rectangle3_data = rectangle3_data;
        }

        private String rectangle3_type;
        private String rectangle2_data;
        private String rectangle3_data;
        private String square_image;
        private String square_type;
        private String square_data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getRectangle1_image() {
            return rectangle1_image;
        }

        public void setRectangle1_image(String rectangle1_image) {
            this.rectangle1_image = rectangle1_image;
        }

        public String getRectangle1_type() {
            return rectangle1_type;
        }

        public void setRectangle1_type(String rectangle1_type) {
            this.rectangle1_type = rectangle1_type;
        }

        public String getRectangle1_data() {
            return rectangle1_data;
        }

        public void setRectangle1_data(String rectangle1_data) {
            this.rectangle1_data = rectangle1_data;
        }

        public String getRectangle2_image() {
            return rectangle2_image;
        }

        public void setRectangle2_image(String rectangle2_image) {
            this.rectangle2_image = rectangle2_image;
        }

        public String getRectangle2_type() {
            return rectangle2_type;
        }

        public void setRectangle2_type(String rectangle2_type) {
            this.rectangle2_type = rectangle2_type;
        }

        public String getRectangle2_data() {
            return rectangle2_data;
        }

        public void setRectangle2_data(String rectangle2_data) {
            this.rectangle2_data = rectangle2_data;
        }

        public String getSquare_image() {
            return square_image;
        }

        public void setSquare_image(String square_image) {
            this.square_image = square_image;
        }

        public String getSquare_type() {
            return square_type;
        }

        public void setSquare_type(String square_type) {
            this.square_type = square_type;
        }

        public String getSquare_data() {
            return square_data;
        }

        public void setSquare_data(String square_data) {
            this.square_data = square_data;
        }
    }

    public static class Home5Bean {


        /**
         * title : 模型版块布局E
         * item : [{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06365736324907908.jpg","type":"","data":""}]
         */

        private String title;
        private List<ItemBean> item;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06365736324907908.jpg
             * type :
             * data :
             */

            private String image;
            private String type;
            private String data;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }

    public static class Home6Bean {


        /**
         * title : 模型版块布局E
         * item : [{"image":"https://daluxmall.com/data/upload/mobile/special/s0/s0_06365736678465954.png","type":"","data":""}]
         */

        private String title;
        private List<ItemBean> item;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06365736678465954.png
             * type :
             * data :
             */

            private String image;
            private String type;
            private String data;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }

    public static class Home2Bean {

        /**
         * title : 艾特头条
         * style : 艾特头条
         * square_image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06292375891278913.jpg
         * square_type :
         * square_data :
         * rectangle1_image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06292371386264324.jpg
         * rectangle1_type :
         * rectangle1_data :
         * rectangle2_image : https://daluxmall.com/data/upload/mobile/special/s0/s0_06292371586246158.jpg
         * rectangle2_type :
         * rectangle2_data :
         */

        private String title;
        private String style;
        private String square_image;
        private String square_type;
        private String square_data;
        private String rectangle1_image;
        private String rectangle1_type;
        private String rectangle1_data;
        private String rectangle2_image;
        private String rectangle2_type;
        private String rectangle2_data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getSquare_image() {
            return square_image;
        }

        public void setSquare_image(String square_image) {
            this.square_image = square_image;
        }

        public String getSquare_type() {
            return square_type;
        }

        public void setSquare_type(String square_type) {
            this.square_type = square_type;
        }

        public String getSquare_data() {
            return square_data;
        }

        public void setSquare_data(String square_data) {
            this.square_data = square_data;
        }

        public String getRectangle1_image() {
            return rectangle1_image;
        }

        public void setRectangle1_image(String rectangle1_image) {
            this.rectangle1_image = rectangle1_image;
        }

        public String getRectangle1_type() {
            return rectangle1_type;
        }

        public void setRectangle1_type(String rectangle1_type) {
            this.rectangle1_type = rectangle1_type;
        }

        public String getRectangle1_data() {
            return rectangle1_data;
        }

        public void setRectangle1_data(String rectangle1_data) {
            this.rectangle1_data = rectangle1_data;
        }

        public String getRectangle2_image() {
            return rectangle2_image;
        }

        public void setRectangle2_image(String rectangle2_image) {
            this.rectangle2_image = rectangle2_image;
        }

        public String getRectangle2_type() {
            return rectangle2_type;
        }

        public void setRectangle2_type(String rectangle2_type) {
            this.rectangle2_type = rectangle2_type;
        }

        public String getRectangle2_data() {
            return rectangle2_data;
        }

        public void setRectangle2_data(String rectangle2_data) {
            this.rectangle2_data = rectangle2_data;
        }
    }

    public static class GoodsBean {
        /**
         * title :
         * style :
         * item : [{"goods_id":"6","goods_name":"柬埔寨商品","goods_promotion_type":"0","goods_promotion_price":"1.00","goods_image":"https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/24/1_06304950184013577_240.jpg","goods_marketprice":"1.00","level_0_price":"1.00","level_1_price":"1.00","level_2_price":"1.00","level_3_price":"1.00","level_0_auth_price":"1.00","level_1_auth_price":"1.00","level_2_auth_price":"1.00","level_3_auth_price":"1.00","goods_price":"1.00","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=6"},{"goods_id":"34","goods_name":"第三方","goods_promotion_type":"0","goods_promotion_price":"0.05","goods_image":"https://daluxmall.com/data/upload/shop/common/default_goods_image_240.gif","goods_marketprice":"0.05","level_0_price":"0.00","level_1_price":"0.00","level_2_price":"0.00","level_3_price":"0.00","level_0_auth_price":"0.00","level_1_auth_price":"0.00","level_2_auth_price":"0.00","level_3_auth_price":"0.00","goods_price":"0.05","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=34"},{"goods_id":"33","goods_name":"第三方","goods_promotion_type":"0","goods_promotion_price":"0.05","goods_image":"https://daluxmall.com/data/upload/shop/common/default_goods_image_240.gif","goods_marketprice":"0.05","level_0_price":"0.00","level_1_price":"0.00","level_2_price":"0.00","level_3_price":"0.00","level_0_auth_price":"0.00","level_1_auth_price":"0.00","level_2_auth_price":"0.00","level_3_auth_price":"0.00","goods_price":"0.05","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=33"},{"goods_id":"32","goods_name":"第三方","goods_promotion_type":"0","goods_promotion_price":"0.05","goods_image":"https://daluxmall.com/data/upload/shop/common/default_goods_image_240.gif","goods_marketprice":"0.05","level_0_price":"0.00","level_1_price":"0.00","level_2_price":"0.00","level_3_price":"0.00","level_0_auth_price":"0.00","level_1_auth_price":"0.00","level_2_auth_price":"0.00","level_3_auth_price":"0.00","goods_price":"0.05","is_own_shop":"0","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=32"}]
         */

        private String title;
        private String style;
        private List<ItemBeanXX> item;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public List<ItemBeanXX> getItem() {
            return item;
        }

        public void setItem(List<ItemBeanXX> item) {
            this.item = item;
        }

        public static class ItemBeanXX {
            /**
             * goods_id : 6
             * goods_name : 柬埔寨商品
             * goods_promotion_type : 0
             * goods_promotion_price : 1.00
             * goods_image : https://daluxmall.com/data/upload/shop/store/goods/1/2019/12/24/1_06304950184013577_240.jpg
             * goods_marketprice : 1.00
             * level_0_price : 1.00
             * level_1_price : 1.00
             * level_2_price : 1.00
             * level_3_price : 1.00
             * level_0_auth_price : 1.00
             * level_1_auth_price : 1.00
             * level_2_auth_price : 1.00
             * level_3_auth_price : 1.00
             * goods_price : 1.00
             * is_own_shop : 0
             * groupbuy_info : null
             * xianshi_info : null
             * miaosha_info : []
             * spellgroup_info : []
             * goods_url : https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=6
             */

            private String goods_id;
            private String goods_name;
            private String goods_promotion_type;
            private String goods_promotion_price;
            private String goods_image;
            private String goods_marketprice;
            private String level_0_price;
            private String level_1_price;
            private String level_2_price;
            private String level_3_price;
            private String level_0_auth_price;
            private String level_1_auth_price;
            private String level_2_auth_price;
            private String level_3_auth_price;
            private String goods_price;
            private String is_own_shop;
            private Object groupbuy_info;
            private Object xianshi_info;
            private String goods_url;
            private Object miaosha_info;
            private Object spellgroup_info;

            public String getGoods_id() {
                return goods_id;
            }

            public Object getSpellgroup_info() {
                return spellgroup_info;
            }

            public void setSpellgroup_info(Object spellgroup_info) {
                this.spellgroup_info = spellgroup_info;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

//            public List<SpellgroupInfoBean> getSpellgroup_info() {
//                return spellgroup_info;
//            }
//
//            public void setSpellgroup_info(List<SpellgroupInfoBean> spellgroup_info) {
//                this.spellgroup_info = spellgroup_info;
//            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_promotion_type() {
                return goods_promotion_type;
            }

            public void setGoods_promotion_type(String goods_promotion_type) {
                this.goods_promotion_type = goods_promotion_type;
            }

            public String getGoods_promotion_price() {
                return goods_promotion_price;
            }

            public void setGoods_promotion_price(String goods_promotion_price) {
                this.goods_promotion_price = goods_promotion_price;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public String getLevel_0_price() {
                return level_0_price;
            }

            public void setLevel_0_price(String level_0_price) {
                this.level_0_price = level_0_price;
            }

            public String getLevel_1_price() {
                return level_1_price;
            }

            public void setLevel_1_price(String level_1_price) {
                this.level_1_price = level_1_price;
            }

            public String getLevel_2_price() {
                return level_2_price;
            }

            public void setLevel_2_price(String level_2_price) {
                this.level_2_price = level_2_price;
            }

            public String getLevel_3_price() {
                return level_3_price;
            }

            public void setLevel_3_price(String level_3_price) {
                this.level_3_price = level_3_price;
            }

            public String getLevel_0_auth_price() {
                return level_0_auth_price;
            }

            public void setLevel_0_auth_price(String level_0_auth_price) {
                this.level_0_auth_price = level_0_auth_price;
            }

            public String getLevel_1_auth_price() {
                return level_1_auth_price;
            }

            public void setLevel_1_auth_price(String level_1_auth_price) {
                this.level_1_auth_price = level_1_auth_price;
            }

            public String getLevel_2_auth_price() {
                return level_2_auth_price;
            }

            public void setLevel_2_auth_price(String level_2_auth_price) {
                this.level_2_auth_price = level_2_auth_price;
            }

            public String getLevel_3_auth_price() {
                return level_3_auth_price;
            }

            public void setLevel_3_auth_price(String level_3_auth_price) {
                this.level_3_auth_price = level_3_auth_price;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getIs_own_shop() {
                return is_own_shop;
            }

            public void setIs_own_shop(String is_own_shop) {
                this.is_own_shop = is_own_shop;
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

            public Object getMiaosha_info() {
                return miaosha_info;
            }

            public void setMiaosha_info(Object miaosha_info) {
                this.miaosha_info = miaosha_info;
            }


            public static class SpellgroupInfoBean {

                /**
                 * SpellGroup_ID : 1
                 * goods_id : 19
                 * goods_name : Tgsppppp
                 * goods_image : 2019/12/31/1_06311170384968434_240.jpg
                 * goods_price : 100.00
                 * SpellGroup_name : Let's go to pin tun
                 * SpellGroup_startDate : 1578153600
                 * SpellGroup_endDate : 1580400000
                 * SpellGroup_personNum : 5
                 * SpellGroup_price : 20.00
                 * SpellGroup_hour : 24
                 * SpellGroup_max : 0
                 * store_id : 1
                 * store_name : jpz01
                 * member_id : 5
                 * member_name : jpz01
                 * Addtime : 1578107719
                 * Status : 1
                 * state_text : 正常
                 * state_flag : buy-now
                 * editable : true
                 */

                private String SpellGroup_ID;
                private String goods_id;
                private String goods_name;
                private String goods_image;
                private String goods_price;
                private String SpellGroup_name;
                private String SpellGroup_startDate;
                private String SpellGroup_endDate;
                private String SpellGroup_personNum;
                private String SpellGroup_price;
                private String SpellGroup_hour;
                private String SpellGroup_max;
                private String store_id;
                private String store_name;
                private String member_id;
                private String member_name;
                private String Addtime;
                private String Status;
                private String state_text;
                private String state_flag;
                private boolean editable;

                public String getSpellGroup_ID() {
                    return SpellGroup_ID;
                }

                public void setSpellGroup_ID(String SpellGroup_ID) {
                    this.SpellGroup_ID = SpellGroup_ID;
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

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getSpellGroup_name() {
                    return SpellGroup_name;
                }

                public void setSpellGroup_name(String SpellGroup_name) {
                    this.SpellGroup_name = SpellGroup_name;
                }

                public String getSpellGroup_startDate() {
                    return SpellGroup_startDate;
                }

                public void setSpellGroup_startDate(String SpellGroup_startDate) {
                    this.SpellGroup_startDate = SpellGroup_startDate;
                }

                public String getSpellGroup_endDate() {
                    return SpellGroup_endDate;
                }

                public void setSpellGroup_endDate(String SpellGroup_endDate) {
                    this.SpellGroup_endDate = SpellGroup_endDate;
                }

                public String getSpellGroup_personNum() {
                    return SpellGroup_personNum;
                }

                public void setSpellGroup_personNum(String SpellGroup_personNum) {
                    this.SpellGroup_personNum = SpellGroup_personNum;
                }

                public String getSpellGroup_price() {
                    return SpellGroup_price;
                }

                public void setSpellGroup_price(String SpellGroup_price) {
                    this.SpellGroup_price = SpellGroup_price;
                }

                public String getSpellGroup_hour() {
                    return SpellGroup_hour;
                }

                public void setSpellGroup_hour(String SpellGroup_hour) {
                    this.SpellGroup_hour = SpellGroup_hour;
                }

                public String getSpellGroup_max() {
                    return SpellGroup_max;
                }

                public void setSpellGroup_max(String SpellGroup_max) {
                    this.SpellGroup_max = SpellGroup_max;
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

                public String getMember_id() {
                    return member_id;
                }

                public void setMember_id(String member_id) {
                    this.member_id = member_id;
                }

                public String getMember_name() {
                    return member_name;
                }

                public void setMember_name(String member_name) {
                    this.member_name = member_name;
                }

                public String getAddtime() {
                    return Addtime;
                }

                public void setAddtime(String Addtime) {
                    this.Addtime = Addtime;
                }

                public String getStatus() {
                    return Status;
                }

                public void setStatus(String Status) {
                    this.Status = Status;
                }

                public String getState_text() {
                    return state_text;
                }

                public void setState_text(String state_text) {
                    this.state_text = state_text;
                }

                public String getState_flag() {
                    return state_flag;
                }

                public void setState_flag(String state_flag) {
                    this.state_flag = state_flag;
                }

                public boolean isEditable() {
                    return editable;
                }

                public void setEditable(boolean editable) {
                    this.editable = editable;
                }
            }
        }
    }
}
