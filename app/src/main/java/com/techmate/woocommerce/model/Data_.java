package com.techmate.woocommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_ {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("user_pass")
    @Expose
    private String userPass;
    @SerializedName("user_nicename")
    @Expose
    private String userNicename;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_url")
    @Expose
    private String userUrl;
    @SerializedName("user_registered")
    @Expose
    private String userRegistered;
    @SerializedName("user_activation_key")
    @Expose
    private String userActivationKey;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("display_name")
    @Expose
    private String displayName;

    public String getID() {
        return iD;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserNicename() {
        return userNicename;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public String getUserRegistered() {
        return userRegistered;
    }

    public String getUserActivationKey() {
        return userActivationKey;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getDisplayName() {
        return displayName;
    }

}