package com.rial.covid_19tracker.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rial.covid_19tracker.data.source.remote.CovidApiService
import com.rial.covid_19tracker.database.CountryDao
import com.rial.covid_19tracker.database.CovidDatabase
import com.rial.covid_19tracker.database.getDatabase
import com.rial.covid_19tracker.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://corona.azure-api.net")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    fun provideCovidApiService(retrofit: Retrofit): CovidApiService = retrofit.create(CovidApiService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: CovidDatabase) = db.countryDao

    @Singleton
    @Provides
    fun provideRepository(covidApiService: CovidApiService,
                          localDataSource: CountryDao) =
        CountriesRepository(covidApiService, localDataSource)

}