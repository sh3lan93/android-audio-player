package com.shalan.audioplayer.usecases

import android.content.SharedPreferences
import com.shalan.audioplayer.base.usecase.DefaultUseCase
import com.shalan.audioplayer.base.usecase.Param
import com.shalan.audioplayer.storage.SharedPrefConstants
import com.shalan.audioplayer.storage.put

class SaveUserDeezerTokenUseCase(private val sharedPreferences: SharedPreferences) :
    DefaultUseCase<SaveUserDeezerTokenUseCase.Parameters>() {

    data class Parameters(val token: String) : Param

    override fun execute(params: Parameters) {
        sharedPreferences.put(SharedPrefConstants.USER_DEEZER_TOKEN_KEY, params.token)
    }
}