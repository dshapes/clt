package com.techmate.woocommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.techmate.woocommerce.ui.HomeActivity;
import com.techmate.woocommerce.ui.ProductDetailActivity;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.List;

public class YouMayLikeAdapter extends RecyclerView.Adapter<YouMayLikeAdapter.ViewHolder> {

    private static final String TAG = "ProductItemAdapter";
    private Context context;
    private List<TrendingProductItem> trendingProductItemList;

    public YouMayLikeAdapter(Context context, List<TrendingProductItem> trendingProductItems) {
        this.context = context;
        this.trendingProductItemList = trendingProductItems;
    }

    @NonNull
    @Override
    public YouMayLikeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull YouMayLikeAdapter.ViewHolder holder, int position) {

        if (trendingProductItemList.get(position) == null) {
            return;
        }

        TrendingProductItem productItem = trendingProductItemList.get(position);

        if (productItem.getImages() != null && productItem.getImages().size() > 0) {
            Glide.with(context).load(productItem.getImages().get(0).getSrc()).into(holder.imgProduct);
        }

        if (!TextUtils.isEmpty(productItem.getName())) {
            holder.txtProductName.setText(productItem.getName());
        }

        if (!TextUtils.isEmpty(productItem.getSalePrice())) {
            holder.txtYourPrice.setText(productItem.getSalePrice());
        }

        if (!TextUtils.isEmpty(productItem.getPrice())) {
            holder.txtOriginalPrice.setText(productItem.getPrice());
            holder.txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.itemView.setOnClickListener((View.OnClickListener) view -> {
            Intent intent = new Intent((Activity) context, ProductDetailActivity.class);
            intent.putExtra(Constants.INTENT_PRODUCT_ID, productItem.getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return trendingProductItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct, imgDelete;
        private TextView txtProductName, txtYourPrice, txtOriginalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtOriginalPrice = itemView.findViewById(R.id.txtOriginalPrice);
            txtYourPrice = itemView.findViewById(R.id.txtYourPrice);
        }
    }

}
