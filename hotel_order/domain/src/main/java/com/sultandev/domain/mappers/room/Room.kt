package com.sultandev.domain.mappers.room

import com.sultandev.data.models.room.RoomData

data class Room(
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
)

fun RoomData.toDomain() = Room(
    id = id,
    imageUrls = imageUrls,
    name = name,
    peculiarities = peculiarities,
    price = price,
    pricePer = pricePer
)