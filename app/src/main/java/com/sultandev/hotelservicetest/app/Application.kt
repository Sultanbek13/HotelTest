package com.sultandev.hotelservicetest.app

import android.app.Application
import android.content.Context
import com.sultandev.di.component.DaggerHotelOrderComponent
import com.sultandev.di.component.HotelOrderComponent
import com.sultandev.hotelservicetest.BuildConfig
import com.sultandev.hotelservicetest.di.AppComponent
import com.sultandev.hotelservicetest.di.AppModule
import com.sultandev.hotelservicetest.di.DaggerAppComponent
import com.sultandev.presentation.di.injectors.HotelOrderComponentProvider
import com.sultandev.presentation.ui.hotel.HotelFragment
import com.sultandev.presentation.ui.paid.PaidFragment
import com.sultandev.presentation.ui.room.RoomFragment
import com.sultandev.presentation.ui.reservation.ReservationFragment
import com.sultandev.util.base_url.BaseDataUrlProvider
import com.sultandev.util.context.GetApplicationContext

class Application : Application(), BaseDataUrlProvider, GetApplicationContext, HotelOrderComponentProvider {

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private lateinit var appComponent: AppComponent
    private lateinit var hotelOrderComponent: HotelOrderComponent

    private fun setupDagger() {
        appComponent =
            DaggerAppComponent.builder().appModule(AppModule(applicationContext, this)).build()
        hotelOrderComponent = DaggerHotelOrderComponent.factory().create(
            appComponent
        )
        appComponent.inject(this)
    }

    override fun getBaseUrl(): String = BuildConfig.BASE_URL_HOTEL_ORDER

    override fun inject(fragment: HotelFragment) = hotelOrderComponent.inject(fragment)

    override fun inject(fragment: RoomFragment) = hotelOrderComponent.inject(fragment)

    override fun inject(fragment: ReservationFragment) = hotelOrderComponent.inject(fragment)

    override fun inject(fragment: PaidFragment) = hotelOrderComponent.inject(fragment)

    override fun getContext(): Context = this.applicationContext

}
