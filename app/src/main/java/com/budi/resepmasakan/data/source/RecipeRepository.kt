package com.budi.resepmasakan.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.budi.resepmasakan.data.CategoryEntity
import com.budi.resepmasakan.data.RecipeEntity
import com.budi.resepmasakan.data.source.remote.RecipeDataSource
import com.budi.resepmasakan.data.source.remote.RemoteDataSource
import com.budi.resepmasakan.data.source.remote.response.*
import com.budi.resepmasakan.vo.Resource

class RecipeRepository private constructor(
        private val remoteDataSource: RemoteDataSource
): RecipeDataSource{

    companion object {
        @Volatile
        private var instance: RecipeRepository? = null

        fun getInstance(remoteData: RemoteDataSource): RecipeRepository = instance ?: synchronized(this){
            instance ?: RecipeRepository(remoteData).apply { instance = this }
        }
    }

    override fun getNewRecipe(): LiveData<List<NewRecipesList>> {
        val recipeResults = MutableLiveData<List<NewRecipesList>>()
        remoteDataSource.getNewRecipesList(object: RemoteDataSource.LoadNewRecipeCallback{
            override fun onAllNewRecipeReceived(newRecipeResponse: List<NewRecipesList>) {
                val recipeList = ArrayList<NewRecipesList>()
                for(list in newRecipeResponse){
                    val recipe = NewRecipesList(
                            list.times,
                            list.thumb,
                            list.portion,
                            list.title,
                            list.key,
                            list.dificulty
                    )
                    recipeList.add(recipe)
                }
                recipeResults.postValue(recipeList)
            }
        })
        return recipeResults
    }

    override fun getDetailRecipe(key: String): LiveData<RecipeResponse> {
        val recipeResults = MutableLiveData<RecipeResponse>()
        remoteDataSource.getDetailRecipe(key, object : RemoteDataSource.LoadDetailRecipeCallback {
            override fun onDetailRecipeReceived(detailRecipeResponse: RecipeResponse) {
                recipeResults.postValue(detailRecipeResponse)
            }
        })
        return recipeResults
    }

    override fun getCategoryRecipes(): LiveData<List<CategoryResponse>> {
        val results = MutableLiveData<List<CategoryResponse>>()
        remoteDataSource.getCategoryList(object : RemoteDataSource.LoadCategoryCallback{
            override fun onAllCategoryReceived(categoryResponse: List<CategoryResponse>) {
                val categoryList = ArrayList<CategoryResponse>()
                for(list in categoryResponse){
//                    val category = CategoryResponse()
                    val category = CategoryResponse(
                            list.category,
                            list.url,
                            list.key
                    )
                    categoryList.add(category)
                }
                results.postValue(categoryList)
            }
        })
        return results
    }

    override fun getRecipeByCategory(key: String): LiveData<List<ResultsItemCat>> {
        val results = MutableLiveData<List<ResultsItemCat>>()
        remoteDataSource.getRecipeByCategory(key, object : RemoteDataSource.LoadRecipeByCategoryCallback{
            override fun onAllRecipeByCategoryReceived(recipeByCategoryResponse: List<ResultsItemCat>) {
                val categories = ArrayList<ResultsItemCat>()
                for(list in recipeByCategoryResponse){
                    val category = ResultsItemCat(
                            list.times,
                            list.thumb,
                            list.portion,
                            list.title,
                            list.key,
                            list.dificulty
                    )
                    categories.add(category)
                }
                results.postValue(categories)
            }
        })
        return results
    }

}