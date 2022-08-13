package com.budi.resepmasakan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RecipeReaderActivity : AppCompatActivity() {

    companion object {
        const val RECIPE_ID = "recipe_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_reader)
    }
}