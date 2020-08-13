package com.rial.covid_19tracker.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rial.covid_19tracker.database.Country

class DetailViewModel(country: Country) : ViewModel() {

    private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country>
        get() = _selectedCountry

    private val _navigateToList = MutableLiveData<Boolean?>()
    val navigateToList: LiveData<Boolean?>
        get() = _navigateToList

    fun doneNavigating() {
        _navigateToList.value = null
    }

    init{
        _selectedCountry.value = country
        //this.countryId.value = countryId
    }
}