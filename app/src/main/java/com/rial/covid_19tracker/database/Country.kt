package com.rial.covid_19tracker.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country (
        val code: String,
        val name: String,
        val confirmed: Int,
        val deaths: Int,
        val newDeaths: Int,
        val recovered: Int,
        val newConfirmed: Int,
        val newRecovered: Int,
        val active: Int
) : Parcelable

