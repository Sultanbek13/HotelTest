package com.sultandev.presentation.di.injectors

import com.sultandev.presentation.ui.hotel.HotelFragment
import com.sultandev.presentation.ui.paid.PaidFragment
import com.sultandev.presentation.ui.room.RoomFragment
import com.sultandev.presentation.ui.reservation.ReservationFragment

interface HotelOrderComponentProvider {

    fun inject(fragment: HotelFragment)

    fun inject(fragment: RoomFragment)

    fun inject(fragment: ReservationFragment)

    fun inject(fragment: PaidFragment)

}