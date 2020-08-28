package com.rial.covid_19tracker.list

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.rial.covid_19tracker.database.Country
import com.rial.covid_19tracker.database.CountryDao
import com.rial.covid_19tracker.database.getDatabase
import com.rial.covid_19tracker.repository.CountriesRepository
import kotlinx.coroutines.*

class ListViewModel @ViewModelInject constructor(private val repository: CountriesRepository) : ViewModel() {

    //private val repository = CountriesRepository(getDatabase(application))

    // allows you to cancel all coroutines started by this view model when the view model is no longer used and is destroyed
    private var viewModelJob = Job()

    // The scope determines what thread the coroutine will run on, and the scope also needs to know about the job.
    // Using Dispatchers.Main means that coroutines launched in the uiScope will run on the main thread (they result in an update of the UI.)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val countries = repository.countries

    private val _navigateToDetail = MutableLiveData<Country>()
    val navigateToDetail: LiveData<Country>
        get() = _navigateToDetail

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var oneCountry = MutableLiveData<Country?>()

    init {
        refreshDataFromRepository()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onNavegatingToDetail(country: Country) {
        _navigateToDetail.value = country
    }

    fun doneNavegatingToDetail() {
        _navigateToDetail.value = null
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun refreshDataFromRepository() {
        uiScope.launch {
            try {
                repository.refreshCountries()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                if(countries.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}