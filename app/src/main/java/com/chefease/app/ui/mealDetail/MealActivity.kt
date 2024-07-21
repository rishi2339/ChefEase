package com.chefease.app.ui.mealDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chefease.app.R
import com.chefease.app.data.remote.randomMeal.dto.Meal
import com.chefease.app.databinding.ActivityMealBinding
import com.chefease.app.fragments.HomeFragment

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var mealDetailViewModel: MealDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMealBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.meal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mealDetailViewModel = ViewModelProvider(this)[MealDetailViewModel::class.java]
        observeMealDetailsLiveData()

        getRandomMeal()
        mealDetailViewModel.getMealDetail(mealId)
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
    }

    private fun observeMealDetailsLiveData() {
        mealDetailViewModel.observeMealDetailLiveData().observe(this, Observer<Meal> { meal ->
            binding.mealCategory.text = meal.strCategory
            binding.mealArea.text = meal.strArea
            binding.instructionsText.text = meal.strInstructions
            binding.youtubeIv.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube))
                startActivity(intent)
            }
        })
    }

    private fun getRandomMeal() {
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}