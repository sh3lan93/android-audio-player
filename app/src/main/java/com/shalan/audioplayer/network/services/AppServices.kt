package com.shalan.audioplayer.network.services

import com.shalan.audioplayer.App
import com.shalan.audioplayer.network.respons.model.countries.AllCountries
import com.shalan.audioplayer.network.respons.model.radios.AllRadios
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppServices {

    @GET("/api.php")
    fun getCountriesList(@Query("categories") categories: String = " "): Single<AllCountries>

    @GET("/api.php")
    fun getAllRadiosByCountry(
        @Query("id") countryId: Long,
        @Query("count") count: Long = App.PAGINATION_PAGE_SIZE,
        @Query("page") page: Long
    ): Single<AllRadios>
}