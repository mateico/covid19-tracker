package com.rial.covid_19tracker.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.rial.covid_19tracker.*
import com.rial.covid_19tracker.database.CovidDatabase
import com.rial.covid_19tracker.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var viewModelFactory: ListViewModelFactory



    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*To inflate the fragment's view, call the DataBindingUtil.inflate() method on the fragment's Binding object, which is FragmentTitleBinding */
        binding = DataBindingUtil.inflate<FragmentListBinding>(inflater,
            R.layout.fragment_list,container,false)

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        // The requireNotNull Kotlin function throws an IllegalArgumentException if the value is null.
        val application = requireNotNull(this.activity).application

        // I need a reference to my data source via a reference to the DAO.
        val dataSource= CovidDatabase.getInstance(
            application
        ).countryDao

        viewModelFactory = ListViewModelFactory(
            dataSource,
            application
        )
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        val adapter = CountryAdapter()
        binding.countryRecyclerView.adapter = adapter

        viewModel.countries.observe(viewLifecycleOwner, Observer {
            // asign the value to the adapter's data
            adapter.data = it
        })

        binding.listViewModel = viewModel

        viewModel.navigateToDetail.observe(this, Observer { if(it == true) toDetail()
        })

        return binding.root

    }

    private fun toDetail() {
        val id = viewModel.count.value!!
        //Toast.makeText(activity, "To Detail. Id: $id", Toast.LENGTH_SHORT).show()

        Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            "To Detail. Id: $id",
            Snackbar.LENGTH_SHORT // How long to display the message.
        ).show()

        val action =
            ListFragmentDirections.actionListFragmentToDetailFragment(
                id
            )
        action.countryId = viewModel.count.value?:0
        NavHostFragment.findNavController(this).navigate(action)

        viewModel.doneNavegatingToDetail()
    }

}