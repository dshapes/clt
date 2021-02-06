package com.techmate.woocommerce.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CategoryItemAdapter;
import com.techmate.woocommerce.control.BottomSheetFragment;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.FragmentCategoriesBinding;
import com.techmate.woocommerce.ui.CategoryDetailActivity;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import me.gilo.woodroid.Woocommerce;
import me.gilo.woodroid.models.Category;
import me.gilo.woodroid.models.filters.ProductCategoryFilter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {

    private static final String TAG = "CategoriesFragment";
    private Context context;
    private FragmentCategoriesBinding categoriesBinding;
    private CategoryItemAdapter categoryItemAdapter;
    private List<Category> categoryListItemList;
    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private CategoriesFragment categoriesFragment;

    public static CategoriesFragment getInstance() {
        return new CategoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        categoriesFragment = this;
        categoryListItemList = new ArrayList<>();
        categoriesBinding = FragmentCategoriesBinding.inflate(inflater, container, false);
        initViews();
        return categoriesBinding.getRoot();
    }

    private void initViews() {
        categoriesBinding.recyclerCategories.setLayoutManager(new GridLayoutManager(context, 4));
        gridSpacingItemDecoration = new GridSpacingItemDecoration(4, 10, false);
        categoriesBinding.recyclerCategories.addItemDecoration(gridSpacingItemDecoration);
        categoryItemAdapter = new CategoryItemAdapter(categoriesFragment, context, categoryListItemList, categoriesBinding);
        categoriesBinding.recyclerCategories.setAdapter(categoryItemAdapter);
        apiCall();
    }

    private void apiCall() {

        categoriesBinding.frameProgress.setVisibility(View.VISIBLE);

        Woocommerce woocommerce = Woocommerce.Builder()
                .setSiteUrl(Constants.BASE_URL)
                .setApiVersion(Woocommerce.API_V3)
                .setConsumerKey(Constants.CONSUMER_KEY)
                .setConsumerSecret(Constants.CONSUMER_SECRET)
                .build();

        woocommerce.CategoryRepository().categories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        categoryListItemList.addAll(response.body());
                        categoryItemAdapter.notifyDataSetChanged();
                    }
                }
                categoriesBinding.frameProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Utility.printLog(TAG, t.getMessage());
                categoriesBinding.frameProgress.setVisibility(View.GONE);
            }
        });
    }

    BottomSheetFragment bottomSheetFragment;
    public void showBottomSheetDialog(int parentId, int pos) {

        categoriesBinding.frameProgress.setVisibility(View.VISIBLE);

        List<Category> categoriesByParent = new ArrayList<>();

        Woocommerce woocommerce = Woocommerce.Builder()
                .setSiteUrl(Constants.BASE_URL)
                .setApiVersion(Woocommerce.API_V3)
                .setConsumerKey(Constants.CONSUMER_KEY)
                .setConsumerSecret(Constants.CONSUMER_SECRET)
                .build();

        ProductCategoryFilter productCategoryFilter = new ProductCategoryFilter();
        productCategoryFilter.setParent(new int[]{parentId});

        woocommerce.CategoryRepository().categories(productCategoryFilter).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        categoriesByParent.addAll(response.body());
                        bottomSheetFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_category, categoriesByParent, categoryListItemList.get(pos).getName(), position -> {
                            bottomSheetFragment.dismiss();
                            Intent intent = new Intent(context, CategoryDetailActivity.class);
                            intent.putExtra(Constants.INTENT_CATEGORY_ID, categoriesByParent.get(position).getParent());
                            intent.putExtra(Constants.INTENT_CATEGORY_POS, position);
                            intent.putExtra(Constants.INTENT_CATEGORY_NAME, categoryListItemList.get(pos).getName());
                            startActivity(intent);
                        });

                        if (getFragmentManager() != null) {
                            bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
                        }
                    }
                }
                categoriesBinding.frameProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Utility.printLog(TAG, t.getMessage());
                categoriesBinding.frameProgress.setVisibility(View.GONE);
            }
        });
    }
}
