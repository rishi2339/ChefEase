package com.chefease.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chefease.app.data.remote.allCategoriesMeal.dto.AllCategoriesMeal
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.databinding.CategoryRvItemBinding

class AllCategoriesAdapter(): RecyclerView.Adapter<AllCategoriesAdapter.AllCategoriesViewHolder>() {
    private var categories = ArrayList<AllCategoriesMeal>()
    lateinit var onCategoryImageClick: ((AllCategoriesMeal) -> Unit)

    fun setAllCategories(categories: ArrayList<AllCategoriesMeal>){
        this.categories = categories
        notifyDataSetChanged()
    }

    class AllCategoriesViewHolder(var binding: CategoryRvItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategoriesViewHolder {
        return AllCategoriesViewHolder(CategoryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: AllCategoriesViewHolder, position: Int) {
        holder.binding.categoryItemText.text = categories[position].strCategory
        Glide.with(holder.itemView)
            .load(categories[position].strCategoryThumb)
            .into(holder.binding.categoryItemImage)
        holder.binding.categoryItemImage.setOnClickListener{onCategoryImageClick.invoke(categories[position])}
    }
}