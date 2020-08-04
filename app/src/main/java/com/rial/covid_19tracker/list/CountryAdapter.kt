package com.rial.covid_19tracker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rial.covid_19tracker.database.Country
import com.rial.covid_19tracker.databinding.ListItemCountryBinding

//import com.rial.covid_19tracker.databinding.ListItemCountryBinding

class CountryAdapter( private val onClickListener: OnClickListener ) :
    ListAdapter<Country, CountryAdapter.ViewHolder>(CountryDiffCallback()) {





    // The onBindViewHolder()function is called by RecyclerView to display the data for one list item at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    // is called when the RecyclerView needs a view holder to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Country
        ) {
            binding.country = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListItemCountryBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

    class OnClickListener(val clickListener: (country:Country) -> Unit) {
        fun onClick(country:Country) = clickListener(country)
    }

    class CountryDiffCallback : DiffUtil.ItemCallback<Country>() {

        // if have the same id
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code ==  newItem.code
        }

        // if contain the same data (item updated)
        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

    }



}

