package com.ameliok.newvenues.data.api.model

data class Link(
    val target: String,
    val target_sort: String,
    val target_title: String,
    val title: String,
    val type: String,
    val venue_mainimage_blurhash: String?
)