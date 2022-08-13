package com.budi.resepmasakan.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseCategories(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: ArrayList<ResultsItem>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class ResultsItem(

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
