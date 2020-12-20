package com.techmate.woocommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.techmate.woocommerce.R;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private static final String TAG = "CartItemAdapter";
    private Context context;

    public CartItemAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCartItem, imgMoreoOptions;
        private TextView txtTitle, txtColor, txtSize, txtQty, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            imgMoreoOptions = itemView.findViewById(R.id.imgMoreoOptions);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtSize = itemView.findViewById(R.id.txtSize);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtPrice = itemView.findViewById(R.id.txtPrice);

        }
    }
}
