package com.piotrapps.data.di

import android.content.Context
import androidx.room.Room
import com.piotrapps.data.db.PhotoDatabase
import com.piotrapps.data.db.dao.PhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PhotoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PhotoDatabase::class.java,
            "photo_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavouriteMovieDao(photoDatabase: PhotoDatabase): PhotosDao {
        return photoDatabase.photosDao
    }
}