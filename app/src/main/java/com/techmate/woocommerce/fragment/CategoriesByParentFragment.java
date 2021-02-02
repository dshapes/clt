package com.techmate.woocommerce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.techmate.woocommerce.adapter.ProductItemAdapter;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.FragmentCategoriesByParentBinding;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.Product;
import me.gilo.woodroid.models.filters.ProductFilter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesByParentFragment extends Fragment {

    private static final String TAG = "CategoriesByParentFragm";
    private FragmentCategoriesByParentBinding binding;
    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private ProductItemAdapter productItemAdapter;
    private Context context;
    private List<Product> productList;
    private int subCategoryId;

    public static CategoriesByParentFragment getInstance(int subCatId) {
        CategoriesByParentFragment categoriesByParentFragment = new CategoriesByParentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.INTENT_SUB_CATEGORY_ID, subCatId);
        categoriesByParentFragment.setArguments(bundle);
        return categoriesByParentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            subCategoryId = bundle.getInt(Constants.INTENT_SUB_CATEGORY_ID);
        }
        binding = FragmentCategoriesByParentBinding.inflate(getLayoutInflater());
        initViews();
        return binding.getRoot();
    }

    private void initViews() {

        productList = new ArrayList<>();
        gridSpacingItemDecoration = new GridSpacingItemDecoration(2, 10, false);
        binding.recyclerProductsByCategory.addItemDecoration(gridSpacingItemDecoration);
        binding.recyclerProductsByCategory.setLayoutManager(new GridLayoutManager(context, 2));
        productItemAdapter = new ProductItemAdapter(context, productList);
        binding.recyclerProductsByCategory.setAdapter(productItemAdapter);
        getProductByCategories();
    }

    private void getProductByCategories() {

        binding.frameProgress.setVisibility(View.VISIBLE);

        Woocommerce woocommerce = Woocommerce.Builder()
                .setSiteUrl(Constants.BASE_URL)
                .setApiVersion(Woocommerce.API_V3)
                .setConsumerKey(Constants.CONSUMER_KEY)
                .setConsumerSecret(Constants.CONSUMER_SECRET)
                .build();

        ProductFilter categoryFilter = new ProductFilter();
        categoryFilter.setCategory(subCategoryId);

        woocommerce.ProductRepository().products(categoryFilter).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        productList.addAll(response.body());
                        productItemAdapter.notifyDataSetChanged();
                    }
                }
                binding.frameProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Utility.printLog(TAG, t.getMessage());
                binding.frameProgress.setVisibility(View.GONE);
            }
        });

    }
}
