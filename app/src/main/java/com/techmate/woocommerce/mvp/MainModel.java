package com.techmate.woocommerce.mvp;

import android.content.Context;

import com.techmate.woocommerce.api.DataManager;
import com.techmate.woocommerce.model.Data;
import com.techmate.woocommerce.model.HomeResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainModel implements ViewPresenter.MainPresenter {

    private static final String TAG = MainModel.class.getSimpleName();
    ViewPresenter.MainView mainView;
    Context mContext;
    DataManager dataManager;

    public MainModel(ViewPresenter.MainView mainView, Context mContext) {
        this.mainView = mainView;
        this.mContext = mContext;
        dataManager = DataManager.getInstance(mContext);
    }

    @Override
    public void getData(String path, Map<String, String> hashMap) {
        mainView.showProgressBar();
        if (mainView.checkInternet()) {
            dataManager.getData(path, hashMap, new ConfirmationCallback() {
                @Override
                public void onSuccess(HomeResponse main) {
                    mainView.mainSuccess(main, path);
                    mainView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    mainView.mainError(err);
                    mainView.hideProgressBar();
                }
            });
        } else {
            mainView.mainValidateError(path);
            mainView.hideProgressBar();
        }
    }

    @Override
    public void getDataByGet(String path) {
        mainView.showProgressBar();
        if (mainView.checkInternet()) {
            dataManager.getDataByGet(path, new ConfirmationCallback() {
                @Override
                public void onSuccess(HomeResponse main) {
                    mainView.mainSuccess(main, path);
                    mainView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    mainView.mainError(err);
                    mainView.hideProgressBar();
                }
            });
        } else {
            mainView.mainValidateError(path);
            mainView.hideProgressBar();
        }
    }

    @Override
    public void getLandingPageData(String path) {

        mainView.showProgressBar();
        if (mainView.checkInternet()) {
            dataManager.getLandingPageData(path, new ConfirmationCallback() {
                @Override
                public void onSuccess(HomeResponse main) {
                    mainView.mainSuccess(main, path);
                    mainView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    mainView.mainError(err);
                    mainView.hideProgressBar();
                }
            });
        } else {
            mainView.mainValidateError(path);
            mainView.hideProgressBar();
        }

    }
}
