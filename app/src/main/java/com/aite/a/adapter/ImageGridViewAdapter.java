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

public class ImageGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> imgs;

    public ImageGridViewAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.size();
    }

    @Override
    public Object getItem(int position) {
        return imgs.get(position);
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
                    R.layout.base_img, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        if (imgs != null ) {
            Glide.with(context).load(imgs.get(position)).into(holder.image_view);
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image_view)
        ImageView image_view;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
