package com.sultandev.di.provider

import com.google.gson.Gson
import okhttp3.OkHttpClient

interface CoreNetworkProvider {

    fun provideGson(): Gson

    fun provideOkHttpClient(): OkHttpClient

}