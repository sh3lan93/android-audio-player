package com.shalan.audioplayer

import android.app.Application
import com.shalan.audioplayer.di.networkModule
import com.shalan.audioplayer.di.servicesModule
import com.shalan.audioplayer.di.useCasesModule
import com.shalan.audioplayer.di.viewmodelsModule
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            logger(AndroidLogger())
            modules(networkModule, viewmodelsModule, useCasesModule, servicesModule, networkModule)
        }
    }
}