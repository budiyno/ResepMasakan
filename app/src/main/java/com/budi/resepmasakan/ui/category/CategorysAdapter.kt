package com.budi.resepmasakan.ui.category

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.budi.resepmasakan.data.source.remote.response.CategoryResponse
import com.budi.resepmasakan.databinding.ItemCategorysCardBinding

class CategorysAdapter: RecyclerView.Adapter<CategorysAdapter.CategorysViewHolder>() {

    private val listCategories = ArrayList<CategoryResponse>()

    fun addList(categories: List<CategoryResponse>?){
        if(categories == null) return
        this.listCategories.clear()
        this.listCategories.addAll(categories)
    }

    class CategorysViewHolder(private val binding: ItemCategorysCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(categories: CategoryResponse) {
            with(binding){
                txtCategorys.text = categories.category
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ActivityCategory::class.java)
                    intent.putExtra(ActivityCategory.EXTRA_KEY, categories.key)
                    intent.putExtra(ActivityCategory.EXTRA_CATEGORY, categories.category)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorysViewHolder {
        val itemCategoryBinding = ItemCategorysCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategorysViewHolder(itemCategoryBinding)
    }

    override fun onBindViewHolder(holder: CategorysViewHolder, position: Int) {
        val categories = listCategories[position]
        holder.bind(categories)
    }

    override fun getItemCount(): Int = listCategories.size
}