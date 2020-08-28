package com.rial.covid_19tracker.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rial.covid_19tracker.*
import com.rial.covid_19tracker.database.CovidDatabase
import com.rial.covid_19tracker.database.getDatabase
import com.rial.covid_19tracker.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    //private lateinit var viewModel: ListViewModel
    private val viewModel: ListViewModel by viewModels()
    //private lateinit var viewModelFactory: ListViewModelFactory
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*To inflate the fragment's view, call the DataBindingUtil.inflate() method on the fragment's Binding object, which is FragmentTitleBinding */
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_list,container,false)

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        // The requireNotNull Kotlin function throws an IllegalArgumentException if the value is null.
        val application = requireNotNull(this.activity).application

        // I need a reference to my data source via a reference to the DAO.
        val dataSource= getDatabase(application).countryDao

       /* viewModelFactory = ListViewModelFactory(
            dataSource,
            application
        )
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)*/

        binding.countryRecyclerView.adapter = CountryAdapter(CountryAdapter.OnClickListener {
            viewModel.onNavegatingToDetail(it)
        })

        binding.listViewModel = viewModel

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            //if(it == true) toDetail()
            if(null != it) {
                this.findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailFragment(it))
                viewModel.doneNavegatingToDetail()
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root

    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}