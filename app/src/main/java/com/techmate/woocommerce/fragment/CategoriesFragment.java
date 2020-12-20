package com.techmate.woocommerce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.databinding.FragmentCategoriesBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

public class CategoriesFragment extends Fragment implements ViewPresenter.MainView {

    private static final String TAG = "CategoriesFragment";
    private Context context;
    private MainModel mainModel;
    private FragmentCategoriesBinding categoriesBinding;

    public static CategoriesFragment getInstance() {
        return new CategoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        categoriesBinding =  FragmentCategoriesBinding.inflate(inflater,container,false);
        mainModel = new MainModel(this,context);
        mainModel.getDataByGet(Constants.API_GET_CATEGORIES);
        return categoriesBinding.getRoot();
    }

    @Override
    public boolean checkInternet() {
        return Utility.isConnectedToInternet(context);
    }

    @Override
    public void mainValidateError(String whichError) {
        Utility.printLog(TAG,"whicherror -- >> " + whichError);
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

        Utility.printGson(TAG,responseModel);

    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG,"err -- >> " + err);
    }
}
