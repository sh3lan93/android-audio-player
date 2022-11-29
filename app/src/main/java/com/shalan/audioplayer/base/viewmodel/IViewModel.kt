package com.shalan.audioplayer.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.shalan.audioplayer.base.ui.Failure
import com.shalan.audioplayer.base.ui.Loading
import com.shalan.audioplayer.base.ui.Success
import com.shalan.audioplayer.base.ui.UIState
import io.reactivex.rxjava3.core.Single

interface IViewModel {

    fun <T : Any> Single<T>.async(result: MutableLiveData<UIState<T>>) {
        result.value = Loading()
        this.subscribe({
            result.postValue(Success(data = it))
        }, {
            result.postValue(Failure(error = it.localizedMessage))
        })
    }
}