package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.ActivityAddShippingAddressBinding;

public class AddShippingAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddShippingAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddShippingAddressBinding.inflate(getLayoutInflater());
        binding.imgBack.setOnClickListener(this);
        setContentView(binding.getRoot());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }
}