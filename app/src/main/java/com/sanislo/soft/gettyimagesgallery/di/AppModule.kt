package com.sanislo.soft.gettyimagesgallery.di

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(NetworkModule::class, MainModule::class, ViewModelModule::class))
class AppModule {
    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}
