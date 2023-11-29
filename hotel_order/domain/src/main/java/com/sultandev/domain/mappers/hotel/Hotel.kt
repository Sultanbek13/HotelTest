package com.sultandev.domain.mappers.hotel

import com.sultandev.data.models.hotel.HotelData

data class Hotel(
    val aboutTheHotel: AboutTheHotel,
    val adress: String,
    val id: Int,
    val imageUrls: List<String>,
    val minimalPrice: Int,
    val name: String,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String
)

fun HotelData.toDomain() = Hotel(
    aboutTheHotel = aboutTheHotelData.toDomain(),
    adress = adress,
    id = id,
    imageUrls = imageUrls,
    minimalPrice = minimalPrice,
    name = name,
    priceForIt = priceForIt,
    rating = rating,
    ratingName = ratingName
)

