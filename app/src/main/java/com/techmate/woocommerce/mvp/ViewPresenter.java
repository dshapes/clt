package com.techmate.woocommerce.mvp;


import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.model.ProductDetailResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface ViewPresenter {

    interface MainView {

        boolean checkInternet();

        void mainValidateError(String whichError);

        void showProgressBar();

        void hideProgressBar();

        void mainSuccess(HomeResponse responseModel, String whichResponse);

        void mainError(String err);
    }

    interface CategoryListView {

        boolean checkInternet();

        void mainValidateError(String whichError);

        void showProgressBar();

        void hideProgressBar();

        void mainSuccess(List<CategoryListItem> categoryListItemList, String whichResponse);

        void mainError(String err);
    }

    interface ProductDetailView {

        boolean checkInternet();

        void mainValidateError(String whichError);

        void showProgressBar();

        void hideProgressBar();

        void mainSuccess(ProductDetailResponse productDetailResponse, String whichResponse);

        void mainError(String err);
    }

    interface OffersView {

        boolean checkInternet();

        void mainValidateError(String whichError);

        void showProgressBar();

        void hideProgressBar();

        void mainSuccess(List<HomeResponse> homeResponseList, String whichResponse);

        void mainError(String err);
    }

    interface ProfileView {

        boolean checkInternet();

        void mainValidateError(String whichError);

        void showProgressBar();

        void hideProgressBar();

        void mainSuccess(HomeResponse homeResponse, String whichResponse);

        void mainError(String err);
    }

    interface MainPresenter {

        void getData(String path, Map<String, String> hashMap);
        void getDataByGet(String path);
        void getLandingPageData(String path);
        void getCategoryListing(String path);
        void getProductDetails(String path);
        void getOffers(String path);
        void updateProfile(String path, Map<String, String> hashMap);
        void updateProfileImage(RequestBody id, MultipartBody.Part imageFile);
    }
}
