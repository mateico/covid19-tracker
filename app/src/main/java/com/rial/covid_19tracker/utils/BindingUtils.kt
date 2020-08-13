package com.rial.covid_19tracker.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rial.covid_19tracker.database.Country
import com.rial.covid_19tracker.database.DatabaseCountry
import com.rial.covid_19tracker.list.CountryAdapter
import java.text.DecimalFormat

val dec = DecimalFormat("#,###.##")

@BindingAdapter("listCountries")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Country>?) {
    val adapter = recyclerView.adapter as CountryAdapter
    adapter.submitList(data)
}

@BindingAdapter("nameFormatted")
fun TextView.setNameFormatted(item: Country) {
    text = item.name
}

@BindingAdapter("confirmedCasesFormatted")
fun TextView.setConfirmedFormatted(item: Country) {
    text = "%,d".format(item.confirmed)
    //text = java.text.NumberFormat.getIntegerInstance().format(item.confirmed)
}

@BindingAdapter("deathsFormatted")
fun TextView.setDeathsFormatted(item: Country) {
    text = item.deaths.toString()
}

@BindingAdapter("newDeathsFormatted")
fun TextView.setNewDeathsFormatted(item: Country) {
    text = item.newDeaths.toString()
}

@BindingAdapter("recoveredFormatted")
fun TextView.setRecoveredFormatted(item: Country) {
    text = item.recovered.toString()
}

@BindingAdapter("newConfirmedFormatted")
fun TextView.setNewConfirmedFormatted(item: Country) {
    text = item.newConfirmed.toString()
}

@BindingAdapter("newRecoveredFormatted")
fun TextView.setNewRecoveredFormatted(item: Country) {
    text = item.newRecovered.toString()
}

@BindingAdapter("activeFormatted")
fun TextView.setActiveFormatted(item: Country) {
    text = item.active.toString()
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