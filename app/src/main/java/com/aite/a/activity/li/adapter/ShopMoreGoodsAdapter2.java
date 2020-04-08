package com.aite.a.activity.li.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.ProductDetailsActivity;
import com.aite.a.bean.ShopHomeBean4;
import com.aite.a.bean.ShopHomeBean5;
import com.bumptech.glide.Glide;
import com.jiananshop.a.R;

import java.util.List;

public class ShopMoreGoodsAdapter2 extends RecyclerView.Adapter<ShopMoreGoodsAdapter2.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<ShopHomeBean4.GoodsListBean> mDatas;

    public ShopMoreGoodsAdapter2(Context context, List<ShopHomeBean4.GoodsListBean> datas) {
        //这里适配器是写给主活动互相调用的方法
        this.context = context;
        this.mDatas = datas;
        this.inflater = LayoutInflater.from(context);

    }

    public ShopMoreGoodsAdapter2.OnclickInterface getOnclickInterface() {
        return onclickInterface;
    }

    public void setOnclickInterface(ShopMoreGoodsAdapter2.OnclickInterface onclickInterface) {
        this.onclickInterface = onclickInterface;
    }

    private ShopMoreGoodsAdapter2.OnclickInterface onclickInterface;

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ShopMoreGoodsAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.guesslike_item_layout, parent, false);
        ShopMoreGoodsAdapter2.ViewHolder viewHolder = new ShopMoreGoodsAdapter2.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopMoreGoodsAdapter2.ViewHolder holder, final int position) {
//        String currentStrLetter = mDatas.get(position).getBrand_name().charAt(0) + "";
        Glide.with(context).load(mDatas.get(position).getGoods_image()).into(holder.gusee_like_img);
        holder.gusee_like_name.setText(mDatas.get(position).getGoods_name());
        holder.gusee_like_price.setText("$" + mDatas.get(position).getGoods_price());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,
                        ProductDetailsActivity.class);
                intent.putExtra("goods_id", mDatas.get(position).getGoods_id());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView gusee_like_price, gusee_like_name;
        ImageView gusee_like_img;
        LinearLayout layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gusee_like_img = itemView.findViewById(R.id.gusee_like_img);
            gusee_like_name = itemView.findViewById(R.id.gusee_like_name);
            gusee_like_price = itemView.findViewById(R.id.gusee_like_price);
            layout = itemView.findViewById(R.id.layout);


        }
    }

    public interface OnclickInterface {
        void getPostion(int postion);
    }

}
