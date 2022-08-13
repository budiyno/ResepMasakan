package com.budi.resepmasakan.data.source.remote

import android.util.Log
import com.budi.resepmasakan.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class RemoteDataSource {

    private val status: Boolean = true

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource().apply { instance = this }
        }

        private const val TAG = "RemoteDataSource"

    }

    fun getCategoryList(callback: LoadCategoryCallback) {
        val categoryList = ArrayList<CategoryResponse>()
        val dataFromApi = ApiConfig.getApiService().getCategory()
        dataFromApi.enqueue(object : Callback<ResponseCategories>{
            override fun onResponse(call: Call<ResponseCategories>, response: Response<ResponseCategories>) {
                if(response.isSuccessful){
                    val categoryListResult : ArrayList<ResultsItem>? = response.body()?.results
                    if(categoryListResult != null){
                        for(list in categoryListResult){
                            val category = CategoryResponse(
                                    list.category,
                                    list.url,
                                    list.key
                            )
                            categoryList.add(category)
                        }
                    }
                    callback.onAllCategoryReceived(categoryList)
                }else{
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseCategories>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getNewRecipesList(callback: LoadNewRecipeCallback) {
        val recipeList = ArrayList<NewRecipesList>()
        val dataFromApi = ApiConfig.getApiService().getNewRecipes()
        dataFromApi.enqueue(object : Callback<NewRecipeResponse>{
            override fun onResponse(call: Call<NewRecipeResponse>, response: Response<NewRecipeResponse>) {
                if(response.isSuccessful){
                    val newRecipeResult : ArrayList<ResultsItems>? = response.body()?.results
                    if(newRecipeResult != null){
                        for(list in newRecipeResult){
                            val recipes = NewRecipesList(
                                    list.times,
                                    list.thumb,
                                    list.portion,
                                    list.title,
                                    list.key,
                                    list.dificulty
                            )
                            recipeList.add(recipes)
                        }
                    }
                    callback.onAllNewRecipeReceived(recipeList)
                }else{
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewRecipeResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getDetailRecipe(key: String, callback: LoadDetailRecipeCallback) {
        lateinit var recipe : RecipeResponse
        val dataFromApi = ApiConfig.getApiService().getRecipe(key)
        dataFromApi.enqueue(object : Callback<ResponseRecipes> {
            override fun onResponse(call: Call<ResponseRecipes>, response: Response<ResponseRecipes>) {
                if (response.isSuccessful) {
                    val recipeDetail: Results? = response.body()?.results
                    if (recipeDetail != null) {
                        recipe = RecipeResponse(
                                "",
                                recipeDetail.title,
                                recipeDetail.thumb,
                                recipeDetail.servings,
                                recipeDetail.times,
                                recipeDetail.dificulty,
                                recipeDetail.author?.user,
                                recipeDetail.author?.datePublished,
                                recipeDetail.ingredient,
                                recipeDetail.step
                        )
                    }
                    callback.onDetailRecipeReceived(recipe)
                }else{
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseRecipes>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getRecipeByCategory(key: String, callback: LoadRecipeByCategoryCallback){
        val recipeList = ArrayList<ResultsItemCat>()
        val dataFromApi = ApiConfig.getApiService().getRecipeByCategory(key)
        dataFromApi.enqueue(object : Callback<ResponseByCategory>{
            override fun onResponse(call: Call<ResponseByCategory>, response: Response<ResponseByCategory>) {
                if(response.isSuccessful){
                    val resultCategory : ArrayList<ResultsItemCat>? = response.body()?.results as ArrayList<ResultsItemCat>?
                    if(resultCategory != null){
                        for(category in resultCategory){
                            val cat = ResultsItemCat(
                                    category.times,
                                    category.thumb,
                                    category.portion,
                                    category.title,
                                    category.key,
                                    category.dificulty
                            )
                            recipeList.add(cat)
                        }
                    }
                    callback.onAllRecipeByCategoryReceived(recipeList)
                }
            }

            override fun onFailure(call: Call<ResponseByCategory>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

    }

    interface LoadRecipeByCategoryCallback {
        fun onAllRecipeByCategoryReceived(recipeByCategoryResponse: List<ResultsItemCat>)
    }

    interface LoadDetailRecipeCallback {
        fun onDetailRecipeReceived(detailRecipeResponse: RecipeResponse)
    }

    interface LoadNewRecipeCallback {
        fun onAllNewRecipeReceived(newRecipeResponse: List<NewRecipesList>)
    }

    interface LoadCategoryCallback {
        fun onAllCategoryReceived(categoryResponse: List<CategoryResponse>)
    }

//    fun getCategoryList(): LiveData<ApiResponse<List<CategoryResponse>>> {
//        val resultCategory = MutableLiveData<ApiResponse<List<CategoryResponse>>>()
//        val categoryList = ArrayList<CategoryResponse>()
//        val dataFromApi = ApiConfig.getApiService().getCategory(status)
//        dataFromApi.enqueue(object : Callback<ResponseCategories>{
//            override fun onResponse(call: Call<ResponseCategories>, response: Response<ResponseCategories>) {
//                if(response.isSuccessful){
//                    val categoryListResult : ArrayList<ResultsItem>? = response.body()?.results
//                    if(categoryListResult != null){
//                        for(list in categoryListResult){
//                            val category = CategoryResponse(
//                                    list.category,
//                                    list.url,
//                                    list.key
//                            )
//                            categoryList.add(category)
//                        }
//                    }
//                    resultCategory.value = ApiResponse.success(categoryList)
//                }else{
//                    Log.e(TAG, "onFailure : ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseCategories>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//
//        })
//        return resultCategory
//    }
//
//    fun getNewRecipesList(): LiveData<ApiResponse<List<NewRecipesList>>> {
//        val resultRecipeList = MutableLiveData<ApiResponse<List<NewRecipesList>>>()
//        val recipeList = ArrayList<NewRecipesList>()
//        val dataFromApi = ApiConfig.getApiService().getNewRecipes(status)
//        dataFromApi.enqueue(object : Callback<NewRecipeResponse>{
//            override fun onResponse(call: Call<NewRecipeResponse>, response: Response<NewRecipeResponse>) {
//                if(response.isSuccessful){
//                    val newRecipeResult : ArrayList<ResultsItems>? = response.body()?.results
//                    if(newRecipeResult != null){
//                        for(list in newRecipeResult){
//                            val recipes = NewRecipesList(
//                                    list.times,
//                                    list.thumb,
//                                    list.portion,
//                                    list.title,
//                                    list.key,
//                                    list.dificulty
//                            )
//                            recipeList.add(recipes)
//                        }
//                    }
//                    resultRecipeList.value = ApiResponse.success(recipeList)
//                }else{
//                    Log.e(TAG, "onFailure : ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<NewRecipeResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//
//        })
//        return resultRecipeList
//    }
//
//    fun getDetailRecipe(key: String): LiveData<ApiResponse<RecipeResponse>>{
//        val resultDetailRecipe = MutableLiveData<ApiResponse<RecipeResponse>>()
//        lateinit var recipe : RecipeResponse
//        val dataFromApi = ApiConfig.getApiService().getRecipe(key)
//        dataFromApi.enqueue(object : Callback<ResponseRecipes> {
//            override fun onResponse(call: Call<ResponseRecipes>, response: Response<ResponseRecipes>) {
//                if (response.isSuccessful) {
//                    val recipeDetail: Results? = response.body()?.results
//                    if (recipeDetail != null) {
//                        recipe = RecipeResponse(
//                                "",
//                                recipeDetail.title,
//                                recipeDetail.thumb,
//                                recipeDetail.servings,
//                                recipeDetail.times,
//                                recipeDetail.dificulty,
//                                recipeDetail.author?.user,
//                                recipeDetail.author?.datePublished,
//                                recipeDetail.ingredient,
//                                recipeDetail.step
//                        )
//                    }
//                    resultDetailRecipe.value = ApiResponse.success(recipe)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseRecipes>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//        return resultDetailRecipe
//    }
}
