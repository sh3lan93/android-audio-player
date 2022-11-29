package com.shalan.audioplayer.network.respons.model.radios


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Radio(
    @Json(name = "id")
    val id: String,
    @Json(name = "md5_image")
    val md5Image: String?,
    @Json(name = "picture")
    val picture: String?,
    @Json(name = "picture_big")
    val pictureBig: String?,
    @Json(name = "picture_medium")
    val pictureMedium: String?,
    @Json(name = "picture_small")
    val pictureSmall: String?,
    @Json(name = "picture_xl")
    val pictureXl: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "tracklist")
    val tracklist: String?,
    @Json(name = "type")
    val type: String?
) {
    val radioImage: String = pictureBig ?: ""
}