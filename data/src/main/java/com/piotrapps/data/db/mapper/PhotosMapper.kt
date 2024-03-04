package com.piotrapps.data.db.mapper

import com.piotrapps.data.db.entity.PhotoEntity
import com.piotrapps.data.remote.dto.PhotoItemDto
import com.piotrapps.domain.model.Photo

fun PhotoEntity.toDomain(): Photo {
    return Photo(this.imageUrl, this.description, this.link, this.published)
}

fun PhotoItemDto.toEntity(): PhotoEntity {
    return PhotoEntity(
        this.link,
        this.description,
        this.media.imageUrl,
        this.published
    )
}