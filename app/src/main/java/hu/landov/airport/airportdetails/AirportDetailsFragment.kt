package hu.landov.airport.airportdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import hu.landov.airport.common.data.AirportEntity
import hu.landov.airport.common.domain.Airport
import hu.landov.airport.databinding.FragmentAirportDetailsBinding
import kotlin.concurrent.timer

/**
 * A simple [Fragment] subclass.
 * Use the [AirportDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AirportDetailsFragment : Fragment() {
    private lateinit var viewModel: AirportDetailsViewModel
    private lateinit var binding : FragmentAirportDetailsBinding
    private val args : AirportDetailsFragmentArgs by navArgs()
    private lateinit var airport : Airport

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        airport = args.airport
        viewModel = ViewModelProvider(this,AirportDetailsViewModelFactory(airport)).get(AirportDetailsViewModel::class.java)
        binding = FragmentAirportDetailsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        Log.d("DETAILS", airport.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.lifecycleOwner = viewLifecycleOwner
        // Inflate the layout for this fragment
        return binding.root
    }

}