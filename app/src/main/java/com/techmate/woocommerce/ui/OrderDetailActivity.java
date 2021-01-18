package com.techmate.woocommerce.ui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CartItemAdapter;
import com.techmate.woocommerce.control.MyStepperAdapter;
import com.techmate.woocommerce.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener, StepperLayout.StepperListener {

    private ActivityOrderDetailBinding binding;
    private Context context;
    private CartItemAdapter cartItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        context = this;
        initViews();
        setContentView(binding.getRoot());
    }

    private void initViews() {
        binding.imgBack.setOnClickListener(this);
        binding.recyclerProductsPurchased.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        binding.stepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(),context));
        binding.stepperLayout.setListener(this);
        cartItemAdapter = new CartItemAdapter(context);
        binding.recyclerProductsPurchased.setAdapter(cartItemAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }
}