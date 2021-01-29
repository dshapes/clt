package com.techmate.woocommerce.mvp;

import com.techmate.woocommerce.model.HomeResponse;
import com.techmate.woocommerce.model.ProductDetailResponse;

import java.util.List;

public interface OffersCallback {
    void onSuccess(List<HomeResponse> home);
    void onError(String err);

}