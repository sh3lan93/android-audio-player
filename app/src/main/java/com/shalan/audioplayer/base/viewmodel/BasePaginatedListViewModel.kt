package com.shalan.audioplayer.base.viewmodel

abstract class BasePaginatedListViewModel<T : Any> : BaseSingleListViewModel<T>() {

    var page: Long = 1

    fun loadMoreData(){
        page += 1
        loadFromScratch().async(_listResult)
    }
}