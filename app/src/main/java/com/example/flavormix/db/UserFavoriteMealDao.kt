package com.example.flavormix.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flavormix.model.Meal
import com.example.flavormix.model.UserFavoriteMeal

@Dao
interface UserFavoriteMealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFavoriteMeal(userFavoriteMeal: UserFavoriteMeal)

    @Query("DELETE FROM UserFavoriteMeal WHERE userId = :userId AND mealId = :mealId")
    suspend fun deleteUserFavoriteMeal(userId: Int, mealId: String)

    @Query("SELECT * FROM mealInformation WHERE idMeal IN (SELECT mealId FROM UserFavoriteMeal WHERE userId = :userId)")
    fun getUserFavoriteMeals(userId: Int): LiveData<List<Meal>>

    @Query("SELECT * FROM UserFavoriteMeal WHERE userId = :userId AND mealId = :mealId LIMIT 1")
    suspend fun getUserFavoriteMeal(userId: Int, mealId: String): UserFavoriteMeal?
}
