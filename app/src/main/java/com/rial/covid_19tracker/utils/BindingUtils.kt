package com.rial.covid_19tracker.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.rial.covid_19tracker.database.Country

@BindingAdapter("nameFormated")
fun TextView.setNameFormatted(item: Country) {
    text = item.name
}

@BindingAdapter("confirmedCasesFormated")
fun TextView.setConfirmedFormatted(item: Country) {
    text = item.confirmed.toString()
}

@BindingAdapter("totalDeathsFormated")
fun TextView.setTotalDeathsFormatted(item: Country) {
    text = item.deaths.toString()
}

@BindingAdapter("newDeathsFormated")
fun TextView.setNewDeathsFormatted(item: Country) {
    text = item.newDeaths.toString()
}

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError", "countrieslist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, countrieslist: Any?) {
    view.visibility = if (countrieslist != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}