package com.techmate.woocommerce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techmate.woocommerce.R;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.MainModel;
import com.techmate.woocommerce.mvp.ViewPresenter;
import com.techmate.woocommerce.utils.Utility;

public class HomeFragment extends Fragment implements ViewPresenter.MainView {

    private static final String TAG = "HomeFragment";
    private MainModel mainModel;
    private Context context;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        //mainModel = new MainModel(this,context);
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home,container,false);
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

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void mainSuccess(HomeResponse responseModel, String whichResponse) {

    }

    @Override
    public void mainError(String err) {
        Utility.printLog(TAG,"err -- >> " + err);
    }
}
