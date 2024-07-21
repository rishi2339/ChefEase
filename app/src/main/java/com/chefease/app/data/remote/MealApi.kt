package com.chefease.app.data.remote

import com.chefease.app.data.remote.allCategoriesMeal.dto.AllCategoriesMealResponse
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMealResponse
import com.chefease.app.data.remote.randomMeal.dto.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi{
    @GET("random.php")
    fun getRandomMeal(): Call<MealResponse>

    @GET("lookup.php?")
    fun getMealById(@Query("i") mealId: String): Call<MealResponse>

    @GET("filter.php?")
    fun getCategoryMeal(@Query("c") categoryName: String): Call<CategoryMealResponse>

    @GET("categories.php")
    fun getCategories(): Call<AllCategoriesMealResponse>
}