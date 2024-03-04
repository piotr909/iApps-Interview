package com.piotrapps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoListDto(
    @SerializedName("items") val items: List<PhotoItemDto>
)
