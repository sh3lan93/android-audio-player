package com.shalan.audioplayer

import android.app.Application
import com.shalan.audioplayer.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        const val PAGINATION_PAGE_SIZE: Long = 10
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            logger(AndroidLogger())
            modules(
                networkModule,
                viewmodelsModule,
                useCasesModule,
                servicesModule,
                networkModule,
                storageModule
            )
        }
    }
}