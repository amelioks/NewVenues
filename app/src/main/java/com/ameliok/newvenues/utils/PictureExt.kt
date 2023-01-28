package com.ameliok.newvenues.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(imgUrl: String?) {
    if (imgUrl.isNullOrBlank()) return

    Glide.with(this)
        .load(imgUrl)
        .override(600,200)
        .fitCenter()
        .into(this)
}
