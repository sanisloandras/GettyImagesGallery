package com.sanislo.soft.gettyimagesgallery.presentation

import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sanislo.soft.gettyimagesgallery.R
import com.sanislo.soft.gettyimagesgallery.domain.ImageModel

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ImageAdapter(val clickHandler: ClickHandler) : ListAdapter<ImageModel, ImageAdapter.ViewHolder>(asyncDiffConfig) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind()
    }

    interface ClickHandler {
        fun onClick(imageModel: ImageModel, view: View)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv: ImageView

        init {
            iv = itemView.findViewById(R.id.iv_image)
            iv.setOnClickListener {
                clickHandler.onClick(getItem(adapterPosition), it)
            }
        }

        fun bind() {
            val (_, _, url) = getItem(adapterPosition)
            iv.transitionName = url
            val requestOptions = RequestOptions.placeholderOf(R.drawable.placeholder)
                    .dontTransform()
            Glide.with(itemView.context)
                    .load(url)
                    .apply(requestOptions)
                    .into(iv)
        }
    }

    companion object {
        val asyncDiffConfig = AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(imageModel: ImageModel, t1: ImageModel): Boolean {
                return imageModel.id == t1.id
            }

            override fun areContentsTheSame(imageModel: ImageModel, t1: ImageModel): Boolean {
                return imageModel == t1
            }
        }).setBackgroundThreadExecutor(Executors.newSingleThreadExecutor()).build()
    }
}
