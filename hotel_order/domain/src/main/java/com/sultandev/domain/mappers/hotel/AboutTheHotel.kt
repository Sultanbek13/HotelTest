package com.sultandev.domain.mappers.hotel

import com.sultandev.data.models.hotel.AboutTheHotelData

data class AboutTheHotel(
    val description: String,
    val peculiarities: List<String>
)

fun AboutTheHotelData.toDomain() = AboutTheHotel(
    description = description,
    peculiarities = peculiarities
)