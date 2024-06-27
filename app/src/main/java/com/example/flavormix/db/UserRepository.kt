package com.example.flavormix.repository

import com.example.flavormix.db.UserDao
import com.example.flavormix.db.UserFavoriteMealDao
import com.example.flavormix.model.User
import com.example.flavormix.model.UserFavoriteMeal


class UserRepository(private val userDao: UserDao, private val userFavoriteMealDao: UserFavoriteMealDao) {
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)

    suspend fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    suspend fun insertUserFavoriteMeal(userFavoriteMeal: UserFavoriteMeal) {
        userFavoriteMealDao.insertUserFavoriteMeal(userFavoriteMeal)
    }

    suspend fun deleteUserFavoriteMeal(userId: Int, mealId: String) {
        userFavoriteMealDao.deleteUserFavoriteMeal(userId, mealId)
    }

    fun getUserFavoriteMeals(userId: Int) = userFavoriteMealDao.getUserFavoriteMeals(userId)

    suspend fun getUserFavoriteMeal(userId: Int, mealId: String) = userFavoriteMealDao.getUserFavoriteMeal(userId, mealId)
}
