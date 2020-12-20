package com.techmate.woocommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Allcaps {

    @SerializedName("read")
    @Expose
    private Boolean read;
    @SerializedName("customer")
    @Expose
    private Boolean customer;

    public Boolean getRead() {
        return read;
    }

    public Boolean getCustomer() {
        return customer;
    }


}