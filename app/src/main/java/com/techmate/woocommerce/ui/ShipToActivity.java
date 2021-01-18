package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.ShipToAdapter;
import com.techmate.woocommerce.databinding.ActivityShipToBinding;

public class ShipToActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityShipToBinding binding;
    private Context context;
    private ShipToAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShipToBinding.inflate(getLayoutInflater());
        context = this;
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getString("mode") != null && bundle.getString("mode").equals("display")) {
                //binding.btnNext.setVisibility(View.GONE);
            }
        }

        binding.imgBack.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);
        adapter = new ShipToAdapter(context);
        binding.recyclerShippingAdress.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.btnNext:
                showSuccessScreen();
                break;
        }
    }

    private void showSuccessScreen() {

        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_order_success);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Button btnContinueShopping = dialog.findViewById(R.id.btnContinueShopping);

        btnContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}