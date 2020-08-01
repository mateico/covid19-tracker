package com.rial.covid_19tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private val _goToDetail = MutableLiveData<Boolean>()
    val goToDetail: LiveData<Boolean>
        get() = _goToDetail

    init {
        _count.value = 0
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun addCounter(){
        _count.value = _count.value?.plus(1)
    }

    fun onGoToDetail() {
        _goToDetail.value = true
    }

    fun onGoToDetailComplete() {
        _goToDetail.value = false
    }
}