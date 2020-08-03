package com.rial.covid_19tracker.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rial.covid_19tracker.R
import com.rial.covid_19tracker.database.Country

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    var data = listOf<Country>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // The RecyclerView needs to know how many items the adapter has for it to display
    override fun getItemCount() = data.size

    // The onBindViewHolder()function is called by RecyclerView to display the data for one list item at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.code.text = item.code
    }

    // is called when the RecyclerView needs a view holder to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        // create the view by asking the layoutinflater to inflate it.
        val view = layoutInflater.inflate(R.layout.list_item_country, parent, false)

        // return a CountryAdapter.ViewHolder made with view
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val code: TextView = itemView.findViewById(R.id.codeTextView)
        val name: TextView = itemView.findViewById(R.id.nameTextView)
    }
}