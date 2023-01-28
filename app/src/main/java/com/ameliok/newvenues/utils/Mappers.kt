package com.ameliok.newvenues.utils

import com.ameliok.newvenues.domain.model.Image
import com.ameliok.newvenues.data.api.model.Item as ItemNetwork
import com.ameliok.newvenues.domain.model.Item
import com.ameliok.newvenues.domain.model.Venue
import com.ameliok.newvenues.data.api.model.Venue as VenueNetwork

fun ItemNetwork.toDomainItem(): Item {
    return Item(
        image = Image(image.url),
        venue = venue!!.toDomainVenue()
            )
}

fun VenueNetwork.toDomainVenue(): Venue {
    return Venue(
        id = id,
        name = name,
        short_description = short_description
    )
}