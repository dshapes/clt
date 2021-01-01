package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.ActivityCategoryDetailBinding;
import com.techmate.woocommerce.utils.Utility;

public class CategoryDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCategoryDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        }
    }
}