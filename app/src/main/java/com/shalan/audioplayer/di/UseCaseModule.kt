package com.shalan.audioplayer.di

import com.shalan.audioplayer.usecases.GetRadiosUseCase
import com.shalan.audioplayer.usecases.SaveUserDeezerTokenUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {

    factory {
        SaveUserDeezerTokenUseCase(sharedPreferences = get())
    }

    factory {
        GetRadiosUseCase(
            sharedPreferences = get(), services = get(), ioScheduler = get(
                named(IO_SCHEDULER)
            ), mainScheduler = get(named(MAIN_THREAD_SCHEDULER))
        )
    }
}