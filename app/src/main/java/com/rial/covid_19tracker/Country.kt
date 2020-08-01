package com.rial.covid_19tracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_summary")
data class Country (
        @PrimaryKey
        val code: String = "",
        @ColumnInfo(name = "name")
        val slug: String = "",
        @ColumnInfo(name = "confirmed_cases")
        val confirmed: Int = 0,
        @ColumnInfo(name = "total_deaths")
        val deaths: Int = 0,
        @ColumnInfo(name = "new_deaths")
        val newDeaths: Int = 0
)