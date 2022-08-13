package com.budi.resepmasakan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.budi.resepmasakan.data.source.RecipeRepository
import com.budi.resepmasakan.data.source.remote.response.NewRecipesList

class HomeViewModel(private val dataRepository: RecipeRepository): ViewModel() {
    fun getNewRecipes(): LiveData<List<NewRecipesList>> = dataRepository.getNewRecipe()
}