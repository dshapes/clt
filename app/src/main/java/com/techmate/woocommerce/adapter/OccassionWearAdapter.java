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
import com.techmate.woocommerce.model.OccasionWearItem;
import com.techmate.woocommerce.ui.HomeActivity;
import com.techmate.woocommerce.ui.ProductDetailActivity;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.List;

public class OccassionWearAdapter extends RecyclerView.Adapter<OccassionWearAdapter.ViewHolder> {

    private static final String TAG = "OccassionWearAdapter";
    private Context context;
    private List<OccasionWearItem> occasionWearItemList;

    public OccassionWearAdapter(Context context, List<OccasionWearItem> occasionWearItemList){
        this.context = context;
        this.occasionWearItemList = occasionWearItemList;
    }

    @NonNull
    @Override
    public OccassionWearAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OccassionWearAdapter.ViewHolder holder, int position) {

        if (occasionWearItemList.get(position) == null){
            return;
        }

        OccasionWearItem productItem = occasionWearItemList.get(position);

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

        holder.itemView.setOnClickListener((View.OnClickListener) view -> {
            Intent intent = new Intent((Activity) context, ProductDetailActivity.class);
            intent.putExtra(Constants.INTENT_PRODUCT_ID, productItem.getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return context instanceof HomeActivity ? 2 : occasionWearItemList.size();
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
