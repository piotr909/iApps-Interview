package com.piotrapps.domain.usecase

import com.piotrapps.domain.model.Photo
import com.piotrapps.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPhotoItemsUseCase @Inject constructor(
    private val photosRepository: PhotosRepository
) {

    suspend operator fun invoke(): Flow<List<Photo>> {
        return photosRepository.getPhotoItems().map {
            it.sortedBy { it.published }
        }
    }
}