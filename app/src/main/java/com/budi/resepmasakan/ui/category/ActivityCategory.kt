package com.budi.resepmasakan.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.budi.resepmasakan.R
import com.budi.resepmasakan.databinding.ActivityCategoryBinding
import com.budi.resepmasakan.ui.detail.DetailViewModel
import com.budi.resepmasakan.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.fragment_category.*

class ActivityCategory : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    companion object {
        const val EXTRA_KEY = "extra_key"
        const val EXTRA_CATEGORY = "extra_category"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[CategoriesViewModel::class.java]
        val categoryAdapter = GroupCategoryAdapter()

        progress_bar_category.visibility = View.VISIBLE
        
        val extras = intent.extras
        if(extras != null){
            val key = extras.getString(EXTRA_KEY)
            val category = extras.getString(EXTRA_CATEGORY)
            title_category.text = category.toString()
            if(key != null){
                viewModel.setSelectedCategory(key)
                viewModel.getRecipeByCategories().observe(this, {category ->
                    progress_bar_category.visibility = View.GONE
                    categoryAdapter.setRecipes(category)
                    categoryAdapter.notifyDataSetChanged()
                })
                with(binding.rvCategoryByGroup){
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = categoryAdapter
                }
            }
        }

    }
}