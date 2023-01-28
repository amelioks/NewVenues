package com.ameliok.newvenues.ui.recyclerview


import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ameliok.newvenues.R
import com.ameliok.newvenues.domain.model.Item
import com.ameliok.newvenues.databinding.RestaurantListBinding
import com.ameliok.newvenues.domain.repository.RestaurantRepository
import com.ameliok.newvenues.utils.setImageUrl

class GetRestaurantViewHolder(
    private val binding: RestaurantListBinding,
    private val repository: RestaurantRepository
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        itemView.apply {
            val restaurant = item.venue
            binding.textViewRestaurantName.text = restaurant?.name
            binding.textViewRestaurantDescription.text = restaurant?.short_description
            binding.imageViewRestaurant.setImageUrl(item.image.url)

            item.isFavorite = repository.isFavoriteRestaurant(item.venue!!.id)
            setImageResource(binding.imageViewFavoriteIcon, item.isFavorite)

            binding.imageViewFavoriteIcon.setOnClickListener {
                item.isFavorite = !item.isFavorite
                setImageResource(binding.imageViewFavoriteIcon, item.isFavorite)
                if (item.isFavorite) repository.saveFavoriteRestaurant(item.venue.id)
                else repository.removeFavoriteRestaurant(item.venue.id)
            }
        }
    }

    private fun setImageResource(imageView: ImageView, isFavorite: Boolean) {
        if (isFavorite) imageView.setImageResource(R.drawable.baseline_favorite_black_20)
        else imageView.setImageResource(R.drawable.baseline_favorite_border_black_20)
    }

}