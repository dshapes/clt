package com.techmate.woocommerce.mvp;

import com.techmate.woocommerce.model.CategoryListItem;
import com.techmate.woocommerce.model.HomeResponse;

import java.util.List;

public interface CategoryListingCallback {
    void onSuccess(List<CategoryListItem> home);
    void onError(String err);

}