package com.techmate.woocommerce.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CartItemAdapter;
import com.techmate.woocommerce.control.BottomSheetCallback;
import com.techmate.woocommerce.control.BottomSheetFragment;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.control.QuantityChangeListener;
import com.techmate.woocommerce.databinding.ActivityCartBinding;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.HashMap;

public class CartActivity extends AppCompatActivity implements View.OnClickListener, BottomSheetCallback, QuantityChangeListener {

    private ActivityCartBinding binding;
    private Context context;
    private CartItemAdapter adapter;
    private GridSpacingItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(this);
        binding.txtPromo.setOnClickListener(this);
        binding.btnBuyNow.setOnClickListener(this);
        binding.recyclerCart.setLayoutManager(new LinearLayoutManager(context));
        itemDecoration = new GridSpacingItemDecoration(1, 10, false);
        binding.recyclerCart.addItemDecoration(itemDecoration);
        adapter = new CartItemAdapter(context);
        binding.recyclerCart.setAdapter(adapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.txtPromo:
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_cart,0,this);
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                break;
            case R.id.btnBuyNow:
                Utility.startActivity((Activity) context, ShipToActivity.class);
                break;
        }
    }

    @Override
    public void onSelected(int position) {

    }

    @Override
    public void onQuantityChanged(HashMap<String, String> map) {

    }
}