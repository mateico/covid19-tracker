package com.rial.covid_19tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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

        viewModelFactory = ListViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        binding.listViewModel = viewModel

        viewModel.goToDetail.observe(viewLifecycleOwner, Observer<Boolean> { mustNavigate ->
            if (mustNavigate) toDetail()
        })

        return binding.root

    }

    private fun toDetail() {
        val id = viewModel.count.value!!
        Toast.makeText(activity, "To Detail. Id: $id", Toast.LENGTH_SHORT).show()

        val action = ListFragmentDirections.actionListFragmentToDetailFragment(id)
        action.countryId = viewModel.count.value?:0
        NavHostFragment.findNavController(this).navigate(action)

        viewModel.onGoToDetailComplete()
    }

}