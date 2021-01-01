package com.techmate.woocommerce.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SubCategoryItem{

	@SerializedName("image")
	private String image;

	@SerializedName("sub-category")
	private List<Object> subCategory;

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

	public List<Object> getSubCategory(){
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