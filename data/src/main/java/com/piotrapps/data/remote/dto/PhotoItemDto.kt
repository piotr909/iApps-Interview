package com.piotrapps.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PhotoItemDto(
    @SerializedName("link") val link: String,
    @SerializedName("description") val description: String,
    @SerializedName("published") val published: Date,
    @SerializedName("media") val media: MediaDto
) {
    data class MediaDto(
        @SerializedName("m") val imageUrl: String
    )
}
