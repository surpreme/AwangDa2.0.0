package com.aite.a.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiananshop.a.R;
import com.valy.baselibrary.utils.OnClickLstenerInterface;

import java.util.List;


public class BaseTextViewRecyAdapter extends RecyclerView.Adapter<BaseTextViewRecyAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> messages;

    public BaseTextViewRecyAdapter(Context context, List<String> messages) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.base_text, parent, false));
    }

    private OnClickLstenerInterface.OnRecyClickInterface clickInterface;


    public void setClickInterface(OnClickLstenerInterface.OnRecyClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text.setText(messages.get(position));
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
        TextView text;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);


        }
    }
}
