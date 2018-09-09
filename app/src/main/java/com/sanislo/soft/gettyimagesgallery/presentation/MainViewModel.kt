package com.sanislo.soft.gettyimagesgallery.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanislo.soft.gettyimagesgallery.domain.ImageModel

import com.sanislo.soft.gettyimagesgallery.domain.ImagesRepository
import com.sanislo.soft.gettyimagesgallery.domain.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel(val mImagesRepository: ImagesRepository) : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val images = MutableLiveData<List<ImageModel>>()
    val progress = MutableLiveData<Boolean>()
    val error = SingleLiveEvent<Throwable>()

    init {
        mImagesRepository.loadImages()
                .doOnSubscribe {
                    compositeDisposable.add(it)
                    progress.postValue(true)
                }
                .doFinally {
                    progress.postValue(false)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    images.value = it
                }) {
                    error.value = it
                }
    }
}
