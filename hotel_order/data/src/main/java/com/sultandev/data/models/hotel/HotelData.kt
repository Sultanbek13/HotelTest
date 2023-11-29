package com.sultandev.data.models.hotel

import com.google.gson.annotations.SerializedName

data class HotelData(
    @SerializedName("about_the_hotel")
    val aboutTheHotelData: AboutTheHotelData,
    val adress: String,
    val id: Int,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("minimal_price")
    val minimalPrice: Int,
    val name: String,
    @SerializedName("price_for_it")
    val priceForIt: String,
    val rating: Int,
    @SerializedName("rating_name")
    val ratingName: String
)