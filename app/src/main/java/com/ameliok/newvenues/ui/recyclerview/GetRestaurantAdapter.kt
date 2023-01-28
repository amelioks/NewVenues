package com.ameliok.newvenues.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ameliok.newvenues.data.preference.SharedPreferenceHelper
import com.ameliok.newvenues.data.api.model.Item
import com.ameliok.newvenues.databinding.RestaurantListBinding

class GetRestaurantAdapter(
    private val sharedPreferenceHelper: SharedPreferenceHelper
)
    : ListAdapter<Item, GetRestaurantViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.venue == newItem.venue
        }

        override fun areContentsTheSame(
            oldItem: Item, newItem: Item
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: GetRestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetRestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RestaurantListBinding.inflate(layoutInflater, parent, false)
        return GetRestaurantViewHolder(binding, sharedPreferenceHelper)
    }

}