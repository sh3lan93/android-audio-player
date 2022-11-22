package com.shalan.audioplayer.network.services

import com.shalan.audioplayer.network.respons.model.AllCountries
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppServices {

    @GET("")
    fun getCountriesList(@Query("categories") categories: String = ""): Single<AllCountries>
}