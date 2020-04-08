package com.aite.awangdalibrary.ui.activity.productdetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/23
 * @desc:
 */
public class GoodsInformationBean {

    /**
     * goods_info : {"style":"","gc_id_1":"38","gc_id_2":"39","gc_id_3":"40","spec_value_bak":null,"goods_specname":"","goods_price":"102.00","goods_marketprice":"201.00","goods_costprice":"0.50","goods_weight":"0","goods_discount":"50","goods_serial":"2","goods_storage_alarm":"0","transport_id":"0","transport_title":"","goods_freight":"0.00","goods_vat":"0","areaid_1":"0","areaid_2":"0","goods_stcids":"","plateid_top":"0","plateid_bottom":"0","is_virtual":"0","virtual_indate":"0","virtual_limit":"0","virtual_invalid_refund":"0","is_fcode":"0","is_appoint":"0","appoint_satedate":"0","is_presell":"0","presell_deliverdate":"0","is_own_shop":"0","level_0_price":"102.00","level_1_price":"102.00","level_2_price":"102.00","level_3_price":"102.00","level_0_auth_price":"102.00","level_1_auth_price":"102.00","level_2_auth_price":"102.00","level_3_auth_price":"102.00","is_more_discount":"0","goods_type":"0","parent_commonid":"0","hotel_id":"0","is_service":"0","is_installment":"0","installment_money":"N;","is_vat":"0","is_index_select":"0","is_kuajing":"0","origin":null,"kj_service":null,"native":null,"is_native":null,"seaport":null,"clearance":null,"clearance_sn":null,"goods_points_offset":"0","is_visit_comm":"0","is_Independent_comm":"0","is_deleted":"0","goods_labels_ids":null,"third_party_url":"","goods_video":"","language_id":"2","goods_name":"鸡蛋，小鸡","goods_short_title":"","goods_jingle":"","goods_unit":"","spec_name":null,"spec_value":null,"goods_attr":null,"mobile_body":"","goods_id":"196","goods_name_bak":"","goods_short_title_bak":"","goods_jingle_bak":"","goods_unit_bak":null,"store_name_bak":"","goods_promotion_price":"102.00","goods_promotion_type":"0","goods_click":57,"goods_salenum":"3","goods_collect":"0","goods_spec_bak":null,"goods_storage":"96","color_id":"0","evaluation_good_star":"5","evaluation_count":"0","have_gift":"0","parent_goodsid":"0","wholesale_price":"","goods_salenum_vr":"0","goods_start_vr":"0","goods_param_bak":null,"comm_rule":null,"goods_spec":null,"goods_param":"","groupbuy_info":null,"xianshi_info":null,"miaosha_info":[],"spellgroup_info":[],"goods_url":"https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=196","promotion_type":"","goods_name_no_spec":"鸡蛋，小鸡","goods_salenum_all":3,"goods_salenum_vr_all":0,"goods_image_primary":"https://daluxmall.com/data/upload/shop/store/goods/3/2020/03/04/3_06366334106822827_240.jpg","mobile_goods_url":"https://daluxmall.com/wap/index.php?act=goods&op=goods_detail&goods_id=196","area_info":" ","goods_param_info":[]}
     */

    private GoodsInfoBean goods_info;
    private List<GuessLikeBean> goods_commend_list;
    private List<GoodsEvaluateListBean> goods_evaluate_list;
    private List<StoreCallcenterBean> store_callcenter;
    private String isFavorites;
    private ArrayList<VouchersBean> vouchers;

    public String getIsFavorites() {
        return isFavorites;
    }

    public void setIsFavorites(String isFavorites) {
        this.isFavorites = isFavorites;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    private String goods_image;

    public String getGoods_evaluate_count() {
        return goods_evaluate_count;
    }

    public void setGoods_evaluate_count(String goods_evaluate_count) {
        this.goods_evaluate_count = goods_evaluate_count;
    }

    private String goods_evaluate_count;
    /**
     * store_info : {"store_id":"3","store_name":"jpzShop","member_id":"14","member_name":"jpz01","store_qq":"2268510358","avatar":"https://daluxmall.com/data/upload/shop/store/06379422781222890_sm.png","store_credit_percent":90,"store_credit_info":{"store_desccredit":{"text":"描述相符","credit":4.5,"percent":"15%","percent_class":"high","percent_text":"高于"},"store_servicecredit":{"text":"服务态度","credit":4.5,"percent":"18%","percent_class":"high","percent_text":"高于"},"store_deliverycredit":{"text":"发货速度","credit":4.5,"percent":"18%","percent_class":"high","percent_text":"高于"}}}
     */

    private StoreInfoBean store_info;

    public GoodsInfoBean getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(GoodsInfoBean goods_info) {
        this.goods_info = goods_info;
    }


    public StoreInfoBean getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfoBean store_info) {
        this.store_info = store_info;
    }

    public List<GuessLikeBean> getGoods_commend_list() {
        return goods_commend_list;
    }

    public void setGoods_commend_list(List<GuessLikeBean> goods_commend_list) {
        this.goods_commend_list = goods_commend_list;
    }

    public List<GoodsEvaluateListBean> getGoods_evaluate_list() {
        return goods_evaluate_list;
    }

    public void setGoods_evaluate_list(List<GoodsEvaluateListBean> goods_evaluate_list) {
        this.goods_evaluate_list = goods_evaluate_list;
    }

    public List<StoreCallcenterBean> getStore_callcenter() {
        return store_callcenter;
    }

    public void setStore_callcenter(List<StoreCallcenterBean> store_callcenter) {
        this.store_callcenter = store_callcenter;
    }

    public ArrayList<VouchersBean> getVouchers() {
        return vouchers;
    }

    public void setVouchers(ArrayList<VouchersBean> vouchers) {
        this.vouchers = vouchers;
    }

    public static class GoodsInfoBean implements Serializable {
        /**
         * style :
         * gc_id_1 : 38
         * gc_id_2 : 39
         * gc_id_3 : 40
         * spec_value_bak : null
         * goods_specname :
         * goods_price : 102.00
         * goods_marketprice : 201.00
         * goods_costprice : 0.50
         * goods_weight : 0
         * goods_discount : 50
         * goods_serial : 2
         * goods_storage_alarm : 0
         * transport_id : 0
         * transport_title :
         * goods_freight : 0.00
         * goods_vat : 0
         * areaid_1 : 0
         * areaid_2 : 0
         * goods_stcids :
         * plateid_top : 0
         * plateid_bottom : 0
         * is_virtual : 0
         * virtual_indate : 0
         * virtual_limit : 0
         * virtual_invalid_refund : 0
         * is_fcode : 0
         * is_appoint : 0
         * appoint_satedate : 0
         * is_presell : 0
         * presell_deliverdate : 0
         * is_own_shop : 0
         * level_0_price : 102.00
         * level_1_price : 102.00
         * level_2_price : 102.00
         * level_3_price : 102.00
         * level_0_auth_price : 102.00
         * level_1_auth_price : 102.00
         * level_2_auth_price : 102.00
         * level_3_auth_price : 102.00
         * is_more_discount : 0
         * goods_type : 0
         * parent_commonid : 0
         * hotel_id : 0
         * is_service : 0
         * is_installment : 0
         * installment_money : N;
         * is_vat : 0
         * is_index_select : 0
         * is_kuajing : 0
         * origin : null
         * kj_service : null
         * native : null
         * is_native : null
         * seaport : null
         * clearance : null
         * clearance_sn : null
         * goods_points_offset : 0
         * is_visit_comm : 0
         * is_Independent_comm : 0
         * is_deleted : 0
         * goods_labels_ids : null
         * third_party_url :
         * goods_video :
         * language_id : 2
         * goods_name : 鸡蛋，小鸡
         * goods_short_title :
         * goods_jingle :
         * goods_unit :
         * spec_name : null
         * spec_value : null
         * goods_attr : null
         * mobile_body :
         * goods_id : 196
         * goods_name_bak :
         * goods_short_title_bak :
         * goods_jingle_bak :
         * goods_unit_bak : null
         * store_name_bak :
         * goods_promotion_price : 102.00
         * goods_promotion_type : 0
         * goods_click : 57
         * goods_salenum : 3
         * goods_collect : 0
         * goods_spec_bak : null
         * goods_storage : 96
         * color_id : 0
         * evaluation_good_star : 5
         * evaluation_count : 0
         * have_gift : 0
         * parent_goodsid : 0
         * wholesale_price :
         * goods_salenum_vr : 0
         * goods_start_vr : 0
         * goods_param_bak : null
         * comm_rule : null
         * goods_spec : null
         * goods_param :
         * groupbuy_info : null
         * xianshi_info : null
         * miaosha_info : []
         * spellgroup_info : []
         * goods_url : https://daluxmall.com/store/index.php?act=goods&op=index&goods_id=196
         * promotion_type :
         * goods_name_no_spec : 鸡蛋，小鸡
         * goods_salenum_all : 3
         * goods_salenum_vr_all : 0
         * goods_image_primary : https://daluxmall.com/data/upload/shop/store/goods/3/2020/03/04/3_06366334106822827_240.jpg
         * mobile_goods_url : https://daluxmall.com/wap/index.php?act=goods&op=goods_detail&goods_id=196
         * area_info :
         * goods_param_info : []
         */

        private String style;
        private String gc_id_1;
        private String gc_id_2;
        private String gc_id_3;
        private Object spec_value_bak;
        private String goods_specname;
        private String goods_price;
        private String goods_marketprice;
        private String goods_costprice;
        private String goods_weight;
        private String goods_discount;
        private String goods_serial;
        private String goods_storage_alarm;
        private String transport_id;
        private String transport_title;
        private String goods_freight;
        private String goods_vat;
        private String areaid_1;
        private String areaid_2;
        private String goods_stcids;
        private String plateid_top;
        private String plateid_bottom;
        private String is_virtual;
        private String virtual_indate;
        private String virtual_limit;
        private String virtual_invalid_refund;
        private String is_fcode;
        private String is_appoint;
        private String appoint_satedate;
        private String is_presell;
        private String presell_deliverdate;
        private String is_own_shop;
        private String level_0_price;
        private String level_1_price;
        private String level_2_price;
        private String level_3_price;
        private String level_0_auth_price;
        private String level_1_auth_price;
        private String level_2_auth_price;
        private String level_3_auth_price;
        private String is_more_discount;
        private String goods_type;
        private String parent_commonid;
        private String hotel_id;
        private String is_service;
        private String is_installment;
        private String installment_money;
        private String is_vat;
        private String is_index_select;
        private String is_kuajing;
        private Object origin;
        private Object kj_service;

        private List<Goods_spec_info> goods_spec_info;

        public List<SpectListBean> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<SpectListBean> spec_list) {
            this.spec_list = spec_list;
        }

        private List<SpectListBean> spec_list;

        @SerializedName("native")
        private Object nativeX;
        private Object is_native;
        private Object seaport;
        private Object clearance;
        private Object clearance_sn;
        private String goods_points_offset;
        private String is_visit_comm;


        private String user_symbol;
        private String is_Independent_comm;
        private String is_deleted;
        private Object goods_labels_ids;
        private String third_party_url;
        private String goods_video;
        private String language_id;
        private String goods_name;
        private String goods_short_title;
        private String goods_jingle;
        private String goods_unit;
        private Object spec_name;
        private Object spec_value;
        private Object goods_attr;
        private String mobile_body;
        private String goods_id;
        private String goods_name_bak;
        private String goods_short_title_bak;
        private String goods_jingle_bak;
        private Object goods_unit_bak;
        private String store_name_bak;
        private String goods_promotion_price;
        private String goods_promotion_type;
        private int goods_click;
        private String goods_salenum;
        private String goods_collect;
        private Object goods_spec_bak;
        private String goods_storage;
        private String color_id;
        private String evaluation_good_star;
        private String evaluation_count;
        private String have_gift;
        private String parent_goodsid;
        private String wholesale_price;
        private String goods_salenum_vr;
        private String goods_start_vr;
        private Object goods_param_bak;
        private Object comm_rule;
        private Object goods_spec;
        private String goods_param;
        private Object groupbuy_info;
        private Object xianshi_info;
        private String goods_url;
        private String promotion_type;
        private String goods_name_no_spec;
        private int goods_salenum_all;
        private int goods_salenum_vr_all;
        private String goods_image_primary;
        private String mobile_goods_url;
        private String area_info;
        private List<?> miaosha_info;
        private List<?> spellgroup_info;
        private List<?> goods_param_info;

        public String getUser_symbol() {
            return user_symbol;
        }

        public void setUser_symbol(String user_symbol) {
            this.user_symbol = user_symbol;
        }

        public List<Goods_spec_info> getGoods_spec_info() {
            return goods_spec_info;
        }

        public void setGoods_spec_info(List<Goods_spec_info> goods_spec_info) {
            this.goods_spec_info = goods_spec_info;
        }

        public static class SpectListBean {
            public String id;
            public String goods_id;

            public SpectListBean(String id, String goods_id) {
                this.id = id;
                this.goods_id = goods_id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }


        }

        public static class Goods_spec_info {
            private String spec_id;
            private String spec_name;
            private List<Spec_value> spec_value;

            public static class Spec_value {
                public String id;
                public String value;
                public boolean ispick = false;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public boolean isIspick() {
                    return ispick;
                }

                public void setIspick(boolean ispick) {
                    this.ispick = ispick;
                }
            }

            public List<Spec_value> getSpec_value() {
                return spec_value;
            }

            public void setSpec_value(List<Spec_value> spec_value) {
                this.spec_value = spec_value;
            }

            public String getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(String spec_id) {
                this.spec_id = spec_id;
            }

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }


        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getGc_id_1() {
            return gc_id_1;
        }

        public void setGc_id_1(String gc_id_1) {
            this.gc_id_1 = gc_id_1;
        }

        public String getGc_id_2() {
            return gc_id_2;
        }

        public void setGc_id_2(String gc_id_2) {
            this.gc_id_2 = gc_id_2;
        }

        public String getGc_id_3() {
            return gc_id_3;
        }

        public void setGc_id_3(String gc_id_3) {
            this.gc_id_3 = gc_id_3;
        }

        public Object getSpec_value_bak() {
            return spec_value_bak;
        }

        public void setSpec_value_bak(Object spec_value_bak) {
            this.spec_value_bak = spec_value_bak;
        }

        public String getGoods_specname() {
            return goods_specname;
        }

        public void setGoods_specname(String goods_specname) {
            this.goods_specname = goods_specname;
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

        public String getGoods_costprice() {
            return goods_costprice;
        }

        public void setGoods_costprice(String goods_costprice) {
            this.goods_costprice = goods_costprice;
        }

        public String getGoods_weight() {
            return goods_weight;
        }

        public void setGoods_weight(String goods_weight) {
            this.goods_weight = goods_weight;
        }

        public String getGoods_discount() {
            return goods_discount;
        }

        public void setGoods_discount(String goods_discount) {
            this.goods_discount = goods_discount;
        }

        public String getGoods_serial() {
            return goods_serial;
        }

        public void setGoods_serial(String goods_serial) {
            this.goods_serial = goods_serial;
        }

        public String getGoods_storage_alarm() {
            return goods_storage_alarm;
        }

        public void setGoods_storage_alarm(String goods_storage_alarm) {
            this.goods_storage_alarm = goods_storage_alarm;
        }

        public String getTransport_id() {
            return transport_id;
        }

        public void setTransport_id(String transport_id) {
            this.transport_id = transport_id;
        }

        public String getTransport_title() {
            return transport_title;
        }

        public void setTransport_title(String transport_title) {
            this.transport_title = transport_title;
        }

        public String getGoods_freight() {
            return goods_freight;
        }

        public void setGoods_freight(String goods_freight) {
            this.goods_freight = goods_freight;
        }

        public String getGoods_vat() {
            return goods_vat;
        }

        public void setGoods_vat(String goods_vat) {
            this.goods_vat = goods_vat;
        }

        public String getAreaid_1() {
            return areaid_1;
        }

        public void setAreaid_1(String areaid_1) {
            this.areaid_1 = areaid_1;
        }

        public String getAreaid_2() {
            return areaid_2;
        }

        public void setAreaid_2(String areaid_2) {
            this.areaid_2 = areaid_2;
        }

        public String getGoods_stcids() {
            return goods_stcids;
        }

        public void setGoods_stcids(String goods_stcids) {
            this.goods_stcids = goods_stcids;
        }

        public String getPlateid_top() {
            return plateid_top;
        }

        public void setPlateid_top(String plateid_top) {
            this.plateid_top = plateid_top;
        }

        public String getPlateid_bottom() {
            return plateid_bottom;
        }

        public void setPlateid_bottom(String plateid_bottom) {
            this.plateid_bottom = plateid_bottom;
        }

        public String getIs_virtual() {
            return is_virtual;
        }

        public void setIs_virtual(String is_virtual) {
            this.is_virtual = is_virtual;
        }

        public String getVirtual_indate() {
            return virtual_indate;
        }

        public void setVirtual_indate(String virtual_indate) {
            this.virtual_indate = virtual_indate;
        }

        public String getVirtual_limit() {
            return virtual_limit;
        }

        public void setVirtual_limit(String virtual_limit) {
            this.virtual_limit = virtual_limit;
        }

        public String getVirtual_invalid_refund() {
            return virtual_invalid_refund;
        }

        public void setVirtual_invalid_refund(String virtual_invalid_refund) {
            this.virtual_invalid_refund = virtual_invalid_refund;
        }

        public String getIs_fcode() {
            return is_fcode;
        }

        public void setIs_fcode(String is_fcode) {
            this.is_fcode = is_fcode;
        }

        public String getIs_appoint() {
            return is_appoint;
        }

        public void setIs_appoint(String is_appoint) {
            this.is_appoint = is_appoint;
        }

        public String getAppoint_satedate() {
            return appoint_satedate;
        }

        public void setAppoint_satedate(String appoint_satedate) {
            this.appoint_satedate = appoint_satedate;
        }

        public String getIs_presell() {
            return is_presell;
        }

        public void setIs_presell(String is_presell) {
            this.is_presell = is_presell;
        }

        public String getPresell_deliverdate() {
            return presell_deliverdate;
        }

        public void setPresell_deliverdate(String presell_deliverdate) {
            this.presell_deliverdate = presell_deliverdate;
        }

        public String getIs_own_shop() {
            return is_own_shop;
        }

        public void setIs_own_shop(String is_own_shop) {
            this.is_own_shop = is_own_shop;
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

        public String getIs_more_discount() {
            return is_more_discount;
        }

        public void setIs_more_discount(String is_more_discount) {
            this.is_more_discount = is_more_discount;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getParent_commonid() {
            return parent_commonid;
        }

        public void setParent_commonid(String parent_commonid) {
            this.parent_commonid = parent_commonid;
        }

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getIs_service() {
            return is_service;
        }

        public void setIs_service(String is_service) {
            this.is_service = is_service;
        }

        public String getIs_installment() {
            return is_installment;
        }

        public void setIs_installment(String is_installment) {
            this.is_installment = is_installment;
        }

        public String getInstallment_money() {
            return installment_money;
        }

        public void setInstallment_money(String installment_money) {
            this.installment_money = installment_money;
        }

        public String getIs_vat() {
            return is_vat;
        }

        public void setIs_vat(String is_vat) {
            this.is_vat = is_vat;
        }

        public String getIs_index_select() {
            return is_index_select;
        }

        public void setIs_index_select(String is_index_select) {
            this.is_index_select = is_index_select;
        }

        public String getIs_kuajing() {
            return is_kuajing;
        }

        public void setIs_kuajing(String is_kuajing) {
            this.is_kuajing = is_kuajing;
        }

        public Object getOrigin() {
            return origin;
        }

        public void setOrigin(Object origin) {
            this.origin = origin;
        }

        public Object getKj_service() {
            return kj_service;
        }

        public void setKj_service(Object kj_service) {
            this.kj_service = kj_service;
        }

        public Object getNativeX() {
            return nativeX;
        }

        public void setNativeX(Object nativeX) {
            this.nativeX = nativeX;
        }

        public Object getIs_native() {
            return is_native;
        }

        public void setIs_native(Object is_native) {
            this.is_native = is_native;
        }

        public Object getSeaport() {
            return seaport;
        }

        public void setSeaport(Object seaport) {
            this.seaport = seaport;
        }

        public Object getClearance() {
            return clearance;
        }

        public void setClearance(Object clearance) {
            this.clearance = clearance;
        }

        public Object getClearance_sn() {
            return clearance_sn;
        }

        public void setClearance_sn(Object clearance_sn) {
            this.clearance_sn = clearance_sn;
        }

        public String getGoods_points_offset() {
            return goods_points_offset;
        }

        public void setGoods_points_offset(String goods_points_offset) {
            this.goods_points_offset = goods_points_offset;
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

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public Object getGoods_labels_ids() {
            return goods_labels_ids;
        }

        public void setGoods_labels_ids(Object goods_labels_ids) {
            this.goods_labels_ids = goods_labels_ids;
        }

        public String getThird_party_url() {
            return third_party_url;
        }

        public void setThird_party_url(String third_party_url) {
            this.third_party_url = third_party_url;
        }

        public String getGoods_video() {
            return goods_video;
        }

        public void setGoods_video(String goods_video) {
            this.goods_video = goods_video;
        }

        public String getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(String language_id) {
            this.language_id = language_id;
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

        public String getGoods_unit() {
            return goods_unit;
        }

        public void setGoods_unit(String goods_unit) {
            this.goods_unit = goods_unit;
        }

        public Object getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(Object spec_name) {
            this.spec_name = spec_name;
        }

        public Object getSpec_value() {
            return spec_value;
        }

        public void setSpec_value(Object spec_value) {
            this.spec_value = spec_value;
        }

        public Object getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(Object goods_attr) {
            this.goods_attr = goods_attr;
        }

        public String getMobile_body() {
            return mobile_body;
        }

        public void setMobile_body(String mobile_body) {
            this.mobile_body = mobile_body;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name_bak() {
            return goods_name_bak;
        }

        public void setGoods_name_bak(String goods_name_bak) {
            this.goods_name_bak = goods_name_bak;
        }

        public String getGoods_short_title_bak() {
            return goods_short_title_bak;
        }

        public void setGoods_short_title_bak(String goods_short_title_bak) {
            this.goods_short_title_bak = goods_short_title_bak;
        }

        public String getGoods_jingle_bak() {
            return goods_jingle_bak;
        }

        public void setGoods_jingle_bak(String goods_jingle_bak) {
            this.goods_jingle_bak = goods_jingle_bak;
        }

        public Object getGoods_unit_bak() {
            return goods_unit_bak;
        }

        public void setGoods_unit_bak(Object goods_unit_bak) {
            this.goods_unit_bak = goods_unit_bak;
        }

        public String getStore_name_bak() {
            return store_name_bak;
        }

        public void setStore_name_bak(String store_name_bak) {
            this.store_name_bak = store_name_bak;
        }

        public String getGoods_promotion_price() {
            return goods_promotion_price;
        }

        public void setGoods_promotion_price(String goods_promotion_price) {
            this.goods_promotion_price = goods_promotion_price;
        }

        public String getGoods_promotion_type() {
            return goods_promotion_type;
        }

        public void setGoods_promotion_type(String goods_promotion_type) {
            this.goods_promotion_type = goods_promotion_type;
        }

        public int getGoods_click() {
            return goods_click;
        }

        public void setGoods_click(int goods_click) {
            this.goods_click = goods_click;
        }

        public String getGoods_salenum() {
            return goods_salenum;
        }

        public void setGoods_salenum(String goods_salenum) {
            this.goods_salenum = goods_salenum;
        }

        public String getGoods_collect() {
            return goods_collect;
        }

        public void setGoods_collect(String goods_collect) {
            this.goods_collect = goods_collect;
        }

        public Object getGoods_spec_bak() {
            return goods_spec_bak;
        }

        public void setGoods_spec_bak(Object goods_spec_bak) {
            this.goods_spec_bak = goods_spec_bak;
        }

        public String getGoods_storage() {
            return goods_storage;
        }

        public void setGoods_storage(String goods_storage) {
            this.goods_storage = goods_storage;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getEvaluation_good_star() {
            return evaluation_good_star;
        }

        public void setEvaluation_good_star(String evaluation_good_star) {
            this.evaluation_good_star = evaluation_good_star;
        }

        public String getEvaluation_count() {
            return evaluation_count;
        }

        public void setEvaluation_count(String evaluation_count) {
            this.evaluation_count = evaluation_count;
        }

        public String getHave_gift() {
            return have_gift;
        }

        public void setHave_gift(String have_gift) {
            this.have_gift = have_gift;
        }

        public String getParent_goodsid() {
            return parent_goodsid;
        }

        public void setParent_goodsid(String parent_goodsid) {
            this.parent_goodsid = parent_goodsid;
        }

        public String getWholesale_price() {
            return wholesale_price;
        }

        public void setWholesale_price(String wholesale_price) {
            this.wholesale_price = wholesale_price;
        }

        public String getGoods_salenum_vr() {
            return goods_salenum_vr;
        }

        public void setGoods_salenum_vr(String goods_salenum_vr) {
            this.goods_salenum_vr = goods_salenum_vr;
        }

        public String getGoods_start_vr() {
            return goods_start_vr;
        }

        public void setGoods_start_vr(String goods_start_vr) {
            this.goods_start_vr = goods_start_vr;
        }

        public Object getGoods_param_bak() {
            return goods_param_bak;
        }

        public void setGoods_param_bak(Object goods_param_bak) {
            this.goods_param_bak = goods_param_bak;
        }

        public Object getComm_rule() {
            return comm_rule;
        }

        public void setComm_rule(Object comm_rule) {
            this.comm_rule = comm_rule;
        }

        public Object getGoods_spec() {
            return goods_spec;
        }

        public void setGoods_spec(Object goods_spec) {
            this.goods_spec = goods_spec;
        }

        public String getGoods_param() {
            return goods_param;
        }

        public void setGoods_param(String goods_param) {
            this.goods_param = goods_param;
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

        public String getPromotion_type() {
            return promotion_type;
        }

        public void setPromotion_type(String promotion_type) {
            this.promotion_type = promotion_type;
        }

        public String getGoods_name_no_spec() {
            return goods_name_no_spec;
        }

        public void setGoods_name_no_spec(String goods_name_no_spec) {
            this.goods_name_no_spec = goods_name_no_spec;
        }

        public int getGoods_salenum_all() {
            return goods_salenum_all;
        }

        public void setGoods_salenum_all(int goods_salenum_all) {
            this.goods_salenum_all = goods_salenum_all;
        }

        public int getGoods_salenum_vr_all() {
            return goods_salenum_vr_all;
        }

        public void setGoods_salenum_vr_all(int goods_salenum_vr_all) {
            this.goods_salenum_vr_all = goods_salenum_vr_all;
        }

        public String getGoods_image_primary() {
            return goods_image_primary;
        }

        public void setGoods_image_primary(String goods_image_primary) {
            this.goods_image_primary = goods_image_primary;
        }

        public String getMobile_goods_url() {
            return mobile_goods_url;
        }

        public void setMobile_goods_url(String mobile_goods_url) {
            this.mobile_goods_url = mobile_goods_url;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
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

        public List<?> getGoods_param_info() {
            return goods_param_info;
        }

        public void setGoods_param_info(List<?> goods_param_info) {
            this.goods_param_info = goods_param_info;
        }
    }

    public static class StoreInfoBean {
        /**
         * store_id : 3
         * store_name : jpzShop
         * member_id : 14
         * member_name : jpz01
         * store_qq : 2268510358
         * avatar : https://daluxmall.com/data/upload/shop/store/06379422781222890_sm.png
         * store_credit_percent : 90
         * store_credit_info : {"store_desccredit":{"text":"描述相符","credit":4.5,"percent":"15%","percent_class":"high","percent_text":"高于"},"store_servicecredit":{"text":"服务态度","credit":4.5,"percent":"18%","percent_class":"high","percent_text":"高于"},"store_deliverycredit":{"text":"发货速度","credit":4.5,"percent":"18%","percent_class":"high","percent_text":"高于"}}
         */

        private String store_id;
        private String store_name;
        private String member_id;
        private String member_name;
        private String store_qq;
        private String avatar;
        private int store_credit_percent;
        private StoreCreditInfoBean store_credit_info;

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

        public String getStore_qq() {
            return store_qq;
        }

        public void setStore_qq(String store_qq) {
            this.store_qq = store_qq;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getStore_credit_percent() {
            return store_credit_percent;
        }

        public void setStore_credit_percent(int store_credit_percent) {
            this.store_credit_percent = store_credit_percent;
        }

        public StoreCreditInfoBean getStore_credit_info() {
            return store_credit_info;
        }

        public void setStore_credit_info(StoreCreditInfoBean store_credit_info) {
            this.store_credit_info = store_credit_info;
        }

        public static class StoreCreditInfoBean {
            /**
             * store_desccredit : {"text":"描述相符","credit":4.5,"percent":"15%","percent_class":"high","percent_text":"高于"}
             * store_servicecredit : {"text":"服务态度","credit":4.5,"percent":"18%","percent_class":"high","percent_text":"高于"}
             * store_deliverycredit : {"text":"发货速度","credit":4.5,"percent":"18%","percent_class":"high","percent_text":"高于"}
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
                 * credit : 4.5
                 * percent : 15%
                 * percent_class : high
                 * percent_text : 高于
                 */

                private String text;
                private double credit;
                private String percent;
                private String percent_class;
                private String percent_text;

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

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getPercent_class() {
                    return percent_class;
                }

                public void setPercent_class(String percent_class) {
                    this.percent_class = percent_class;
                }

                public String getPercent_text() {
                    return percent_text;
                }

                public void setPercent_text(String percent_text) {
                    this.percent_text = percent_text;
                }
            }

            public static class StoreServicecreditBean {
                /**
                 * text : 服务态度
                 * credit : 4.5
                 * percent : 18%
                 * percent_class : high
                 * percent_text : 高于
                 */

                private String text;
                private double credit;
                private String percent;
                private String percent_class;
                private String percent_text;

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

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getPercent_class() {
                    return percent_class;
                }

                public void setPercent_class(String percent_class) {
                    this.percent_class = percent_class;
                }

                public String getPercent_text() {
                    return percent_text;
                }

                public void setPercent_text(String percent_text) {
                    this.percent_text = percent_text;
                }
            }

            public static class StoreDeliverycreditBean {
                /**
                 * text : 发货速度
                 * credit : 4.5
                 * percent : 18%
                 * percent_class : high
                 * percent_text : 高于
                 */

                private String text;
                private double credit;
                private String percent;
                private String percent_class;
                private String percent_text;

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

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getPercent_class() {
                    return percent_class;
                }

                public void setPercent_class(String percent_class) {
                    this.percent_class = percent_class;
                }

                public String getPercent_text() {
                    return percent_text;
                }

                public void setPercent_text(String percent_text) {
                    this.percent_text = percent_text;
                }
            }
        }
    }

    public static class GoodsEvaluateListBean {
        /**
         * geval_scores : 3
         * geval_content : 还行
         * geval_frommembername : wly123
         * geval_member_avatar : https://daluxmall.com/data/upload/shop/avatar/avatar_17.jpg
         * geval_addtime : 2020-03-10
         */

        private String geval_scores;
        private String geval_content;
        private String geval_frommembername;
        private String geval_member_avatar;
        private String geval_addtime;

        public String getGeval_scores() {
            return geval_scores;
        }

        public void setGeval_scores(String geval_scores) {
            this.geval_scores = geval_scores;
        }

        public String getGeval_content() {
            return geval_content;
        }

        public void setGeval_content(String geval_content) {
            this.geval_content = geval_content;
        }

        public String getGeval_frommembername() {
            return geval_frommembername;
        }

        public void setGeval_frommembername(String geval_frommembername) {
            this.geval_frommembername = geval_frommembername;
        }

        public String getGeval_member_avatar() {
            return geval_member_avatar;
        }

        public void setGeval_member_avatar(String geval_member_avatar) {
            this.geval_member_avatar = geval_member_avatar;
        }

        public String getGeval_addtime() {
            return geval_addtime;
        }

        public void setGeval_addtime(String geval_addtime) {
            this.geval_addtime = geval_addtime;
        }
    }

    public static class StoreCallcenterBean {
        /**
         * type_name : 售前客服
         * callcenter_list : [{"name":"售前2","type":"im","num":"14","img":"https://daluxmall.com/data/resource/img/type3.png"}]
         */

        private String type_name;
        private List<CallcenterListBean> callcenter_list;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public List<CallcenterListBean> getCallcenter_list() {
            return callcenter_list;
        }

        public void setCallcenter_list(List<CallcenterListBean> callcenter_list) {
            this.callcenter_list = callcenter_list;
        }

        public static class CallcenterListBean {
            /**
             * name : 售前2
             * type : im
             * num : 14
             * img : https://daluxmall.com/data/resource/img/type3.png
             */

            private String name;
            private String type;
            private String num;
            private String img;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }

    public static class VouchersBean {
        /**
         * voucher_t_id : 18
         * voucher_t_start_date : 1584674455
         * voucher_t_end_date : 1585584000
         * voucher_t_price : 50
         * voucher_t_limit : 100
         * voucher_t_total : 100
         * voucher_t_giveout : 0
         * voucher_t_points : 1000
         * voucher_t_eachlimit : 1
         * voucher_t_start_date_format : 2020-03-20
         * voucher_t_end_date_format : 2020-03-31
         * has_exchange : false
         * can_exchange : true
         */

        private String voucher_t_id;
        private String voucher_t_start_date;
        private String voucher_t_end_date;
        private String voucher_t_price;
        private int voucher_t_limit;
        private String voucher_t_total;
        private String voucher_t_giveout;
        private String voucher_t_points;
        private String voucher_t_eachlimit;
        private String voucher_t_start_date_format;
        private String voucher_t_end_date_format;
        private boolean has_exchange;
        private boolean can_exchange;
        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }


        public String getVoucher_t_id() {
            return voucher_t_id;
        }

        public void setVoucher_t_id(String voucher_t_id) {
            this.voucher_t_id = voucher_t_id;
        }

        public String getVoucher_t_start_date() {
            return voucher_t_start_date;
        }

        public void setVoucher_t_start_date(String voucher_t_start_date) {
            this.voucher_t_start_date = voucher_t_start_date;
        }

        public String getVoucher_t_end_date() {
            return voucher_t_end_date;
        }

        public void setVoucher_t_end_date(String voucher_t_end_date) {
            this.voucher_t_end_date = voucher_t_end_date;
        }

        public String getVoucher_t_price() {
            return voucher_t_price;
        }

        public void setVoucher_t_price(String voucher_t_price) {
            this.voucher_t_price = voucher_t_price;
        }

        public int getVoucher_t_limit() {
            return voucher_t_limit;
        }

        public void setVoucher_t_limit(int voucher_t_limit) {
            this.voucher_t_limit = voucher_t_limit;
        }

        public String getVoucher_t_total() {
            return voucher_t_total;
        }

        public void setVoucher_t_total(String voucher_t_total) {
            this.voucher_t_total = voucher_t_total;
        }

        public String getVoucher_t_giveout() {
            return voucher_t_giveout;
        }

        public void setVoucher_t_giveout(String voucher_t_giveout) {
            this.voucher_t_giveout = voucher_t_giveout;
        }

        public String getVoucher_t_points() {
            return voucher_t_points;
        }

        public void setVoucher_t_points(String voucher_t_points) {
            this.voucher_t_points = voucher_t_points;
        }

        public String getVoucher_t_eachlimit() {
            return voucher_t_eachlimit;
        }

        public void setVoucher_t_eachlimit(String voucher_t_eachlimit) {
            this.voucher_t_eachlimit = voucher_t_eachlimit;
        }

        public String getVoucher_t_start_date_format() {
            return voucher_t_start_date_format;
        }

        public void setVoucher_t_start_date_format(String voucher_t_start_date_format) {
            this.voucher_t_start_date_format = voucher_t_start_date_format;
        }

        public String getVoucher_t_end_date_format() {
            return voucher_t_end_date_format;
        }

        public void setVoucher_t_end_date_format(String voucher_t_end_date_format) {
            this.voucher_t_end_date_format = voucher_t_end_date_format;
        }

        public boolean isHas_exchange() {
            return has_exchange;
        }

        public void setHas_exchange(boolean has_exchange) {
            this.has_exchange = has_exchange;
        }

        public boolean isCan_exchange() {
            return can_exchange;
        }

        public void setCan_exchange(boolean can_exchange) {
            this.can_exchange = can_exchange;
        }
    }
}
