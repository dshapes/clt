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

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.adapter.CategoryItemAdapter;
import com.techmate.woocommerce.control.GridSpacingItemDecoration;
import com.techmate.woocommerce.databinding.FragmentCategoriesBinding;
import com.techmate.woocommerce.databinding.FragmentOffersBinding;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.Utility;

import java.util.ArrayList;

public class OffersFragment extends Fragment implements ViewPresenter.MainView {

    private static final String TAG = "OffersFragment";
    private FragmentOffersBinding binding;
    private Context context;
    private MainModel mainModel;

    public static OffersFragment getInstance() {
        return new OffersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        binding = FragmentOffersBinding.inflate(getLayoutInflater());
        mainModel = new MainModel(this, context);
        mainModel.getDataByGet(Constants.API_COUPONS);
        return binding.getRoot();
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

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {
        if (!whichResponse.equals(Constants.API_COUPONS)){
            return;
        }
        Utility.printGson(TAG, responseModel);
    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG, "err -- >> " + err);
    }
}
