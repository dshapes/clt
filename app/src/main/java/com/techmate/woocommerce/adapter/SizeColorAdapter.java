package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.techmate.woocommerce.R;

import java.util.List;


public class SizeColorAdapter extends RecyclerView.Adapter<SizeColorAdapter.ViewHolder> {

    private static final String TAG = "SizeColorAdapter";
    private Context context;
    private List<String> list;
    private int type;

    public SizeColorAdapter(Context context, List<String> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public SizeColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_size_color_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (type == 1){

            holder.btnSizeColor.setText(list.get(position));

            holder.btnSizeColor.setOnClickListener(view -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.btnSizeColor.setTextColor(context.getColor(R.color.red));
                    holder.btnSizeColor.setStrokeColor(ColorStateList.valueOf(context.getColor(R.color.red)));
                }
            });
        }
        else if (type == 2){

            switch (position){

                case 0:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.btnSizeColor.setBackgroundColor(context.getColor(R.color.red));
                    }
                    break;
                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.btnSizeColor.setBackgroundColor(context.getColor(R.color.black));
                    }
                    break;
                case 2:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.btnSizeColor.setBackgroundColor(context.getColor(R.color.dark_grey));
                    }
                    break;
                case 3:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.btnSizeColor.setBackgroundColor(context.getColor(R.color.purple_700));
                    }
                    break;
            }

        }


    }

    @Override
    public int getItemCount() {
        switch (type){
            case 1:
                return 5;
            case 2:
                return 4;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialButton btnSizeColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnSizeColor = itemView.findViewById(R.id.btnSizeColor);
        }
    }
}
