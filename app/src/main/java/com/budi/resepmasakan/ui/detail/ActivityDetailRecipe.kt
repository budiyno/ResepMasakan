package com.budi.resepmasakan.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.budi.resepmasakan.R
import com.budi.resepmasakan.data.source.remote.response.RecipeResponse
import com.budi.resepmasakan.databinding.ActivityDetailRecipeBinding
import com.budi.resepmasakan.databinding.ContentDetailRecipeBinding
import com.budi.resepmasakan.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_recipe.*

class ActivityDetailRecipe : AppCompatActivity() {

    private lateinit var detailContentBinding : ContentDetailRecipeBinding
    private lateinit var bahanAdapter: BahanAdapter
    private lateinit var stepAdapter: StepAdapter

    companion object {
        const val EXTRA_KEY = "extra_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailRecipeBinding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailRecipeBinding.detailRecipe
        setContentView(activityDetailRecipeBinding.root)

//        setSupportActionBar(activityDetailRecipeBinding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)

        detail_recipe.visibility = View.INVISIBLE
        progressbar.visibility = View.VISIBLE
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if(extras != null){
            val key = extras.getString(EXTRA_KEY)
            if(key != null){
                viewModel.setSelectedRecipe(key)
                viewModel.getDetailRecipe().observe(this, { recipe ->
                    progressbar.visibility = View.GONE
                    detail_recipe.visibility = View.VISIBLE
                    if(recipe != null){
                        populateRecipe(recipe)
                    }
                })
            }
        }
    }

    private fun populateRecipe(recipe: RecipeResponse) {
        detailContentBinding.apply {
            txtTitle.text = recipe.title
            txtKesulitan.text = recipe.dificulty
            txtDurasiMemasak.text = recipe.times
            txtJumlahSajian.text = recipe.servings
            detailAdapter(recipe.ingredient, recipe.step)
        }
        Glide.with(this)
                .load(recipe.thumb)
                .transform(RoundedCorners(5))
                .apply(RequestOptions.placeholderOf(R.drawable.chef_hat))
                .error(R.drawable.ic_error)
                .into(detailContentBinding.imageDetail)
    }

    private fun detailAdapter(ingredient: List<String?>?, step: List<String?>?) = with(detailContentBinding) {
        cvIngredients.layoutManager = LinearLayoutManager(applicationContext)
        bahanAdapter = BahanAdapter()
        cvIngredients.adapter = bahanAdapter
        bahanAdapter.setBahan(ingredient)

        cvStepCooking.layoutManager = LinearLayoutManager(applicationContext)
        stepAdapter = StepAdapter()
        cvStepCooking.adapter = stepAdapter
        stepAdapter.setStep(step)
    }


}