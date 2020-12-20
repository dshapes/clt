package com.techmate.woocommerce.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CartItemAdapter;
import com.techmate.woocommerce.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCartBinding binding;
    private Context context;
    private CartItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgLogo.setOnClickListener(this);
        binding.recyclerCart.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerCart.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set((int) getResources().getDimension(R.dimen._10sdp),10,(int) getResources().getDimension(R.dimen._10sdp),10);
            }
        });
        adapter = new CartItemAdapter(context);
        binding.recyclerCart.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgLogo:
                onBackPressed();
                break;
        }
    }
}