package com.shalan.audioplayer.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalan.audioplayer.base.ui.UIState
import io.reactivex.rxjava3.core.Single

abstract class BaseSingleListViewModel<T : Any> : ViewModel(), IViewModel {

    private val _listResult: MutableLiveData<UIState> by lazy {
        MutableLiveData()
    }

    val listResult: LiveData<UIState> = _listResult

    open fun startLogic() {
        if (_listResult.value == null)
            loadFromScratch().async(_listResult)
    }

    abstract fun loadFromScratch(): Single<List<T>>

}