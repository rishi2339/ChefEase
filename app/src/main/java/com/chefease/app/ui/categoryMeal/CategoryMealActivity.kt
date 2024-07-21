package com.chefease.app.ui.categoryMeal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chefease.app.R
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.data.remote.randomMeal.dto.Meal
import com.chefease.app.databinding.ActivityCategoryMealBinding
import com.chefease.app.databinding.CategoryRvItemBinding
import com.chefease.app.databinding.CategorylistRvItemBinding
import com.chefease.app.fragments.HomeFragment
import com.chefease.app.ui.adapter.AllCategoriesAdapter
import com.chefease.app.ui.adapter.MealByCategoryAdapter
import com.chefease.app.ui.mealDetail.MealDetailViewModel

class CategoryMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealBinding
    private lateinit var categoryMealViewModel: CategoryMealViewModel
    private lateinit var categoriesAdapter: MealByCategoryAdapter
    private lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        categoriesAdapter = MealByCategoryAdapter()
        binding.categoryListRv.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(this@CategoryMealActivity, LinearLayoutManager.VERTICAL, false)
        }
        categoryMealViewModel = ViewModelProvider(this)[CategoryMealViewModel::class.java]

        categoryName = intent.getStringExtra(HomeFragment.MEAL_CATEGORY)!!
        categoryMealViewModel.getCategoryMealDetail(categoryName)
        binding.categoryNameTv.text = categoryName
        binding.backIv.setOnClickListener {
            finish()
        }
        observeCategoryMeal()
    }

    private fun observeCategoryMeal() {
        categoryMealViewModel.observeCategoryMealLiveData().observe(this, Observer<List<CategoryMeal>> { meal ->
            categoriesAdapter.setMealByCategory(meal as ArrayList<CategoryMeal>)
        })
    }
}