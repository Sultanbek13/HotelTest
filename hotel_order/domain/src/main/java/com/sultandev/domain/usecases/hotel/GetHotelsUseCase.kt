package com.sultandev.domain.usecases.hotel

import com.sultandev.data.repository.HotelOrderRepository
import com.sultandev.domain.mappers.hotel.Hotel
import com.sultandev.domain.mappers.hotel.toDomain
import com.sultandev.util.base_result.ResultModel
import javax.inject.Inject

class GetHotelsUseCase @Inject constructor(private val repository: HotelOrderRepository) {
    suspend operator fun invoke(): ResultModel<Hotel> {
        val response = repository.getHotels()
        return ResultModel(
            data = response.data?.toDomain(),
            status = response.status,
            errorThrowable = response.errorThrowable
        )
    }
}