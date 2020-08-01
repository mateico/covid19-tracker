package com.rial.covid_19tracker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CountryDao {

    @Insert
    fun insert(country: Country)

    @Update
    fun update(country: Country)

    @Query("SELECT * from countries_summary WHERE code = :key")
    fun getCountryByCode(key: String): Country?

    @Query("DELETE FROM countries_summary")
    fun clear()

    @Query("SELECT * FROM countries_summary ORDER BY confirmed_cases DESC")
    fun getAllCountries(): LiveData<List<Country>>
}