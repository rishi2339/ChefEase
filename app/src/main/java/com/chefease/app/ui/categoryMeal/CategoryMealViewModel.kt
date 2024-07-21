package com.chefease.app.ui.categoryMeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chefease.app.data.remote.RetrofitInstance
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMeal
import com.chefease.app.data.remote.categoryMeal.dto.CategoryMealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealViewModel:ViewModel() {
    private var categoryMealLiveData = MutableLiveData<List<CategoryMeal>>()

    fun getCategoryMealDetail(categoryName: String){
        RetrofitInstance.mealApi.getCategoryMeal(categoryName).enqueue(object : Callback<CategoryMealResponse> {
            override fun onResponse(call: Call<CategoryMealResponse>, response: Response<CategoryMealResponse>) {
                if(response.body()!=null) {
                    val categoryMeal = response.body()
                    categoryMealLiveData.value = categoryMeal!!.meals
                }
            }

            override fun onFailure(call: Call<CategoryMealResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun observeCategoryMealLiveData(): LiveData<List<CategoryMeal>> {
        return categoryMealLiveData
    }
}