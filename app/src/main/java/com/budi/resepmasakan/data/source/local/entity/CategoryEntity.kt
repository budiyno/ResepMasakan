package com.budi.resepmasakan.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoryentity")
data class CategoryEntity(
    @ColumnInfo(name = "category")
    var category: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "key")
    var key: String? = null
)
