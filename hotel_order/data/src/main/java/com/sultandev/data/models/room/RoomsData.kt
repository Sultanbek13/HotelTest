package com.sultandev.data.models.room

import com.google.gson.annotations.SerializedName

data class RoomsData(
    @SerializedName("rooms")
    val roomData: List<RoomData>?
)