package com.ameliok.newvenues.utils

import com.ameliok.newvenues.domain.model.ImageDomain
import com.ameliok.newvenues.data.api.model.Item as ItemNetwork
import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.domain.model.VenueDomain
import com.ameliok.newvenues.data.api.model.Venue as VenueNetwork

fun ItemNetwork.toDomainItem(): ItemDomain {
    return ItemDomain(
        imageDomain = ImageDomain(image.url),
        venueDomain = venue!!.toDomainVenue()
            )
}

fun VenueNetwork.toDomainVenue(): VenueDomain {
    return VenueDomain(
        id = id,
        name = name,
        short_description = short_description
    )
}