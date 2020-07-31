package com.rial.covid_19tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.rial.covid_19tracker.databinding.FragmentListBinding

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*To inflate the fragment's view, call the DataBindingUtil.inflate() method on the fragment's Binding object, which is FragmentTitleBinding */
        val binding = DataBindingUtil.inflate<FragmentListBinding>(inflater,
            R.layout.fragment_list,container,false)

        binding.detailButton.setOnClickListener{view : View ->
            view.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(5))
        }

        return binding.root

    }


}