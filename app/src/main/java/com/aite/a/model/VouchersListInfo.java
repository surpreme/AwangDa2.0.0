package com.aite.a.model;

import java.util.List;

/**
 * 代金券列表
 * 
 * @author Administrator
 *
 */
public class VouchersListInfo {

	public String hasmore;
	public String page_total;
	public datas datas;

	public static class datas {
		public List<voucher_list> voucher_list;

		public static class voucher_list {
			/**
			 * voucher_id : 74
			 * voucher_code : 220638564425105000
			 * voucher_t_type : 1
			 * voucher_t_range : 2
			 * voucher_t_rangeid : 1
			 * voucher_title : 平台代金券2
			 * voucher_desc : 666666
			 * voucher_start_date : 1585220425
			 * voucher_end_date : 1585584000
			 * voucher_price : 50
			 * voucher_limit : 60.00
			 * voucher_state : 1
			 * voucher_order_id : null
			 * voucher_store_id : 0
			 * voucher_t_customimg : https://daluxmall.com/data/upload/shop/voucher/platform/06385643996249294_small.jpg
			 * store_name : Platform-Classification-..
			 * store_id : 0
			 * store_domain : 
			 * voucher_state_text : Unused
			 */

			public String voucher_id;
			public String voucher_code;
			public String voucher_t_type;
			public String voucher_t_range;
			public String voucher_t_rangeid;
			public String voucher_title;
			public String voucher_desc;
			public String voucher_start_date;
			public String voucher_end_date;
			public String voucher_price;
			public String voucher_limit;
			public String voucher_state;
			public Object voucher_order_id;
			public String voucher_store_id;
			public String voucher_t_customimg;
			public String store_name;
			public String store_id;
			public String store_domain;
			public String voucher_state_text;

		
//
//			public String voucher_id;
//			public String voucher_code;
//			public String voucher_title;
//			public String voucher_desc;
//			public String voucher_start_date;
//			public String voucher_end_date;
//			public String voucher_price;
//			public String voucher_limit;
//			public String voucher_state;
//			public String voucher_order_id;
//			public String voucher_store_id;
//			public String store_name;
//			public String store_id;
//			public String member_mobile;
//			public String store_domain;
//			public String voucher_t_customimg;
//			public String voucher_state_text;
		}
	}
}
