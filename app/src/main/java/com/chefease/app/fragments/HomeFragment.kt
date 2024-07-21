package com.chefease.app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chefease.app.data.remote.allCategoriesMeal.dto.AllCategoriesMeal
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.data.remote.randomMeal.dto.Meal
import com.chefease.app.ui.mealDetail.MealActivity
import com.chefease.app.databinding.FragmentHomeBinding
import com.chefease.app.ui.categoryMeal.CategoryMealActivity
import com.chefease.app.ui.adapter.AllCategoriesAdapter
import com.chefease.app.ui.adapter.PopularItemsAdapter
import com.chefease.app.ui.home.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: PopularItemsAdapter
    private lateinit var allCategoriesMeal: AllCategoriesAdapter

    companion object{
        const val MEAL_ID = "com.chefease.app.ui.home.idMeal"
        const val MEAL_NAME = "com.chefease.app.ui.home.nameMeal"
        const val MEAL_THUMB = "com.chefease.app.ui.home.thumbMeal"
        const val MEAL_CATEGORY = "com.chefease.app.ui.home.categoryMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel= ViewModelProvider(this)[HomeViewModel::class.java]
        popularItemsAdapter= PopularItemsAdapter()
        allCategoriesMeal = AllCategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
            adapter = allCategoriesMeal
        }
        homeViewModel.getRandomMeal()
        observeRandomMeal()
        homeViewModel.getPopularMeals()
        observePopularMeal()
        homeViewModel.getAllCategories()
        observeAllCategories()
        binding.randomImage.setOnClickListener{
            onMealImageClick()
        }
        popularItemsAdapter.onImageClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
        allCategoriesMeal.onCategoryImageClick = { meal ->
            val intent = Intent(activity, CategoryMealActivity::class.java)
            intent.putExtra(MEAL_CATEGORY,meal.strCategory)
            startActivity(intent)
        }
    }

    private fun observeAllCategories() {
        homeViewModel.observeAllCategoriesLiveData().observe(viewLifecycleOwner){ category ->
            allCategoriesMeal.setAllCategories(category as ArrayList<AllCategoriesMeal>)
        }
    }

    private fun onMealImageClick() {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
    }

    private fun observeRandomMeal() {
        homeViewModel.observeRandomMealLiveData().observe(viewLifecycleOwner) { value ->
            Glide.with(this@HomeFragment)
                .load(value.strMealThumb)
                .into(binding.randomImage)
            this.randomMeal=value
        }
    }
    private fun observePopularMeal() {
        homeViewModel.observePopularMealLiveData().observe(viewLifecycleOwner) { mealList ->
            popularItemsAdapter.setPopularItems(mealList as ArrayList<CategoryMeal>)
        }
    }
}