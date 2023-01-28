package com.ameliok.newvenues.domain.model


data class Item(
    val image: Image,
    val venue: Venue?,
    var isFavorite: Boolean = false
)