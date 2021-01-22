package com.techmate.woocommerce.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CategoryItemAdapter;

import com.techmate.woocommerce.control.BottomSheetCallback;
import com.techmate.woocommerce.control.BottomSheetFragment;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;

import com.techmate.woocommerce.databinding.FragmentCategoriesBinding;
import com.techmate.woocommerce.listener.CategoryListener;
import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.ui.CategoryDetailActivity;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements ViewPresenter.MainView, BottomSheetCallback {

    private static final String TAG = "CategoriesFragment";
    private Context context;
    private MainModel mainModel;
    private FragmentCategoriesBinding categoriesBinding;
    private CategoryItemAdapter categoryItemAdapter;
    private List<CategoryListItem> categoryListItemList;
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
        categoriesBinding.recyclerCategories.setLayoutManager(new GridLayoutManager(context, 4));
        gridSpacingItemDecoration = new GridSpacingItemDecoration(4, 10, false);
        categoriesBinding.recyclerCategories.addItemDecoration(gridSpacingItemDecoration);
        categoryItemAdapter = new CategoryItemAdapter(categoriesFragment, context, categoryListItemList, categoryListItemList.size());
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
        if (!whichResponse.equals(Constants.API_GET_CATEGORIES)) {
            return;
        }
        Utility.printLog(TAG, new Gson().toJson(responseModel.getData()));
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err -- >> " + err);
        Toast.makeText(getActivity(), err,Toast.LENGTH_SHORT).show();
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

    public void showBottomSheetDialog() {

        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(R.layout.bottom_sheet_dialog_category, 5, this);
        if (getFragmentManager() != null) {
            bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
        }

    }

    @Override
    public void onSelected(int position) {
        Utility.startActivity((Activity) context, CategoryDetailActivity.class);
    }
}
