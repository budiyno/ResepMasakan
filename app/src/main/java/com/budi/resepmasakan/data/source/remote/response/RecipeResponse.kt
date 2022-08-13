package com.budi.resepmasakan.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeResponse(
        var RecId : String,
        var title : String?,
        var thumb : String?,
        var servings : String?,
        var times :String?,
        var dificulty : String?,
        var user : String?,
        var datedPublish : String?,
        var ingredient : List<String?>?,
        var step : List<String?>?
):Parcelable
