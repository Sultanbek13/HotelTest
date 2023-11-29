package com.sultandev.data.api

import com.sultandev.data.models.hotel.HotelData
import com.sultandev.data.models.reservation.ReservationData
import com.sultandev.data.models.room.RoomsData
import com.sultandev.data.utils.Constant.GET_HOTEL_URL
import com.sultandev.data.utils.Constant.GET_LIST_ROOMS_URL
import com.sultandev.data.utils.Constant.GET_RESERVATION_INFO_URL
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceHotelOrder {

    @GET(GET_HOTEL_URL)
    suspend fun getHotels(): Response<HotelData>

    @GET(GET_LIST_ROOMS_URL)
    suspend fun getRooms(): Response<RoomsData>

    @GET(GET_RESERVATION_INFO_URL)
    suspend fun getReservationInfo(): Response<ReservationData>

}