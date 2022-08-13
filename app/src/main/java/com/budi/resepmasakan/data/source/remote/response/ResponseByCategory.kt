package com.budi.resepmasakan.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseByCategory(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItemCat?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class ResultsItemCat(

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("portion")
	val portion: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null
)
