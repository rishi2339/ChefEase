package com.chefease.app.data.remote.allCategoriesMeal.dto

import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.data.remote.randomMeal.dto.Meal


data class AllCategoriesMealResponse(
    val categories: List<AllCategoriesMeal>
)

data class AllCategoriesMeal(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)