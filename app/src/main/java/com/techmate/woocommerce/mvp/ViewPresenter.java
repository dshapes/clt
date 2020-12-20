package com.techmate.woocommerce.mvp;


import com.techmate.woocommerce.model.HomeResponse;
import java.util.Map;

public interface ViewPresenter {

    interface MainView {

        boolean checkInternet();

        void mainValidateError(String whichError);

        void showProgressBar();

        void hideProgressBar();

        void mainSuccess(HomeResponse responseModel, String whichResponse);

        void mainError(String err);

    }

    interface MainPresenter {

        void getData(String path, Map<String, String> hashMap);
        void getDataByGet(String path);

    }
}
