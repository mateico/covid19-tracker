package com.rial.covid_19tracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel(countryId: Int) : ViewModel() {

    var countryId = MutableLiveData<Int>()

    init{
        this.countryId.value = countryId
    }
}