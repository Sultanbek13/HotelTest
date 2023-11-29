package com.sultandev.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sultandev.data.api.ApiServiceHotelOrder
import com.sultandev.data.repository.HotelOrderRepository
import com.sultandev.data.repository.HotelOrderRepositoryImpl
import com.sultandev.presentation.di.scopes.HotelOrderScope
import com.sultandev.util.base_url.BaseDataUrlProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HotelOrderDataModule {

    @HotelOrderScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @HotelOrderScope
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().serializeNulls().create()

    @HotelOrderScope
    @Provides
    fun provideApi(
        applicationContext: Context,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ApiServiceHotelOrder {
        val hotelOrderBaseUrl = (applicationContext as BaseDataUrlProvider).getBaseUrl()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(hotelOrderBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceHotelOrder::class.java)
    }

    @HotelOrderScope
    @Provides
    fun provideApiImpl(
        api: ApiServiceHotelOrder
    ): HotelOrderRepository {
        return HotelOrderRepositoryImpl(apiServiceHotelOrder = api)
    }
}