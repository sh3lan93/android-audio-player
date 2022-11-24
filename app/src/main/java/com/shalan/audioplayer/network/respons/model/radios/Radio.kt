package com.shalan.audioplayer.network.respons.model.radios


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Radio(
    @Json(name = "genre")
    val genre: String?,
    @Json(name = "radio_id")
    val radioId: Long,
    @Json(name = "radio_image")
    val radioImage: String?,
    @Json(name = "radio_name")
    val radioName: String,
    @Json(name = "radio_url")
    val radioUrl: String
)