package com.aite.a.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jiananshop.a.R;
import com.valy.baselibrary.utils.OnClickLstenerInterface;

import java.util.List;


public class BaseImageRecyAdapter extends RecyclerView.Adapter<BaseImageRecyAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> messages;

    public BaseImageRecyAdapter(Context context, List<String> messages) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.base_img, parent, false));
    }

    private OnClickLstenerInterface.OnRecyClickInterface clickInterface;


    public void setClickInterface(OnClickLstenerInterface.OnRecyClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(messages.get(position)).into(holder.image_view);
        holder.itemView.setOnClickListener(v -> {
            if (clickInterface != null)
                clickInterface.getPosition(position);
        });

    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view = itemView.findViewById(R.id.image_view);


        }
    }
}
