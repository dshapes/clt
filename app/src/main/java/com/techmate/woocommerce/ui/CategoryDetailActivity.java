package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CategoryPagerAdapter;
import com.techmate.woocommerce.databinding.ActivityCategoryDetailBinding;
import com.techmate.woocommerce.utils.Utility;

public class CategoryDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCategoryDetailBinding binding;
    private CategoryPagerAdapter pagerAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryDetailBinding.inflate(getLayoutInflater());
        initViews();
        setContentView(binding.getRoot());
    }

    private void initViews() {

        binding.imgBack.setOnClickListener(this);
        binding.imgCart.setOnClickListener(this);
        binding.imgSort.setOnClickListener(this);
        binding.imgFilter.setOnClickListener(this);
        pagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragments("Line Kurti");
        pagerAdapter.addFragments("Straight Kurti");
        pagerAdapter.addFragments("Round Kurti");
        pagerAdapter.addFragments("Tunic");
        pagerAdapter.addFragments("Silly");

        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
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