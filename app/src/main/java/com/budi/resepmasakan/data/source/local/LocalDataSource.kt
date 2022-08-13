package com.budi.resepmasakan.data.source.local

import androidx.lifecycle.LiveData
import com.budi.resepmasakan.data.source.local.entity.CategoryEntity
import com.budi.resepmasakan.data.source.local.entity.RecipeEntity
import com.budi.resepmasakan.data.source.local.room.RecipeDao

class LocalDataSource private constructor(private val mRecipeDao: RecipeDao) {

    companion object {
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(recipeDao: RecipeDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(recipeDao)
    }

    fun getNewRecipes(): LiveData<List<RecipeEntity>> = mRecipeDao.getNewRecipe()

    fun getFavoriteRecipe(): LiveData<List<RecipeEntity>> = mRecipeDao.getFavoritedRecipe()

    fun getDetailRecipe(key: String): LiveData<RecipeEntity> = mRecipeDao.getDetailRecipe(key)

    fun getCategory(): LiveData<List<CategoryEntity>> = mRecipeDao.getCategory()

    fun insertRecipe(recipes : List<RecipeEntity>) = mRecipeDao.insertRecipe(recipes)

    fun setFavorite(fav: RecipeEntity, newState: Boolean){
        fav.favorite = newState
        mRecipeDao.updateRecipe(fav)
    }

    fun updateRecipe(key: RecipeEntity){
        mRecipeDao.updateRecipe(key)
    }
}