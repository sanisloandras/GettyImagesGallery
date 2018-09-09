package com.sanislo.soft.gettyimagesgallery.persistance.network

import com.sanislo.soft.gettyimagesgallery.BuildConfig
import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class GettyImagesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val builder: Request.Builder = chain.request().newBuilder()
        builder.addHeader("Api-Key", BuildConfig.API_KEY)
        return chain.proceed(builder.build())
    }
}
