package com.budi.resepmasakan.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.budi.resepmasakan.data.source.local.entity.CategoryEntity
import com.budi.resepmasakan.data.source.local.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipeentity")
    fun getNewRecipe(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipeentity WHERE favorite = 1")
    fun getFavoritedRecipe(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM categoryentity")
    fun getCategory(): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM recipeentity WHERE recId = :key")
    fun getDetailRecipe(key : String): LiveData<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipes: List<RecipeEntity>)

    @Update
    fun updateRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categories: List<CategoryEntity>)

    @Update
    fun updateCategory(category: CategoryEntity)

}