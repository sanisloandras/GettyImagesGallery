package com.sanislo.soft.gettyimagesgallery.persistance.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesResponse{

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("result_count")
	private int resultCount;

	public void setImages(List<ImagesItem> images){
		this.images = images;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public void setResultCount(int resultCount){
		this.resultCount = resultCount;
	}

	public int getResultCount(){
		return resultCount;
	}

	@Override
 	public String toString(){
		return 
			"ImagesResponse{" + 
			"images = '" + images + '\'' + 
			",result_count = '" + resultCount + '\'' + 
			"}";
		}
}