package com.rial.covid_19tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel(private var countryId: Int) : ViewModel() {

    //var countryId = MutableLiveData<Int>()
    private val _navigateToList = MutableLiveData<Boolean?>()

    val navigateToList: LiveData<Boolean?>
        get() = _navigateToList

    fun doneNavigating() {
        _navigateToList.value = null
    }

    init{
        //this.countryId.value = countryId
    }
}