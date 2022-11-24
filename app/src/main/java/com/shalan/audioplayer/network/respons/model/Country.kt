package com.shalan.audioplayer.network.respons.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "country_id")
    val countryId: Long,
    @Json(name = "country_image")
    val countryImage: String,
    @Json(name = "country_name")
    val countryName: String,
    @Json(name = "total")
    val total: Int
)