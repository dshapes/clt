package com.techmate.woocommerce.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("permalink")
    @Expose
    private String permalink;
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
    @SerializedName("description")
    @Expose
    private String description;
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
    @SerializedName("menu_order")
    @Expose
    private Integer menuOrder;
    @SerializedName("price_html")
    @Expose
    private String priceHtml;
    @SerializedName("related_ids")
    @Expose
    private List<Integer> relatedIds = null;
    @SerializedName("meta_data")
    @Expose
    private List<MetaDatum> metaData = null;
    @SerializedName("stock_status")
    @Expose
    private String stockStatus;
    @SerializedName("yoast_head")
    @Expose
    private String yoastHead;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getDateModifiedGmt() {
        return dateModifiedGmt;
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

    public String getDescription() {
        return description;
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

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public String getPriceHtml() {
        return priceHtml;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

    public List<MetaDatum> getMetaData() {
        return metaData;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public String getYoastHead() {
        return yoastHead;
    }

    public Links getLinks() {
        return links;
    }
}