package com.shalan.audioplayer.network.respons.model.countries

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllCountries(val countries: List<Country>)
