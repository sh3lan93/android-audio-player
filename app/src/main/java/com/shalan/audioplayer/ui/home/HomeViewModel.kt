package com.shalan.audioplayer.ui.home

import com.shalan.audioplayer.base.viewmodel.BaseSingleListViewModel
import com.shalan.audioplayer.network.respons.model.radios.Radio
import com.shalan.audioplayer.usecases.GetRadiosUseCase
import io.reactivex.rxjava3.core.Single

class HomeViewModel(private val getRadiosUseCase: GetRadiosUseCase) :
    BaseSingleListViewModel<Radio>() {

    override fun loadFromScratch(): Single<List<Radio>> =
        getRadiosUseCase.buildUseCase(params = GetRadiosUseCase.Parameters())

}