package com.sanislo.soft.gettyimagesgallery.persistance.response;

import com.google.gson.annotations.SerializedName;

public class DisplaySizesItem{

	@SerializedName("is_watermarked")
	private boolean isWatermarked;

	@SerializedName("name")
	private String name;

	@SerializedName("uri")
	private String uri;

	public void setIsWatermarked(boolean isWatermarked){
		this.isWatermarked = isWatermarked;
	}

	public boolean isIsWatermarked(){
		return isWatermarked;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUri(String uri){
		this.uri = uri;
	}

	public String getUri(){
		return uri;
	}

	@Override
 	public String toString(){
		return 
			"DisplaySizesItem{" + 
			"is_watermarked = '" + isWatermarked + '\'' + 
			",name = '" + name + '\'' + 
			",uri = '" + uri + '\'' + 
			"}";
		}
}