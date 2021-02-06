package com.techmate.woocommerce.api;

import android.content.Context;

import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.model.ProductDetailResponse;
import com.techmate.woocommerce.mvp.CategoryListingCallback;
import com.techmate.woocommerce.mvp.ConfirmationCallback;
import com.techmate.woocommerce.mvp.OffersCallback;
import com.techmate.woocommerce.mvp.ProductDetailCallback;
import com.techmate.woocommerce.utils.Constants;
import com.techmate.woocommerce.utils.PrefManager;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DataManager {

    private String TAG = DataManager.class.getSimpleName();
    private Context context;
    private ApiService apiService;
    private PrefManager prefManager;
    private static DataManager mDataManager;
    private ConfirmationCallback confirmationCallback;
    private CategoryListingCallback categoryListingCallback;
    private ProductDetailCallback productDetailCallback;
    private OffersCallback offersCallback;

    public DataManager(Context mContext) {
        this.context = mContext;
        prefManager = new PrefManager(mContext);
    }

    public static DataManager getInstance(Context mContext) {
        if (mDataManager == null) {
            mDataManager = new DataManager(mContext);
        }
        return mDataManager;
    }

    public void getData(String path, Map<String, String> hashMap, final ConfirmationCallback confirmationCallback) {

        apiService = ApiClient.getPOSTClient(false);

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

    public void getDataByGet(String path, final ConfirmationCallback confirmationCallback) {

        apiService = ApiClient.getPOSTClient(true);

        this.confirmationCallback = confirmationCallback;
        Observable<HomeResponse> mainObservable = apiService.getDataByGetMethod(path, Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
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

    public void getLandingPageData(String path, final ConfirmationCallback confirmationCallback) {

        apiService = ApiClient.getPOSTWPClient();

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

    public void getListingData(String path, String ckey, String csecret, final CategoryListingCallback categoryListingCallback) {

        apiService = ApiClient.getListingClient();

        this.categoryListingCallback = categoryListingCallback;
        Observable<List<CategoryListItem>> mainObservable = apiService.getCategoryList(path,ckey,csecret);
        if (mainObservable !=    null) {
            mainObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(main -> {
                        if (main != null) {
                            categoryListingCallback.onSuccess(main);
                        } else {
                            categoryListingCallback.onError("Error");
                        }
                    }, throwable -> categoryListingCallback.onError("" + throwable.getMessage()));
        }


    }

    public void getProductDetail(String path, String ckey, String csecret, final ProductDetailCallback productDetailCallback) {

        apiService = ApiClient.getProductDetailClient();

        this.productDetailCallback = productDetailCallback;
        Observable<ProductDetailResponse> mainObservable = apiService.getProductDetails(path,ckey,csecret);
        if (mainObservable != null) {
            mainObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(main -> {
                        if (main != null) {
                            productDetailCallback.onSuccess(main);
                        } else {
                            productDetailCallback.onError("Error");
                        }
                    }, throwable -> productDetailCallback.onError("" + throwable.getMessage()));
        }


    }

    public void getOffers(String path, String ckey, String csecret, final OffersCallback offersCallback) {

        apiService = ApiClient.getProductDetailClient();

        this.offersCallback = offersCallback;
        Observable<List<HomeResponse>> mainObservable = apiService.getOffers(path,ckey,csecret);
        if (mainObservable != null) {
            mainObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(main -> {
                        if (main != null) {
                            offersCallback.onSuccess(main);
                        } else {
                            offersCallback.onError("Error");
                        }
                    }, throwable -> offersCallback.onError("" + throwable.getMessage()));
        }
    }

    public void updateProfile(String path, Map<String, String> hashMap, final ConfirmationCallback confirmationCallback) {

        apiService = ApiClient.getPOSTClient(false);

        this.confirmationCallback = confirmationCallback;
        Observable<HomeResponse> mainObservable = apiService.getData(path,hashMap);
        if (mainObservable != null) {
            mainObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(main -> {
                        if (main != null) {
                            confirmationCallback.onSuccess(main);
                        } else {
                            confirmationCallback.onError("Error");
                        }
                    }, throwable -> confirmationCallback.onError("" + throwable.getMessage()));
        }
    }

    public void updateProfileImage(RequestBody id, MultipartBody.Part imageFile, final ConfirmationCallback confirmationCallback) {

        apiService = ApiClient.getPOSTClient(false);

        this.confirmationCallback = confirmationCallback;
        Observable<HomeResponse> mainObservable = apiService.uploadProfileImage(id,imageFile);
        if (mainObservable != null) {
            mainObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(main -> {
                        if (main != null) {
                            confirmationCallback.onSuccess(main);
                        } else {
                            confirmationCallback.onError("Error");
                        }
                    }, throwable -> confirmationCallback.onError("" + throwable.getMessage()));
        }
    }
}
