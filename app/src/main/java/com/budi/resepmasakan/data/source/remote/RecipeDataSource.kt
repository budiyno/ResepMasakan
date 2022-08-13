package com.budi.resepmasakan.data.source.remote

import androidx.lifecycle.LiveData
import com.budi.resepmasakan.data.CategoryEntity
import com.budi.resepmasakan.data.RecipeEntity
import com.budi.resepmasakan.data.source.remote.response.*

interface RecipeDataSource {

    fun getNewRecipe(): LiveData<List<NewRecipesList>>

    fun getDetailRecipe(key: String): LiveData<RecipeResponse>

    fun getCategoryRecipes(): LiveData<List<CategoryResponse>>

    fun getRecipeByCategory(key: String): LiveData<List<ResultsItemCat>>
}