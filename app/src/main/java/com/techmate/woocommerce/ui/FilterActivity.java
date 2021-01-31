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
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.Attribute;
import me.gilo.woodroid.models.Category;
import me.gilo.woodroid.models.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FilterActivity";
    private ActivityFilterBinding binding;
    private List<Attribute> filterList;
    private LinearLayoutManager linearLayoutManager;
    private FilterAdapter filterAdapter;
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
        filterList = new ArrayList<>();
        filterAdapter = new FilterAdapter(context, filterList);
        binding.recyclerFilter.setAdapter(filterAdapter);

        apiCall();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.raw_filter_item);
        binding.listViewPrice.setVisibility(View.VISIBLE);
        binding.listViewPrice.setAdapter(arrayAdapter);

    }

    private void apiCall() {

//        binding.frameProgress.setVisibility(View.VISIBLE);

        Woocommerce woocommerce = Woocommerce.Builder()
                .setSiteUrl(Constants.BASE_URL)
                .setApiVersion(Woocommerce.API_V3)
                .setConsumerKey(Constants.CONSUMER_KEY)
                .setConsumerSecret(Constants.CONSUMER_SECRET)
                .build();

        woocommerce.AttributeRepository().attributes().enqueue(new Callback<List<Attribute>>() {
            @Override
            public void onResponse(Call<List<Attribute>> call, Response<List<Attribute>> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        filterList.addAll(response.body());
                        filterAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Attribute>> call, Throwable t) {
                Utility.printLog(TAG, t.getMessage());
            }
        });
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgClose) {
            onBackPressed();
        } else if (view.getId() == R.id.btnFilterCancel) {
            onBackPressed();
        }
    }
}