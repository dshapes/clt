package com.techmate.woocommerce.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryListItem{

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

	public String getImage(){
		return image;
	}

	public List<SubCategoryItem> getSubCategory(){
		return subCategory;
	}

	public String getName(){
		return name;
	}

	public int getCount(){
		return count;
	}

	public int getId(){
		return id;
	}

	public String getSlug(){
		return slug;
	}
}