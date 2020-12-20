package com.techmate.woocommerce.mvp;

import com.techmate.woocommerce.model.HomeResponse;

public interface ConfirmationCallback {
    void onSuccess(HomeResponse home);
    void onError(String err);
}