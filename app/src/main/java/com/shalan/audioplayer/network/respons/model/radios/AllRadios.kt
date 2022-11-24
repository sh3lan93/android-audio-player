package com.shalan.audioplayer.network.respons.model.radios

import com.shalan.audioplayer.network.respons.model.BasePaginatedResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllRadios(
    override val count: Long,
    @Json(name = "count_total") override val countTotal: Long,
    override val pages: Long,
    val stations: List<Radio>
) : BasePaginatedResponse {

    override fun shouldPaginate(totalLoadedCount: Long): Boolean = totalLoadedCount < countTotal
}
