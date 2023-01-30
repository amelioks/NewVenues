package com.ameliok.newvenues.domain.model


data class ItemDomain(
    val imageDomain: ImageDomain,
    val venueDomain: VenueDomain,
    var isFavorite: Boolean = false
)