package com.piotrapps.domain.repository

import com.piotrapps.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    suspend fun getPhotoItems(): Flow<List<Photo>>

    suspend fun downloadData(): Flow<Unit>
}