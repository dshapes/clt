package com.techmate.woocommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Caps {

    @SerializedName("customer")
    @Expose
    private Boolean customer;

    public Boolean getCustomer() {
        return customer;
    }


}
