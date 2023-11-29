package com.sultandev.domain.mappers.room

import com.sultandev.data.models.room.RoomsData

data class Rooms(
    val roomData: List<Room>?
)

fun RoomsData.toDomain() = Rooms(
    roomData = roomData?.map { it.toDomain() }
)