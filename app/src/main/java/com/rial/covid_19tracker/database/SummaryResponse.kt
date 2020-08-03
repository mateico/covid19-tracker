package com.rial.covid_19tracker.database

import com.rial.covid_19tracker.database.Country
import com.rial.covid_19tracker.database.GlobalData
import com.squareup.moshi.Json

data class SummaryResponse (
    @Json(name = "globalData") val globalData: GlobalData,
    @Json(name = "countries") val countries: List<Country>
)