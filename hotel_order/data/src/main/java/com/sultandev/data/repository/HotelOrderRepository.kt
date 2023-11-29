package com.sultandev.data.repository

import com.sultandev.data.models.hotel.HotelData
import com.sultandev.data.models.reservation.ReservationData
import com.sultandev.data.models.room.RoomsData
import com.sultandev.util.base_result.ResultModel

interface HotelOrderRepository {

    suspend fun getHotels(): ResultModel<HotelData>

    suspend fun getRooms(): ResultModel<RoomsData>

    suspend fun getReservationInfo(): ResultModel<ReservationData>

}