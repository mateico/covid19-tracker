package com.rial.covid_19tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.rial.covid_19tracker.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*To inflate the fragment's view, call the DataBindingUtil.inflate() method on the fragment's Binding object, which is FragmentTitleBinding */
        binding = DataBindingUtil.inflate<FragmentListBinding>(inflater,
            R.layout.fragment_list,container,false)

        binding.detailButton.setOnClickListener{view : View ->
            view.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(viewModel.count))
        }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        binding.addButton.setOnClickListener{view : View ->
            viewModel.addCounter()
            binding.countTextView.text = viewModel.count.toString()
        }


        return binding.root

    }

    override fun onResume() {
        super.onResume()
        binding.countTextView.text = viewModel.count.toString()
    }
}