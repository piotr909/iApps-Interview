package com.piotrapps.domain.usecase

import com.piotrapps.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DownloadDataUseCase @Inject constructor(
    private val photosRepository: PhotosRepository
) {

    suspend operator fun invoke(): Flow<Unit> {
        return photosRepository.downloadData()
    }
}