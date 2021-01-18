package com.techmate.woocommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponse {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    // Categories
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("menu_order")
    @Expose
    private Integer menuOrder;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("yoast_head")
    @Expose
    private String yoastHead;
    @SerializedName("_links")
    @Expose
    private Links links;

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_created_gmt")
    @Expose
    private String dateCreatedGmt;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("date_modified_gmt")
    @Expose
    private String dateModifiedGmt;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("date_expires")
    @Expose
    private Object dateExpires;
    @SerializedName("date_expires_gmt")
    @Expose
    private Object dateExpiresGmt;
    @SerializedName("usage_count")
    @Expose
    private Integer usageCount;
    @SerializedName("individual_use")
    @Expose
    private Boolean individualUse;
    @SerializedName("product_ids")
    @Expose
    private List<Object> productIds = null;
    @SerializedName("excluded_product_ids")
    @Expose
    private List<Object> excludedProductIds = null;
    @SerializedName("usage_limit")
    @Expose
    private Integer usageLimit;
    @SerializedName("usage_limit_per_user")
    @Expose
    private Object usageLimitPerUser;
    @SerializedName("limit_usage_to_x_items")
    @Expose
    private Object limitUsageToXItems;
    @SerializedName("free_shipping")
    @Expose
    private Boolean freeShipping;
    @SerializedName("product_categories")
    @Expose
    private List<Integer> productCategories = null;
    @SerializedName("excluded_product_categories")
    @Expose
    private List<Object> excludedProductCategories = null;
    @SerializedName("exclude_sale_items")
    @Expose
    private Boolean excludeSaleItems;
    @SerializedName("minimum_amount")
    @Expose
    private String minimumAmount;
    @SerializedName("maximum_amount")
    @Expose
    private String maximumAmount;
    @SerializedName("email_restrictions")
    @Expose
    private List<Object> emailRestrictions = null;
    @SerializedName("used_by")
    @Expose
    private List<Object> usedBy = null;
    @SerializedName("meta_data")
    @Expose
    private List<MetaDatum> metaData = null;

    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("catalog_visibility")
    @Expose
    private String catalogVisibility;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("date_on_sale_from")
    @Expose
    private Object dateOnSaleFrom;
    @SerializedName("date_on_sale_from_gmt")
    @Expose
    private Object dateOnSaleFromGmt;
    @SerializedName("date_on_sale_to")
    @Expose
    private Object dateOnSaleTo;
    @SerializedName("date_on_sale_to_gmt")
    @Expose
    private Object dateOnSaleToGmt;
    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;
    @SerializedName("purchasable")
    @Expose
    private Boolean purchasable;
    @SerializedName("total_sales")
    @Expose
    private Integer totalSales;
    @SerializedName("virtual")
    @Expose
    private Boolean virtual;
    @SerializedName("downloadable")
    @Expose
    private Boolean downloadable;
    @SerializedName("downloads")
    @Expose
    private List<Object> downloads = null;
    @SerializedName("download_limit")
    @Expose
    private Integer downloadLimit;
    @SerializedName("download_expiry")
    @Expose
    private Integer downloadExpiry;
    @SerializedName("external_url")
    @Expose
    private String externalUrl;
    @SerializedName("button_text")
    @Expose
    private String buttonText;
    @SerializedName("tax_status")
    @Expose
    private String taxStatus;
    @SerializedName("tax_class")
    @Expose
    private String taxClass;
    @SerializedName("manage_stock")
    @Expose
    private Boolean manageStock;
    @SerializedName("stock_quantity")
    @Expose
    private Integer stockQuantity;
    @SerializedName("backorders")
    @Expose
    private String backorders;
    @SerializedName("backorders_allowed")
    @Expose
    private Boolean backordersAllowed;
    @SerializedName("backordered")
    @Expose
    private Boolean backordered;
    @SerializedName("sold_individually")
    @Expose
    private Boolean soldIndividually;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("dimensions")
    @Expose
    private Dimensions dimensions;
    @SerializedName("shipping_required")
    @Expose
    private Boolean shippingRequired;
    @SerializedName("shipping_taxable")
    @Expose
    private Boolean shippingTaxable;
    @SerializedName("shipping_class")
    @Expose
    private String shippingClass;
    @SerializedName("shipping_class_id")
    @Expose
    private Integer shippingClassId;
    @SerializedName("reviews_allowed")
    @Expose
    private Boolean reviewsAllowed;
    @SerializedName("average_rating")
    @Expose
    private String averageRating;
    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;
    @SerializedName("upsell_ids")
    @Expose
    private List<Object> upsellIds = null;
    @SerializedName("cross_sell_ids")
    @Expose
    private List<Object> crossSellIds = null;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("purchase_note")
    @Expose
    private String purchaseNote;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("attributes")
    @Expose
    private List<Attribute> attributes = null;
    @SerializedName("default_attributes")
    @Expose
    private List<Object> defaultAttributes = null;
    @SerializedName("variations")
    @Expose
    private List<Object> variations = null;
    @SerializedName("grouped_products")
    @Expose
    private List<Object> groupedProducts = null;
    @SerializedName("price_html")
    @Expose
    private String priceHtml;
    @SerializedName("related_ids")
    @Expose
    private List<Integer> relatedIds = null;
    @SerializedName("stock_status")
    @Expose
    private String stockStatus;

    public String getPermalink() {
        return permalink;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public String getCatalogVisibility() {
        return catalogVisibility;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getSku() {
        return sku;
    }

    public String getPrice() {
        return price;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public Object getDateOnSaleFrom() {
        return dateOnSaleFrom;
    }

    public Object getDateOnSaleFromGmt() {
        return dateOnSaleFromGmt;
    }

    public Object getDateOnSaleTo() {
        return dateOnSaleTo;
    }

    public Object getDateOnSaleToGmt() {
        return dateOnSaleToGmt;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public Boolean getPurchasable() {
        return purchasable;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Boolean getVirtual() {
        return virtual;
    }

    public Boolean getDownloadable() {
        return downloadable;
    }

    public List<Object> getDownloads() {
        return downloads;
    }

    public Integer getDownloadLimit() {
        return downloadLimit;
    }

    public Integer getDownloadExpiry() {
        return downloadExpiry;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public Boolean getManageStock() {
        return manageStock;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public String getBackorders() {
        return backorders;
    }

    public Boolean getBackordersAllowed() {
        return backordersAllowed;
    }

    public Boolean getBackordered() {
        return backordered;
    }

    public Boolean getSoldIndividually() {
        return soldIndividually;
    }

    public String getWeight() {
        return weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Boolean getShippingRequired() {
        return shippingRequired;
    }

    public Boolean getShippingTaxable() {
        return shippingTaxable;
    }

    public String getShippingClass() {
        return shippingClass;
    }

    public Integer getShippingClassId() {
        return shippingClassId;
    }

    public Boolean getReviewsAllowed() {
        return reviewsAllowed;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public List<Object> getUpsellIds() {
        return upsellIds;
    }

    public List<Object> getCrossSellIds() {
        return crossSellIds;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getPurchaseNote() {
        return purchaseNote;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Object> getTags() {
        return tags;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Object> getDefaultAttributes() {
        return defaultAttributes;
    }

    public List<Object> getVariations() {
        return variations;
    }

    public List<Object> getGroupedProducts() {
        return groupedProducts;
    }

    public String getPriceHtml() {
        return priceHtml;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    public void setDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDateModifiedGmt() {
        return dateModifiedGmt;
    }

    public void setDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Object getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(Object dateExpires) {
        this.dateExpires = dateExpires;
    }

    public Object getDateExpiresGmt() {
        return dateExpiresGmt;
    }

    public void setDateExpiresGmt(Object dateExpiresGmt) {
        this.dateExpiresGmt = dateExpiresGmt;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }

    public Boolean getIndividualUse() {
        return individualUse;
    }

    public void setIndividualUse(Boolean individualUse) {
        this.individualUse = individualUse;
    }

    public List<Object> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Object> productIds) {
        this.productIds = productIds;
    }

    public List<Object> getExcludedProductIds() {
        return excludedProductIds;
    }

    public void setExcludedProductIds(List<Object> excludedProductIds) {
        this.excludedProductIds = excludedProductIds;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Object getUsageLimitPerUser() {
        return usageLimitPerUser;
    }

    public void setUsageLimitPerUser(Object usageLimitPerUser) {
        this.usageLimitPerUser = usageLimitPerUser;
    }

    public Object getLimitUsageToXItems() {
        return limitUsageToXItems;
    }

    public void setLimitUsageToXItems(Object limitUsageToXItems) {
        this.limitUsageToXItems = limitUsageToXItems;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public List<Integer> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<Integer> productCategories) {
        this.productCategories = productCategories;
    }

    public List<Object> getExcludedProductCategories() {
        return excludedProductCategories;
    }

    public void setExcludedProductCategories(List<Object> excludedProductCategories) {
        this.excludedProductCategories = excludedProductCategories;
    }

    public Boolean getExcludeSaleItems() {
        return excludeSaleItems;
    }

    public void setExcludeSaleItems(Boolean excludeSaleItems) {
        this.excludeSaleItems = excludeSaleItems;
    }

    public String getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(String minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public String getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(String maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public List<Object> getEmailRestrictions() {
        return emailRestrictions;
    }

    public void setEmailRestrictions(List<Object> emailRestrictions) {
        this.emailRestrictions = emailRestrictions;
    }

    public List<Object> getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(List<Object> usedBy) {
        this.usedBy = usedBy;
    }

    public List<MetaDatum> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<MetaDatum> metaData) {
        this.metaData = metaData;
    }

    public Links getLinks() {
        return links;
    }

    public int getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getParent() {
        return parent;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplay() {
        return display;
    }

    public Object getImage() {
        return image;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public Integer getCount() {
        return count;
    }

    public String getYoastHead() {
        return yoastHead;
    }
}