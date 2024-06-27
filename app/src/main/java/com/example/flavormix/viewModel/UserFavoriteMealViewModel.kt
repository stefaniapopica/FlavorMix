package com.example.flavormix.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flavormix.model.Meal
import com.example.flavormix.model.UserFavoriteMeal
import com.example.flavormix.repository.UserFavoriteMealRepository
import kotlinx.coroutines.launch

class UserFavoriteMealViewModel(private val repository: UserFavoriteMealRepository) : ViewModel() {

    fun insertUserFavoriteMeal(userFavoriteMeal: UserFavoriteMeal) = viewModelScope.launch {
        repository.addFavoriteMeal(userFavoriteMeal)
    }

    fun deleteUserFavoriteMeal(userFavoriteMeal: UserFavoriteMeal) = viewModelScope.launch {
        repository.removeFavoriteMeal(userFavoriteMeal.userId, userFavoriteMeal.mealId)
    }

    fun getUserFavoriteMeals(userId: Int): LiveData<List<Meal>> {
        return repository.getUserFavoriteMeals(userId)
    }

    suspend fun getUserFavoriteMeal(userId: Int, mealId: String): UserFavoriteMeal? {
        return repository.getUserFavoriteMeal(userId, mealId)
    }
}
