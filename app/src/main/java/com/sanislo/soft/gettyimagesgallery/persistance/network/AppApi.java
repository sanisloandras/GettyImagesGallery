package com.sanislo.soft.gettyimagesgallery.persistance.network;

import com.sanislo.soft.gettyimagesgallery.persistance.response.ImagesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppApi {
    @GET("search/images")
    Single<ImagesResponse> images(@Query("phrase") String phrase,
                                  @Query("fields") String fields,
                                  @Query("sort_order") String sortOrder,
                                  @Query("page_size") int pageSize);
}
