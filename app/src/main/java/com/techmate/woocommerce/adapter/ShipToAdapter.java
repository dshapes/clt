package com.techmate.woocommerce.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.ui.AddShippingAddressActivity;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

public class ShipToAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ShipToAdapter";
    private Context context;

    public ShipToAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 2) {
            return new FooterViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_shipping_footer_item, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_shipping_address_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {

            ((ViewHolder) holder).imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                    dialog.setContentView(R.layout.dialog_delete_address_confirmation);
                    dialog.setCancelable(true);
                    dialog.setCanceledOnTouchOutside(true);

                    Button btnDeleteAdd = dialog.findViewById(R.id.btnDeleteAdd);
                    FrameLayout frameCancel = dialog.findViewById(R.id.frameCancel);

                    frameCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    btnDeleteAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });


            ((ViewHolder) holder).imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddShippingAddressActivity.class);
                    intent.putExtra(Constants.INTENT_MODE, Constants.MODE_EDIT);
                    context.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgDelete, imgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);

        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView imgAddShippingAddress;

        public FooterViewHolder(View inflate) {
            super(inflate);

            imgAddShippingAddress = inflate.findViewById(R.id.imgAddShippingAddress);

            imgAddShippingAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddShippingAddressActivity.class);
                    intent.putExtra(Constants.INTENT_MODE, Constants.MODE_ADD);
                    context.startActivity(intent);
                }
            });
        }
    }
}
