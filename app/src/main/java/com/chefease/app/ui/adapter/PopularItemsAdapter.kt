package com.chefease.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMealResponse
import com.chefease.app.databinding.PopularRvItemBinding

class PopularItemsAdapter(): RecyclerView.Adapter<PopularItemsAdapter.ViewHolder>() {
    private var popularItemsList = ArrayList<CategoryMeal>()
    lateinit var onImageClick: ((CategoryMeal) -> Unit)

    fun setPopularItems(mealList: ArrayList<CategoryMeal>){
        this.popularItemsList = mealList
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: PopularRvItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PopularItemsAdapter.ViewHolder(PopularRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return popularItemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(popularItemsList[position].strMealThumb)
            .into(holder.binding.rvPopularImage)
        holder.itemView.setOnClickListener {
            onImageClick.invoke(popularItemsList[position])
        }
    }
}