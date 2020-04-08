package com.aite.a.view;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.aite.a.adapter.GoodsSpecAdapter;
import com.aite.a.model.GoodsDetailsInfo;
import com.bumptech.glide.Glide;
import com.community.utils.ClutterUtils;
import com.jiananshop.a.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.aite.a.base.Mark.GOODS_SETSPEC;


/**
 * 商品规格
 */
public class GoodsSpecPopup extends PopupWindow implements View.OnClickListener {
    private final View numReduce;
    private final View numAdd;
    private final EditText goodNumEt;
    private String goods_storage;
    private Activity mActivity = null;
    private ImageView iv_icon, iv_close;
    private TextView tv_price, tv_instock, tv_name, tv_addcart, tv_buy;
    private ListView lv_spec;
    private GoodsDetailsInfo detailsInfo;
    private GoodsSpecAdapter goodsSpecAdapter;
    private String goodsid, name;//规格ID

    private int curMaxNumber = Integer.MAX_VALUE;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GOODS_SETSPEC://选中ID
                    if (msg.obj != null) {
                        Map<String, String> map = (Map<String, String>) msg.obj;
                        String str = map.get("id");
                        name = map.get("name");
                        Log.i("-------------", "选中ID  " + str);
                        for (int i = 0; i < detailsInfo.spec.spec_list.size(); i++) {//遍历规格ID
                            if (detailsInfo.spec.spec_list.get(i).id.equals(str)) {
                                goodsid = detailsInfo.spec.spec_list.get(i).goods_id;
                                break;
                            }
                        }

                        setGoodsSpec();

                        mmenu.onItemClick(3, goodsid, name, 0,tv_name.getText().toString());
                    }
                    break;
                    default:
            }
        }
    };

    private void setGoodsSpec() {
        int curNumber = -1;
        List<Integer> enablePositions = new ArrayList<>();
        int curSelectNumber = Integer.parseInt(goodNumEt.getText().toString().trim());
        if (detailsInfo.spec_goods_list == null)
            return;
        for (int i = 0; i < detailsInfo.spec_goods_list.size(); i++) {//遍历对应商品
            if (Integer.parseInt(detailsInfo.spec_goods_list.get(i).goods_storage) <= 0){
                enablePositions.add(i);
            }
            if (detailsInfo.spec_goods_list.get(i).goods_id.equals(goodsid)) {
                curNumber = i;
            }
        }

        goodsSpecAdapter.setEnableList(enablePositions);

        if (curNumber >= 0  && curNumber < detailsInfo.spec_goods_list.size()){
            GoodsDetailsInfo.spec_goods_list spec_goods_list = detailsInfo.spec_goods_list.get(curNumber);
            Glide.with(mActivity).load(spec_goods_list.goods_image_url).into(iv_icon);
            tv_price.setText(detailsInfo.goods_info.user_symbol + spec_goods_list.goods_price);
            tv_name.setText(spec_goods_list.goods_name);

            tv_instock.setText(mActivity.getString(R.string.goodsdatails_reminder14) + spec_goods_list.goods_storage +
                    mActivity.getString(R.string.act_a));

            curMaxNumber = Integer.parseInt(goods_storage = spec_goods_list.goods_storage);
            if (curNumber == 0 && curMaxNumber > 0){
                goodNumEt.setText("1");
            }
            if (curSelectNumber > curMaxNumber){
                goodNumEt.setText(spec_goods_list.goods_storage);
            }
        }
    }

    public void uploadOrderInfo(GoodsDetailsInfo detailsInfo){
        String price;
        if (detailsInfo.goods_info.promotion_type == null || detailsInfo.goods_info.promotion_type.length() == 0) {
            price= detailsInfo.goods_info.goods_price;    //无优惠显示原价
        } else {
            price= detailsInfo.goods_info.goods_promotion_price;
        }

        tv_price.setText(detailsInfo.goods_info.user_symbol + price);
        tv_name.setText(detailsInfo.goods_info.goods_name);
        goods_storage = detailsInfo.goods_info.goods_storage;
        tv_instock.setText(mActivity.getString(R.string.goodsdatails_reminder14) + goods_storage + mActivity.getString(R.string.act_a));
        Glide.with(mActivity).load(detailsInfo.goods_info.goods_image_primary).into(iv_icon);
    }

    public GoodsSpecPopup(Activity activity, GoodsDetailsInfo detailsInfo, String id, boolean noAttri) {
        goodsid = id;
        mActivity = activity;
        this.detailsInfo = detailsInfo;
        // 设置SelectPicPopupWindow弹出窗体的宽
        setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//		setHeight((int) (height * 0.3));
        setHeight((int) (ClutterUtils.getScreenHeight(mActivity) * 0.7));
        View view = View.inflate(mActivity, R.layout.dialog_goodsspec, null);
        iv_icon = view.findViewById(R.id.iv_icon);
        tv_price = view.findViewById(R.id.tv_price);
        tv_instock = view.findViewById(R.id.tv_instock);
        tv_name = view.findViewById(R.id.tv_name);
        tv_addcart = view.findViewById(R.id.tv_addcart);
        tv_buy = view.findViewById(R.id.tv_buy);
        lv_spec = view.findViewById(R.id.lv_spec);
        iv_close = view.findViewById(R.id.iv_close);
        numReduce = view.findViewById(R.id.pop_num_reduce);
        numAdd = view.findViewById(R.id.pop_num_add);
        goodNumEt = view.findViewById(R.id.pop_good_num);
        numReduce.setOnClickListener(this);
        numAdd.setOnClickListener(this);
        tv_addcart.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        tv_addcart.setVisibility(View.VISIBLE);
        tv_buy.setText(activity.getResources().getString(R.string.confirm));
        tv_addcart.setText(R.string.cancel);
        // 设置SelectPicPopupWindow的View
        setContentView(view);
//        Glide.with(mActivity).load(detailsInfo.goods_info.goods_image_primary).into(iv_icon);
//        String sellPrice = TextEmptyHandlerUtils.getNumber(detailsInfo.goods_info.goods_exchange_rate_price).equals("0")
//                ? TextEmptyHandlerUtils.getNumber(detailsInfo.goods_info.goods_promotion_price)
//                : TextEmptyHandlerUtils.getNumber(detailsInfo.goods_info.goods_exchange_rate_price);
//        tv_price.setText(detailsInfo.goods_info.user_symbol + detailsInfo.goods_info.goods_promotion_price);
//        tv_name.setText(detailsInfo.goods_info.goods_name);
//        goods_storage = detailsInfo.goods_info.goods_storage;
//        tv_instock.setText(mActivity.getString(R.string.goodsdatails_reminder14) + goods_storage + mActivity.getString(R.string.act_a));
        if (!noAttri) {
//        goodsid=detailsInfo.spec.spec_list.get(0).goods_id;
            lv_spec.setVisibility(View.VISIBLE);
            goodsSpecAdapter = new GoodsSpecAdapter(activity, detailsInfo.goods_info.goods_spec_info, handler);
            lv_spec.setAdapter(goodsSpecAdapter);
        }
        setGoodsSpec();
        // 设置点击视图之外的地方是否取消当前的PopupWindow
        setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        setBackgroundDrawable(dw);
        // 设置PopupWindow弹出窗体动画效果
        //  setAnimationStyle(R.style.AnimBottomPopup);

        setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = mActivity.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                mActivity.getWindow().setAttributes(lp);
            }
        });
    }

    Handler h = new Handler() {
        // 显示玩popup后，改变背景透明度
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    WindowManager.LayoutParams lp = mActivity.getWindow()
                            .getAttributes();
                    lp.alpha = 0.8f;
                    mActivity.getWindow().setAttributes(lp);
                    break;
            }
        }
    };

    private void showEvent() {
//        h.sendEmptyMessage(0);
        h.sendEmptyMessageDelayed(0, 300);
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        showEvent();
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        showEvent();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        showEvent();
    }
    private Toast toast;
    @Override
    public void onClick(View v) {
        int selectedNum;
        if (mmenu == null) return;
        switch (v.getId()) {
            case R.id.tv_addcart://加入购物车
                dismiss();
                /*if (goodsid == null) {
                    if (toast == null) {
                       toast = Toast.makeText(mActivity, null, Toast.LENGTH_SHORT);
                       toast.setText(mActivity.getString(R.string.goods_empty));
                       toast.show();
                    }else {
                        toast.setText(mActivity.getString(R.string.goods_empty));
                        toast.show();
                    }
                    return;
                }
                if ("".equals(goods_storage) || Integer.parseInt(goods_storage) == 0){
                    if (toast == null) {
                        toast = Toast.makeText(mActivity,null, Toast.LENGTH_SHORT);
                        toast.setText( mActivity.getString(R.string.storage_zero));
                        toast.show();
                    }else {
                        toast.setText( mActivity.getString(R.string.storage_zero));
                        toast.show();
                    }
                    return;
                }

                selectedNum = Integer.parseInt(goodNumEt.getText().toString().trim());
                if (goods_storage != null && selectedNum > Integer.parseInt(goods_storage)) {
                    if (toast == null) {
                        toast = Toast.makeText(mActivity, null, Toast.LENGTH_SHORT);
                        toast.setText(mActivity.getString(R.string.num_over));
                        toast.show();
                    }else {
                        toast.setText(mActivity.getString(R.string.num_over));
                        toast.show();
                    }
                    return;
                }
                mmenu.onItemClick(1, goodsid, name, selectedNum, tv_name.getText().toString());
                dismiss();*/
                break;
            case R.id.tv_buy://立即购买
                if (goodsid == null) {
                    if (toast == null) {
                       toast =  Toast.makeText(mActivity, null, Toast.LENGTH_SHORT);
                       toast.setText(mActivity.getString(R.string.goods_empty));
                       toast.show();
                    }else {
                        toast.setText(mActivity.getString(R.string.goods_empty));
                        toast.show();
                    }
                    return;
                }
                if ("".equals(goods_storage) || Integer.parseInt(goods_storage) == 0){
                    if (toast == null) {
                        toast = Toast.makeText(mActivity, null, Toast.LENGTH_SHORT);
                        toast.setText(mActivity.getString(R.string.storage_zero));
                        toast.show();
                    }else {
                        toast.setText(mActivity.getString(R.string.storage_zero));
                        toast.show();
                    }
                    return;
                }

                selectedNum = Integer.parseInt(goodNumEt.getText().toString().trim());
                if (goods_storage != null && selectedNum > Integer.parseInt(goods_storage)) {
                    if (toast == null) {
                        toast = Toast.makeText(mActivity,null, Toast.LENGTH_SHORT);
                        toast.setText( mActivity.getString(R.string.num_over));
                        toast.show();
                    }else {
                        toast.setText( mActivity.getString(R.string.num_over));
                        toast.show();
                    }
                    return;
                }

                if (name == null || name.isEmpty()){
                    if (goodsSpecAdapter!= null)
                        name = goodsSpecAdapter.getId().get("name");
                }

                mmenu.onItemClick(2, goodsid, name, selectedNum, tv_name.getText().toString());
                dismiss();
                break;
            case R.id.iv_close://关闭
                dismiss();
                break;
            case R.id.pop_num_reduce://减商品数量
                numAdd.setEnabled(true);
                int curNum = Integer.parseInt(goodNumEt.getText().toString().trim());
                int nextNum = curNum- 1;
                if (nextNum <= 0) {
                    numReduce.setEnabled(false);
                    nextNum = 0;
                }
                goodNumEt.setText(String.valueOf(nextNum));
                break;
            case R.id.pop_num_add://加商品数量
                numReduce.setEnabled(true);
                int curNumA = Integer.parseInt(goodNumEt.getText().toString().trim());
                int nextNumA = curNumA+ 1;
                if (nextNumA >= curMaxNumber) {
                    numAdd.setEnabled(false);
                    nextNumA = curMaxNumber;
                }
                goodNumEt.setText(String.valueOf(nextNumA));
                break;
        }

    }

    private menu mmenu = null;

    public void setmenu(menu p) {
        mmenu = p;
    }

    public interface menu {
        void onItemClick(int type, String id, String name, int selectedNum, String goodsName);
    }

}
