package com.techmate.woocommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.techmate.woocommerce.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import me.gilo.woodroid.models.Product;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {

    private static final String TAG = "ProductItemAdapter";
    private Context context;
    private List<TrendingProductItem> trendingProductItemList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();
    private boolean isProductListing = true;
    Paint paint = new Paint();

    public ProductItemAdapter(Context context, List<TrendingProductItem> trendingProductItems, boolean isProductListing) {
        this.context = context;
        this.isProductListing = isProductListing;
        this.trendingProductItemList = trendingProductItems;
    }

    public ProductItemAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (isProductListing) {

            Product product = productList.get(position);

            if (product.getImages() != null && product.getImages().size() > 0) {
                Glide.with(context).load(product.getImages().get(0).getSrc()).placeholder(R.drawable.placeholder).into(holder.imgProduct);
            }

            if (!TextUtils.isEmpty(product.getName())) {
                holder.txtProductName.setText(product.getName());
            }

            if (!TextUtils.isEmpty(product.getPrice())) {
                holder.txtYourPrice.setText(product.getPrice());
            }

            holder.txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            if (!TextUtils.isEmpty(product.getPrice_html())) {
                holder.txtOriginalPrice.setText(Html.fromHtml(product.getPrice_html()));
            }

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent((Activity) context, ProductDetailActivity.class);
                intent.putExtra(Constants.INTENT_PRODUCT_ID, product.getId());
                context.startActivity(intent);
            });

        } else {

            TrendingProductItem productItem = trendingProductItemList.get(position);

            if (productItem.getImages() != null && productItem.getImages().size() > 0) {
                Glide.with(context).load(productItem.getImages().get(0).getSrc()).placeholder(R.drawable.placeholder).into(holder.imgProduct);
            }

            if (!TextUtils.isEmpty(productItem.getName())) {
                holder.txtProductName.setText(productItem.getName());
            }

            if (!TextUtils.isEmpty(productItem.getSalePrice())) {
                holder.txtYourPrice.setText(productItem.getSalePrice());
            }

            holder.txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            if (!TextUtils.isEmpty(productItem.getRegularPrice())) {
                holder.txtOriginalPrice.setText(productItem.getRegularPrice());
            }

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent((Activity) context, ProductDetailActivity.class);
                intent.putExtra(Constants.INTENT_PRODUCT_ID, productItem.getId());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return context instanceof HomeActivity ? 2 : trendingProductItemList.size() > 0 ? trendingProductItemList.size() : productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct, imgDelete;
        private LikeButton likeButton;
        private TextView txtProductName, txtYourPrice, txtOriginalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            likeButton = itemView.findViewById(R.id.imgWishlist);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtOriginalPrice = itemView.findViewById(R.id.txtOriginalPrice);
            txtYourPrice = itemView.findViewById(R.id.txtYourPrice);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                paint.setColor(context.getColor(R.color.dark_grey));
                txtOriginalPrice.setLayerPaint(paint);
            }
        }
    }
}
