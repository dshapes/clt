package com.techmate.woocommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.FilterAdapter;
import com.techmate.woocommerce.adapter.ListAdapter;
import com.techmate.woocommerce.databinding.ActivityFilterBinding;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFilterBinding binding;
    private List<String> filterList;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = ActivityFilterBinding.inflate(getLayoutInflater());
        initViews();
        setContentView(binding.getRoot());
    }

    private void initViews() {

        filterList = new ArrayList<>();
        binding.imgClose.setOnClickListener(this);
        binding.btnFilterCancel.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(context);
        binding.recyclerFilter.setLayoutManager(linearLayoutManager);
        binding.recyclerFilter.setAdapter(new FilterAdapter(context,new ArrayList<>()));

/*
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.raw_filter_item);
        binding.listViewPrice.setVisibility(View.VISIBLE);
        binding.listViewPrice.setAdapter(arrayAdapter);
*/

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgClose) {
            onBackPressed();
        }
        else if (view.getId() == R.id.btnFilterCancel){
            onBackPressed();
        }
    }
}