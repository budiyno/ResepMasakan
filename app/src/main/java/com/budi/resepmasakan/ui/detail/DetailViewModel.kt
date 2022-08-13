package com.budi.resepmasakan.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.budi.resepmasakan.data.source.RecipeRepository
import com.budi.resepmasakan.data.source.remote.response.RecipeResponse

class DetailViewModel(private val dataRepository: RecipeRepository): ViewModel() {

    private lateinit var key : String

    fun setSelectedRecipe(key: String){
        this.key = key
    }

    fun getDetailRecipe(): LiveData<RecipeResponse> = dataRepository.getDetailRecipe(key)
}