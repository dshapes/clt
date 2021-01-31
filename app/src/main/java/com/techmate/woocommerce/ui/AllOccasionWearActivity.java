package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.OccassionWearAdapter;
import com.techmate.woocommerce.adapter.ProductItemAdapter;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.ActivityAllOccasionWearBinding;
import com.techmate.woocommerce.databinding.ActivityAllProductListingBinding;
import com.techmate.woocommerce.model.OccasionWearItem;
import com.techmate.woocommerce.model.TrendingProductItem;
import com.techmate.woocommerce.utils.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AllOccasionWearActivity extends AppCompatActivity {

    private ActivityAllOccasionWearBinding binding;
    private List<OccasionWearItem> occasionWearItemList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllOccasionWearBinding.inflate(getLayoutInflater());
        context = AllOccasionWearActivity.this;
        setContentView(binding.getRoot());
        getIntentData();
        initClickListener();
    }

    private void initClickListener() {
        binding.imgBack.setOnClickListener(v -> onBackPressed());
    }

    private void getIntentData() {

        occasionWearItemList = new ArrayList<>();
        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                Bundle bundle = getIntent().getExtras();
                if (bundle.getSerializable(Constants.INTENT_PRODUCTS_LIST) != null){
                    occasionWearItemList.addAll((Collection<? extends OccasionWearItem>) bundle.getSerializable(Constants.INTENT_PRODUCTS_LIST));
                    binding.recyclerProducts.setLayoutManager(new GridLayoutManager(context,2));
                    binding.recyclerProducts.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
                    binding.recyclerProducts.setAdapter(new OccassionWearAdapter(context,occasionWearItemList));
                }
            }
        }
    }
}