package com.shalan.audioplayer.di

import com.shalan.audioplayer.ui.auth.AuthViewModel
import com.shalan.audioplayer.ui.countries_list.CountriesListViewModel
import com.shalan.audioplayer.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewmodelsModule = module {
    viewModel {
        CountriesListViewModel(getCountriesUseCase = get(), saveUserSelectedCountryUseCase = get())
    }

    viewModel {
        HomeViewModel(getRadiosUseCase = get(), getUserSelectedCountryUseCase = get())
    }

    viewModel {
        AuthViewModel()
    }
}