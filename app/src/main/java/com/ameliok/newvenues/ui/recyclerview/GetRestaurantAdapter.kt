package com.ameliok.newvenues.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ameliok.newvenues.domain.model.ItemDomain
import com.ameliok.newvenues.databinding.RestaurantListBinding
import com.ameliok.newvenues.domain.repository.RestaurantRepository

class GetRestaurantAdapter(
    private val repository: RestaurantRepository
)
    : ListAdapter<ItemDomain, GetRestaurantViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ItemDomain>() {
        override fun areItemsTheSame(oldItemDomain: ItemDomain, newItemDomain: ItemDomain): Boolean {
            return oldItemDomain.venueDomain == newItemDomain.venueDomain
        }

        override fun areContentsTheSame(
            oldItemDomain: ItemDomain, newItemDomain: ItemDomain
        ): Boolean {
            return oldItemDomain == newItemDomain
        }
    }

    override fun onBindViewHolder(holder: GetRestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetRestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RestaurantListBinding.inflate(layoutInflater, parent, false)
        return GetRestaurantViewHolder(binding, repository)
    }

}