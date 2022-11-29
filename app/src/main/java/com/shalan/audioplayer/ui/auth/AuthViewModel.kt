package com.shalan.audioplayer.ui.auth

import androidx.lifecycle.LiveData
import com.shalan.audioplayer.base.utils.SingleLiveEvent
import com.shalan.audioplayer.base.viewmodel.BaseViewModel
import com.shalan.audioplayer.usecases.SaveUserDeezerTokenUseCase

class AuthViewModel(private val saveUserTokenUseCase: SaveUserDeezerTokenUseCase) :
    BaseViewModel() {

    private val _navigateToHome: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }

    val navigateToHome: LiveData<Boolean> = _navigateToHome

    fun saveUserToken(token: String) {
        saveUserTokenUseCase.execute(params = SaveUserDeezerTokenUseCase.Parameters(token = token))
        _navigateToHome.value = true
    }
}