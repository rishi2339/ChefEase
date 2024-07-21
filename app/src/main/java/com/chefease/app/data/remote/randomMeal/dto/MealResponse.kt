package com.chefease.app.data.remote.randomMeal.dto

data class MealResponse(
    val meals: List<Meal>
)

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strCategory: String,
    val strArea: String,
    val strYoutube: String
)
