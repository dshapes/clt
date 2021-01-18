package com.techmate.woocommerce.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.techmate.woocommerce.model.FlashSaleItem;
import com.techmate.woocommerce.model.TrendingProductItem;
import com.techmate.woocommerce.ui.HomeActivity;
import com.techmate.woocommerce.ui.ProductDetailActivity;
import com.techmate.woocommerce.utils.Utility;

import java.util.List;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.ViewHolder> {

    private static final String TAG = "FlashSaleAdapter";
    private Context context;
    private List<FlashSaleItem> flashSaleItemList;

    public FlashSaleAdapter(Context context, List<FlashSaleItem> flashSaleItemList){
        this.context = context;
        this.flashSaleItemList = flashSaleItemList;
    }

    @NonNull
    @Override
    public FlashSaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlashSaleAdapter.ViewHolder holder, int position) {

        if (flashSaleItemList.get(position) == null){
            return;
        }

        FlashSaleItem productItem = flashSaleItemList.get(position);

        if (productItem.getImages() != null && productItem.getImages().size() > 0){
            Glide.with(context).load(productItem.getImages().get(0).getSrc()).into(holder.imgProduct);
        }

        if (!TextUtils.isEmpty(productItem.getName())){
            holder.txtProductName.setText(productItem.getName());
        }

        if (!TextUtils.isEmpty(productItem.getSalePrice())){
            holder.txtYourPrice.setText(productItem.getSalePrice());
        }

        holder.txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (!TextUtils.isEmpty(productItem.getPrice())){
            holder.txtOriginalPrice.setText(productItem.getPrice());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.startActivity((Activity) context, ProductDetailActivity.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return context instanceof HomeActivity ? 2 : flashSaleItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct,imgDelete;
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
