package com.shalan.audioplayer.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("app:img_url")
fun loadImageFromUrl(view: ImageView, url: String) {
    view.load(url)
}