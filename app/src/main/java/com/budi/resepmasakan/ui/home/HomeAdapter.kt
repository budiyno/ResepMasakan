package com.budi.resepmasakan.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.budi.resepmasakan.R
import com.budi.resepmasakan.RecipeReaderActivity
import com.budi.resepmasakan.data.source.remote.response.NewRecipesList
import com.budi.resepmasakan.databinding.ItemCardRecipeBinding
import com.budi.resepmasakan.ui.detail.ActivityDetailRecipe
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val listRecipes = ArrayList<NewRecipesList>()

    fun setRecipe(recipes: List<NewRecipesList>?){
        if(recipes == null) return
        this.listRecipes.clear()
        this.listRecipes.addAll(recipes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemCardRecipeBinding = ItemCardRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemCardRecipeBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val recipe = listRecipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = listRecipes.size

    class HomeViewHolder(private val binding: ItemCardRecipeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: NewRecipesList) {
            with(binding){
                itemName.text = recipe.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ActivityDetailRecipe::class.java)
                    intent.putExtra(ActivityDetailRecipe.EXTRA_KEY, recipe.key)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(recipe.thumb)
                        .apply(RequestOptions.placeholderOf(R.drawable.book_brown))
                        .error(R.drawable.ic_error)
                        .into(imgNewRecipe)
            }
        }
    }
}