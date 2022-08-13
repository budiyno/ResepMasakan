package com.budi.resepmasakan.ui.category

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.budi.resepmasakan.R
import com.budi.resepmasakan.data.source.remote.response.ResultsItemCat
import com.budi.resepmasakan.databinding.ItemCardRecipeBinding
import com.budi.resepmasakan.ui.detail.ActivityDetailRecipe
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GroupCategoryAdapter: RecyclerView.Adapter<GroupCategoryAdapter.GroupViewHolder>() {

    private val listGroup = ArrayList<ResultsItemCat>()

    fun setRecipes(category: List<ResultsItemCat>?){
        if(category == null) return
        this.listGroup.clear()
        this.listGroup.addAll(category)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupCategoryAdapter.GroupViewHolder {
        val itemCardBinding = ItemCardRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupViewHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: GroupCategoryAdapter.GroupViewHolder, position: Int) {
        val category = listGroup[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = listGroup.size

    class GroupViewHolder(private val binding: ItemCardRecipeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: ResultsItemCat) {
            with(binding){
                itemName.text = category.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ActivityDetailRecipe::class.java)
                    intent.putExtra(ActivityDetailRecipe.EXTRA_KEY, category.key)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(category.thumb)
                        .apply(RequestOptions.placeholderOf(R.drawable.book_brown))
                        .error(R.drawable.ic_error)
                        .into(imgNewRecipe)
            }

        }

    }
}