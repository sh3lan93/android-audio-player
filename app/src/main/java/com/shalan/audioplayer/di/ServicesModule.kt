package com.shalan.audioplayer.di

import com.shalan.audioplayer.network.services.AppServices
import org.koin.dsl.module
import retrofit2.Retrofit


val servicesModule = module {
    factory {
        get<Retrofit>().create(AppServices::class.java)
    }
}