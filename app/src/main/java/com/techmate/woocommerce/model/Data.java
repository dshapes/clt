package com.techmate.woocommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

	@SerializedName("status")
	private int status;

	@SerializedName("data")
	@Expose
	private Data_ data;
	@SerializedName("ID")
	@Expose
	private Integer iD;
	@SerializedName("caps")
	@Expose
	private Caps caps;
	@SerializedName("cap_key")
	@Expose
	private String capKey;
	@SerializedName("roles")
	@Expose
	private List<String> roles = null;
	@SerializedName("allcaps")
	@Expose
	private Allcaps allcaps;
	@SerializedName("filter")
	@Expose
	private Object filter;


	public Data_ getData() {
		return data;
	}

	public Integer getiD() {
		return iD;
	}

	public Caps getCaps() {
		return caps;
	}

	public String getCapKey() {
		return capKey;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Allcaps getAllcaps() {
		return allcaps;
	}

	public Object getFilter() {
		return filter;
	}

	public int getStatus(){
		return status;
	}
}