package com.ameliok.newvenues

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(imgUrl: String?) {
    if (imgUrl.isNullOrBlank()) return

    Glide.with(this)
        .load(imgUrl)
        .into(this)
}
