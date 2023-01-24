package com.ameliok.newvenues


import androidx.recyclerview.widget.RecyclerView
import com.ameliok.newvenues.data.model.Item
import com.ameliok.newvenues.databinding.RestaurantListBinding

class GetRestaurantViewHolder(val binding: RestaurantListBinding)
    : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item) {
            itemView.apply {
                val restaurant = item.venue
                binding.textViewRestaurantName.text = restaurant?.name
                binding.textViewRestaurantDescription.text = restaurant?.short_description
                binding.imageViewRestaurant.setRestaurantIconUrl(item.image.url)
            }
        }
}