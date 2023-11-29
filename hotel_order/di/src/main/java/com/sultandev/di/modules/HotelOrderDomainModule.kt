package com.sultandev.di.modules

import com.sultandev.data.repository.HotelOrderRepository
import com.sultandev.domain.usecases.hotel.GetHotelsUseCase
import com.sultandev.presentation.di.scopes.HotelOrderScope
import dagger.Module
import dagger.Provides

@Module
class HotelOrderDomainModule {

    @HotelOrderScope
    @Provides
    fun provideHotelOrderGetHotelsUseCase(
        repository: HotelOrderRepository,
    ): GetHotelsUseCase = GetHotelsUseCase(
        repository = repository
    )
}