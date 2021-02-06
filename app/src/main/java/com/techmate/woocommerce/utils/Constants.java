package com.techmate.woocommerce.utils;

import com.techmate.woocommerce.model.CategoryListItem;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String APP_NAME = "WooCommerce";
    public static final String BASE_URL = "https://clothinaa.com";
    public static final String API_BASE_URL = "https://www.clothinaa.com/wp-json/wp/v3/";
    public static final String API_GET_BASE_URL = "https://www.clothinaa.com/wp-json/wc/v3/";
    public static final String API_GET_WP_BASE_URL = "https://clothinaa.com/wp-json/wp/v3/";
    public static final String CONSUMER_KEY = "ck_5e35ac0b98e8872a1d64f5821824f02525ca64cd";
    public static final String CONSUMER_SECRET = "cs_ff2ac50f39167a6a79a138b3a801b32a3011c595";
    public static final String API_REGISTER = "users/register";
    public static final String API_LOGIN = "users/login";
    public static final String API_FORGOT_PASSWORD = "users/profile";
    public static final String API_GET_CATEGORIES = "products/categories";
    public static final String API_LANDING_PAGE = "landingpage";
    public static final String API_UPDATE_USER = "users/update";
    public static final String API_PROFILE = "users/profile";
    public static final String API_DELETE_PROFILE_IMAGE = "profile/delete";
    public static final String API_GET_COSTOMER_DETAIL = "customers";
    public static final String API_UPDATE_COSTOMER_DETAIL = "customers";
    public static final String API_USER_DETAIL = "users/detail";
    public static final String API_PRODUCTS = "products";
    public static final String API_ATTRIBUTES = "products/attributes";
    public static final String API_COUPONS = "coupons";
    public static final String PARAM_GENDER = "gender";
    public static  int SIZE_VIEW_TYPE = 0;
    public static  int COLOR_VIEW_TYPE = 1;

    public static final String PARAM_ID = "id";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_MOBILE_NO = "mobile";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_DOB = "dob";
    public static final String PARAM_PROFILE_PIC = "profile_pic";
    public static final String INTENT_SUB_CATEGORY_ID = "subCategoryId";
    public static final String INTENT_PRODUCTS_LIST = "productsList";

    public static final String INTENT_MODE = "mode";
    public static final String INTENT_PRODUCT_ID = "product_id";
    public static final String INTENT_CATEGORY_ID = "category_id";
    public static final String INTENT_CATEGORY_NAME = "category_name";
    public static final String INTENT_CATEGORY_POS = "category_pos";
    public static final String MODE_ADD = "add";
    public static final String MODE_EDIT = "edit";

    public static enum BOTTOM_SHEET_VIEW_TYPE {
        SIZE,
        COLOR,
        INFORMATIVE
    }


}
