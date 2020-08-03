package com.rial.covid_19tracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "countries_summary")
data class Country (
        @PrimaryKey
        @Json(name = "Code") val code: String = "",
        @ColumnInfo(name = "name")
        @Json(name = "Slug") val slug: String = "",
        @ColumnInfo(name = "confirmed_cases")
        @Json(name = "Confirmed") val confirmed: Int = 0,
        @ColumnInfo(name = "total_deaths")
        @Json(name = "Deaths") val deaths: Int = 0,
        @ColumnInfo(name = "new_deaths")
        @Json(name = "NewDeaths") val newDeaths: Int = 0
)