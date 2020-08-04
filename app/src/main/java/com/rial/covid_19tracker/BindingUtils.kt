package com.rial.covid_19tracker

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