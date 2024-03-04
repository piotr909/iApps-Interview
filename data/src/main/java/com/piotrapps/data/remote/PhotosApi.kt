package com.piotrapps.data.remote

import com.piotrapps.data.remote.dto.PhotoListDto
import retrofit2.http.GET

interface PhotosApi {

    @GET("/services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
    suspend fun downloadPhotos(): PhotoListDto
}