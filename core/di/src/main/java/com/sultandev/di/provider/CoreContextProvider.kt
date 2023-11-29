package com.sultandev.di.provider

import android.content.Context

interface CoreContextProvider {
    fun provideApplicationContext(): Context
}