package com.shalan.audioplayer.usecases

import com.shalan.audioplayer.base.usecase.BaseSingleUseCase
import com.shalan.audioplayer.base.usecase.Param
import com.shalan.audioplayer.network.respons.model.countries.Country
import com.shalan.audioplayer.network.services.AppServices
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class GetAllCountriesUseCase(
    private val service: AppServices,
    override val ioScheduler: Scheduler,
    override val mainScheduler: Scheduler
) :
    BaseSingleUseCase<List<Country>, GetAllCountriesUseCase.Params>() {

    class Params : Param

    override fun buildUseCase(params: Params): Single<List<Country>> =
        service.getCountriesList().map { it.countries }
}