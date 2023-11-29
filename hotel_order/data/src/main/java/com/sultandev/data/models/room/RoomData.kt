package com.sultandev.data.models.room

import com.google.gson.annotations.SerializedName

data class RoomData(
    val id: Int,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    @SerializedName("price_per")
    val pricePer: String
)