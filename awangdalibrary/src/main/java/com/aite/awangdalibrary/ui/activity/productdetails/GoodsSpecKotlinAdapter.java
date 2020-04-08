package com.aite.awangdalibrary.ui.activity.productdetails;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aite.awangdalibrary.R;
import com.aite.awangdalibrary.view.LabelsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品规格
 * Created by mayn on 2018/11/14.
 */
public class GoodsSpecKotlinAdapter extends BaseAdapter {
    private Context mcontext;
    private List<GoodsInformationBean.GoodsInfoBean.Goods_spec_info> goods_spec_info;
//    private Handler handler;

    ////, Handler handler
    public GoodsSpecKotlinAdapter(Context mcontext, List<GoodsInformationBean.GoodsInfoBean.Goods_spec_info> goods_spec_info) {
        this.mcontext = mcontext;
        this.goods_spec_info = goods_spec_info;
//        this.handler = handler;
    }

    /**
     * 修改选中
     *
     * @param id
     * @param spec_value
     */
    private void setPick(int id, List<GoodsInformationBean.GoodsInfoBean.Goods_spec_info.Spec_value> spec_value) {
        for (int j = 0; j < spec_value.size(); j++) {
            spec_value.get(j).ispick = false;
        }
        spec_value.get(id).ispick = true;
        notifyDataSetChanged();
        if (idInterface != null) {
            idInterface.getId(getId());
        }
//        handler.sendMessage(handler.obtainMessage(1122, getId()));
    }

    public IdInterface idInterface;

    public void setMcontext(Context mcontext) {
        this.mcontext = mcontext;
    }

    public interface IdInterface {
        void getId(Map<String, String> map);
    }

    /**
     * 获取选中位置
     *
     * @param spec_value
     * @return
     */
    private int getPickPosition(List<GoodsInformationBean.GoodsInfoBean.Goods_spec_info.Spec_value> spec_value) {
        if (spec_value == null) {
            return 0;
        }else {
            for (int j = 0; j < spec_value.size(); j++) {
                if (spec_value.get(j).ispick) {
                    return j;
                }
            }
//        spec_value.get(0).ispick = true;
            return -1;
        }

    }

    /**
     * 获取选中ID
     *
     * @return
     */
    public Map<String, String> getId() {
        List<Integer> ids = new ArrayList<>();
        StringBuffer name = new StringBuffer();
        int num = 0;
        for (int i = 0; i < goods_spec_info.size(); i++) {
            for (int j = 0; j < goods_spec_info.get(i).getSpec_value().size(); j++) {
                if (goods_spec_info.get(i).getSpec_value().get(j).ispick) {
                    ids.add(Integer.parseInt(goods_spec_info.get(i).getSpec_value().get(j).id));
                    name.append(goods_spec_info.get(i).getSpec_value().get(j).value + "\t");
                    num++;
                    continue;
                }
            }
        }
        if (goods_spec_info.size() != num) return null;
        Collections.sort(ids);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < ids.size(); i++) {
            str.append(ids.get(i) + "|");
        }
        String str2 = str.toString().substring(0, str.toString().length() - 1);
        Map<String, String> map = new HashMap<>();
        map.put("id", str2);
        map.put("name", name.toString());
        return map;
    }

    @Override
    public int getCount() {
        return goods_spec_info == null ? 0 : goods_spec_info.size();
    }

    @Override
    public Object getItem(int i) {
        return goods_spec_info == null ? null : goods_spec_info.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(mcontext, R.layout.item_goodsspec, null);
            new GoodsSpecHolder(view);
        }
        GoodsSpecHolder holder = (GoodsSpecHolder) view.getTag();
        final GoodsInformationBean.GoodsInfoBean.Goods_spec_info goods_spec_info = this.goods_spec_info.get(i);
        holder.tv_name.setText(goods_spec_info.getSpec_name());

        holder.labels.setLabels(goods_spec_info.getSpec_value(), new LabelsView.LabelTextProvider<GoodsInformationBean.GoodsInfoBean.Goods_spec_info.Spec_value>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, GoodsInformationBean.GoodsInfoBean.Goods_spec_info.Spec_value data) {
                return data.value;
            }
        });
        int pickPosition = getPickPosition(goods_spec_info.getSpec_value());
        if (pickPosition != -1) {
            holder.labels.setSelects(pickPosition);//设置选中
        }
        holder.labels.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                setPick(position, goods_spec_info.getSpec_value());
            }
        });
        if (i == getCount() - 1 && enables != null) {
            holder.labels.setEnables(enables);
        }
        return view;
    }

    private int[] enables = null;

    public void setEnableList(List<Integer> positions) {
        if (positions != null && !positions.isEmpty()) {
            enables = new int[positions.size()];
            for (int i = 0; i < positions.size(); i++) {
                enables[i] = positions.get(i);
            }
            notifyDataSetChanged();
        }
    }

    private Menu mmenu = null;

    public void setmenu(Menu p) {
        mmenu = p;
    }

    public interface Menu {
        void onItemClick(int type, String id, String name, int selectedNum, String goodsName);
    }

    class GoodsSpecHolder {
        TextView tv_name;
        LabelsView labels;

        public GoodsSpecHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            labels = view.findViewById(R.id.labels);
            view.setTag(this);
        }
    }
}
