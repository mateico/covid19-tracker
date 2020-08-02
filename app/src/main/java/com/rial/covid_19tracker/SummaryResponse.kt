package com.rial.covid_19tracker

import com.squareup.moshi.Json

data class SummaryResponse (
    @Json(name = "globalData") val globalData: GlobalData,
    @Json(name = "countries") val countries: List<Country>
)