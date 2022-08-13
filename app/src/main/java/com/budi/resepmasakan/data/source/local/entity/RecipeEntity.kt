package com.budi.resepmasakan.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipeentity")
data class RecipeEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recId")
    var recId : String,

    @ColumnInfo(name = "title")
    var title : String?,

    @ColumnInfo(name = "thumb")
    var thumb : String?,

    @ColumnInfo(name = "servings")
    var servings : String?,

    @ColumnInfo(name = "times")
    var times :String?,

    @ColumnInfo(name = "dificulty")
    var dificulty : String?,

    @ColumnInfo(name = "user")
    var user : String?,

    @ColumnInfo(name = "datedPublish")
    var datedPublish : String?,

    @ColumnInfo(name = "ingredient")
    var ingredient : List<String?>?,

    @ColumnInfo(name = "step")
    var step : List<String?>?,

    @ColumnInfo(name = "favorite")
    var favorite : Boolean = false
)
