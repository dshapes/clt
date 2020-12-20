package com.techmate.woocommerce.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.HomePagerAdapter;
import com.techmate.woocommerce.databinding.ActivityHomeBinding;
import com.techmate.woocommerce.utils.Utility;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pagerHome.setUserInputEnabled(false);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        binding.pagerHome.setAdapter(new HomePagerAdapter(getSupportFragmentManager(),getLifecycle()));
        binding.pagerHome.setOffscreenPageLimit(5);

        binding.imgCart.setOnClickListener(this);
        binding.imgSearch.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                setActionBarTitle(0);
                binding.pagerHome.setCurrentItem(0,false);
                break;
            case R.id.action_categories:
                setActionBarTitle(1);
                binding.pagerHome.setCurrentItem(1,false);
                break;
            case R.id.action_offers:
                setActionBarTitle(2);
                binding.pagerHome.setCurrentItem(2,false);
                break;
            case R.id.action_wishlist:
                setActionBarTitle(3);
                binding.pagerHome.setCurrentItem(3,false);
                break;
            case R.id.action_account:
                setActionBarTitle(4);
                binding.pagerHome.setCurrentItem(4,false);
                break;
        }
        return true;
    }

    private void setActionBarTitle(int position) {

        switch (position){
            case 0:
                binding.txtTitle.setVisibility(View.GONE);
                binding.imgLogo.setVisibility(View.VISIBLE);
                break;
            case 1:
                binding.imgLogo.setVisibility(View.GONE);
                binding.imgSearch.setVisibility(View.VISIBLE);
                binding.imgCart.setVisibility(View.VISIBLE);
                binding.txtTitle.setText(getResources().getString(R.string.category));
                binding.txtTitle.setVisibility(View.VISIBLE);
                break;
            case 2:
                binding.imgLogo.setVisibility(View.GONE);
                binding.imgSearch.setVisibility(View.GONE);
                binding.imgCart.setVisibility(View.VISIBLE);
                binding.txtTitle.setText(getResources().getString(R.string.offers));
                binding.txtTitle.setVisibility(View.VISIBLE);
                break;
            case 3:
                binding.imgLogo.setVisibility(View.GONE);
                binding.imgSearch.setVisibility(View.VISIBLE);
                binding.imgCart.setVisibility(View.VISIBLE);
                binding.txtTitle.setText(getResources().getString(R.string.wishlist));
                binding.txtTitle.setVisibility(View.VISIBLE);
                break;
            case 4:
                binding.imgLogo.setVisibility(View.GONE);
                binding.imgSearch.setVisibility(View.GONE);
                binding.imgCart.setVisibility(View.VISIBLE);
                binding.txtTitle.setText(getResources().getString(R.string.account));
                binding.txtTitle.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgSearch:
                break;
            case R.id.imgCart:
                Utility.startActivity(HomeActivity.this, CartActivity.class);
                break;
        }
    }
}