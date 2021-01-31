package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.FlashSaleAdapter;
import com.techmate.woocommerce.adapter.ProductItemAdapter;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.ActivityAllFlashSaleBinding;
import com.techmate.woocommerce.model.FlashSaleItem;
import com.techmate.woocommerce.model.TrendingProductItem;
import com.techmate.woocommerce.utils.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AllFlashSaleActivity extends AppCompatActivity {

    private ActivityAllFlashSaleBinding binding;
    private List<FlashSaleItem> flashSaleItemList;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllFlashSaleBinding.inflate(getLayoutInflater());
        context = AllFlashSaleActivity.this;
        setContentView(binding.getRoot());
        getIntentData();
        initClickListener();
    }
    private void initClickListener() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getIntentData() {

        flashSaleItemList = new ArrayList<>();
        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                Bundle bundle = getIntent().getExtras();
                if (bundle.getSerializable(Constants.INTENT_PRODUCTS_LIST) != null){
                    flashSaleItemList.addAll((Collection<? extends FlashSaleItem>) bundle.getSerializable(Constants.INTENT_PRODUCTS_LIST));
                    binding.recyclerProducts.setLayoutManager(new GridLayoutManager(context,2));
                    binding.recyclerProducts.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
                    binding.recyclerProducts.setAdapter(new FlashSaleAdapter(context,flashSaleItemList));
                }
            }
        }
    }
}