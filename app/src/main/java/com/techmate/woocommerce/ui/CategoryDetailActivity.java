package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CategoryPagerAdapter;
import com.techmate.woocommerce.databinding.ActivityCategoryDetailBinding;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.List;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.Category;
import me.gilo.woodroid.models.filters.ProductCategoryFilter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CategoryDetailActivity";
    private ActivityCategoryDetailBinding binding;
    private CategoryPagerAdapter pagerAdapter;
    private int categoryId;
    private String categoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryDetailBinding.inflate(getLayoutInflater());
        initViews();
        apiCall();
        setContentView(binding.getRoot());
    }

    private void initViews() {

        binding.imgBack.setOnClickListener(this);
        binding.imgCart.setOnClickListener(this);
        binding.imgSort.setOnClickListener(this);
        binding.imgFilter.setOnClickListener(this);
        pagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager());

        if (getIntent().getExtras() != null) {
            categoryId = getIntent().getExtras().getInt(Constants.INTENT_CATEGORY_ID,0);
            categoryTitle = getIntent().getExtras().getString(Constants.INTENT_CATEGORY_NAME,"");
            binding.txtTitle.setText(categoryTitle);
        }
    }

    private void apiCall() {

        binding.frameProgress.setVisibility(View.VISIBLE);

        Woocommerce woocommerce = Woocommerce.Builder()
                .setSiteUrl(Constants.BASE_URL)
                .setApiVersion(Woocommerce.API_V3)
                .setConsumerKey(Constants.CONSUMER_KEY)
                .setConsumerSecret(Constants.CONSUMER_SECRET)
                .build();

        ProductCategoryFilter categoryFilter = new ProductCategoryFilter();
        categoryFilter.setParent(new int[]{categoryId});

        woocommerce.CategoryRepository().categories(categoryFilter).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        for (Category category : response.body()) {
                            pagerAdapter.addFragments(category.getName());
                            pagerAdapter.setSubCategoryId(category.getId());
                        }
                        binding.viewPager.setAdapter(pagerAdapter);
                        binding.viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
                        binding.tabLayout.setupWithViewPager(binding.viewPager);
                    }
                }
                binding.frameProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                binding.frameProgress.setVisibility(View.GONE);
                Utility.printLog(TAG, t.getMessage());
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.imgSearch:
                break;
            case R.id.imgCart:
                Utility.startActivity(this, CartActivity.class);
                break;
            case R.id.imgSort:
                break;
            case R.id.imgFilter:
                Utility.startActivity(this, FilterActivity.class);
                break;
        }
    }
}