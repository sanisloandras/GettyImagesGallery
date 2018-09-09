package com.sanislo.soft.gettyimagesgallery.di

import com.sanislo.soft.gettyimagesgallery.BuildConfig
import com.sanislo.soft.gettyimagesgallery.persistance.network.AppApi
import com.sanislo.soft.gettyimagesgallery.persistance.network.GettyImagesInterceptor

import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): AppApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(AppApi::class.java)
    }

    @Provides
    fun provideBaseURL(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    fun provideOkHttpClient(gettyImagesInterceptor: GettyImagesInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(gettyImagesInterceptor)
                .build()
    }

    @Provides
    fun provideGettyImagesInterceptor(): GettyImagesInterceptor {
        return GettyImagesInterceptor()
    }
}