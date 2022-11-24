package com.shalan.audioplayer.usecases

import android.content.SharedPreferences
import com.shalan.audioplayer.base.usecase.DefaultUseCaseWithReturnType
import com.shalan.audioplayer.base.usecase.Param
import com.shalan.audioplayer.storage.SharedPrefConstants
import com.shalan.audioplayer.storage.get

class GetUserSelectedCountryUseCase(private val sharedPreferences: SharedPreferences) :
    DefaultUseCaseWithReturnType<GetUserSelectedCountryUseCase.Parameters, Long>() {

    data class Parameters(val default: Long) : Param

    override fun execute(params: Parameters): Long =
        sharedPreferences.get(SharedPrefConstants.USER_SELECTED_COUNTRY_ID_KEY, params.default)
            ?: params.default


}