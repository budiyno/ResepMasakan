package com.budi.resepmasakan.utils

import com.budi.resepmasakan.data.RecipeEntity

object DataDummy {

    fun generatesDummyRecipe(): List<RecipeEntity> {

        val recipe = ArrayList<RecipeEntity>()

        recipe.add(RecipeEntity("R01", "Resep Guava Freakshake, Minuman Segar untuk Teman di Rumah Aja",
                "https://www.masakapahariini.com/wp-content/uploads/2021/07/guava-freakshake-siap-780x440.jpg",
                "2 Porsi", "30mnt", "Mudah", "Rian Farisa", "Juli 29, 2021", "200 ml susu", "1 Siapkan blender, kemudian masukkan es batu secukupnya."))

        return recipe
    }
}