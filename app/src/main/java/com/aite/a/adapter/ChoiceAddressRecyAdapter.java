package com.aite.a.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.bean.ChoiceAddressBean;
import com.jiananshop.a.R;
import com.valy.baselibrary.utils.OnClickLstenerInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: valy
 * @datetime: 2020/3/2
 * @desc:
 */
public class ChoiceAddressRecyAdapter extends RecyclerView.Adapter<ChoiceAddressRecyAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<ChoiceAddressBean.AreaListBean> areaListBeansList;

    public ChoiceAddressRecyAdapter(Context context, List<ChoiceAddressBean.AreaListBean> areaListBeansList) {
        this.areaListBeansList = areaListBeansList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.text_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setGravity(Gravity.LEFT | Gravity.CENTER);
        holder.textView.setText(areaListBeansList.get(position).getArea_name());
        holder.itemView.setOnClickListener(v->{
            if (onThingClickInterface != null)
                onThingClickInterface.getThing(position);
        });


    }

    private OnClickLstenerInterface.OnThingClickInterface onThingClickInterface;

    public void setOnThingClickInterface(OnClickLstenerInterface.OnThingClickInterface onThingClickInterface) {
        this.onThingClickInterface = onThingClickInterface;
    }

    @Override
    public int getItemCount() {
        return areaListBeansList == null ? 0 : areaListBeansList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
