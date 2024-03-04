package com.piotrapps.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.piotrapps.data.db.converters.DateConverter
import com.piotrapps.data.db.dao.PhotosDao
import com.piotrapps.data.db.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photosDao: PhotosDao
}