package com.chefease.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chefease.app.data.remote.RetrofitInstance
import com.chefease.app.data.remote.allCategoriesMeal.dto.AllCategoriesMeal
import com.chefease.app.data.remote.allCategoriesMeal.dto.AllCategoriesMealResponse
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMealResponse
import com.chefease.app.data.remote.randomMeal.dto.Meal
import com.chefease.app.data.remote.randomMeal.dto.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel:ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularMealsLiveData = MutableLiveData<List<CategoryMeal>>()
    private var allCategoriesLiveData = MutableLiveData<List<AllCategoriesMeal>>()

    fun getRandomMeal(){
        RetrofitInstance.mealApi.getRandomMeal().enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if(response.body()!=null) {
                    val meal = response.body()
                    randomMealLiveData.value = meal!!.meals[0]
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun getPopularMeals() {
        RetrofitInstance.mealApi.getCategoryMeal("Seafood").enqueue(object : Callback<CategoryMealResponse>{
            override fun onResponse(
                call: Call<CategoryMealResponse>,
                response: Response<CategoryMealResponse>
            ) {
                if(response.body()!=null){
                    val meals = response.body()!!.meals
                    popularMealsLiveData.value = meals
                }
            }

            override fun onFailure(call: Call<CategoryMealResponse>, t: Throwable) {
                Log.e("TAG",t.message.toString())
            }
        })
    }
    fun observePopularMealLiveData(): LiveData<List<CategoryMeal>> {
        return popularMealsLiveData
    }

    fun getAllCategories() {
        RetrofitInstance.mealApi.getCategories().enqueue(object : Callback<AllCategoriesMealResponse>{
            override fun onResponse(
                call: Call<AllCategoriesMealResponse>,
                response: Response<AllCategoriesMealResponse>
            ) {
                if(response.body()!=null){
                    val categories = response.body()!!.categories
                    allCategoriesLiveData.value = categories
                }
            }

            override fun onFailure(call: Call<AllCategoriesMealResponse>, t: Throwable) {
                Log.e("TAG",t.message.toString())
            }
        })
    }
    fun observeAllCategoriesLiveData(): LiveData<List<AllCategoriesMeal>> {
        return allCategoriesLiveData
    }
}