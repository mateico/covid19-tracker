package com.rial.covid_19tracker.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "https://corona.azure-api.net/"
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * A public interface that exposes the [getProperties] method
 */
interface CovidApiService {
    /**
     * Returns a Coroutine [Deferred] [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     */
    //@GET("summary")
    @GET("devbytes")
    fun getSummary(): Deferred<NetworkCountryContainer>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CovidApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retrofitService = retrofit.create(CovidApiService::class.java)
}