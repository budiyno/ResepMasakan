package com.budi.resepmasakan.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.budi.resepmasakan.data.source.remote.response.Ingredients
import com.budi.resepmasakan.databinding.ItemListBinding

class BahanAdapter: RecyclerView.Adapter<BahanAdapter.BahanViewHolder>() {

    private val listBahan = ArrayList<String?>()
    fun setBahan(bahan: List<String?>?){
        if(bahan == null) return
        this.listBahan.clear()
        this.listBahan.addAll(bahan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BahanAdapter.BahanViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BahanViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: BahanAdapter.BahanViewHolder, position: Int) {
        val bahan = listBahan[position]
        holder.bind(bahan)
    }

    override fun getItemCount(): Int = listBahan.size

    class BahanViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(bahan: String?) {
            with(binding){
                txtIngredient.text = bahan
            }
        }
    }
}