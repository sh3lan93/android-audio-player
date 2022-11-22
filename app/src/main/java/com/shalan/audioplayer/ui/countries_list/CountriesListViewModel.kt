package com.shalan.audioplayer.ui.countries_list

import com.shalan.audioplayer.base.viewmodel.BaseSingleListViewModel
import com.shalan.audioplayer.network.respons.model.Country
import com.shalan.audioplayer.usecases.GetAllCountriesUseCase
import io.reactivex.rxjava3.core.Single

class CountriesListViewModel(private val getCountriesUseCase: GetAllCountriesUseCase) :
    BaseSingleListViewModel<Country>() {


    override fun loadFromScratch(): Single<List<Country>> = getCountriesUseCase.execute(
        GetAllCountriesUseCase.Params()
    )
}