package com.sanislo.soft.gettyimagesgallery.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log.d
import android.view.View
import com.sanislo.soft.gettyimagesgallery.R
import com.sanislo.soft.gettyimagesgallery.databinding.ActivityMainBinding
import com.sanislo.soft.gettyimagesgallery.domain.ImageModel
import com.sanislo.soft.gettyimagesgallery.presentation.ImageAdapter.ClickHandler
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    lateinit var mViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        obtainViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupImagesList()
        observeImages()
        observeErrors()
        observeProgress()
    }

    fun setupImagesList() {
        imageAdapter = ImageAdapter(object : ClickHandler {
            override fun onClick(imageModel: ImageModel, view: View) {
                launchImageDetails(imageModel, view)
            }
        })
        binding.rvImages.layoutManager = GridLayoutManager(this, 3)
        binding.rvImages.adapter = imageAdapter
    }

    fun launchImageDetails(imageModel: ImageModel, view: View) {
        val intent = Intent(this@MainActivity, ImageActivity::class.java)
        intent.putExtra(ImageActivity.EXTRA_URL, imageModel.url)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, view, view.transitionName)
        startActivity(intent, options.toBundle())
    }

    fun observeImages() {
        mViewModel.images.observe(this, Observer {
            d(TAG, "images $it")
            imageAdapter.submitList(it)
        })
    }

    fun observeProgress() {
        mViewModel.progress.observe(this, Observer {
            binding.pb.visibility = if (it == true) View.VISIBLE else View.GONE
        })
    }

    fun observeErrors() {
        mViewModel.error.observe(this, Observer {
            Snackbar.make(binding.root, it?.localizedMessage
                    ?: getString(R.string.error), Snackbar.LENGTH_LONG).show()
        })
    }

    private fun obtainViewModel() {
        mViewModel = ViewModelProviders.of(
                this,
                mFactory)
                .get(MainViewModel::class.java)
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}
