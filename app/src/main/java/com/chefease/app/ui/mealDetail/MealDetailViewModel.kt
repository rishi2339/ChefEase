package com.chefease.app.ui.mealDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chefease.app.data.remote.RetrofitInstance
import com.chefease.app.data.remote.randomMeal.dto.Meal
import com.chefease.app.data.remote.randomMeal.dto.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailViewModel: ViewModel() {
    private var mealDetailLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id: String){
        RetrofitInstance.mealApi.getMealById(id).enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if(response.body()!=null) {
                    val meal = response.body()
                    mealDetailLiveData.value = meal!!.meals[0]
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun observeMealDetailLiveData(): LiveData<Meal> {
        return mealDetailLiveData
    }
}