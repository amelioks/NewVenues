package com.ameliok.newvenues.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ameliok.newvenues.domain.model.ImageDomain
import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.domain.model.VenueDomain

@Composable
fun RestaurantItem(item: ItemDomain) {

    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
    ) {
        Box(modifier = Modifier
            .width(56.dp)
            .height(56.dp)) {
            Image(
                painter = rememberAsyncImagePainter(item.imageDomain),
                contentDescription = "restaurant image"
            )
        }

        Column(
            modifier = Modifier
                .weight(6f)
        ){
            Text(
                text = item.venueDomain.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = item.venueDomain.short_description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Image(
            painter = painterResource(id = com.ameliok.newvenues.
        R.drawable.baseline_favorite_border_black_20),
            contentDescription = "",
            modifier = Modifier
                .padding(16.dp)
                .weight(1f))
    }

}

@Composable
@Preview
fun RestaurantItemPreview() {
    RestaurantItem(ItemDomain(ImageDomain(
        "https://sitechecker.pro/wp-content/uploads/2017/12/URL-meaning.png"),
        VenueDomain("","test",
        "test description")))
}