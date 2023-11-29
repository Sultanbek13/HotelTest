package com.sultandev.hotelservicetest.di

import com.sultandev.di.dependencies.HotelOrderDependenciesProvider
import com.sultandev.di.module.CoreNetworkModule
import com.sultandev.hotelservicetest.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)

interface AppComponent : HotelOrderDependenciesProvider {
    fun inject(application: Application)
}