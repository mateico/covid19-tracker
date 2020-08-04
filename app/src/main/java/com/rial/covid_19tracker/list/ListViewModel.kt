package com.rial.covid_19tracker.list

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.rial.covid_19tracker.database.Country
import com.rial.covid_19tracker.database.CountryDao
import com.rial.covid_19tracker.CovidApi
import com.rial.covid_19tracker.formatCountries
import kotlinx.coroutines.*

class ListViewModel(val database: CountryDao, application: Application) : AndroidViewModel(application) {

    // allows you to cancel all coroutines started by this view model when the view model is no longer used and is destroyed
    private var viewModelJob = Job()

    // The scope determines what thread the coroutine will run on, and the scope also needs to know about the job.
    // Using Dispatchers.Main means that coroutines launched in the uiScope will run on the main thread (they result in an update of the UI.)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val countries = getCountriesSummary()

    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

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
        _count.value = 0
        initializeTonight()
        getCountriesSummary()
    }

    private fun initializeTonight() {
        uiScope.launch {
            oneCountry.value = getOneCountryFromDatabase()
        }
    }

    private suspend fun getOneCountryFromDatabase(): Country? {
        // eturn the result from a coroutine that runs in the Dispatchers.IO context.
        // Use the I/O dispatcher, because getting data from the database is an I/O operation and has nothing to do with the UI.
        return withContext(Dispatchers.IO) {
            var countr = database.getCountryByCode("US")
            countr
        }
    }

    private suspend fun insert(country: Country) {
        withContext(Dispatchers.IO) {
            database.insert(country)
        }
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
    private fun getCountriesSummary(): MutableLiveData<List<Country>> {
        val result = MutableLiveData<List<Country>>()
        uiScope.launch {
            // Get the Deferred object for our Retrofit request
            // To use the Deferred object that Retrofit returns for the network task, you have to be inside a coroutine
            var getCountriesDeferred = CovidApi.retrofitService.getSummary()
            try {
                // Await the completion of our Retrofit request
                // Calling await() on the Deferred object returns the result from the network call when the value is ready.
                var listResult = getCountriesDeferred.await()
                // update the response message for the successful response
                _response.value = "Success: ${listResult.countries.size} summary retrieved"
                result.value = listResult.countries
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                if(countries.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
        return result
    }



    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}