package com.sultandev.domain.usecases.reservation

import com.sultandev.data.repository.HotelOrderRepository
import com.sultandev.domain.mappers.reservation.Reservation
import com.sultandev.domain.mappers.reservation.toDomain
import com.sultandev.util.base_result.ResultModel
import javax.inject.Inject

class GetReservationInfoUseCase @Inject constructor(private val repository: HotelOrderRepository) {
    suspend operator fun invoke(): ResultModel<Reservation> {
        val response = repository.getReservationInfo()
        return ResultModel(
            data = response.data?.toDomain(),
            status = response.status,
            errorThrowable = response.errorThrowable
        )
    }
}