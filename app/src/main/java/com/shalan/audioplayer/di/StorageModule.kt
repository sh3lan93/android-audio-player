package com.shalan.audioplayer.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val SHARED_PREFERENCES_NAME = "Audio_Player_Shared_Pref"
val storageModule = module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}