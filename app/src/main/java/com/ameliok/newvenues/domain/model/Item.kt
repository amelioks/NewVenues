package com.ameliok.newvenues.domain.model

import com.ameliok.newvenues.data.api.model.*

data class Item(
    val image: Image,
    val venue: Venue?,
    var isFavorited: Boolean = false
)