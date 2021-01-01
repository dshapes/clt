package com.techmate.woocommerce.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.model.TrendingProductItem;
import com.techmate.woocommerce.ui.HomeActivity;
import com.techmate.woocommerce.ui.ProductDetailActivity;
import com.techmate.woocommerce.utils.Utility;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private static final String TAG = "ProductItemAdapter";
    private List<TrendingProductItem> trendingProductItemList;
    private Context context;

    public WishlistAdapter(Context context, List<TrendingProductItem> trendingProductItems){
        this.context = context;
        this.trendingProductItemList = trendingProductItems;
    }

    @NonNull
    @Override
    public WishlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_product_item, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.ViewHolder holder, int position) {

        if (trendingProductItemList.get(position) == null){
            return;
        }

        TrendingProductItem productItem = trendingProductItemList.get(position);

        if (productItem.getImages() != null && productItem.getImages().size() > 0){
            Glide.with(context).load(productItem.getImages().get(0).getSrc()).into(holder.imgProduct);
        }

        if (!TextUtils.isEmpty(productItem.getName())){
            holder.txtProductName.setText(productItem.getName());
        }

        if (!TextUtils.isEmpty(productItem.getSalePrice())){
            holder.txtYourPrice.setText(productItem.getSalePrice());
        }

        if (!TextUtils.isEmpty(productItem.getPrice())){
            holder.txtOriginalPrice.setText(productItem.getPrice());
            holder.txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.frameWishlist.setVisibility(View.VISIBLE);
        holder.imgWishlist.setLiked(true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.startActivity((Activity) context, ProductDetailActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendingProductItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FrameLayout frameWishlist;
        private ImageView imgProduct, imgDelete;
        private TextView txtProductName, txtYourPrice, txtOriginalPrice;
        private LikeButton imgWishlist;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgWishlist = itemView.findViewById(R.id.imgWishlist);
            frameWishlist = itemView.findViewById(R.id.frameWishlist);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtOriginalPrice = itemView.findViewById(R.id.txtOriginalPrice);
            txtYourPrice = itemView.findViewById(R.id.txtYourPrice);
        }
    }

}
