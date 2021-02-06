package com.techmate.woocommerce.mvp;

import android.content.Context;

import com.techmate.woocommerce.api.DataManager;
import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.Data;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.model.ProductDetailResponse;
import com.techmate.woocommerce.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainModel implements ViewPresenter.MainPresenter {

    private static final String TAG = MainModel.class.getSimpleName();
    ViewPresenter.MainView mainView;
    ViewPresenter.CategoryListView categoryListView;
    ViewPresenter.ProductDetailView productDetailView;
    ViewPresenter.OffersView offersView;
    ViewPresenter.ProfileView profileView;
    Context mContext;
    DataManager dataManager;

    public MainModel(ViewPresenter.MainView mainView, Context mContext) {
        this.mainView = mainView;
        this.mContext = mContext;
        dataManager = DataManager.getInstance(mContext);
    }

    public MainModel(ViewPresenter.CategoryListView categoryListView, Context mContext) {
        this.categoryListView = categoryListView;
        this.mContext = mContext;
        dataManager = DataManager.getInstance(mContext);
    }

    public MainModel(ViewPresenter.ProductDetailView productDetailView, Context mContext) {
        this.productDetailView = productDetailView;
        this.mContext = mContext;
        dataManager = DataManager.getInstance(mContext);
    }

    public MainModel(ViewPresenter.OffersView offersView, Context mContext) {
        this.offersView = offersView;
        this.mContext = mContext;
        dataManager = DataManager.getInstance(mContext);
    }

    public MainModel(ViewPresenter.ProfileView profileView, Context mContext) {
        this.profileView = profileView;
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

    @Override
    public void getCategoryListing(String path) {

        categoryListView.showProgressBar();
        if (categoryListView.checkInternet()) {
            dataManager.getListingData(path, Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET, new CategoryListingCallback() {

                @Override
                public void onSuccess(List<CategoryListItem> home) {
                    categoryListView.mainSuccess(home, path);
                    categoryListView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    categoryListView.mainError(err);
                    categoryListView.hideProgressBar();
                }
            });
        } else {
            categoryListView.mainValidateError(path);
            categoryListView.hideProgressBar();
        }
    }

    @Override
    public void getProductDetails(String path) {

        productDetailView.showProgressBar();
        if (productDetailView.checkInternet()) {
            dataManager.getProductDetail(path, Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET, new ProductDetailCallback() {

                @Override
                public void onSuccess(ProductDetailResponse home) {
                    productDetailView.mainSuccess(home, path);
                    productDetailView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    productDetailView.mainError(err);
                    productDetailView.hideProgressBar();
                }
            });
        } else {
            productDetailView.mainValidateError(path);
            productDetailView.hideProgressBar();
        }

    }

    @Override
    public void getOffers(String path) {

        offersView.showProgressBar();
        if (offersView.checkInternet()) {
            dataManager.getOffers(path, Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET, new OffersCallback() {

                @Override
                public void onSuccess(List<HomeResponse> home) {
                    offersView.mainSuccess(home, path);
                    offersView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    offersView.mainError(err);
                    offersView.hideProgressBar();
                }

            });
        } else {
            offersView.mainValidateError(path);
            offersView.hideProgressBar();
        }

    }

    @Override
    public void updateProfile(String path, Map<String, String> hashMap) {

        profileView.showProgressBar();
        if (profileView.checkInternet()) {
            dataManager.updateProfile(path, hashMap, new ConfirmationCallback() {

                @Override
                public void onSuccess(HomeResponse home) {
                    profileView.mainSuccess(home, path);
                    profileView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    profileView.mainError(err);
                    profileView.hideProgressBar();
                }

            });
        } else {
            profileView.mainValidateError(path);
            profileView.hideProgressBar();
        }

    }

    @Override
    public void updateProfileImage(RequestBody id, MultipartBody.Part imageFile) {

        profileView.showProgressBar();
        if (profileView.checkInternet()) {
            dataManager.updateProfileImage(id, imageFile, new ConfirmationCallback() {

                @Override
                public void onSuccess(HomeResponse home) {
                    profileView.mainSuccess(home, "updateProfileImage");
                    profileView.hideProgressBar();
                }

                @Override
                public void onError(String err) {
                    profileView.mainError(err);
                    profileView.hideProgressBar();
                }

            });
        } else {
            profileView.mainValidateError("updateProfileImage");
            profileView.hideProgressBar();
        }

    }
}
