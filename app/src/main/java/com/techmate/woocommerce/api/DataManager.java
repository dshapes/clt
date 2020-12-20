package com.techmate.woocommerce.api;

import android.content.Context;

import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.mvp.ConfirmationCallback;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.PrefManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DataManager {

    private String TAG = DataManager.class.getSimpleName();
    private Context context;
    private ApiService apiService;
    private PrefManager prefManager;
    private static DataManager mDataManager;
    private ConfirmationCallback confirmationCallback;

    public DataManager(Context mContext) {
        this.context = mContext;
        prefManager = new PrefManager(mContext);
        apiService = ApiClient.getClient();
    }

    public static DataManager getInstance(Context mContext) {
        if (mDataManager == null) {
            mDataManager = new DataManager(mContext);
        }
        return mDataManager;
    }

    public void getData(String path, Map<String, String> hashMap, final ConfirmationCallback confirmationCallback){

        this.confirmationCallback = confirmationCallback;
        Observable<HomeResponse> mainObservable = apiService.getData(path, hashMap);
        if (mainObservable != null) {
            mainObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(new Consumer<HomeResponse>() {
                        @Override
                        public void accept(HomeResponse main) throws Exception {
                            if (main != null) {
                                confirmationCallback.onSuccess(main);
                            } else {
                                confirmationCallback.onError("Error");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            confirmationCallback.onError("" + throwable.getMessage());
                        }
                    });
        }


    }

    public void getDataByGet(String path, final ConfirmationCallback confirmationCallback){

        this.confirmationCallback = confirmationCallback;
        Observable<HomeResponse> mainObservable = apiService.getDataByGetMethod(path);
        if (mainObservable != null) {
            mainObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(new Consumer<HomeResponse>() {
                        @Override
                        public void accept(HomeResponse main) throws Exception {
                            if (main != null) {
                                confirmationCallback.onSuccess(main);
                            } else {
                                confirmationCallback.onError("Error");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            confirmationCallback.onError("" + throwable.getMessage());
                        }
                    });
        }


    }


}
