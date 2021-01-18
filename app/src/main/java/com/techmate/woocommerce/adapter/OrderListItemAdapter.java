package com.techmate.woocommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.ui.OrderDetailActivity;
import com.techmate.woocommerce.utils.Utility;

public class OrderListItemAdapter extends RecyclerView.Adapter<OrderListItemAdapter.ViewHolder> {

    private static final String TAG = "OrderListItemAdapter";
    private Context context;

    public OrderListItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_my_orders_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListItemAdapter.ViewHolder holder, int position) {

        holder.btnOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.startActivity((Activity) context, OrderDetailActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialButton btnOrderDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnOrderDetails = itemView.findViewById(R.id.btnOrderDetails);

        }
    }
}
