package com.rial.covid_19tracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * DatabaseCountry represents a video entity in the database.
 */

@Entity(tableName = "countries_summary")
data class DatabaseCountry constructor(
    @PrimaryKey
    val code: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "confirmed_cases")
    val confirmed: Int,
    @ColumnInfo(name = "total_deaths")
    val deaths: Int,
    @ColumnInfo(name = "new_deaths")
    val newDeaths: Int
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<DatabaseCountry>.asDomainModel(): List<Country> {
    return map {
        Country(
            code = it.code,
            name = it.name,
            confirmed = it.confirmed,
            deaths = it.deaths,
            newDeaths = it.newDeaths)
    }
}