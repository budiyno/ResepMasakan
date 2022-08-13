package com.budi.resepmasakan.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.budi.resepmasakan.R
import com.budi.resepmasakan.databinding.FragmentCategoryBinding
//import com.budi.resepmasakan.databinding.FragmentCategoryBinding
import com.budi.resepmasakan.viewmodel.ViewModelFactory

class CategoryFragment : Fragment() {

    private lateinit var categoryBinding: FragmentCategoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        categoryBinding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return categoryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[CategoriesViewModel::class.java]
            val adapterCategory = CategorysAdapter()

            categoryBinding.progressBar.visibility = View.VISIBLE

            viewModel.getCategories().observe(viewLifecycleOwner, { category ->
                categoryBinding.progressBar.visibility = View.GONE
                adapterCategory.addList(category)
                adapterCategory.notifyDataSetChanged()
            })
            with(categoryBinding.rvCategorys){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapterCategory
            }
        }
    }

}