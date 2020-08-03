package com.rial.covid_19tracker.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


// ViewModelProvider.Factory is an interface to create a ViewModel object
class DetailViewModelFactory(private val countryId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(countryId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}