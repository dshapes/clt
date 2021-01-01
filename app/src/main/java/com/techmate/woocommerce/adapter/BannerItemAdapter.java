package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.model.TrendingProductItem;

import java.util.List;

public class BannerItemAdapter extends RecyclerView.Adapter<BannerItemAdapter.ViewHolder> {

    private Context context;
    private List<String> bannerList;

    public BannerItemAdapter(Context context, List<String> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @NonNull
    @Override
    public BannerItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerItemAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(bannerList.get(position)).into(holder.imgBanner);

    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBanner = itemView.findViewById(R.id.imgBanner);
        }
    }

}
