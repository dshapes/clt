package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.utils.Constants;

import java.util.List;

import me.gilo.woodroid.models.Option;


public class SizeColorAdapter extends RecyclerView.Adapter<SizeColorAdapter.ViewHolder> {

    private static final String TAG = "SizeColorAdapter";
    private Context context;
    private List<String> optionList;
    private Constants.BOTTOM_SHEET_VIEW_TYPE view_type;


    public SizeColorAdapter(Context context, List<String> optionList, Constants.BOTTOM_SHEET_VIEW_TYPE viewType) {
        this.context = context;
        this.optionList = optionList;
        this.view_type = viewType;
    }

    @NonNull
    @Override
    public SizeColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_size_color_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (view_type == Constants.BOTTOM_SHEET_VIEW_TYPE.SIZE){

            holder.btnSizeColor.setText(optionList.get(position));

            holder.btnSizeColor.setOnClickListener(view -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.btnSizeColor.setTextColor(context.getColor(R.color.red));
                    holder.btnSizeColor.setStrokeColor(ColorStateList.valueOf(context.getColor(R.color.red)));
                }
            });
        }
        else if (view_type == Constants.BOTTOM_SHEET_VIEW_TYPE.COLOR){

            switch (optionList.get(position)){

                case "Black":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.btnSizeColor.setBackgroundColor(context.getColor(R.color.black));
                    }
                    break;
            }

        }
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialButton btnSizeColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnSizeColor = itemView.findViewById(R.id.btnSizeColor);
        }
    }
}
