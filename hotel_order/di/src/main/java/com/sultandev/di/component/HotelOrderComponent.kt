package com.sultandev.di.component

import com.sultandev.di.dependencies.HotelOrderDependenciesProvider
import com.sultandev.di.modules.HotelOrderDataModule
import com.sultandev.di.modules.HotelOrderDomainModule
import com.sultandev.presentation.di.scopes.HotelOrderScope
import com.sultandev.presentation.ui.hotel.HotelFragment
import com.sultandev.presentation.ui.paid.PaidFragment
import com.sultandev.presentation.ui.room.RoomFragment
import com.sultandev.presentation.ui.reservation.ReservationFragment
import dagger.Component

@HotelOrderScope
@Component(
    modules = [HotelOrderDataModule::class, HotelOrderDomainModule::class],
    dependencies = [
        HotelOrderDependenciesProvider::class
    ]
)

interface HotelOrderComponent {

    fun inject(fragment: HotelFragment)

    fun inject(fragment: RoomFragment)

    fun inject(fragment: ReservationFragment)

    fun inject(fragment: PaidFragment)

    @Component.Factory
    interface Factory {
        fun create(provider: HotelOrderDependenciesProvider): HotelOrderComponent
    }
}