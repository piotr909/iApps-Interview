package com.piotrapps.data.repository

import com.piotrapps.data.db.dao.PhotosDao
import com.piotrapps.data.db.mapper.toDomain
import com.piotrapps.data.db.mapper.toEntity
import com.piotrapps.data.remote.PhotosApi
import com.piotrapps.domain.model.Photo
import com.piotrapps.domain.repository.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class PhotosRepositoryImpl(private val photosApi: PhotosApi, private val photosDao: PhotosDao) :
    PhotosRepository {
    override suspend fun getPhotoItems(): Flow<List<Photo>> {
        return photosDao.loadPhotos()
            .map { list ->
                list.map { it.toDomain() }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun downloadData(): Flow<Unit> {
        delay(10000)
        return flow { emit(photosApi.downloadPhotos()) }
            .catch {
                //Ignore
            }
            .map {
                it.items
            }
            .onEach {
                photosDao.insert(it.map { it.toEntity() })
            }
            .flatMapConcat { flowOf(Unit) }
            .flowOn(Dispatchers.IO)
    }
}