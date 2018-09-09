package com.sanislo.soft.gettyimagesgallery.persistance.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesItem{

	@SerializedName("id")
	private String id;

	@SerializedName("display_sizes")
	private List<DisplaySizesItem> displaySizes;

	@SerializedName("title")
	private String title;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDisplaySizes(List<DisplaySizesItem> displaySizes){
		this.displaySizes = displaySizes;
	}

	public List<DisplaySizesItem> getDisplaySizes(){
		return displaySizes;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return
			"ImagesItem{" +
			"id = '" + id + '\'' +
			",display_sizes = '" + displaySizes + '\'' +
			",title = '" + title + '\'' +
			"}";
		}
}