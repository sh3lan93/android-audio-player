package com.shalan.audioplayer.ui.home

import androidx.lifecycle.MutableLiveData
import com.shalan.audioplayer.base.viewmodel.BasePaginatedListViewModel
import com.shalan.audioplayer.network.respons.model.radios.Radio
import com.shalan.audioplayer.usecases.GetRadiosByCountryIdUseCase
import com.shalan.audioplayer.usecases.GetUserSelectedCountryUseCase
import io.reactivex.rxjava3.core.Single

class HomeViewModel(
    private val getRadiosUseCase: GetRadiosByCountryIdUseCase,
    private val getUserSelectedCountryUseCase: GetUserSelectedCountryUseCase
) :
    BasePaginatedListViewModel<Radio>() {

    /**
     * This holds two information:
     * 1. Total items
     * 2. Total loaded items
     * */
    private val paginationInfo: MutableLiveData<Pair<Long, Long>> by lazy {
        MutableLiveData()
    }

    override fun loadFromScratch(): Single<List<Radio>> =
        getRadiosUseCase.buildUseCase(
            params = GetRadiosByCountryIdUseCase.Parameters(
                countryId = getCountryId(),
                page = page
            )
        ).doOnSuccess {
            val previousLoadedCount: Long = paginationInfo.value?.second ?: 0L
            paginationInfo.postValue(Pair(it.countTotal, previousLoadedCount + it.count))
        }.map {
            it.stations
        }

    private fun getCountryId(): Long =
        getUserSelectedCountryUseCase.execute(params = GetUserSelectedCountryUseCase.Parameters(0L))

    fun isLastPageReached(): Boolean = paginationInfo.value?.first == paginationInfo.value?.second
}