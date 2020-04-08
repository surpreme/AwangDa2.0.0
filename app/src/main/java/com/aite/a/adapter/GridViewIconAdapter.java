package com.aite.a.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiananshop.a.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridViewIconAdapter extends BaseAdapter {
    private Context context;
    private List<String> imgs;
    private List<String> names;

    public GridViewIconAdapter(Context context, List<String> imgs, List<String> names) {
        this.context = context;
        this.imgs = imgs;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names == null ? 0 : names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View view = null;

        if (convertView == null) {
            view = View.inflate(context,
                    R.layout.item_top_img_bottom_tv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        if (imgs != null && names != null) {
            holder.title.setText(names.get(position));
            Glide.with(context).load(imgs.get(position)).into(holder.icon);
//            holder.icon.setImageResource(imgs[position]);
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.title)
        TextView title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
