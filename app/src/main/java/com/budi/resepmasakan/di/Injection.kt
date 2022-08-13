package com.budi.resepmasakan.di

import android.content.Context
import com.budi.resepmasakan.data.source.RecipeRepository
import com.budi.resepmasakan.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): RecipeRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return RecipeRepository.getInstance(remoteDataSource)
    }
}