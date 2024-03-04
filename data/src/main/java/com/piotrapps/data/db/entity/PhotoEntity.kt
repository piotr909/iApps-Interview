package com.piotrapps.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "published") val published: Date
)
