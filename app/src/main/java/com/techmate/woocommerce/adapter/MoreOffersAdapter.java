package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techmate.woocommerce.R;

public class MoreOffersAdapter extends RecyclerView.Adapter<MoreOffersAdapter.ViewHolder> {

    private static final String TAG = "MoreOffersAdapter";
    private Context context;

    public MoreOffersAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MoreOffersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_more_offers_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoreOffersAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
