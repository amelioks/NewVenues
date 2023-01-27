package com.ameliok.newvenues.UI


import androidx.recyclerview.widget.RecyclerView
import com.ameliok.newvenues.R
import com.ameliok.newvenues.data.model.Item
import com.ameliok.newvenues.databinding.RestaurantListBinding
import com.ameliok.newvenues.setImageUrl

class GetRestaurantViewHolder(val binding: RestaurantListBinding)
    : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item) {
            itemView.apply {
                val restaurant = item.venue
                binding.textViewRestaurantName.text = restaurant?.name
                binding.textViewRestaurantDescription.text = restaurant?.short_description
                binding.imageViewRestaurant.setImageUrl(item.image.url)

                var drawable = if (item.isFavorited) {
                    R.drawable.baseline_favorite_black_20
                }
                else R.drawable.baseline_favorite_border_black_20

                binding.imageViewFavoriteIcon.setImageResource(drawable)

                binding.imageViewFavoriteIcon.setOnClickListener {
                    item.isFavorited = !item.isFavorited

                    if(item.isFavorited) binding.imageViewFavoriteIcon.setImageResource(R.drawable.baseline_favorite_black_20)
                    else binding.imageViewFavoriteIcon.setImageResource(R.drawable.baseline_favorite_border_black_20)
                }
            }
        }

}