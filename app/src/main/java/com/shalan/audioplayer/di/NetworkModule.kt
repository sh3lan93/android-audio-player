package com.shalan.audioplayer.di

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.shalan.audioplayer.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_KEY_HEADER = "X-RapidAPI-Key"
const val HOST_KEY_HEADER = "X-RapidAPI-Host"

const val IO_SCHEDULER = "io_scheduler"
const val MAIN_THREAD_SCHEDULER = "main_scheduler"
val networkModule = module {

    single<Scheduler>(named(IO_SCHEDULER)) {
        IoScheduler()
    }

    single<Scheduler>(named(MAIN_THREAD_SCHEDULER)) {
        AndroidSchedulers.mainThread()
    }

    single {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .addHeader(API_KEY_HEADER, BuildConfig.X_RapidAPI_Key)
                    .addHeader(HOST_KEY_HEADER, BuildConfig.X_RapidAPI_Host)
                    .setLevel(Level.BODY)
                    .build()
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
}