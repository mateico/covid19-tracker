package com.rial.covid_19tracker.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rial.covid_19tracker.R
import com.rial.covid_19tracker.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflates fragment's view
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail,container,false)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModelFactory =
            DetailViewModelFactory(
                DetailFragmentArgs.fromBundle(arguments!!).selectedCountry
            )
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.detailViewModel = viewModel

        return binding.root
    }

}