package com.techmate.woocommerce.mvp;

import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.ProductDetailResponse;

import java.util.List;

public interface ProductDetailCallback {
    void onSuccess(ProductDetailResponse home);
    void onError(String err);

}