package com.shalan.audioplayer.network.services

import com.shalan.audioplayer.network.respons.model.BaseResponse
import com.shalan.audioplayer.network.respons.model.radios.Radio
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppServices {


    @GET("radio")
    fun getRadios(@Query("access_token") accessToken: String): Single<BaseResponse<List<Radio>>>

}