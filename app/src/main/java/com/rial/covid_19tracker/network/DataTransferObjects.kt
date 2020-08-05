package com.rial.covid_19tracker.network

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.rial.covid_19tracker.database.Country
import com.rial.covid_19tracker.database.DatabaseCountry
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)


@JsonClass(generateAdapter = true)
data class NetworkCountryContainer(val countries: List<NetworkCountry>)

@JsonClass(generateAdapter = true)
data class NetworkGlobalDataContainer(val globalData: NetworkGlobalData)

@JsonClass(generateAdapter = true)
data class NetworkSummaryResponseContainer(val summaryResponse: NetworkSummaryResponse)


/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkCountry(
    val Country_Region: String,
    val Code: String,
    val Confirmed: Int,
    val Deaths: Int,
    val NewDeaths: Int
)


/**
 * Convert Network results to database objects
 */
/*
fun NetworkCountryContainer.asDomainModel(): List<Country> {
    return countries.map {
        Country(
            code = it.Code,
            name = it.Country_Region,
            confirmed = it.Confirmed,
            deaths = it.Deaths,
            newDeaths = it.NewDeaths)
    }
}
*/

/**
 * Convert Network results to database objects
 */
fun NetworkCountryContainer.asDatabaseModel(): List<DatabaseCountry> {
    return countries.map {
        DatabaseCountry(
            code = it.Code,
            name = it.Country_Region,
            confirmed = it.Confirmed,
            deaths = it.Deaths,
            newDeaths = it.NewDeaths)
    }
}

@JsonClass(generateAdapter = true)
data class NetworkGlobalData(
    val Confirmed: Int,
    val Deaths: Int,
    val Recovered: Int,
    val Active: Int,
    val NewConfirmed: Int,
    val NewDeaths: Int,
    val NewRecovered: Int,
    val Last_Update: String)

/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkSummaryResponse(
    val globalData: NetworkGlobalDataContainer,
    val countries: NetworkCountryContainer)