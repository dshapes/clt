package com.techmate.woocommerce.control;

import java.util.HashMap;

public interface QuantityChangeListener {
    void onQuantityChanged(HashMap<String, String> map);
}