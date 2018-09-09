package com.sanislo.soft.gettyimagesgallery.di

import com.sanislo.soft.gettyimagesgallery.presentation.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun contributeMainActivity(): MainActivity
}