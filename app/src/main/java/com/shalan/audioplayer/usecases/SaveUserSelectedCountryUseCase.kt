package com.shalan.audioplayer.usecases

import android.content.SharedPreferences
import com.shalan.audioplayer.base.usecase.DefaultUseCase
import com.shalan.audioplayer.base.usecase.Param
import com.shalan.audioplayer.storage.SharedPrefConstants
import com.shalan.audioplayer.storage.put

class SaveUserSelectedCountryUseCase(private val sharedPreferences: SharedPreferences) :
    DefaultUseCase<SaveUserSelectedCountryUseCase.Parameters>() {

    data class Parameters(val countryId: Long) : Param

    override fun execute(params: Parameters) {
        sharedPreferences.put(SharedPrefConstants.USER_SELECTED_COUNTRY_ID_KEY, params.countryId)
    }
}