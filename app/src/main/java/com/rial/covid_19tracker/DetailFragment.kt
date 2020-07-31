package com.rial.covid_19tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rial.covid_19tracker.databinding.FragmentDetailBinding
import com.rial.covid_19tracker.databinding.FragmentListBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflates fragment's view
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater,
            R.layout.fragment_detail,container,false)

        val args = DetailFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context, "Country Id: ${args.countryId}", Toast.LENGTH_LONG).show()

        return binding.root
    }

}