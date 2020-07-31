package com.rial.covid_19tracker

import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    var count: Int = 0

    init {

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun addCounter(){
        count += 1
    }
}