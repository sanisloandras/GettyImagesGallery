package com.sanislo.soft.gettyimagesgallery.domain

import com.sanislo.soft.gettyimagesgallery.persistance.network.AppApi
import com.sanislo.soft.gettyimagesgallery.persistance.response.ImagesItem
import com.sanislo.soft.gettyimagesgallery.persistance.response.ImagesResponse

import io.reactivex.Single
import io.reactivex.functions.Function

class ImagesRepository(private val mAppApi: AppApi) {
    val mapper = ImageResponseToImageModel()

    fun loadImages(): Single<List<ImageModel>> {
        return mAppApi.images("star wars",
                "id, title, preview",
                "most_popular",
                60)
                .map {
                    mapper.map(it.images)
                }
    }

    inner class ImageResponseToImageModel : Mapper<ImagesItem, ImageModel>() {
        override fun map(input: ImagesItem): ImageModel {
            return ImageModel(input.id, input.title, input.displaySizes[0].uri)
        }
    }
}
