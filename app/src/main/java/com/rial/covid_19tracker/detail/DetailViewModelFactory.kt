package com.rial.covid_19tracker.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rial.covid_19tracker.database.Country


// ViewModelProvider.Factory is an interface to create a ViewModel object
class DetailViewModelFactory(private val country: Country) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(country) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}