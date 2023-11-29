package com.sultandev.domain.usecases.room

import com.sultandev.data.repository.HotelOrderRepository
import com.sultandev.domain.mappers.room.Rooms
import com.sultandev.domain.mappers.room.toDomain
import com.sultandev.util.base_result.ResultModel
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(private val repository: HotelOrderRepository) {
    suspend operator fun invoke(): ResultModel<Rooms> {
        val response = repository.getRooms()
        return ResultModel(
            data = response.data?.toDomain(),
            status = response.status,
            errorThrowable = response.errorThrowable
        )
    }
}