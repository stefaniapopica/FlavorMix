package com.example.flavormix.repository

import androidx.lifecycle.LiveData
import com.example.flavormix.db.UserFavoriteMealDao
import com.example.flavormix.model.Meal
import com.example.flavormix.model.UserFavoriteMeal

class UserFavoriteMealRepository(private val userFavoriteMealDao: UserFavoriteMealDao) {

    suspend fun addFavoriteMeal(userFavoriteMeal: UserFavoriteMeal) {
        userFavoriteMealDao.insertUserFavoriteMeal(userFavoriteMeal)
    }

    suspend fun removeFavoriteMeal(userId: Int, mealId: String) {
        userFavoriteMealDao.deleteUserFavoriteMeal(userId, mealId)
    }

    fun getUserFavoriteMeals(userId: Int): LiveData<List<Meal>> {
        return userFavoriteMealDao.getUserFavoriteMeals(userId)
    }

    suspend fun getUserFavoriteMeal(userId: Int, mealId: String): UserFavoriteMeal? {
        return userFavoriteMealDao.getUserFavoriteMeal(userId, mealId)
    }
}
