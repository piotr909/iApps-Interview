package com.piotrapps.data.di

import com.piotrapps.data.db.dao.PhotosDao
import com.piotrapps.data.remote.PhotosApi
import com.piotrapps.data.repository.PhotosRepositoryImpl
import com.piotrapps.domain.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(photosApi: PhotosApi, photosDao: PhotosDao): PhotosRepository {
        return PhotosRepositoryImpl(photosApi, photosDao)
    }
}