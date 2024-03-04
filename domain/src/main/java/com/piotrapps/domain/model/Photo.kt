package com.piotrapps.domain.model

import java.util.Date

data class Photo(
    val imageUrl: String,
    val description: String,
    val link: String,
    val published: Date
)
