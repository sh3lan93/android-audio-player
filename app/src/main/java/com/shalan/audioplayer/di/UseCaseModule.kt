package com.shalan.audioplayer.di

import com.shalan.audioplayer.usecases.GetAllCountriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {
    factory {
        GetAllCountriesUseCase(
            service = get(),
            ioScheduler = get(named(IO_SCHEDULER)),
            mainScheduler = get(
                named(MAIN_THREAD_SCHEDULER)
            )
        )
    }
}