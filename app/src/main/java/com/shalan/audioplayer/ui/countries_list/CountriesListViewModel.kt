package com.shalan.audioplayer.ui.countries_list

import androidx.lifecycle.LiveData
import com.shalan.audioplayer.base.utils.SingleLiveEvent
import com.shalan.audioplayer.base.viewmodel.BaseSingleListViewModel
import com.shalan.audioplayer.network.respons.model.Country
import com.shalan.audioplayer.usecases.GetAllCountriesUseCase
import com.shalan.audioplayer.usecases.SaveUserSelectedCountryUseCase
import io.reactivex.rxjava3.core.Single

class CountriesListViewModel(
    private val getCountriesUseCase: GetAllCountriesUseCase,
    private val saveUserSelectedCountryUseCase: SaveUserSelectedCountryUseCase
) :
    BaseSingleListViewModel<Country>() {

    private val _navigateToHome: SingleLiveEvent<Any> by lazy {
        SingleLiveEvent()
    }

    val navigateToHome: LiveData<Any> = _navigateToHome

    override fun loadFromScratch(): Single<List<Country>> = getCountriesUseCase.execute(
        GetAllCountriesUseCase.Params()
    )

    fun saveUserSelectedCountry(country: Country) {
        saveUserSelectedCountryUseCase
            .execute(SaveUserSelectedCountryUseCase.Parameters(countryId = country.countryId))
        _navigateToHome.value = true
    }
}