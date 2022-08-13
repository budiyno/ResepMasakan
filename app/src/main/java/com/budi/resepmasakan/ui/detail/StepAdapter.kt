package com.budi.resepmasakan.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.budi.resepmasakan.databinding.ItemListBinding

class StepAdapter: RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    private val listStep = ArrayList<String?>()
    fun setStep(step: List<String?>?){
        if(step == null) return
        this.listStep.clear()
        this.listStep.addAll(step)
    }

    class StepViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(step: String?) {
            with(binding){
                txtIngredient.text = step
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = listStep[position]
        holder.bind(step)
    }

    override fun getItemCount(): Int = listStep.size
}