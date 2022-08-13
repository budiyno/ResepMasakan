package com.budi.resepmasakan.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.budi.resepmasakan.data.source.RecipeRepository
import com.budi.resepmasakan.di.Injection
import com.budi.resepmasakan.ui.category.CategoriesViewModel
import com.budi.resepmasakan.ui.detail.DetailViewModel
import com.budi.resepmasakan.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val mRecipeRepository: RecipeRepository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this){
                    instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                        instance = this
                    }
                }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(mRecipeRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mRecipeRepository) as T
            }
            modelClass.isAssignableFrom(CategoriesViewModel::class.java) -> {
                return CategoriesViewModel(mRecipeRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}