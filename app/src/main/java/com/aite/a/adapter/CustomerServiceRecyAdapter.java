package com.aite.a.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.bean.ShopChatBean2;
import com.bumptech.glide.Glide;
import com.jiananshop.a.R;
import com.valy.baselibrary.utils.OnClickLstenerInterface;

import java.util.List;


public class CustomerServiceRecyAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater inflater;
    private List<ShopChatBean2> shopChatBeans;
    private static final int VIEW_TYPE_ONE = 1;
    private static final int VIEW_TYPE_TWO = 2;

    public CustomerServiceRecyAdapter(Context context, List<ShopChatBean2> shopChatBeans) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.shopChatBeans = shopChatBeans;
    }


    @Override
    public int getItemViewType(int position) {
        if (shopChatBeans.get(position).getTitle() != null && !shopChatBeans.get(position).getTitle().equals("")) {
            return VIEW_TYPE_ONE;
        } else {
            return VIEW_TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_ONE:
                viewHolder = new TitleViewHolder(inflater.inflate(R.layout.text_layout, parent, false));
                break;
            case VIEW_TYPE_TWO:
                viewHolder = new IconTitleViewHolder(inflater.inflate(R.layout.item_recy_chat_recy, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_ONE:
                ((TitleViewHolder) holder).text.setText(shopChatBeans.get(position).getTitle());
                ((TitleViewHolder) holder).text.setGravity(Gravity.CENTER | Gravity.LEFT);
                ((TitleViewHolder) holder).text.setPadding(20, 0, 0, 0);
                break;
            case VIEW_TYPE_TWO:
                ((IconTitleViewHolder) holder).itemView.setOnClickListener(v -> {
                    if (clickInterface != null) clickInterface.getPosition(position);
                });
                ((IconTitleViewHolder) holder).title.setText(shopChatBeans.get(position).getName());
                Glide.
                        with(context).
                        load(shopChatBeans.
                                get(position).
                                getImg()).
                        into(((IconTitleViewHolder) holder).icon);
                break;

        }
    }

    private OnClickLstenerInterface.OnRecyClickInterface clickInterface;


    public void setClickInterface(OnClickLstenerInterface.OnRecyClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    @Override
    public int getItemCount() {
        return shopChatBeans != null ? shopChatBeans.size() : 0;
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);
        }
    }

    static class IconTitleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        IconTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            icon = itemView.findViewById(R.id.icon_iv);
        }
    }
}
