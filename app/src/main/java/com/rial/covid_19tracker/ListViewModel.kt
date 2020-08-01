package com.rial.covid_19tracker

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ListViewModel(val database: CountryDao, application: CovidApplication) : AndroidViewModel(application) {

    // allows you to cancel all coroutines started by this view model when the view model is no longer used and is destroyed
    private var viewModelJob = Job()

    // The scope determines what thread the coroutine will run on, and the scope also needs to know about the job.
    // Using Dispatchers.Main means that coroutines launched in the uiScope will run on the main thread (they result in an update of the UI.)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)




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
        viewModelJob.cancel()
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