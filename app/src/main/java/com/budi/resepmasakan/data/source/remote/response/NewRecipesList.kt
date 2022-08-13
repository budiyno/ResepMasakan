package com.budi.resepmasakan.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewRecipesList(
        val times: String,
        val thumb: String,
        val portion: String,
        val title: String,
        val key: String,
        val dificulty: String
):Parcelable
