package com.shalan.audioplayer.usecases

import android.content.SharedPreferences
import com.shalan.audioplayer.base.usecase.BaseSingleUseCase
import com.shalan.audioplayer.base.usecase.Param
import com.shalan.audioplayer.network.respons.model.radios.Radio
import com.shalan.audioplayer.network.services.AppServices
import com.shalan.audioplayer.storage.SharedPrefConstants
import com.shalan.audioplayer.storage.get
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class GetRadiosUseCase(
    override val ioScheduler: Scheduler,
    override val mainScheduler: Scheduler,
    private val services: AppServices,
    private val sharedPreferences: SharedPreferences
) : BaseSingleUseCase<List<Radio>, GetRadiosUseCase.Parameters>() {

    class Parameters : Param

    override fun buildUseCase(params: Parameters): Single<List<Radio>> {
        val accessToken = sharedPreferences.get(SharedPrefConstants.USER_DEEZER_TOKEN_KEY, "")!!
        return services.getRadios(accessToken = accessToken).map { it.data ?: emptyList() }
    }
}