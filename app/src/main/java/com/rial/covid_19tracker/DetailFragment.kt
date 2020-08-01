package com.rial.covid_19tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rial.covid_19tracker.databinding.FragmentDetailBinding
import com.rial.covid_19tracker.databinding.FragmentListBinding

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflates fragment's view
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater,
            R.layout.fragment_detail,container,false)

        viewModelFactory = DetailViewModelFactory(DetailFragmentArgs.fromBundle(arguments!!).countryId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }

}