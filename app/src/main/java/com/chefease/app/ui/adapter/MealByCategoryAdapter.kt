package com.chefease.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chefease.app.data.remote.allCategoriesMeal.dto.AllCategoriesMeal
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.databinding.ActivityCategoryMealBinding
import com.chefease.app.databinding.CategorylistRvItemBinding

class MealByCategoryAdapter(): RecyclerView.Adapter<MealByCategoryAdapter.ViewHolder>() {
    private var mealByCategory = ArrayList<CategoryMeal>()

    fun setMealByCategory(mealByCategory: List<CategoryMeal>) {
        this.mealByCategory = mealByCategory as ArrayList<CategoryMeal>
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: CategorylistRvItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MealByCategoryAdapter.ViewHolder(CategorylistRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return mealByCategory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealByCategory[position].strMealThumb)
            .into(holder.binding.categoryListIv)
        holder.binding.categoryItemText.text = mealByCategory[position].strMeal
    }
}