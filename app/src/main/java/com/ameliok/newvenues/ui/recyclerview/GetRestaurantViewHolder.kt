package com.ameliok.newvenues.ui.recyclerview


import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ameliok.newvenues.R
import com.ameliok.newvenues.data.SharedPreferenceHelper
import com.ameliok.newvenues.data.model.Item
import com.ameliok.newvenues.databinding.RestaurantListBinding
import com.ameliok.newvenues.setImageUrl

class GetRestaurantViewHolder(
    private val binding: RestaurantListBinding,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        itemView.apply {
            val restaurant = item.venue
            binding.textViewRestaurantName.text = restaurant?.name
            binding.textViewRestaurantDescription.text = restaurant?.short_description
            binding.imageViewRestaurant.setImageUrl(item.image.url)

            item.isFavorited = sharedPreferenceHelper.favoriteVenuesIds.contains(item.venue!!.id)

            setImageResource(binding.imageViewFavoriteIcon, item.isFavorited)

            binding.imageViewFavoriteIcon.setOnClickListener {
                item.isFavorited = !item.isFavorited
                setImageResource(binding.imageViewFavoriteIcon, item.isFavorited)
                if (item.isFavorited) {
                    sharedPreferenceHelper.favoriteVenuesIds =
                        sharedPreferenceHelper.favoriteVenuesIds.plus(item.venue.id)
                } else {
                    sharedPreferenceHelper.favoriteVenuesIds =
                        sharedPreferenceHelper.favoriteVenuesIds.minus(item.venue.id)
                }
            }
        }
    }

    private fun setImageResource(imageView: ImageView, isFavorited: Boolean) {
        if (isFavorited) imageView.setImageResource(R.drawable.baseline_favorite_black_20)
        else imageView.setImageResource(R.drawable.baseline_favorite_border_black_20)
    }

}