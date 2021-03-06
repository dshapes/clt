package com.techmate.woocommerce.api;

import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.model.ProductDetailResponse;
import com.techmate.woocommerce.utils.Constants;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST(Constants.API_REGISTER)
    @FormUrlEncoded
    Observable<HomeResponse> register(@FieldMap Map<String, String> options);

    @POST("{path}")
    @FormUrlEncoded
    Observable<HomeResponse> getData(@Path(value="path", encoded = true) String path, @FieldMap() Map<String, String> formData);

    @GET("{path}")
    Observable<HomeResponse> getDataByGetMethod(@Path(value="path", encoded = true) String path, @Query("consumer_key") String ckey, @Query("consumer_secret") String csecret);

    @GET("{path}")
    Observable<HomeResponse> getDataByGetMethod(@Path(value="path", encoded = true) String path);

    @GET("{path}")
    Observable<List<CategoryListItem>> getCategoryList(@Path(value="path", encoded = true) String path, @Query("consumer_key") String ckey, @Query("consumer_secret") String csecret);

    @GET("{path}")
    Observable<List<CategoryListItem>> getCategoryListByParent(@Path(value="path", encoded = true) String path, @Query("parent") String parentId, @Query("consumer_key") String ckey, @Query("consumer_secret") String csecret);

    @GET("{path}")
    Observable<ProductDetailResponse> getProductDetails(@Path(value="path", encoded = true) String path, @Query("consumer_key") String ckey, @Query("consumer_secret") String csecret);

    @GET("{path}")
    Observable<List<HomeResponse>> getOffers(@Path(value="path", encoded = true) String path, @Query("consumer_key") String ckey, @Query("consumer_secret") String csecret);

    @Multipart
    @POST(Constants.API_PROFILE)
    Observable<HomeResponse> uploadProfileImage(@Part("id") RequestBody userId, @Part MultipartBody.Part filePart);

    @POST("{path}")
    @FormUrlEncoded
    Observable<HomeResponse> updateProfileImage(@Path(value="path", encoded = true) String path, @FieldMap() Map<String, Object> formData);

}
