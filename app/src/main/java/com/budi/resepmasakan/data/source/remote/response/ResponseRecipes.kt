package com.budi.resepmasakan.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseRecipes(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: Results? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Author(

	@field:SerializedName("datePublished")
	val datePublished: String? = null,

	@field:SerializedName("user")
	val user: String? = null
)

data class Results(

	@field:SerializedName("servings")
	val servings: String? = null,

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("ingredient")
	val ingredient: List<String?>? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("author")
	val author: Author? = null,

	@field:SerializedName("step")
	val step: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)

data class Ingredients(
		@field:SerializedName("ingredient")
		val bahan: String? = null
)

data class Step(
		@field:SerializedName("step")
		val step: String? = null
)
