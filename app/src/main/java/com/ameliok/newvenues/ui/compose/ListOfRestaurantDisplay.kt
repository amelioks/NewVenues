package com.ameliok.newvenues.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ameliok.newvenues.domain.model.ImageDomain
import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.domain.model.VenueDomain

@Composable
fun ListOfRestaurantDisplay(item: List<ItemDomain>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "New Venues",
            fontSize = 32.sp
        )
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(item) { restaurant ->
                RestaurantItem(restaurant)
            }
        }
    }
}


@Composable
@Preview
fun PreviewListOfRestaurant() {
    ListOfRestaurantDisplay(
        listOf(
            ItemDomain(ImageDomain(""), VenueDomain("", "test1", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description")),
            ItemDomain(ImageDomain(""), VenueDomain("", "test2", "test description"))
        )
    )

}