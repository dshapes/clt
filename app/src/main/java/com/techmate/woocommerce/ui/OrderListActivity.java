package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.OrderListItemAdapter;
import com.techmate.woocommerce.databinding.ActivityOrderListBinding;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOrderListBinding binding;
    private Context context;
    private OrderListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = ActivityOrderListBinding.inflate(getLayoutInflater());
        initViews();
        setContentView(binding.getRoot());
    }

    private void initViews() {
        binding.imgBack.setOnClickListener(this);
        adapter = new OrderListItemAdapter(context);
        binding.recyclerMyOrders.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerMyOrders.setAdapter(adapter);
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