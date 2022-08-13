package com.budi.resepmasakan.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.budi.resepmasakan.data.source.RecipeRepository
import com.budi.resepmasakan.data.source.remote.response.CategoryResponse
import com.budi.resepmasakan.data.source.remote.response.ResultsItemCat

class CategoriesViewModel(private val repository: RecipeRepository): ViewModel() {

    private lateinit var key : String
    fun setSelectedCategory(key: String){
        this.key = key
    }

    fun getCategories(): LiveData<List<CategoryResponse>> = repository.getCategoryRecipes()

    fun getRecipeByCategories(): LiveData<List<ResultsItemCat>> = repository.getRecipeByCategory(key)
}