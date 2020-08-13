package com.rial.covid_19tracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries_summary ORDER BY confirmed_cases DESC")
    fun getAllCountries(): LiveData<List<DatabaseCountry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( countries: List<DatabaseCountry>)
}