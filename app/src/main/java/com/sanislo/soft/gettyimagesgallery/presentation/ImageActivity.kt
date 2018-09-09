package com.sanislo.soft.gettyimagesgallery.presentation

import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sanislo.soft.gettyimagesgallery.R
import com.sanislo.soft.gettyimagesgallery.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image)
        binding.ivImage.transitionName = intent.getStringExtra(EXTRA_URL)
        loadImage()
    }

    fun loadImage() {
        val requestOptions = RequestOptions.placeholderOf(R.drawable.placeholder)
                .dontTransform()
        Glide.with(this)
                .load(intent.getStringExtra(EXTRA_URL))
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                })
                .into(binding.ivImage)
    }

    companion object {
        val TAG = ImageActivity::class.java.simpleName
        val EXTRA_URL = "EXTRA_URL"
    }
}
