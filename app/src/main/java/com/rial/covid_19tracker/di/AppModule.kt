package com.rial.covid_19tracker.di

import com.rial.covid_19tracker.data.source.remote.CovidApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(covidApiService: CovidApiService) = CharacterRemoteDataSource(covidApiService)

    @Provides
    fun provide


}