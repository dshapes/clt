package com.techmate.woocommerce.model;

import com.google.gson.annotations.SerializedName;

public class ImagesItem{

	@SerializedName("date_modified_gmt")
	private String dateModifiedGmt;

	@SerializedName("date_modified")
	private String dateModified;

	@SerializedName("src")
	private String src;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("name")
	private String name;

	@SerializedName("alt")
	private String alt;

	@SerializedName("date_created_gmt")
	private String dateCreatedGmt;

	@SerializedName("id")
	private int id;

	public String getDateModifiedGmt(){
		return dateModifiedGmt;
	}

	public String getDateModified(){
		return dateModified;
	}

	public String getSrc(){
		return src;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public String getName(){
		return name;
	}

	public String getAlt(){
		return alt;
	}

	public String getDateCreatedGmt(){
		return dateCreatedGmt;
	}

	public int getId(){
		return id;
	}

	public void setDateModifiedGmt(String dateModifiedGmt) {
		this.dateModifiedGmt = dateModifiedGmt;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public void setDateCreatedGmt(String dateCreatedGmt) {
		this.dateCreatedGmt = dateCreatedGmt;
	}

	public void setId(int id) {
		this.id = id;
	}
}