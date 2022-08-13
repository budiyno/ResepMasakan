package com.budi.resepmasakan.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryResponse(
        var category: String? = null,
        var url: String? = null,
        var key: String? = null
):Parcelable
