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
    val newDeaths: Int,
    @ColumnInfo(name = "recovered")
    val recovered: Int,
    @ColumnInfo(name = "new_confirmed")
    val newConfirmed: Int,
    @ColumnInfo(name = "new_recovered")
    val newRecovered: Int,
    @ColumnInfo(name = "active")
    val active: Int

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
            newDeaths = it.newDeaths,
            recovered = it.recovered,
            newConfirmed = it.newConfirmed,
            newRecovered = it.newRecovered,
            active = it.active
        )
    }
}