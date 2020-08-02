package com.rial.covid_19tracker

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.*

class ListViewModel(val database: CountryDao, application: Application) : AndroidViewModel(application) {

    // allows you to cancel all coroutines started by this view model when the view model is no longer used and is destroyed
    private var viewModelJob = Job()

    // The scope determines what thread the coroutine will run on, and the scope also needs to know about the job.
    // Using Dispatchers.Main means that coroutines launched in the uiScope will run on the main thread (they result in an update of the UI.)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val countries = database.getAllCountries()

    val countriesString = Transformations.map(countries) { countries ->
        formatCountries(countries)
    }

    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private val _navigateToDetail = MutableLiveData<Boolean>()
    val navigateToDetail: LiveData<Boolean>
        get() = _navigateToDetail

    private var oneCountry = MutableLiveData<Country?>()

    init {
        _count.value = 0
        initializeTonight()
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

    fun onInsertCountry() {
        uiScope.launch {
            var country = Country(code = _count.value.toString())
            insert(country)
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

    fun addCounter(){
        _count.value = _count.value?.plus(1)
        onInsertCountry()
    }

    fun onNavegatingToDetail() {
        _navigateToDetail.value = true
    }

    fun doneNavegatingToDetail() {
        _navigateToDetail.value = false
    }
}