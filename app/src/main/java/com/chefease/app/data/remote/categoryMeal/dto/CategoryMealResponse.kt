package com.chefease.app.data.remote.categoryMeal.dto

data class CategoryMealResponse(
    val meals: List<CategoryMeal>
)

data class CategoryMeal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)
