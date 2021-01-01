package com.techmate.woocommerce.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CategoryItemAdapter;

import com.techmate.woocommerce.databinding.FragmentCategoriesBinding;
import com.techmate.woocommerce.listener.CategoryListener;
import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements ViewPresenter.MainView {

    private static final String TAG = "CategoriesFragment";
    private Context context;
    private MainModel mainModel;
    private FragmentCategoriesBinding categoriesBinding;
    private CategoryItemAdapter categoryItemAdapter;
    private List<CategoryListItem> categoryListItemList;

    public static CategoriesFragment getInstance() {
        return new CategoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        categoryListItemList = new ArrayList<>();
        categoriesBinding = FragmentCategoriesBinding.inflate(inflater, container, false);
        categoriesBinding.recyclerCategories.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(10, 20, 10, 20);
            }
        });
        categoriesBinding.recyclerCategories.setLayoutManager(new GridLayoutManager(context, 4));
        categoryItemAdapter = new CategoryItemAdapter(context, categoryListItemList,categoryListItemList.size());
        categoriesBinding.recyclerCategories.setAdapter(categoryItemAdapter);
        mainModel = new MainModel(this, context);
        mainModel.getDataByGet(Constants.API_GET_CATEGORIES);
        return categoriesBinding.getRoot();

    }

    @Override
    public boolean checkInternet() {
        return Utility.isConnectedToInternet(context);
    }

    @Override
    public void mainValidateError(String whichError) {
        Utility.printLog(TAG, "whicherror -- >> " + whichError);
    }

    @Override
    public void showProgressBar() {
        categoriesBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        categoriesBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {
        Utility.printGson(TAG, responseModel);
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err -- >> " + err);
    }

    @Override
    public void onResume() {
        if (categoryListItemList.size() == 0) {
            categoryListItemList.addAll(Constants.CATEGORIES);
            categoryItemAdapter.setListSize(categoryListItemList.size());
            categoryItemAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }
}
