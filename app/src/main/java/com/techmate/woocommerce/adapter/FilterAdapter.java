package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.techmate.woocommerce.R;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    private static final String TAG = "CartItemAdapter";
    private List<String> filterList;
    private Context context;

    public FilterAdapter(Context context, List<String> filterList) {
        this.context = context;
        this.filterList = filterList;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_filter_by, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (position){
            case 0:
                holder.btnFilterName.setText("Price");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.btnFilterName.setTextColor(context.getColor(R.color.red));
                    holder.btnFilterName.setIcon(context.getDrawable(R.drawable.ic_price_red));
                    holder.btnFilterName.setIconTintResource(R.color.red);
                }
                break;
            case 1:
                holder.btnFilterName.setText("Category");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.btnFilterName.setIcon(context.getDrawable(R.drawable.ic_category));
                    holder.btnFilterName.setIconTintResource(R.color.maroon);
                }
                break;
            case 2:
                holder.btnFilterName.setText("Color");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.btnFilterName.setIcon(context.getDrawable(R.drawable.ic_color));
                    holder.btnFilterName.setIconTintResource(R.color.maroon);
                }
                break;
            case 3:
                holder.btnFilterName.setText("Size");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.btnFilterName.setIcon(context.getDrawable(R.drawable.ic_size));
                    holder.btnFilterName.setIconTintResource(R.color.maroon);
                }
                break;
            case 4:
                holder.btnFilterName.setText("Material");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.btnFilterName.setIcon(context.getDrawable(R.drawable.ic_material));
                    holder.btnFilterName.setIconTintResource(R.color.maroon);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialButton btnFilterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnFilterName = itemView.findViewById(R.id.btnFilterName);

        }
    }
}
