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

import me.gilo.woodroid.models.Attribute;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    private static final String TAG = "FilterAdapter";
    private List<Attribute> attributeList;
    private Context context;

    public FilterAdapter(Context context, List<Attribute> filterList) {
        this.context = context;
        this.attributeList = filterList;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_filter_by, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.btnFilterName.setText(attributeList.get(position).getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.btnFilterName.setTextColor(context.getColor(R.color.red));
            holder.btnFilterName.setIcon(context.getDrawable(R.drawable.ic_price_red));
            holder.btnFilterName.setIconTintResource(R.color.red);
        }

        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return attributeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialButton btnFilterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnFilterName = itemView.findViewById(R.id.btnFilterName);

        }
    }
}
