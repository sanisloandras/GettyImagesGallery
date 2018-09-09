package com.sanislo.soft.gettyimagesgallery.di

import com.sanislo.soft.gettyimagesgallery.domain.ImagesRepository
import com.sanislo.soft.gettyimagesgallery.persistance.network.AppApi
import com.sanislo.soft.gettyimagesgallery.presentation.MainViewModel

import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun provideMainViewModel(imagesRepository: ImagesRepository): MainViewModel {
        return MainViewModel(imagesRepository)
    }

    @Provides
    fun provideImagesRepository(appApi: AppApi): ImagesRepository {
        return ImagesRepository(appApi)
    }
}
