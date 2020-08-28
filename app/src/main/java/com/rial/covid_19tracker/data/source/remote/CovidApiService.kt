package com.rial.covid_19tracker.data.source.remote

import com.rial.covid_19tracker.network.NetworkCountryContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CovidApiService {
    @GET("summary")
    fun getSummaryAsync(): Deferred<NetworkCountryContainer>
}