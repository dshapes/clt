package com.techmate.woocommerce.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryListItem {

    @SerializedName("image")
    private String image;

    @SerializedName("sub-category")
    private List<SubCategoryItem> subCategory;

    @SerializedName("name")
    private String name;

    @SerializedName("count")
    private int count;

    @SerializedName("id")
    private int id;

    @SerializedName("slug")
    private String slug;

    // Categories

    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("menu_order")
    @Expose
    private Integer menuOrder;
    @SerializedName("yoast_head")
    @Expose
    private String yoastHead;
    @SerializedName("_links")
    @Expose
    private Links links;


    public Integer getParent() {
        return parent;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplay() {
        return display;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public String getYoastHead() {
        return yoastHead;
    }

    public Links getLinks() {
        return links;
    }

    public String getImage() {
        return image;
    }

    public List<SubCategoryItem> getSubCategory() {
        return subCategory;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }


}