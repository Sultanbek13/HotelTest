package com.sultandev.data.repository

import com.sultandev.base.datasource.BaseDataSource
import com.sultandev.data.api.ApiServiceHotelOrder
import com.sultandev.data.models.hotel.HotelData
import com.sultandev.data.models.reservation.ReservationData
import com.sultandev.data.models.room.RoomsData
import com.sultandev.util.base_result.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelOrderRepositoryImpl @Inject constructor(private val apiServiceHotelOrder: ApiServiceHotelOrder) :
    HotelOrderRepository, BaseDataSource() {
    override suspend fun getHotels(): ResultModel<HotelData> =
        invokeRequest {
            withContext(Dispatchers.IO) {
                apiServiceHotelOrder.getHotels()
            }
        }

    override suspend fun getRooms(): ResultModel<RoomsData> =
        invokeRequest {
            withContext(Dispatchers.IO) {
                apiServiceHotelOrder.getRooms()
            }
        }

    override suspend fun getReservationInfo(): ResultModel<ReservationData> =
        invokeRequest {
            withContext(Dispatchers.IO) {
                apiServiceHotelOrder.getReservationInfo()
            }
        }
}