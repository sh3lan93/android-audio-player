package com.shalan.audioplayer.di

import com.shalan.audioplayer.usecases.*
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

    factory {
        SaveUserSelectedCountryUseCase(sharedPreferences = get())
    }

    factory {
        GetRadiosByCountryIdUseCase(
            services = get(),
            ioScheduler = get(named(IO_SCHEDULER)),
            mainScheduler = get(
                named(MAIN_THREAD_SCHEDULER)
            )
        )
    }

    factory {
        GetUserSelectedCountryUseCase(sharedPreferences = get())
    }

    factory {
        SaveUserDeezerTokenUseCase(sharedPreferences = get())
    }
}