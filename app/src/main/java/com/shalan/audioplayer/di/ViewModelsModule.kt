package com.shalan.audioplayer.di

import com.shalan.audioplayer.ui.countries_list.CountriesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewmodelsModule = module {
    viewModel {
        CountriesListViewModel(getCountriesUseCase = get())
    }
}