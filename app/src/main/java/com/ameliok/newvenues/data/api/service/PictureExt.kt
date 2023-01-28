package com.ameliok.newvenues.data.api.service

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
