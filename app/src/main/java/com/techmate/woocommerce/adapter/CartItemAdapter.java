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

import java.util.List;

import me.gilo.woodroid.models.LineItem;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private static final String TAG = "CartItemAdapter";
    private Context context;
    private List<LineItem> lineItemList;

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    public CartItemAdapter(List<LineItem> lineItemList) {
        this.context = context;
        this.lineItemList = lineItemList;
    }

    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {

        if (lineItemList == null && lineItemList.size() == 0){
            Glide.with(context).load("https://clothinaa.com/wp-content/uploads/2020/11/KR720KSL.jpg").centerInside().into(holder.imgCartItem);
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCartItem, imgMoreoOptions, imgAddItem, imgRemoveItem;
        private TextView txtTitle, txtColor, txtSize, txtQty, txtPrice;
        private int qtyCount = 1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            imgMoreoOptions = itemView.findViewById(R.id.imgMoreoOptions);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtSize = itemView.findViewById(R.id.txtSize);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgAddItem = itemView.findViewById(R.id.imgAddItem);
            imgRemoveItem = itemView.findViewById(R.id.imgRemoveItem);

            imgAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qtyCount++;
                    txtQty.setText(String.valueOf(qtyCount));
                }
            });

            imgRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (qtyCount > 1){
                        qtyCount--;
                        txtQty.setText(String.valueOf(qtyCount));
                    }

                }
            });

        }
    }
}
