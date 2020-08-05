package com.rial.covid_19tracker.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.rial.covid_19tracker.database.Country

fun formatCountries(country: List<Country>): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("hardocde title")
        country.forEach {
            append("<br>")
            append("start_time")
            append("\t${it.code}<br>")
                append("\t${it.deaths}<br>")


        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)