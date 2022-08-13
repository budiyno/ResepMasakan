package com.budi.resepmasakan.data.source.remote.response

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("recipe/:{key}")
    fun getRecipe(@Path("key") key: String): Call<ResponseRecipes>

    @GET("category/recipes")
    fun getCategory(): Call<ResponseCategories>

//    @GET("categorys/recipes")
//    fun getCategory(@Query("status") status: Boolean): Call<ResponseCategories>

    @GET("recipes")
    fun getNewRecipes(): Call<NewRecipeResponse>

    @GET("category/recipes/:{key}")
    fun getRecipeByCategory(@Path("key") key: String): Call<ResponseByCategory>

}