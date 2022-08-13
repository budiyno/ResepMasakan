package com.budi.resepmasakan.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.budi.resepmasakan.R
import com.budi.resepmasakan.databinding.FragmentHomeBinding
import com.budi.resepmasakan.ui.category.ActivityCategory
import com.budi.resepmasakan.ui.category.CategoryFragment
import com.budi.resepmasakan.ui.detail.BahanAdapter
import com.budi.resepmasakan.ui.detail.StepAdapter
import com.budi.resepmasakan.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_recipe_reader.view.*

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentHomeBinding.catDessert.setOnClickListener {
            val intent = Intent(context, ActivityCategory::class.java)
            intent.putExtra(ActivityCategory.EXTRA_KEY, "resep-dessert")
            intent.putExtra(ActivityCategory.EXTRA_CATEGORY, "Dessert")
            startActivity(intent)
        }
        fragmentHomeBinding.catBreakfast.setOnClickListener {
            val intent = Intent(context, ActivityCategory::class.java)
            intent.putExtra(ActivityCategory.EXTRA_KEY, "sarapan")
            intent.putExtra(ActivityCategory.EXTRA_CATEGORY, "Sarapan")
            startActivity(intent)
        }
        fragmentHomeBinding.catSeafood.setOnClickListener {
            val intent = Intent(context, ActivityCategory::class.java)
            intent.putExtra(ActivityCategory.EXTRA_KEY, "resep-seafood")
            intent.putExtra(ActivityCategory.EXTRA_CATEGORY, "Resep Seafood")
            startActivity(intent)
        }
        fragmentHomeBinding.traditional.setOnClickListener {
            val intent = Intent(context, ActivityCategory::class.java)
            intent.putExtra(ActivityCategory.EXTRA_KEY, "masakan-tradisional")
            intent.putExtra(ActivityCategory.EXTRA_CATEGORY, "Masakan Tradisional")
            startActivity(intent)
        }

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            val recipeAdapter = HomeAdapter()

            fragmentHomeBinding.progressBar.visibility = View.VISIBLE

            viewModel.getNewRecipes().observe(viewLifecycleOwner, { recipe ->
                fragmentHomeBinding.progressBar.visibility = View.GONE
                recipeAdapter.setRecipe(recipe)
                recipeAdapter.notifyDataSetChanged()
            })
            with(fragmentHomeBinding.rvRecomendation){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = recipeAdapter
            }
        }
    }
}