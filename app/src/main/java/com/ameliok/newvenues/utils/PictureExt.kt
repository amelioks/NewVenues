package com.ameliok.newvenues.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.setImageUrl(imgUrl: String?) {
    if (imgUrl.isNullOrBlank()) return

    Glide.with(this)
        .load(imgUrl)
        .centerInside()
        .transform(RoundedCorners(16))
        .into(this)
}
