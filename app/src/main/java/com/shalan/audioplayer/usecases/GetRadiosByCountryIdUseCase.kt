package com.shalan.audioplayer.usecases

import com.shalan.audioplayer.base.usecase.BaseSingleUseCase
import com.shalan.audioplayer.base.usecase.Param
import com.shalan.audioplayer.network.respons.model.radios.AllRadios
import com.shalan.audioplayer.network.services.AppServices
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class GetRadiosByCountryIdUseCase(
    private val services: AppServices,
    override val ioScheduler: Scheduler,
    override val mainScheduler: Scheduler
) :
    BaseSingleUseCase<AllRadios, GetRadiosByCountryIdUseCase.Parameters>() {

    data class Parameters(val countryId: Long, val page: Long) : Param

    override fun buildUseCase(params: Parameters): Single<AllRadios> =
        services.getAllRadiosByCountry(countryId = params.countryId, page = params.page)
}