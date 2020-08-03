package com.rial.covid_19tracker.database

import com.squareup.moshi.Json

data class GlobalData (

    @Json(name = "Confirmed") val confirmed: Int = 0,
    @Json(name = "Deaths") val deaths: Int = 0,
    @Json(name = "Recovered") val recovered: Int = 0,
    @Json(name = "Active") val active: Int = 0,
    @Json(name = "NewConfirmed") val newConfirmed: Int = 0,
    @Json(name = "NewDeaths") val newDeaths: Int = 0,
    @Json(name = "NewRecovered") val newRecovered: Int = 0,
    @Json(name = "Last_Update") val lastUpdate: String = ""

)