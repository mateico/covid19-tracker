package com.rial.covid_19tracker.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rial.covid_19tracker.data.source.remote.CovidApiService
import com.rial.covid_19tracker.database.Country
import com.rial.covid_19tracker.database.CountryDao
import com.rial.covid_19tracker.database.asDomainModel
import com.rial.covid_19tracker.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CountriesRepository@Inject constructor(
    private val rcovidApiService: CovidApiService,
    private val localDataSource: CountryDao) {

    val countries : LiveData<List<Country>> = Transformations.map(localDataSource.getAllCountries()) {
        it.asDomainModel()
    }

    /**
     * Refresh the countries stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */
    suspend fun refreshCountries(){
        withContext(Dispatchers.IO) {
            val countries = rcovidApiService.getSummaryAsync().await()
            localDataSource.insertAll(countries.asDatabaseModel())
            Timber.i("$countries")
        }
    }
}