package com.ameliok.newvenues.ui.recyclerview


import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ameliok.newvenues.R
import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.databinding.RestaurantListBinding
import com.ameliok.newvenues.domain.repository.RestaurantRepository
import com.ameliok.newvenues.utils.setImageUrl

class GetRestaurantViewHolder(
    private val binding: RestaurantListBinding,
    private val repository: RestaurantRepository
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemDomain: ItemDomain) {
        itemView.apply {
            val restaurant = itemDomain.venueDomain
            binding.textViewRestaurantName.text = restaurant.name
            binding.textViewRestaurantDescription.text = restaurant.short_description
            binding.imageViewRestaurant.setImageUrl(itemDomain.imageDomain.url)

            itemDomain.isFavorite = repository.isFavoriteRestaurant(itemDomain.venueDomain.id)
            setImageResource(binding.imageViewFavoriteIcon, itemDomain.isFavorite)

            binding.imageViewFavoriteIcon.setOnClickListener {
                itemDomain.isFavorite = !itemDomain.isFavorite
                setImageResource(binding.imageViewFavoriteIcon, itemDomain.isFavorite)
                if (itemDomain.isFavorite) repository.saveFavoriteRestaurant(itemDomain.venueDomain.id)
                else repository.removeFavoriteRestaurant(itemDomain.venueDomain.id)
            }
        }
    }

    private fun setImageResource(imageView: ImageView, isFavorite: Boolean) {
        if (isFavorite) imageView.setImageResource(R.drawable.baseline_favorite_black_20)
        else imageView.setImageResource(R.drawable.baseline_favorite_border_black_20)
    }

}