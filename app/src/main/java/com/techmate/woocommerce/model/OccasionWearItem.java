package com.techmate.woocommerce.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OccasionWearItem implements Serializable {

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("featured")
	private boolean featured;

	@SerializedName("tax_status")
	private String taxStatus;

	@SerializedName("description")
	private String description;

	@SerializedName("review_count")
	private int reviewCount;

	@SerializedName("catalog_visibility")
	private String catalogVisibility;

	@SerializedName("type")
	private String type;

	@SerializedName("regular_price")
	private String regularPrice;

	@SerializedName("reviews_allowed")
	private boolean reviewsAllowed;

	@SerializedName("price")
	private String price;

	@SerializedName("id")
	private int id;

	@SerializedName("sku")
	private String sku;

	@SerializedName("total_sales")
	private int totalSales;

	@SerializedName("stock")
	private boolean stock;

	@SerializedName("slug")
	private String slug;

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("stock_status")
	private String stockStatus;

	@SerializedName("tax_class")
	private String taxClass;

	@SerializedName("average_rating")
	private String averageRating;

	@SerializedName("stock_quantity")
	private Object stockQuantity;

	@SerializedName("sale_price")
	private String salePrice;

	@SerializedName("sale_date_start")
	private Object saleDateStart;

	@SerializedName("sale_date_to")
	private Object saleDateTo;

	@SerializedName("name")
	private String name;

	@SerializedName("rating_counts")
	private List<Object> ratingCounts;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("status")
	private String status;

	public String getShortDescription(){
		return shortDescription;
	}

	public boolean isFeatured(){
		return featured;
	}

	public String getTaxStatus(){
		return taxStatus;
	}

	public String getDescription(){
		return description;
	}

	public int getReviewCount(){
		return reviewCount;
	}

	public String getCatalogVisibility(){
		return catalogVisibility;
	}

	public String getType(){
		return type;
	}

	public String getRegularPrice(){
		return regularPrice;
	}

	public boolean isReviewsAllowed(){
		return reviewsAllowed;
	}

	public String getPrice(){
		return price;
	}

	public int getId(){
		return id;
	}

	public String getSku(){
		return sku;
	}

	public int getTotalSales(){
		return totalSales;
	}

	public boolean isStock(){
		return stock;
	}

	public String getSlug(){
		return slug;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public String getStockStatus(){
		return stockStatus;
	}

	public String getTaxClass(){
		return taxClass;
	}

	public String getAverageRating(){
		return averageRating;
	}

	public Object getStockQuantity(){
		return stockQuantity;
	}

	public String getSalePrice(){
		return salePrice;
	}

	public Object getSaleDateStart(){
		return saleDateStart;
	}

	public Object getSaleDateTo(){
		return saleDateTo;
	}

	public String getName(){
		return name;
	}

	public List<Object> getRatingCounts(){
		return ratingCounts;
	}

	public String getPermalink(){
		return permalink;
	}

	public String getStatus(){
		return status;
	}
}